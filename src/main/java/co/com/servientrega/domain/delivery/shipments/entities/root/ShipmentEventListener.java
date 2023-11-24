package co.com.servientrega.domain.delivery.shipments.entities.root;

import co.com.servientrega.domain.delivery.shipments.entities.DeliveryOrder;
import co.com.servientrega.domain.delivery.shipments.entities.Invoice;
import co.com.servientrega.domain.delivery.shipments.entities.Package;
import co.com.servientrega.domain.delivery.shipments.events.*;
import co.com.servientrega.domain.delivery.common.values.Date;
import co.com.sofka.domain.generic.EventChange;

public class ShipmentEventListener extends EventChange {
    public ShipmentEventListener(Shipment instance) {
        super.apply((ShipmentCreated event) -> {
            instance.sentAt = event.sentAt();
            instance.deliveredAt = event.deliveredAt();
            // other fields are null?
        });

        super.apply((InvoiceAdded event) -> {
            if (instance.invoice != null) {
                throw new IllegalArgumentException("You cannot add an invoice since one already exists.");
            }
            instance.invoice = new Invoice(
                    event.invoiceId(),
                    event.issuerId(), event.price()
            );
        });

        super.apply((DeliveryOrderAdded event) -> {
            if (instance.deliveryOrder != null) {
                throw new IllegalArgumentException("You cannot add a delivery order since one already exists.");
            }
            instance.deliveryOrder = new DeliveryOrder(
                    event.deliveryOrderId(),
                    event.sender(),
                    event.addressee()
            );
        });

        super.apply((PackageAdded event) -> {
            if (instance.item != null) {
                throw new IllegalArgumentException("You cannot add a package since one already exists.");
            }
            instance.item = new Package(
                    event.packageId(),
                    event.packageName(),
                    event.packageDescription(),
                    event.packageWeight(),
                    event.packageSize()
            );
        });

        super.apply((AddresseeChanged event) -> instance.deliveryOrder.changeAddressee(event.addressee()));

        super.apply((CalculatedDeliveryCost event) -> instance.invoice.changeCost(event.calculatedPrice()));

        super.apply((InvoiceCostChanged event) -> instance.invoice.changeCost(event.newCost()));

        super.apply((PackageDelivered event) -> instance.deliveredAt = Date.now());

        super.apply((PackageNameChanged event) -> instance.item.changeName(event.newPackageName()));

        super.apply((PackageDescriptionChanged event) -> instance.item.changeDescription(event.newPackageDescription()));

        super.apply((PackageSizeChanged event) -> instance.item.changeSize(event.newSize()));

        super.apply((PackageWeightChanged event) -> instance.item.changeWeight(event.newWeight()));
    }
}
