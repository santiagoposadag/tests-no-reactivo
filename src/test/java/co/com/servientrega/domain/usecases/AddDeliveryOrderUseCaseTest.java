package co.com.servientrega.domain.usecases;

import co.com.servientrega.domain.delivery.common.identity.EmployeeId;
import co.com.servientrega.domain.delivery.common.values.Date;
import co.com.servientrega.domain.delivery.common.values.Size;
import co.com.servientrega.domain.delivery.common.values.Weight;
import co.com.servientrega.domain.delivery.shipments.commands.AddDeliveryOrder;
import co.com.servientrega.domain.delivery.shipments.events.DeliveryOrderAdded;
import co.com.servientrega.domain.delivery.shipments.events.InvoiceAdded;
import co.com.servientrega.domain.delivery.shipments.events.PackageAdded;
import co.com.servientrega.domain.delivery.shipments.events.ShipmentCreated;
import co.com.servientrega.domain.delivery.shipments.identity.InvoiceId;
import co.com.servientrega.domain.delivery.shipments.identity.PackageId;
import co.com.servientrega.domain.delivery.shipments.identity.ShipmentId;
import co.com.servientrega.domain.delivery.shipments.values.*;
import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class AddDeliveryOrderUseCaseTest {
    private final String randomUUID = generateRandomUUID();

    @Mock
    private DomainEventRepository eventRepository;

    @BeforeEach
    void setUp() {
        ShipmentId shipmentId = new ShipmentId(randomUUID);
        BDDMockito
                .when(eventRepository.getEventsBy(randomUUID))
                .thenReturn(List.of(
                        new ShipmentCreated(shipmentId, Date.now(), null),
                        new InvoiceAdded(shipmentId, new InvoiceId(generateRandomUUID()), new Money(89.0), new EmployeeId(generateRandomUUID())),
                        new PackageAdded(shipmentId, new PackageId(generateRandomUUID()), new PackageName("Soccer Ball"), new PackageDescription("A soccer ball to play soccer."), new Weight(0.5), new Size(20.0, 20.0, 20.0))
                ));
    }

    @Test
    @DisplayName("Test AddDeliveryOrderUseCase#executeUseCase")
    void executeUseCase() {
        // Arrange
        Sender sender = new Sender("Pepito Perez", "3204545786", "93247983274", "Street 34 # 3 - 46");
        Addressee addressee = new Addressee("Juanito Gonzalez", "3098540568", "345345435234", "Street 56 # 1 - 34");
        AddDeliveryOrder command = new AddDeliveryOrder(new ShipmentId(randomUUID), sender, addressee);
        AddDeliveryOrderUseCase useCase = new AddDeliveryOrderUseCase();
        useCase.addRepository(this.eventRepository);

        // Act
        DeliveryOrderAdded event = (DeliveryOrderAdded) UseCaseHandler
                .getInstance()
                .setIdentifyExecutor(randomUUID)
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow(() -> new RuntimeException("Something went wrong while adding the delivery order."))
                .getDomainEvents()
                .get(0);

        // Assert
        assertThat(event.addressee().value().fullName()).isEqualTo(addressee.value().fullName());
        assertThat(event.sender().value().fullName()).isEqualTo(sender.value().fullName());
        BDDMockito.verify(eventRepository).getEventsBy(this.randomUUID);
    }

    private static String generateRandomUUID() {
        return UUID.randomUUID().toString();
    }
}