package co.com.servientrega.domain.delivery.offices.events;

import co.com.servientrega.domain.delivery.offices.identity.OfficeId;
import co.com.servientrega.domain.delivery.offices.identity.StoreId;
import co.com.servientrega.domain.delivery.shipments.identity.ShipmentId;
import co.com.sofka.domain.generic.DomainEvent;

public class ShipmentAddedToStore extends DomainEvent {
    private final OfficeId officeId;
    private final StoreId storeId;
    private final ShipmentId shipmentId;

    public ShipmentAddedToStore(OfficeId officeId, StoreId storeId, ShipmentId shipmentId) {
        super("Servientrega.offices.ShipmentAddedToStore");
        this.officeId = officeId;
        this.storeId = storeId;
        this.shipmentId = shipmentId;
    }

    public OfficeId officeId() {
        return officeId;
    }

    public StoreId storeId() {
        return storeId;
    }

    public ShipmentId shipmentId() {
        return shipmentId;
    }
}
