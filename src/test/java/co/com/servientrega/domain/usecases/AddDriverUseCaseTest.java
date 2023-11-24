package co.com.servientrega.domain.usecases;

import co.com.servientrega.domain.delivery.common.values.*;
import co.com.servientrega.domain.delivery.transports.commands.AddDriver;
import co.com.servientrega.domain.delivery.transports.events.DeliveryAssistantAdded;
import co.com.servientrega.domain.delivery.transports.events.DriverAdded;
import co.com.servientrega.domain.delivery.transports.identity.TransportId;
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
class AddDriverUseCaseTest {
    private final String randomUUID = generateRandomUUID();

    @Mock
    private DomainEventRepository eventRepository;

    @BeforeEach
    void setUp() {
        TransportId transportId = new TransportId(randomUUID);
        BDDMockito
                .when(this.eventRepository.getEventsBy(transportId.value()))
                .thenReturn(List.of(
                        new DeliveryAssistantAdded(
                                transportId,
                                new DNI("340840955"),
                                new FullName("driver assistant", "last name"),
                                new Email("deliveryassistant@gmail.com"),
                                new PhoneNumber("42349098209"),
                                new Salary(new BigDecimal(4000000))
                        )
                ));
    }

    @Test
    @DisplayName("Test for AddDriverUseCase#executeUseCase")
    void executeUseCase() {
        // Arrange
        DNI dni = new DNI("3405840395");
        FullName fullName = new FullName("Driver", "last name");
        Email email = new Email("driver@gmail.com");
        PhoneNumber phoneNumber = new PhoneNumber("43208502495");
        Salary salary = new Salary(new BigDecimal(5000000));
        AddDriver command = new AddDriver(new TransportId(this.randomUUID), dni, fullName, email, phoneNumber, salary);
        AddDriverUseCase useCase = new AddDriverUseCase();
        useCase.addRepository(this.eventRepository);

        // Act
        DriverAdded event = (DriverAdded) UseCaseHandler
                .getInstance()
                .setIdentifyExecutor(randomUUID)
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow(() -> new RuntimeException("Something went wrong while adding the driver."))
                .getDomainEvents()
                .get(0);

        // Assert
        Assertions.assertThat(event.transportId()).isNotNull();
        Assertions.assertThat(event.dni().value()).isEqualTo(dni.value());
        Assertions.assertThat(event.fullName()).isEqualTo(fullName);
        Assertions.assertThat(event.email().value()).isEqualTo(email.value());
        Assertions.assertThat(event.phoneNumber().value()).isEqualTo(phoneNumber.value());
        Assertions.assertThat(event.salary().value()).isEqualTo(salary.value());
        BDDMockito.verify(eventRepository).getEventsBy(this.randomUUID);
    }

    private static String generateRandomUUID() {
        return UUID.randomUUID().toString();
    }
}