package co.com.servientrega.domain.usecases;

import co.com.servientrega.domain.delivery.offices.commands.CreateOffice;
import co.com.servientrega.domain.delivery.offices.entities.root.Office;
import co.com.servientrega.domain.delivery.offices.identity.OfficeId;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;

public class CreateOfficeUseCase extends UseCase<RequestCommand<CreateOffice>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<CreateOffice> createOfficeRequestCommand) {
        CreateOffice command = createOfficeRequestCommand.getCommand();
        Office office = new Office(new OfficeId(), command.location(), command.createdAt());

        super.emit().onResponse(new ResponseEvents(office.getUncommittedChanges()));
    }
}
