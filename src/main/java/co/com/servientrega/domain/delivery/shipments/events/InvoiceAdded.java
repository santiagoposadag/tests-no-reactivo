package co.com.servientrega.domain.delivery.shipments.events;

import co.com.servientrega.domain.delivery.common.identity.EmployeeId;
import co.com.servientrega.domain.delivery.shipments.identity.InvoiceId;
import co.com.servientrega.domain.delivery.shipments.identity.ShipmentId;
import co.com.servientrega.domain.delivery.shipments.values.Money;
import co.com.sofka.domain.generic.DomainEvent;

public class InvoiceAdded extends DomainEvent {
    private final ShipmentId shipmentId;
    private final InvoiceId invoiceId;
    private final Money price;
    private final EmployeeId issuerId;


    public InvoiceAdded(ShipmentId shipmentId, InvoiceId invoiceId, Money price, EmployeeId issuerId) {
        super("Servientrega.shipments.InvoiceAdded");
        this.shipmentId = shipmentId;
        this.invoiceId = invoiceId;
        this.price = price;
        this.issuerId = issuerId;
    }

    public ShipmentId shipmentId() {
        return shipmentId;
    }

    public InvoiceId invoiceId() {
        return invoiceId;
    }

    public Money price() {
        return price;
    }

    public EmployeeId issuerId() {
        return issuerId;
    }
}
