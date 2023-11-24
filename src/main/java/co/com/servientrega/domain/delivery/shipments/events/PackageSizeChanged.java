package co.com.servientrega.domain.delivery.shipments.events;

import co.com.servientrega.domain.delivery.shipments.identity.PackageId;
import co.com.servientrega.domain.delivery.shipments.identity.ShipmentId;
import co.com.servientrega.domain.delivery.common.values.Size;
import co.com.sofka.domain.generic.DomainEvent;

public class PackageSizeChanged extends DomainEvent {
    private final ShipmentId shipmentId;
    private final PackageId packageId;
    private final Size newSize;

    public PackageSizeChanged(ShipmentId shipmentId, PackageId packageId, Size newSize) {
        super("Servientrega.shipments.PackageSizeChanged");
        this.shipmentId = shipmentId;
        this.packageId = packageId;
        this.newSize = newSize;
    }

    public ShipmentId shipmentId() {
        return shipmentId;
    }

    public PackageId packageId() {
        return packageId;
    }

    public Size newSize() {
        return newSize;
    }
}
