package co.com.servientrega.domain.usecases;

import co.com.servientrega.domain.delivery.transports.commands.AddDriver;
import co.com.servientrega.domain.delivery.transports.entities.root.Transport;
import co.com.servientrega.domain.delivery.transports.identity.TransportId;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;

public class AddDriverUseCase extends UseCase<RequestCommand<AddDriver>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<AddDriver> addDriverRequestCommand) {
        AddDriver command = addDriverRequestCommand.getCommand();
        TransportId transportId = command.transportId();
        Transport transport = Transport.from(transportId, super.retrieveEvents(transportId.value()));
        transport.addDriver(command.transportId(), command.dni(), command.fullName(), command.email(), command.phoneNumber(), command.salary());

        super.emit().onResponse(new ResponseEvents(transport.getUncommittedChanges()));
    }
}
