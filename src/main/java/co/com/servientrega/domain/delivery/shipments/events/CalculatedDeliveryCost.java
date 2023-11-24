package co.com.servientrega.domain.delivery.shipments.events;

import co.com.servientrega.domain.delivery.shipments.identity.InvoiceId;
import co.com.servientrega.domain.delivery.shipments.identity.ShipmentId;
import co.com.servientrega.domain.delivery.shipments.values.Money;
import co.com.sofka.domain.generic.DomainEvent;

public class CalculatedDeliveryCost extends DomainEvent {
    private final ShipmentId shipmentId;
    private final InvoiceId invoiceId;
    private final Money calculatedPrice;

    public CalculatedDeliveryCost(ShipmentId shipmentId, InvoiceId invoiceId, Money calculatedPrice) {
        super("Servientrega.shipments.CalculatedDeliveryPrice");
        this.shipmentId = shipmentId;
        this.invoiceId = invoiceId;
        this.calculatedPrice = calculatedPrice;
    }

    public ShipmentId shipmentId() {
        return shipmentId;
    }

    public InvoiceId invoiceId() {
        return invoiceId;
    }

    public Money calculatedPrice() {
        return calculatedPrice;
    }
}
