package co.com.servientrega.domain.delivery.shipments.commands;

import co.com.servientrega.domain.delivery.common.values.Weight;
import co.com.servientrega.domain.delivery.shipments.identity.ShipmentId;
import co.com.servientrega.domain.delivery.common.values.Size;
import co.com.sofka.domain.generic.Command;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CalculateDeliveryCost extends Command {
    private final ShipmentId shipmentId;
    private final Size packageSize;
    private final Weight packageWeight;

    public ShipmentId shipmentId() {
        return shipmentId;
    }

    public Size packageSize() {
        return packageSize;
    }

    public Weight packageWeight() {
        return packageWeight;
    }
}
