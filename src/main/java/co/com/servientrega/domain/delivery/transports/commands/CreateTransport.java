package co.com.servientrega.domain.delivery.transports.commands;

import co.com.servientrega.domain.delivery.common.values.Size;
import co.com.servientrega.domain.delivery.common.values.Weight;
import co.com.sofka.domain.generic.Command;

public class CreateTransport extends Command {
    private final Weight capacityInKg;
    private final Size sizeCapacity;

    public CreateTransport(Weight capacityInKg, Size sizeCapacity) {
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
