package co.com.servientrega.domain.delivery.shipments.events;

import co.com.servientrega.domain.delivery.shipments.identity.PackageId;
import co.com.servientrega.domain.delivery.shipments.identity.ShipmentId;
import co.com.sofka.domain.generic.DomainEvent;

public class PackageDelivered extends DomainEvent {
    private final ShipmentId shipmentId;
    private final PackageId packageId;

    public PackageDelivered(ShipmentId shipmentId, PackageId packageId) {
        super("Servientrega.shipments.PackageDelivered");
        this.shipmentId = shipmentId;
        this.packageId = packageId;
    }

    public ShipmentId shipmentId() {
        return shipmentId;
    }

    public PackageId packageId() {
        return packageId;
    }
}
