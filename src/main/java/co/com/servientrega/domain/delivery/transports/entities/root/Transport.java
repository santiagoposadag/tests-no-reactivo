package co.com.servientrega.domain.delivery.transports.entities.root;

import co.com.servientrega.domain.delivery.common.identity.EmployeeId;
import co.com.servientrega.domain.delivery.common.values.*;
import co.com.servientrega.domain.delivery.offices.identity.OfficeId;
import co.com.servientrega.domain.delivery.shipments.identity.ShipmentId;
import co.com.servientrega.domain.delivery.transports.entities.DeliveryAssistant;
import co.com.servientrega.domain.delivery.transports.entities.Driver;
import co.com.servientrega.domain.delivery.transports.events.*;
import co.com.servientrega.domain.delivery.transports.identity.TransportId;
import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;

import java.util.List;
import java.util.Set;

public class Transport extends AggregateEvent<TransportId> {
    protected Driver driver;
    protected DeliveryAssistant deliveryAssistant;
    protected Size sizeCapacity;
    protected Weight capacityInKg;

    protected Set<ShipmentId> shipmentIds;

    public Transport(TransportId entityId) {
        super(entityId);
        subscribe(new TransportEventListener(this));
    }

    public Transport(TransportId entityId, Size sizeCapacity, Weight capacityInKg) {
        super(entityId);
        this.appendChange(new TransportCreated(capacityInKg, sizeCapacity)).apply();
    }

    public static Transport from(TransportId transportId, List<DomainEvent> events) {
        Transport transport = new Transport(transportId);
        events.forEach(transport::applyEvent);
        return transport;
    }

    public void addDeliveryAssistant(TransportId transportId, DNI dni, FullName fullName, Email email, PhoneNumber phoneNumber, Salary salary) {
        super
                .appendChange(new DeliveryAssistantAdded(transportId, dni, fullName, email, phoneNumber, salary))
                .apply();
    }

    public void addDriver(TransportId transportId, DNI dni, FullName fullName, Email email, PhoneNumber phoneNumber, Salary salary) {
        super
                .appendChange(new DriverAdded(transportId, dni, fullName, email, phoneNumber, salary))
                .apply();
    }

    public void changeDeliveryAssistant(TransportId transportId, EmployeeId previousDeliveryAssistantId, EmployeeId newDeliveryAssistantId, DNI newDeliveryAssistantDNI, FullName newDeliveryAssistantFullName, Email newDeliveryAssistantEmail, PhoneNumber newDeliveryAssistantPhoneNumber, Salary newDeliveryAssistantSalary) {
        super
                .appendChange(new DeliveryAssistantChanged(transportId, previousDeliveryAssistantId, newDeliveryAssistantId,
                        newDeliveryAssistantDNI, newDeliveryAssistantFullName, newDeliveryAssistantEmail,
                        newDeliveryAssistantPhoneNumber, newDeliveryAssistantSalary))
                .apply();
    }

    public void changeDriver(TransportId transportId, EmployeeId previousDriverId, EmployeeId newDriverId, DNI newDriverDNI, FullName newDriverFullName, Email newDriverEmail, PhoneNumber newDriverPhoneNumber, Salary newDriverSalary) {
        super
                .appendChange(new DeliveryAssistantChanged(transportId, previousDriverId, newDriverId,
                        newDriverDNI, newDriverFullName, newDriverEmail, newDriverPhoneNumber, newDriverSalary))
                .apply();
    }

    public void changeDeliveryAssistantEmail(TransportId transportId, EmployeeId deliveryAssistantId, Email newEmail) {
        super
                .appendChange(new DeliveryAssistantEmailChanged(transportId, deliveryAssistantId, newEmail))
                .apply();
    }

    public void changeDriverEmail(TransportId transportId, EmployeeId driverId, Email newEmail) {
        super
                .appendChange(new DeliveryAssistantEmailChanged(transportId, driverId, newEmail))
                .apply();
    }

    public void changeDeliveryAssistantPhoneNumber(TransportId transportId, EmployeeId deliveryAssistantId, PhoneNumber newPhoneNumber) {
        super
                .appendChange(new DeliveryAssistantPhoneNumberChanged(transportId, deliveryAssistantId, newPhoneNumber))
                .apply();
    }

    public void changeDriverPhoneNumber(TransportId transportId, EmployeeId driverId, PhoneNumber newPhoneNumber) {
        super
                .appendChange(new DriverPhoneNumberChanged(transportId, driverId, newPhoneNumber))
                .apply();
    }

    public void changeDeliveryAssistantSalary(TransportId transportId, EmployeeId deliveryAssistantId, Salary newSalary) {
        super
                .appendChange(new DeliveryAssistantSalaryChanged(transportId, deliveryAssistantId, newSalary))
                .apply();
    }

    public void changeDriverSalary(TransportId transportId, EmployeeId driverId, Salary newSalary) {
        super
                .appendChange(new DriverSalaryChanged(transportId, driverId, newSalary))
                .apply();
    }

    public void pickUpShipments(TransportId transportId, OfficeId officeId, Set<ShipmentId> shipmentsIds) {
        super
                .appendChange(new PickedUpShipments(transportId, officeId, shipmentsIds))
                .apply();
    }

    public Driver driver() {
        return driver;
    }

    public DeliveryAssistant deliveryAssistant() {
        return deliveryAssistant;
    }

    public Size sizeCapacity() {
        return sizeCapacity;
    }

    public Weight capacityInKg() {
        return capacityInKg;
    }

    public Set<ShipmentId> shipmentIds() {
        return shipmentIds;
    }
}
