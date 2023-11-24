package co.com.servientrega.domain.delivery.transports.events;

import co.com.servientrega.domain.delivery.offices.identity.OfficeId;
import co.com.servientrega.domain.delivery.shipments.identity.ShipmentId;
import co.com.servientrega.domain.delivery.transports.identity.TransportId;
import co.com.sofka.domain.generic.DomainEvent;

import java.util.Set;

public class PickedUpShipments extends DomainEvent {
    private final TransportId transportId;
    private final OfficeId officeId;
    private final Set<ShipmentId> shipmentsIds;

    public PickedUpShipments(TransportId transportId, OfficeId officeId, Set<ShipmentId> shipmentsIds) {
        super("Servientrega.transports.PickedUpShipments");
        this.transportId = transportId;
        this.officeId = officeId;
        this.shipmentsIds = shipmentsIds;
    }

    public TransportId transportId() {
        return transportId;
    }

    public OfficeId officeId() {
        return officeId;
    }

    public Set<ShipmentId> shipmentsIds() {
        return shipmentsIds;
    }
}
