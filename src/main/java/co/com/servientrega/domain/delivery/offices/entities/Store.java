package co.com.servientrega.domain.delivery.offices.entities;

import co.com.servientrega.domain.delivery.common.values.Size;
import co.com.servientrega.domain.delivery.offices.identity.StoreId;
import co.com.servientrega.domain.delivery.shipments.identity.ShipmentId;
import co.com.sofka.domain.generic.Entity;

import java.util.HashSet;
import java.util.Set;

public class Store extends Entity<StoreId> {
    private Size capacity;
    private final Set<ShipmentId> shipmentsIds;

    public Store(StoreId entityId, Size capacity, Set<ShipmentId> shipmentsIds) {
        super(entityId);
        this.capacity = capacity;
        this.shipmentsIds = shipmentsIds;
    }

    public Store(Size capacity) {
        super(new StoreId());
        this.capacity = capacity;
        this.shipmentsIds = new HashSet<>();
    }

    public void addShipment(ShipmentId shipmentId) {
        this.shipmentsIds.add(shipmentId);
    }

    public void empty() {
        this.shipmentsIds.clear();
    }

    public void changeCapacity(Size newCapacity) {
        this.capacity = newCapacity;
    }

    public Size capacity() {
        return capacity;
    }

    public Set<ShipmentId> shipmentsIds() {
        return shipmentsIds;
    }
}
