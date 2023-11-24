package co.com.servientrega.domain.delivery.shipments.commands;

import co.com.servientrega.domain.delivery.shipments.identity.ShipmentId;
import co.com.servientrega.domain.delivery.shipments.values.PackageDescription;
import co.com.sofka.domain.generic.Command;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ChangePackageDescription extends Command {
    private final ShipmentId shipmentId;
    private final PackageDescription newPackageDescription;

    public ShipmentId shipmentId() {
        return shipmentId;
    }

    public PackageDescription newPackageDescription() {
        return newPackageDescription;
    }
}
