package co.com.servientrega.domain.delivery.shipments.entities;

import co.com.servientrega.domain.delivery.common.identity.EmployeeId;
import co.com.servientrega.domain.delivery.shipments.identity.InvoiceId;
import co.com.servientrega.domain.delivery.shipments.values.Money;
import co.com.sofka.domain.generic.Entity;

public class Invoice extends Entity<InvoiceId> {
    private final EmployeeId issuerId;
    private Money cost;

    public Invoice(InvoiceId entityId, EmployeeId issuerId, Money cost) {
        super(entityId);
        this.issuerId = issuerId;
        this.cost = cost;
    }

    public Invoice(EmployeeId issuerId, Money cost) {
        super(new InvoiceId());
        this.issuerId = issuerId;
        this.cost = cost;
    }

    public void changeCost(Money newCost) {
        this.cost = newCost;
    }

    public EmployeeId issuerId() {
        return issuerId;
    }

    public Money cost() {
        return cost;
    }
}
