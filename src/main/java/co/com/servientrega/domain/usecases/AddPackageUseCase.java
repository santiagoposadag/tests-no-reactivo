package co.com.servientrega.domain.usecases;

import co.com.servientrega.domain.delivery.shipments.commands.AddPackage;
import co.com.servientrega.domain.delivery.shipments.entities.Package;
import co.com.servientrega.domain.delivery.shipments.entities.root.Shipment;
import co.com.servientrega.domain.delivery.shipments.identity.ShipmentId;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;

public class AddPackageUseCase extends UseCase<RequestCommand<AddPackage>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<AddPackage> addPackageRequestCommand) {
        AddPackage command = addPackageRequestCommand.getCommand();
        ShipmentId shipmentId = command.shipmentId();
        Shipment shipment = Shipment.from(shipmentId, super.retrieveEvents(shipmentId.value()));
        Package item = new Package(command.packageName(), command.packageDescription(), command.packageWeight(), command.packageSize());
        shipment.addPackage(shipmentId, item.identity(), command.packageName(), command.packageDescription(), command.packageWeight(), command.packageSize());

        super.emit().onResponse(new ResponseEvents(shipment.getUncommittedChanges()));
    }
}
