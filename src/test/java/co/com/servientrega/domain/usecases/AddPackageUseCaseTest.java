package co.com.servientrega.domain.usecases;

import co.com.servientrega.domain.delivery.common.identity.EmployeeId;
import co.com.servientrega.domain.delivery.common.values.Date;
import co.com.servientrega.domain.delivery.common.values.Size;
import co.com.servientrega.domain.delivery.common.values.Weight;
import co.com.servientrega.domain.delivery.shipments.commands.AddPackage;
import co.com.servientrega.domain.delivery.shipments.events.DeliveryOrderAdded;
import co.com.servientrega.domain.delivery.shipments.events.InvoiceAdded;
import co.com.servientrega.domain.delivery.shipments.events.PackageAdded;
import co.com.servientrega.domain.delivery.shipments.events.ShipmentCreated;
import co.com.servientrega.domain.delivery.shipments.identity.DeliveryOrderId;
import co.com.servientrega.domain.delivery.shipments.identity.InvoiceId;
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
class AddPackageUseCaseTest {
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
                        new InvoiceAdded(
                                shipmentId,
                                new InvoiceId(generateRandomUUID()),
                                new Money(89.0),
                                new EmployeeId(generateRandomUUID())
                        )
                ));
    }

    @Test
    @DisplayName("Test for AddPackageUseCase#executeUseCase")
    void executeUseCase() {
        // Arrange
        EmployeeId issuerId = new EmployeeId(generateRandomUUID());
        PackageName packageName = new PackageName("Tea cup");
        PackageDescription packageDescription = new PackageDescription("A cup to drink tea");
        Weight packageWeight = new Weight(0.2);
        Size packageSize = new Size(10.0, 10.0, 10.0);
        AddPackage command = new AddPackage(new ShipmentId(this.randomUUID), packageName, packageDescription, packageWeight, packageSize);
        AddPackageUseCase useCase = new AddPackageUseCase();
        useCase.addRepository(this.eventRepository);

        // Act
        PackageAdded event = (PackageAdded) UseCaseHandler
                .getInstance()
                .setIdentifyExecutor(randomUUID)
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow(() -> new RuntimeException("Something went wrong while adding the package."))
                .getDomainEvents()
                .get(0);

        // Assert
        assertThat(event.packageId()).isNotNull();
        assertThat(event.packageName().value()).isEqualTo(packageName.value());
        assertThat(event.packageDescription().value()).isEqualTo(packageDescription.value());
        assertThat(event.packageWeight().value()).isEqualTo(packageWeight.value());
        assertThat(event.packageSize()).isEqualTo(packageSize);
        BDDMockito.verify(eventRepository).getEventsBy(this.randomUUID);
    }

    private static String generateRandomUUID() {
        return UUID.randomUUID().toString();
    }
}