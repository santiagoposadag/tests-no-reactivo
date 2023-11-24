package co.com.servientrega.domain.delivery.offices.commands;

import co.com.servientrega.domain.delivery.offices.identity.OfficeId;
import co.com.servientrega.domain.delivery.shipments.identity.ShipmentId;
import co.com.sofka.domain.generic.Command;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AddShipmentToStore extends Command {
    private OfficeId officeId;
    private ShipmentId shipmentId;

    public OfficeId officeId() {
        return officeId;
    }

    public ShipmentId shipmentId() {
        return shipmentId;
    }
}
