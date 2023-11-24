package co.com.servientrega.domain.delivery.shipments.commands;

import co.com.servientrega.domain.delivery.shipments.identity.ShipmentId;
import co.com.sofka.domain.generic.Command;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeliverPackage extends Command {
    private final ShipmentId shipmentId;

    public ShipmentId shipmentId() {
        return shipmentId;
    }
}
