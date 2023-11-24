package co.com.servientrega.domain.delivery.shipments.entities;

import co.com.servientrega.domain.delivery.shipments.identity.DeliveryOrderId;
import co.com.servientrega.domain.delivery.shipments.values.Addressee;
import co.com.servientrega.domain.delivery.shipments.values.Sender;
import co.com.sofka.domain.generic.Entity;

public class DeliveryOrder extends Entity<DeliveryOrderId> {
    private final Sender sender;
    private Addressee addressee;

    public DeliveryOrder(DeliveryOrderId entityId, Sender sender, Addressee addressee) {
        super(entityId);
        this.sender = sender;
        this.addressee = addressee;
    }

    public DeliveryOrder(Sender sender, Addressee addressee) {
        super(new DeliveryOrderId());
        this.sender = sender;
        this.addressee = addressee;
    }

    public void changeAddressee(Addressee addressee) {
        this.addressee = addressee;
    }

    public Sender sender() {
        return sender;
    }

    public Addressee addressee() {
        return addressee;
    }
}
