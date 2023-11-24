package co.com.servientrega.domain.delivery.shipments.events;

import co.com.servientrega.domain.delivery.common.values.Weight;
import co.com.servientrega.domain.delivery.shipments.identity.PackageId;
import co.com.servientrega.domain.delivery.shipments.identity.ShipmentId;
import co.com.sofka.domain.generic.DomainEvent;

public class PackageWeightChanged extends DomainEvent {
    private final ShipmentId shipmentId;
    private final PackageId packageId;
    private final Weight newWeight;

    public PackageWeightChanged(ShipmentId shipmentId, PackageId packageId, Weight newWeight) {
        super("Servientrega.shipments.PackageWeightChanged");
        this.shipmentId = shipmentId;
        this.packageId = packageId;
        this.newWeight = newWeight;
    }

    public ShipmentId shipmentId() {
        return shipmentId;
    }

    public PackageId packageId() {
        return packageId;
    }

    public Weight newWeight() {
        return newWeight;
    }
}
