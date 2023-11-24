package co.com.servientrega.domain.usecases;

import co.com.servientrega.domain.delivery.common.values.*;
import co.com.servientrega.domain.delivery.offices.commands.AddManager;
import co.com.servientrega.domain.delivery.offices.events.ManagerAdded;
import co.com.servientrega.domain.delivery.offices.events.StoreAdded;
import co.com.servientrega.domain.delivery.offices.identity.OfficeId;
import co.com.servientrega.domain.delivery.offices.identity.StoreId;
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

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class AddManagerUseCaseTest {
    private final String randomUUID = generateRandomUUID();

    @Mock
    private DomainEventRepository eventRepository;

    @BeforeEach
    void setUp() {
        OfficeId officeId = new OfficeId(randomUUID);
        BDDMockito
                .when(eventRepository.getEventsBy(randomUUID))
                .thenReturn(List.of(
                        new StoreAdded(officeId, new StoreId(generateRandomUUID()), new Size(5.0, 40.0, 50.0))
                ));
    }

    @Test
    @DisplayName("Test for AddManagerUseCase#executeUseCase")
    void executeUseCase() {
        // Arrange
        DNI dni = new DNI("4320509443");
        FullName fullName = new FullName("Juanito el director", "idk");
        Email email = new Email("juanito@gmail.com");
        PhoneNumber phoneNumber = new PhoneNumber("38450483509");
        Salary salary = new Salary(new BigDecimal(2000000));
        AddManager command = new AddManager(new OfficeId(this.randomUUID), dni, fullName, email, phoneNumber, salary);
        AddManagerUseCase useCase = new AddManagerUseCase();
        useCase.addRepository(this.eventRepository);

        // Act
        ManagerAdded event = (ManagerAdded) UseCaseHandler
                .getInstance()
                .setIdentifyExecutor(randomUUID)
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow(() -> new RuntimeException("Something went wrong while adding the package."))
                .getDomainEvents()
                .get(0);

        // Assert
        assertThat(event.managerId()).isNotNull();
        assertThat(event.dni().value()).isEqualTo(dni.value());
        assertThat(event.fullName()).isEqualTo(fullName);
        assertThat(event.email().value()).isEqualTo(email.value());
        assertThat(event.phoneNumber().value()).isEqualTo(phoneNumber.value());
        assertThat(event.salary().value()).isEqualTo(salary.value());
        BDDMockito.verify(eventRepository).getEventsBy(this.randomUUID);
    }

    private static String generateRandomUUID() {
        return UUID.randomUUID().toString();
    }
}