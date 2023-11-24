package co.com.servientrega.domain.delivery.shipments.commands;

import co.com.servientrega.domain.delivery.common.values.Weight;
import co.com.servientrega.domain.delivery.shipments.identity.ShipmentId;
import co.com.sofka.domain.generic.Command;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ChangePackageWeight extends Command {
    private final ShipmentId shipmentId;
    private final Weight newPackageWeight;

    public ShipmentId shipmentId() {
        return shipmentId;
    }

    public Weight newPackageWeight() {
        return newPackageWeight;
    }
}
