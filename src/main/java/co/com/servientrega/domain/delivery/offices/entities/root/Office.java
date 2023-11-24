package co.com.servientrega.domain.delivery.offices.entities.root;

import co.com.servientrega.domain.delivery.common.identity.EmployeeId;
import co.com.servientrega.domain.delivery.common.values.*;
import co.com.servientrega.domain.delivery.offices.entities.Manager;
import co.com.servientrega.domain.delivery.offices.entities.Store;
import co.com.servientrega.domain.delivery.offices.events.*;
import co.com.servientrega.domain.delivery.offices.identity.OfficeId;
import co.com.servientrega.domain.delivery.offices.identity.StoreId;
import co.com.servientrega.domain.delivery.shipments.identity.ShipmentId;
import co.com.servientrega.domain.delivery.transports.identity.TransportId;
import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;

import java.util.List;
import java.util.Set;

public class Office extends AggregateEvent<OfficeId> {
    protected Store store;
    protected Manager manager;
    protected Location location;
    protected Date createAt;

    public Office(OfficeId entityId) {
        super(entityId);
        super.subscribe(new OfficeEventListener(this));
    }

    public Office(OfficeId entityId, Location location, Date createdAt) {
        super(entityId);
        super.appendChange(new CreatedOffice(entityId, createdAt, location)).apply();
    }

    public static Office from(OfficeId officeId, List<DomainEvent> events) {
        Office office = new Office(officeId);
        events.forEach(office::applyEvent);
        return office;
    }

    public void addStore(OfficeId officeId, StoreId storeId, Size capacity) {
        super
                .appendChange(new StoreAdded(officeId, storeId, capacity))
                .apply();
    }

    public void addManager(OfficeId officeId, EmployeeId managerId, DNI dni, FullName fullName, Email email, PhoneNumber phoneNumber, Salary salary) {
        super
                .appendChange(new ManagerAdded(officeId, managerId, dni, fullName, email, phoneNumber, salary))
                .apply();
    }

    public void addShipmentToStore(OfficeId officeId, StoreId storeId, ShipmentId shipmentId) {
        super
                .appendChange(new ShipmentAddedToStore(officeId, storeId, shipmentId))
                .apply();
    }

    public void changeManager(OfficeId officeId, EmployeeId employeeId, EmployeeId newManagerId, DNI newManagerDNI, FullName newManagerFullName, Email newManagerEmail, PhoneNumber newManagerPhoneNumber, Salary newManagerSalary) {
        super
                .appendChange(new ManagerChanged(officeId, employeeId, newManagerId, newManagerDNI, newManagerFullName, newManagerEmail, newManagerPhoneNumber, newManagerSalary))
                .apply();
    }

    public void changeManagerEmail(OfficeId officeId, EmployeeId managerId, Email newEmail) {
        super
                .appendChange(new ManagerEmailChanged(officeId, managerId, newEmail))
                .apply();
    }

    public void changeManagerPhoneNumber(OfficeId officeId, EmployeeId managerId, PhoneNumber newPhoneNumber) {
        super
                .appendChange(new ManagerPhoneNumberChanged(officeId, managerId, newPhoneNumber))
                .apply();
    }

    public void changeManagerSales(OfficeId officeId, EmployeeId managerId, Salary newSalary) {
        super
                .appendChange(new ManagerSalaryChanged(officeId, managerId, newSalary))
                .apply();
    }

    public void changeStoreCapacity(OfficeId officeId, StoreId storeId, Size newSize) {
        super
                .appendChange(new StoreCapacityChanged(officeId, storeId, newSize))
                .apply();
    }

    public void startShipmentsDelivery(OfficeId officeId, StoreId storeId, Set<ShipmentId> shipmentsIds, TransportId transportId) {
        super
                .appendChange(new ShipmentsDeliveryStarted(transportId, officeId, storeId, shipmentsIds))
                .apply();
    }

    public Store store() {
        return store;
    }

    public Manager manager() {
        return manager;
    }

    public Location location() {
        return location;
    }

    public Date createAt() {
        return createAt;
    }
}
