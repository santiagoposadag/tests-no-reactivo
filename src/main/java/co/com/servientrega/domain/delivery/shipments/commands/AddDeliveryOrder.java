package co.com.servientrega.domain.delivery.shipments.commands;

import co.com.servientrega.domain.delivery.shipments.identity.ShipmentId;
import co.com.servientrega.domain.delivery.shipments.values.Addressee;
import co.com.servientrega.domain.delivery.shipments.values.Sender;
import co.com.sofka.domain.generic.Command;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AddDeliveryOrder extends Command {
    private final ShipmentId shipmentId;
    private final Sender sender;
    private final Addressee addressee;

    public ShipmentId shipmentId() {
        return shipmentId;
    }

    public Sender sender() {
        return sender;
    }

    public Addressee addressee() {
        return addressee;
    }
}
