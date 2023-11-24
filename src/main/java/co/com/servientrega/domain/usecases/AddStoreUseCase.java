package co.com.servientrega.domain.usecases;

import co.com.servientrega.domain.delivery.offices.commands.AddStore;
import co.com.servientrega.domain.delivery.offices.entities.root.Office;
import co.com.servientrega.domain.delivery.offices.identity.OfficeId;
import co.com.servientrega.domain.delivery.offices.identity.StoreId;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;

public class AddStoreUseCase extends UseCase<RequestCommand<AddStore>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<AddStore> addStoreRequestCommand) {
        AddStore command = addStoreRequestCommand.getCommand();
        OfficeId officeId = command.officeId();
        Office office = Office.from(officeId, super.retrieveEvents(officeId.value()));
        office.addStore(officeId, new StoreId(), command.size());

        super.emit().onResponse(new ResponseEvents(office.getUncommittedChanges()));
    }
}
