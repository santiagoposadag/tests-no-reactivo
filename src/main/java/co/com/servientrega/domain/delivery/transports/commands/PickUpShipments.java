package co.com.servientrega.domain.delivery.transports.commands;

import co.com.servientrega.domain.delivery.offices.identity.OfficeId;
import co.com.servientrega.domain.delivery.transports.identity.TransportId;
import co.com.sofka.domain.generic.Command;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PickUpShipments extends Command {
    private final OfficeId officeId;
    private final TransportId transportId;

    public OfficeId officeId() {
        return officeId;
    }

    public TransportId transportId() {
        return transportId;
    }
}
