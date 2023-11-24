package co.com.servientrega.domain.delivery.shipments.commands;

import co.com.servientrega.domain.delivery.shipments.identity.ShipmentId;
import co.com.servientrega.domain.delivery.shipments.values.Money;
import co.com.sofka.domain.generic.Command;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ChangeInvoiceCost extends Command {
    private final ShipmentId shipmentId;
    private final Money newCost;

    public ShipmentId shipmentId() {
        return shipmentId;
    }

    public Money newCost() {
        return newCost;
    }
}
