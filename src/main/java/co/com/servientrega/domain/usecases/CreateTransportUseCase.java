package co.com.servientrega.domain.usecases;

import co.com.servientrega.domain.delivery.transports.commands.CreateTransport;
import co.com.servientrega.domain.delivery.transports.entities.root.Transport;
import co.com.servientrega.domain.delivery.transports.identity.TransportId;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;

public class CreateTransportUseCase extends UseCase<RequestCommand<CreateTransport>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<CreateTransport> createTransportRequestCommand) {
        CreateTransport command = createTransportRequestCommand.getCommand();
        Transport transport = new Transport(new TransportId(), command.sizeCapacity(), command.capacityInKg());

        super.emit().onResponse(new ResponseEvents(transport.getUncommittedChanges()));
    }
}
