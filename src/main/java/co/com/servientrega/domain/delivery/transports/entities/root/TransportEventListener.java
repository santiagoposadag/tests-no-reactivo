package co.com.servientrega.domain.delivery.transports.entities.root;

import co.com.servientrega.domain.delivery.transports.entities.DeliveryAssistant;
import co.com.servientrega.domain.delivery.transports.entities.Driver;
import co.com.servientrega.domain.delivery.transports.events.*;
import co.com.sofka.domain.generic.EventChange;

public class TransportEventListener extends EventChange {
    public TransportEventListener(Transport instance) {
        super.apply((TransportCreated event) -> {
            instance.capacityInKg = event.capacityInKg();
            instance.sizeCapacity = event.sizeCapacity();
        });

        super.apply((DeliveryAssistantAdded event) -> {
            if(instance.deliveryAssistant() != null) {
                throw new IllegalArgumentException("You cannot add a new delivery assistant; one already exists.");
            }
            instance.deliveryAssistant = new DeliveryAssistant(
                    event.dni(),
                    event.fullName(),
                    event.email(),
                    event.phoneNumber(),
                    event.salary()
            );
        });

        super.apply((DriverAdded event) -> {
            if(instance.driver() != null) {
                throw new IllegalArgumentException("You cannot add a new driver; one already exists.");
            }
            instance.driver = new Driver(
                    event.dni(),
                    event.fullName(),
                    event.email(),
                    event.phoneNumber(),
                    event.salary()
            );
        });

        super.apply((DeliveryAssistantChanged event) -> instance.deliveryAssistant = new DeliveryAssistant(
                event.newDeliveryAssistantDNI(),
                event.newDeliveryAssistantFullName(),
                event.newDeliveryAssistantEmail(),
                event.newDeliveryAssistantPhoneNumber(),
                event.newDeliveryAssistantSalary()
        ));

        super.apply((DriverChanged event) -> instance.driver = new Driver(
                event.newDriverDNI(),
                event.newDriverFullName(),
                event.newDriverEmail(),
                event.newDriverPhoneNumber(),
                event.newDriverSalary()
        ));

        super.apply((DeliveryAssistantEmailChanged event) -> instance.deliveryAssistant.changeEmail(event.newEmail()));

        super.apply((DriverEmailChanged event) -> instance.driver.changeEmail(event.newEmail()));

        super.apply((DeliveryAssistantPhoneNumberChanged event) -> instance.deliveryAssistant.changePhoneNumber(event.newPhoneNumber()));

        super.apply((DriverPhoneNumberChanged event) -> instance.driver.changePhoneNumber(event.newPhoneNumber()));

        super.apply((DeliveryAssistantSalaryChanged event) -> instance.deliveryAssistant.changeSalary(event.newSalary()));

        super.apply((DriverSalaryChanged event) -> instance.driver.changeSalary(event.newSalary()));

        super.apply((PickedUpShipments event) -> instance.shipmentIds = event.shipmentsIds());
    }
}
