package co.com.servientrega.domain.delivery.transports.events;

import co.com.servientrega.domain.delivery.common.values.Size;
import co.com.servientrega.domain.delivery.common.values.Weight;
import co.com.sofka.domain.generic.DomainEvent;

public class TransportCreated extends DomainEvent {
    private final Weight capacityInKg;
    private final Size sizeCapacity;

    public TransportCreated(Weight capacityInKg, Size sizeCapacity) {
        super("Servientrega.transports.TransportCreated");
        this.capacityInKg = capacityInKg;
        this.sizeCapacity = sizeCapacity;
    }

    public Weight capacityInKg() {
        return capacityInKg;
    }

    public Size sizeCapacity() {
        return sizeCapacity;
    }
}
