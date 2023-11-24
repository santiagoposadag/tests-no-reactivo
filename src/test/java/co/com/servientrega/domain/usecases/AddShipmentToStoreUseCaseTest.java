package co.com.servientrega.domain.usecases;

import co.com.servientrega.domain.delivery.common.identity.EmployeeId;
import co.com.servientrega.domain.delivery.common.values.*;
import co.com.servientrega.domain.delivery.offices.commands.AddShipmentToStore;
import co.com.servientrega.domain.delivery.offices.events.ManagerAdded;
import co.com.servientrega.domain.delivery.offices.events.ShipmentAddedToStore;
import co.com.servientrega.domain.delivery.offices.events.StoreAdded;
import co.com.servientrega.domain.delivery.offices.identity.OfficeId;
import co.com.servientrega.domain.delivery.offices.identity.StoreId;
import co.com.servientrega.domain.delivery.shipments.identity.ShipmentId;
import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
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
class AddShipmentToStoreUseCaseTest {
    @Mock
    private DomainEventRepository eventRepository;
    private final OfficeId officeId = new OfficeId(generateRandomUUID());
    private final StoreId storeId = new StoreId(generateRandomUUID());
    private final ShipmentId shipmentId = new ShipmentId(generateRandomUUID());

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
                                new StoreAdded(this.officeId, this.storeId, new Size(40.0, 45.0, 23.0))
                        )
                );
    }

    @Test
    @DisplayName("Test for AddShipmentToStoreUseCase#executeUseCase")
    void executeUseCase() {
        // Arrange
        AddShipmentToStore command = new AddShipmentToStore(this.officeId, this.shipmentId);
        AddShipmentToStoreUseCase useCase = new AddShipmentToStoreUseCase();
        useCase.addRepository(this.eventRepository);

        // Act
        ShipmentAddedToStore event = (ShipmentAddedToStore) UseCaseHandler
                .getInstance()
                .setIdentifyExecutor(this.officeId.value())
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow(() -> new RuntimeException("Something went wrong while adding the shipment to the store."))
                .getDomainEvents()
                .get(0);

        // Assert
        Assertions.assertThat(event.shipmentId()).isNotNull().isEqualTo(this.shipmentId);
        Assertions.assertThat(event.officeId()).isNotNull().isEqualTo(this.officeId);
    }

    private static String generateRandomUUID() {
        return UUID.randomUUID().toString();
    }
}