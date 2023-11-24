package co.com.servientrega.domain.usecases;

import co.com.servientrega.domain.delivery.common.values.*;
import co.com.servientrega.domain.delivery.transports.commands.AddDeliveryAssistant;
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
class AddDeliveryAssistantUseCaseTest {
    private final String randomUUID = generateRandomUUID();

    @Mock
    private DomainEventRepository eventRepository;

    @BeforeEach
    void setUp() {
        TransportId transportId = new TransportId(randomUUID);
        BDDMockito
                .when(this.eventRepository.getEventsBy(transportId.value()))
                .thenReturn(List.of(
                        new DriverAdded(
                                transportId,
                                new DNI("54654654234"),
                                new FullName("driver", "last name"),
                                new Email("driver@gmail.com"),
                                new PhoneNumber("2342343241"),
                                new Salary(new BigDecimal(5000000))
                        )
                ));
    }

    @Test
    @DisplayName("Test for AddDeliveryAssistantUseCase#executeUseCase")
    void executeUseCase() {
        // Arrange
        DNI dni = new DNI("354345345");
        FullName fullName = new FullName("Delivery Assistant", "last name");
        Email email = new Email("deliveryassistant@gmail.com");
        PhoneNumber phoneNumber = new PhoneNumber("234234");
        Salary salary = new Salary(new BigDecimal(3000000));
        AddDeliveryAssistant command = new AddDeliveryAssistant(new TransportId(this.randomUUID), dni, fullName, email, phoneNumber, salary);
        AddDeliveryAssistantUseCase useCase = new AddDeliveryAssistantUseCase();
        useCase.addRepository(this.eventRepository);

        // Act
        DeliveryAssistantAdded event = (DeliveryAssistantAdded) UseCaseHandler
                .getInstance()
                .setIdentifyExecutor(randomUUID)
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow(() -> new RuntimeException("Something went wrong while adding the delivery assistant."))
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