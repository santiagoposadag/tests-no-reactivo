package co.com.servientrega.domain.delivery.shipments.commands;

import co.com.servientrega.domain.delivery.common.identity.EmployeeId;
import co.com.servientrega.domain.delivery.shipments.identity.ShipmentId;
import co.com.servientrega.domain.delivery.shipments.values.Money;
import co.com.sofka.domain.generic.Command;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AddInvoice extends Command {
    private final ShipmentId shipmentId;
    private final EmployeeId issuerId;
    private final Money calculatedCost;

    public ShipmentId shipmentId() {
        return shipmentId;
    }

    public EmployeeId issuerId() {
        return issuerId;
    }

    public Money calculatedCost() {
        return calculatedCost;
    }
}
