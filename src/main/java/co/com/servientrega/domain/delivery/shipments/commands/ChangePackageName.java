package co.com.servientrega.domain.delivery.shipments.commands;

import co.com.servientrega.domain.delivery.shipments.identity.ShipmentId;
import co.com.servientrega.domain.delivery.shipments.values.PackageName;
import co.com.sofka.domain.generic.Command;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ChangePackageName extends Command {
    private final ShipmentId shipmentId;
    private final PackageName newPackageName;

    public ShipmentId shipmentId() {
        return shipmentId;
    }

    public PackageName newPackageName() {
        return newPackageName;
    }
}
