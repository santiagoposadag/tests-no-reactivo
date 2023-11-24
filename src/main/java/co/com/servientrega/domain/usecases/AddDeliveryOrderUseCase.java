package co.com.servientrega.domain.usecases;

import co.com.servientrega.domain.delivery.shipments.commands.AddDeliveryOrder;
import co.com.servientrega.domain.delivery.shipments.entities.DeliveryOrder;
import co.com.servientrega.domain.delivery.shipments.entities.root.Shipment;
import co.com.servientrega.domain.delivery.shipments.identity.ShipmentId;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;

public class AddDeliveryOrderUseCase extends UseCase<RequestCommand<AddDeliveryOrder>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<AddDeliveryOrder> addDeliveryOrderRequestCommand) {
        AddDeliveryOrder command = addDeliveryOrderRequestCommand.getCommand();
        ShipmentId shipmentId = command.shipmentId();
        Shipment shipment = Shipment.from(shipmentId, super.retrieveEvents(shipmentId.value()));
        DeliveryOrder deliveryOrder = new DeliveryOrder(command.sender(), command.addressee());
        shipment.addDeliveryOrder(shipmentId, deliveryOrder.identity(), command.sender(), command.addressee());

        super.emit().onResponse(new ResponseEvents(shipment.getUncommittedChanges()));
    }
}
