package co.com.servientrega.domain.usecases;

import co.com.servientrega.domain.delivery.offices.commands.AddShipmentToStore;
import co.com.servientrega.domain.delivery.offices.entities.root.Office;
import co.com.servientrega.domain.delivery.offices.identity.OfficeId;
import co.com.servientrega.domain.delivery.shipments.identity.ShipmentId;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;

public class AddShipmentToStoreUseCase extends UseCase<RequestCommand<AddShipmentToStore>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<AddShipmentToStore> addShipmentToStoreRequestCommand) {
        AddShipmentToStore command = addShipmentToStoreRequestCommand.getCommand();
        ShipmentId shipmentId = command.shipmentId();
        OfficeId officeId = command.officeId();
        Office office = Office.from(officeId, super.retrieveEvents(officeId.value()));
        office.addShipmentToStore(officeId, office.store().identity(), shipmentId);

        super.emit().onResponse(new ResponseEvents(office.getUncommittedChanges()));
    }
}
