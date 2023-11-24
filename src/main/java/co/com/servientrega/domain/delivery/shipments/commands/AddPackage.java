package co.com.servientrega.domain.delivery.shipments.commands;

import co.com.servientrega.domain.delivery.common.values.Weight;
import co.com.servientrega.domain.delivery.shipments.identity.ShipmentId;
import co.com.servientrega.domain.delivery.shipments.values.PackageDescription;
import co.com.servientrega.domain.delivery.shipments.values.PackageName;
import co.com.servientrega.domain.delivery.common.values.Size;
import co.com.sofka.domain.generic.Command;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AddPackage extends Command {
    private final ShipmentId shipmentId;
    private final PackageName packageName;
    private final PackageDescription packageDescription;
    private final Weight packageWeight;
    private final Size packageSize;

    public ShipmentId shipmentId() {
        return shipmentId;
    }

    public PackageName packageName() {
        return packageName;
    }

    public PackageDescription packageDescription() {
        return packageDescription;
    }

    public Weight packageWeight() {
        return packageWeight;
    }

    public Size packageSize() {
        return packageSize;
    }
}
