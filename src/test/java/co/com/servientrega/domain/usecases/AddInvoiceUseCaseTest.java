package co.com.servientrega.domain.usecases;

import co.com.servientrega.domain.delivery.common.identity.EmployeeId;
import co.com.servientrega.domain.delivery.common.values.Date;
import co.com.servientrega.domain.delivery.common.values.Size;
import co.com.servientrega.domain.delivery.common.values.Weight;
import co.com.servientrega.domain.delivery.shipments.commands.AddInvoice;
import co.com.servientrega.domain.delivery.shipments.events.DeliveryOrderAdded;
import co.com.servientrega.domain.delivery.shipments.events.InvoiceAdded;
import co.com.servientrega.domain.delivery.shipments.events.PackageAdded;
import co.com.servientrega.domain.delivery.shipments.events.ShipmentCreated;
import co.com.servientrega.domain.delivery.shipments.identity.DeliveryOrderId;
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

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AddInvoiceUseCaseTest {
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
                        new DeliveryOrderAdded(
                                shipmentId,
                                new DeliveryOrderId(generateRandomUUID()),
                                new Sender("Carlitos Suarez", "328403284", "32487239", "Street idk # 354 - somewhere"),
                                new Addressee("Juanito Perez", "32487239", "345345345", "Street somewhere # mock")
                        ),
                        new PackageAdded(
                                shipmentId,
                                new PackageId(generateRandomUUID()),
                                new PackageName("Soccer Ball"),
                                new PackageDescription("A soccer ball to play soccer."),
                                new Weight(0.5),
                                new Size(20.0, 20.0, 20.0)
                        )
                ));
    }

    @Test
    @DisplayName("Test for AddInvoiceUseCase#executeUseCase")
    void executeUseCase() {
        // Arrange
        EmployeeId issuerId = new EmployeeId(generateRandomUUID());
        Money cost = new Money(567.0);
        AddInvoice command = new AddInvoice(new ShipmentId(randomUUID), issuerId, cost);
        AddInvoiceUseCase useCase = new AddInvoiceUseCase();
        useCase.addRepository(this.eventRepository);

        // Act
        InvoiceAdded event = (InvoiceAdded) UseCaseHandler
                .getInstance()
                .setIdentifyExecutor(randomUUID)
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow(() -> new RuntimeException("Something went wrong while adding the invoice."))
                .getDomainEvents()
                .get(0);

        // Assert
        assertThat(event.price().value()).isEqualTo(cost.value());
        assertThat(event.issuerId().value()).isEqualTo(issuerId.value());
        assertThat(event.shipmentId().value()).isEqualTo(this.randomUUID);
        BDDMockito.verify(eventRepository).getEventsBy(this.randomUUID);
    }

    private static String generateRandomUUID() {
        return UUID.randomUUID().toString();
    }
}