package co.com.servientrega.domain.delivery.shipments.events;

import co.com.servientrega.domain.delivery.shipments.identity.ShipmentId;
import co.com.servientrega.domain.delivery.common.values.Date;
import co.com.sofka.domain.generic.DomainEvent;

public class ShipmentCreated extends DomainEvent {
    private final ShipmentId shipmentId;
    private final Date sentAt;
    private final Date deliveredAt;

    public ShipmentCreated(ShipmentId shipmentId, Date sentAt, Date deliveredAt) {
        super("Servientrega.shipments.ShipmentCreated");
        this.shipmentId = shipmentId;
        this.sentAt = sentAt;
        this.deliveredAt = deliveredAt;
    }

    public ShipmentId shipmentId() {
        return shipmentId;
    }

    public Date sentAt() {
        return sentAt;
    }

    public Date deliveredAt() {
        return deliveredAt;
    }
}
