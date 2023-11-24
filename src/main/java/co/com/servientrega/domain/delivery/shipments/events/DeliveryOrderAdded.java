package co.com.servientrega.domain.delivery.shipments.events;

import co.com.servientrega.domain.delivery.shipments.identity.DeliveryOrderId;
import co.com.servientrega.domain.delivery.shipments.identity.ShipmentId;
import co.com.servientrega.domain.delivery.shipments.values.Addressee;
import co.com.servientrega.domain.delivery.shipments.values.Sender;
import co.com.sofka.domain.generic.DomainEvent;

public class DeliveryOrderAdded extends DomainEvent {
    private final ShipmentId shipmentId;
    private final DeliveryOrderId deliveryOrderId;
    private final Sender sender;
    private final Addressee addressee;

    public DeliveryOrderAdded(ShipmentId shipmentId, DeliveryOrderId deliveryOrderId, Sender sender, Addressee addressee) {
        super("Servientrega.shipments.DeliveryOrderAdded");
        this.shipmentId = shipmentId;
        this.deliveryOrderId = deliveryOrderId;
        this.sender = sender;
        this.addressee = addressee;
    }

    public DeliveryOrderId deliveryOrderId() {
        return deliveryOrderId;
    }

    public ShipmentId shipmentId() {
        return shipmentId;
    }

    public Sender sender() {
        return sender;
    }

    public Addressee addressee() {
        return addressee;
    }
}
