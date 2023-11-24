package co.com.servientrega.domain.usecases;

import co.com.servientrega.domain.delivery.common.identity.EmployeeId;
import co.com.servientrega.domain.delivery.common.values.*;
import co.com.servientrega.domain.delivery.offices.commands.AddStore;
import co.com.servientrega.domain.delivery.offices.events.ManagerAdded;
import co.com.servientrega.domain.delivery.offices.events.StoreAdded;
import co.com.servientrega.domain.delivery.offices.identity.OfficeId;
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
class AddStoreUseCaseTest {
    private final String randomUUID = generateRandomUUID();

    @Mock
    private DomainEventRepository eventRepository;

    @BeforeEach
    void setUp() {
        OfficeId officeId = new OfficeId(randomUUID);
        BDDMockito
                .when(eventRepository.getEventsBy(randomUUID))
                .thenReturn(List.of(
                        new ManagerAdded(
                                officeId,
                                new EmployeeId(generateRandomUUID()),
                                new DNI("434927023948"),
                                new FullName("Juanito el director", "apellido"),
                                new Email("juanito@gmail.com"),
                                new PhoneNumber("320948409328"),
                                new Salary(new BigDecimal(2000000))
                        )
                ));
    }

    @Test
    @DisplayName("Test for AddStoreUseCase#executeUseCase")
    void executeUseCase() {
        // Arrange
        Size size = new Size(20.0, 20.0, 20.0);
        AddStore command = new AddStore(new OfficeId(this.randomUUID), size);
        AddStoreUseCase useCase = new AddStoreUseCase();
        useCase.addRepository(this.eventRepository);

        // Act
        StoreAdded event = (StoreAdded) UseCaseHandler
                .getInstance()
                .setIdentifyExecutor(randomUUID)
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow(() -> new RuntimeException("Something went wrong while adding the store."))
                .getDomainEvents()
                .get(0);

        // Assert
        assertThat(event.storeId()).isNotNull();
        assertThat(event.officeId().value()).isEqualTo(this.randomUUID);
        assertThat(event.capacity()).isEqualTo(size);
        BDDMockito.verify(eventRepository).getEventsBy(this.randomUUID);
    }

    private static String generateRandomUUID() {
        return UUID.randomUUID().toString();
    }
}