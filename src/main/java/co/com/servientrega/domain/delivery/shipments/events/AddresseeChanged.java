package co.com.servientrega.domain.delivery.shipments.events;

import co.com.servientrega.domain.delivery.shipments.identity.ShipmentId;
import co.com.servientrega.domain.delivery.shipments.values.Addressee;
import co.com.sofka.domain.generic.DomainEvent;

public class AddresseeChanged extends DomainEvent {
    private final ShipmentId shipmentId;
    private final Addressee addressee;


    public AddresseeChanged(ShipmentId shipmentId, Addressee addressee) {
        super("Servientrega.shipments.AddresseeChanged");
        this.shipmentId = shipmentId;
        this.addressee = addressee;
    }

    public ShipmentId shipmentId() {
        return shipmentId;
    }

    public Addressee addressee() {
        return addressee;
    }
}
