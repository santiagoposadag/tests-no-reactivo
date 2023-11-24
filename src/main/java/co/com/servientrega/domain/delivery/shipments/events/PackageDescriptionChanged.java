package co.com.servientrega.domain.delivery.shipments.events;

import co.com.servientrega.domain.delivery.shipments.identity.PackageId;
import co.com.servientrega.domain.delivery.shipments.identity.ShipmentId;
import co.com.servientrega.domain.delivery.shipments.values.PackageDescription;
import co.com.sofka.domain.generic.DomainEvent;

public class PackageDescriptionChanged extends DomainEvent {
    private final ShipmentId shipmentId;
    private final PackageId packageId;
    private final PackageDescription packageDescription;

    public PackageDescriptionChanged(ShipmentId shipmentId, PackageId packageId, PackageDescription packageDescription) {
        super("Servientrega.shipments.PackageDescriptionChanged");
        this.shipmentId = shipmentId;
        this.packageId = packageId;
        this.packageDescription = packageDescription;
    }

    public ShipmentId shipmentId() {
        return shipmentId;
    }

    public PackageId packageId() {
        return packageId;
    }

    public PackageDescription newPackageDescription() {
        return packageDescription;
    }
}
