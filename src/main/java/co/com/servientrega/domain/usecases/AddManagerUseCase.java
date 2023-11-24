package co.com.servientrega.domain.usecases;

import co.com.servientrega.domain.delivery.offices.commands.AddManager;
import co.com.servientrega.domain.delivery.offices.entities.Manager;
import co.com.servientrega.domain.delivery.offices.entities.root.Office;
import co.com.servientrega.domain.delivery.offices.identity.OfficeId;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;

public class AddManagerUseCase extends UseCase<RequestCommand<AddManager>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<AddManager> addManagerRequestCommand) {
        AddManager command = addManagerRequestCommand.getCommand();
        OfficeId officeId = command.officeId();
        Office office = Office.from(officeId, super.retrieveEvents(officeId.value()));
        Manager manager = new Manager(
                command.dni(),
                command.fullName(),
                command.email(),
                command.phoneNumber(),
                command.salary()
        );
        office.addManager(officeId, manager.identity(), manager.dni(), manager.fullName(), manager.email(), manager.phoneNumber(), manager.salary());

        super.emit().onResponse(new ResponseEvents(office.getUncommittedChanges()));
    }
}
