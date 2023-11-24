package co.com.servientrega.domain.usecases;

import co.com.servientrega.domain.delivery.shipments.commands.AddInvoice;
import co.com.servientrega.domain.delivery.shipments.entities.Invoice;
import co.com.servientrega.domain.delivery.shipments.entities.root.Shipment;
import co.com.servientrega.domain.delivery.shipments.identity.ShipmentId;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;

public class AddInvoiceUseCase extends UseCase<RequestCommand<AddInvoice>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<AddInvoice> addInvoiceRequestCommand) {
        AddInvoice command = addInvoiceRequestCommand.getCommand();
        ShipmentId shipmentId = command.shipmentId();
        Shipment shipment = Shipment.from(shipmentId, super.retrieveEvents(shipmentId.value()));
        Invoice invoice = new Invoice(command.issuerId(), command.calculatedCost());
        shipment.addInvoice(shipmentId, invoice.identity(), command.issuerId(), command.calculatedCost());

        super.emit().onResponse(new ResponseEvents(shipment.getUncommittedChanges()));
    }
}
