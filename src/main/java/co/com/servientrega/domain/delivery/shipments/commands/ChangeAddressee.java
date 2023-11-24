package co.com.servientrega.domain.delivery.shipments.commands;

import co.com.servientrega.domain.delivery.shipments.identity.ShipmentId;
import co.com.servientrega.domain.delivery.shipments.values.Addressee;
import co.com.sofka.domain.generic.Command;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ChangeAddressee extends Command {
    private final ShipmentId shipmentId;
    private final Addressee newAddressee;

    public ShipmentId shipmentId() {
        return shipmentId;
    }

    public Addressee newAddressee() {
        return newAddressee;
    }
}
