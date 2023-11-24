package co.com.servientrega.domain.usecases;

import co.com.servientrega.domain.delivery.common.identity.EmployeeId;
import co.com.servientrega.domain.delivery.common.values.*;
import co.com.servientrega.domain.delivery.offices.events.ManagerAdded;
import co.com.servientrega.domain.delivery.offices.events.ShipmentAddedToStore;
import co.com.servientrega.domain.delivery.offices.events.StoreAdded;
import co.com.servientrega.domain.delivery.offices.identity.OfficeId;
import co.com.servientrega.domain.delivery.offices.identity.StoreId;
import co.com.servientrega.domain.delivery.shipments.commands.CreateShipment;
import co.com.servientrega.domain.delivery.shipments.events.DeliveryOrderAdded;
import co.com.servientrega.domain.delivery.shipments.events.InvoiceAdded;
import co.com.servientrega.domain.delivery.shipments.events.PackageAdded;
import co.com.servientrega.domain.delivery.shipments.events.ShipmentCreated;
import co.com.servientrega.domain.delivery.shipments.identity.DeliveryOrderId;
import co.com.servientrega.domain.delivery.shipments.identity.InvoiceId;
import co.com.servientrega.domain.delivery.shipments.identity.PackageId;
import co.com.servientrega.domain.delivery.shipments.identity.ShipmentId;
import co.com.servientrega.domain.delivery.shipments.values.*;
import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class CreateShipmentUseCaseTest {
    @Mock
    private DomainEventRepository eventRepository;
    private final ShipmentId shipmentId = new ShipmentId(generateRandomUUID());
    private final OfficeId officeId = new OfficeId(generateRandomUUID());
    private final StoreId storeId = new StoreId(generateRandomUUID());

    @BeforeEach
    void setUp() {
        BDDMockito
                .when(this.eventRepository.getEventsBy(this.officeId.value()))
                .thenReturn(
                        List.of(
                                new ManagerAdded(
                                        officeId,
                                        new EmployeeId(),
                                        new DNI("434927023948"),
                                        new FullName("Juanito el director", "apellido"),
                                        new Email("juanito@gmail.com"),
                                        new PhoneNumber("320948409328"),
                                        new Salary(new BigDecimal(2000000))
                                ),
                                new StoreAdded(this.officeId, this.storeId, new Size(40.0, 45.0, 23.0))
                        )
                );
    }

    @Test
    @DisplayName("Test for CreateShipmentUseCase#executeUseCase")
    void executeUseCase() {
        // Arrange
        CreateShipment command = new CreateShipment(officeId, Date.now());
        CreateShipmentUseCase useCase = new CreateShipmentUseCase();
        useCase.addRepository(this.eventRepository);

        // Act
        List<DomainEvent> events = UseCaseHandler
                .getInstance()
                .setIdentifyExecutor(shipmentId.value())
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow(() -> new RuntimeException("Something went wrong while creating the shipment."))
                .getDomainEvents();
        ShipmentCreated shipmentCreatedEvent = (ShipmentCreated) events.get(0);
        ShipmentAddedToStore shipmentAddedToStoreEvent = (ShipmentAddedToStore) events.get(1);

        // Assert
        Assertions.assertThat(shipmentCreatedEvent.shipmentId()).isNotNull();
        Assertions.assertThat(shipmentCreatedEvent.sentAt().value()).isBefore(LocalDateTime.now());
        Assertions.assertThat(shipmentCreatedEvent.deliveredAt()).isNull();

        Assertions.assertThat(shipmentAddedToStoreEvent.officeId().value()).isNotNull().isEqualTo(this.officeId.value());
        Assertions.assertThat(shipmentAddedToStoreEvent.storeId().value()).isNotNull();
        Assertions.assertThat(shipmentAddedToStoreEvent.shipmentId().value()).isNotNull();
    }

    private static String generateRandomUUID() {
        return UUID.randomUUID().toString();
    }
}