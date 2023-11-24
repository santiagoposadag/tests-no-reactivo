package co.com.servientrega.domain.delivery.shipments.events;

import co.com.servientrega.domain.delivery.common.values.Weight;
import co.com.servientrega.domain.delivery.shipments.identity.PackageId;
import co.com.servientrega.domain.delivery.shipments.identity.ShipmentId;
import co.com.servientrega.domain.delivery.shipments.values.PackageDescription;
import co.com.servientrega.domain.delivery.shipments.values.PackageName;
import co.com.servientrega.domain.delivery.common.values.Size;
import co.com.sofka.domain.generic.DomainEvent;

public class PackageAdded extends DomainEvent {
    private final ShipmentId shipmentId;
    private final PackageId packageId;
    private final PackageName packageName;
    private final PackageDescription packageDescription;
    private final Weight packageWeight;
    private final Size packageSize;

    public PackageAdded(ShipmentId shipmentId, PackageId packageId, PackageName packageName, PackageDescription packageDescription, Weight packageWeight, Size packageSize) {
        super("Servientrega.shipments.PackageAdded");
        this.shipmentId = shipmentId;
        this.packageId = packageId;
        this.packageName = packageName;
        this.packageDescription = packageDescription;
        this.packageWeight = packageWeight;
        this.packageSize = packageSize;
    }

    public ShipmentId shipmentId() {
        return shipmentId;
    }

    public PackageId packageId() {
        return packageId;
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
