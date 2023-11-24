package co.com.servientrega.domain.usecases;

import co.com.servientrega.domain.delivery.common.values.Size;
import co.com.servientrega.domain.delivery.common.values.Weight;
import co.com.servientrega.domain.delivery.transports.commands.CreateTransport;
import co.com.servientrega.domain.delivery.transports.events.TransportCreated;
import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.support.RequestCommand;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;


@ExtendWith(MockitoExtension.class)
class CreateTransportUseCaseTest {
    private final String randomUUID = generateRandomUUID();

    @Test
    @DisplayName("Test for CreateTransportUseCase#executeUseCase")
    void executeUseCase() {
        // Arrange
        Weight capacityInKg = new Weight(300.0);
        Size sizeCapacity = new Size(1.5, 3.0, 1.5);
        CreateTransport command = new CreateTransport(capacityInKg, sizeCapacity);
        CreateTransportUseCase useCase = new CreateTransportUseCase();

        // Act
        TransportCreated event = (TransportCreated) UseCaseHandler
                .getInstance()
                .setIdentifyExecutor(randomUUID)
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow(() -> new RuntimeException("Something went wrong while creating the transport."))
                .getDomainEvents()
                .get(0);

        // Assert
        Assertions.assertThat(event.capacityInKg().value()).isEqualTo(capacityInKg.value());
        Assertions.assertThat(event.sizeCapacity()).isEqualTo(sizeCapacity);
    }

    private static String generateRandomUUID() {
        return UUID.randomUUID().toString();
    }
}