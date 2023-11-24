package co.com.servientrega.domain.delivery.shipments.events;

import co.com.servientrega.domain.delivery.shipments.identity.InvoiceId;
import co.com.servientrega.domain.delivery.shipments.identity.ShipmentId;
import co.com.servientrega.domain.delivery.shipments.values.Money;
import co.com.sofka.domain.generic.DomainEvent;

public class InvoiceCostChanged extends DomainEvent {
    private final ShipmentId shipmentId;
    private final InvoiceId invoiceId;
    private final Money newCost;

    public InvoiceCostChanged(ShipmentId shipmentId, InvoiceId invoiceId, Money newCost) {
        super("Servientrega.shipments.InvoiceCostChanged");
        this.shipmentId = shipmentId;
        this.invoiceId = invoiceId;
        this.newCost = newCost;
    }

    public ShipmentId shipmentId() {
        return shipmentId;
    }

    public InvoiceId invoiceId() {
        return invoiceId;
    }

    public Money newCost() {
        return newCost;
    }
}
