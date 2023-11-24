package co.com.servientrega.domain.delivery.shipments.events;

import co.com.servientrega.domain.delivery.shipments.identity.PackageId;
import co.com.servientrega.domain.delivery.shipments.identity.ShipmentId;
import co.com.servientrega.domain.delivery.shipments.values.PackageName;
import co.com.sofka.domain.generic.DomainEvent;

public class PackageNameChanged extends DomainEvent {
    private final ShipmentId shipmentId;
    private final PackageId packageId;
    private final PackageName newPackageName;

    public PackageNameChanged(ShipmentId shipmentId, PackageId packageId, PackageName newPackageName) {
        super("Servientrega.shipments.PackageNameChanged");
        this.shipmentId = shipmentId;
        this.packageId = packageId;
        this.newPackageName = newPackageName;
    }

    public ShipmentId shipmentId() {
        return shipmentId;
    }

    public PackageId packageId() {
        return packageId;
    }

    public PackageName newPackageName() {
        return newPackageName;
    }
}
