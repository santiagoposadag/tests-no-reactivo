package co.com.servientrega.domain.usecases;

import co.com.servientrega.domain.delivery.common.identity.EmployeeId;
import co.com.servientrega.domain.delivery.common.values.*;
import co.com.servientrega.domain.delivery.offices.commands.StartShipmentsDelivery;
import co.com.servientrega.domain.delivery.offices.events.ManagerAdded;
import co.com.servientrega.domain.delivery.offices.events.ShipmentAddedToStore;
import co.com.servientrega.domain.delivery.offices.events.ShipmentsDeliveryStarted;
import co.com.servientrega.domain.delivery.offices.events.StoreAdded;
import co.com.servientrega.domain.delivery.offices.identity.OfficeId;
import co.com.servientrega.domain.delivery.offices.identity.StoreId;
import co.com.servientrega.domain.delivery.shipments.identity.ShipmentId;
import co.com.servientrega.domain.delivery.transports.events.DeliveryAssistantAdded;
import co.com.servientrega.domain.delivery.transports.events.DriverAdded;
import co.com.servientrega.domain.delivery.transports.events.PickedUpShipments;
import co.com.servientrega.domain.delivery.transports.identity.TransportId;
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
import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class StartShipmentsDeliveryTest {
    @Mock
    private DomainEventRepository eventRepository;
    private final OfficeId officeId = new OfficeId(generateRandomUUID());
    private final StoreId storeId = new StoreId(generateRandomUUID());
    private final ShipmentId shipmentId = new ShipmentId(generateRandomUUID());
    private final TransportId transportId = new TransportId(generateRandomUUID());

    @BeforeEach
    void setUp() {
        BDDMockito
                .when(this.eventRepository.getEventsBy(this.officeId.value()))
                .thenReturn(
                        List.of(
                                new ManagerAdded(
                                        officeId,
                                        new EmployeeId(generateRandomUUID()),
                                        new DNI("434927023948"),
                                        new FullName("Juanito el director", "apellido"),
                                        new Email("juanito@gmail.com"),
                                        new PhoneNumber("320948409328"),
                                        new Salary(new BigDecimal(2000000))
                                ),
                                new StoreAdded(this.officeId, this.storeId, new Size(40.0, 45.0, 23.0)),
                                new ShipmentAddedToStore(this.officeId, this.storeId, this.shipmentId)
                        )
                );

        BDDMockito
                .when(this.eventRepository.getEventsBy(this.transportId.value()))
                .thenReturn(
                        List.of(
                                new DeliveryAssistantAdded(
                                        transportId,
                                        new DNI("340840955"),
                                        new FullName("driver assistant", "last name"),
                                        new Email("deliveryassistant@gmail.com"),
                                        new PhoneNumber("42349098209"),
                                        new Salary(new BigDecimal(4000000))
                                ),
                                new DriverAdded(
                                        transportId,
                                        new DNI("54654654234"),
                                        new FullName("driver", "last name"),
                                        new Email("driver@gmail.com"),
                                        new PhoneNumber("2342343241"),
                                        new Salary(new BigDecimal(5000000))
                                )
                        )
                );
    }

    @Test
    @DisplayName("Test for StartShipmentsDeliveryUseCase#executeUseCase")
    void executeUseCase() {
        // Arrange
        StartShipmentsDelivery command = new StartShipmentsDelivery(this.transportId, this.officeId, this.storeId);
        StartShipmentsDeliveryUseCase useCase = new StartShipmentsDeliveryUseCase();
        useCase.addRepository(this.eventRepository);

        // Act
        List<DomainEvent> events = UseCaseHandler
                .getInstance()
                .setIdentifyExecutor(this.officeId.value())
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow(() -> new RuntimeException("Something went wrong while creating starting the shipments delivery."))
                .getDomainEvents();

        ShipmentsDeliveryStarted deliveryStartedEvent = (ShipmentsDeliveryStarted) events.get(0);
        PickedUpShipments pickedUpShipmentsEvent = (PickedUpShipments) events.get(1);

        // Assert
        Assertions.assertThat(deliveryStartedEvent.transportId()).isNotNull().isEqualTo(this.transportId);
        Assertions.assertThat(deliveryStartedEvent.officeId()).isNotNull().isEqualTo(this.officeId);
        Assertions.assertThat(deliveryStartedEvent.shipmentsIds()).isNotNull().contains(this.shipmentId);

        Assertions.assertThat(pickedUpShipmentsEvent.transportId()).isNotNull().isEqualTo(this.transportId);
        Assertions.assertThat(pickedUpShipmentsEvent.officeId()).isNotNull().isEqualTo(this.officeId);
        Assertions.assertThat(pickedUpShipmentsEvent.shipmentsIds()).contains(this.shipmentId);
    }

    private static String generateRandomUUID() {
        return UUID.randomUUID().toString();
    }
}