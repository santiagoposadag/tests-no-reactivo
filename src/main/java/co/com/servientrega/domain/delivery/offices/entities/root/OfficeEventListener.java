package co.com.servientrega.domain.delivery.offices.entities.root;

import co.com.servientrega.domain.delivery.offices.entities.Manager;
import co.com.servientrega.domain.delivery.offices.entities.Store;
import co.com.servientrega.domain.delivery.offices.events.*;
import co.com.sofka.domain.generic.EventChange;

public class OfficeEventListener extends EventChange {
    public OfficeEventListener(Office instance) {
        super.apply((CreatedOffice event) -> instance.location = event.location());

        super.apply((ManagerAdded event) -> {
            if (instance.manager() != null) {
                throw new IllegalArgumentException("A manager already exists; you cannot add a new one.");
            }
            instance.manager = new Manager(
                    event.dni(),
                    event.fullName(),
                    event.email(),
                    event.phoneNumber(),
                    event.salary()
            );
        });

        super.apply((StoreAdded event) -> {
            if (instance.store() != null) {
                throw new IllegalArgumentException("A store already exists; you cannot add a new one.");
            }
            instance.store = new Store(
                    event.capacity()
            );
        });

        super.apply((ManagerChanged event) -> instance.manager = new Manager(
                event.newManagerDNI(),
                event.newManagerFullName(),
                event.newManagerEmail(),
                event.newManagerPhoneNumber(),
                event.newManagerSalary()
        ));

        super.apply((ManagerEmailChanged event) -> instance.manager().changeEmail(event.newEmail()));

        super.apply((ManagerPhoneNumberChanged event) -> instance.manager().changePhoneNumber(event.newPhoneNumber()));

        super.apply((ManagerSalaryChanged event) -> instance.manager().changeSalary(event.newSalary()));

        super.apply((ShipmentAddedToStore event) -> instance.store().addShipment(event.shipmentId()));

        super.apply((ShipmentsDeliveryStarted event) -> instance.store().empty());

        super.apply((StoreCapacityChanged event) -> instance.store().changeCapacity(event.newSize()));
    }
}
