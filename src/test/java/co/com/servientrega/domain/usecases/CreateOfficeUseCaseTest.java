package co.com.servientrega.domain.usecases;

import co.com.servientrega.domain.delivery.common.values.Date;
import co.com.servientrega.domain.delivery.common.values.Location;
import co.com.servientrega.domain.delivery.offices.commands.CreateOffice;
import co.com.servientrega.domain.delivery.offices.events.CreatedOffice;
import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.support.RequestCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CreateOfficeUseCaseTest {
    private final String randomUUID = generateRandomUUID();

    @Test
    @DisplayName("Test for CreateOffice#executeUseCase")
    void executeUseCase() {
        // Arrange
        Date createdAt = Date.now();
        Location location = new Location("Santander", "Puente Nacional", "Street 23 # 63 - 3");
        CreateOffice command = new CreateOffice(createdAt, location);
        CreateOfficeUseCase useCase = new CreateOfficeUseCase();

        // Act
        CreatedOffice event = (CreatedOffice) UseCaseHandler
                .getInstance()
                .setIdentifyExecutor(randomUUID)
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow(() -> new RuntimeException("Something went wrong while creating the office."))
                .getDomainEvents()
                .get(0);

        // Assert
        assertThat(event.officeId()).isNotNull();
        assertThat(event.createdAt().value()).isEqualTo(createdAt.value());
        assertThat(event.location()).isEqualTo(location);
    }

    private static String generateRandomUUID() {
        return UUID.randomUUID().toString();
    }
}