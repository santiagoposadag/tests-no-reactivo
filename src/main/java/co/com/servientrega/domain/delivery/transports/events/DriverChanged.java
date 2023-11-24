package co.com.servientrega.domain.delivery.transports.events;

import co.com.servientrega.domain.delivery.common.identity.EmployeeId;
import co.com.servientrega.domain.delivery.common.values.*;
import co.com.servientrega.domain.delivery.transports.identity.TransportId;
import co.com.sofka.domain.generic.DomainEvent;

public class DriverChanged extends DomainEvent {
    private final TransportId transportId;
    private final EmployeeId previousDriverId;
    private final EmployeeId newDriverId;
    private final DNI newDriverDNI;
    private final FullName newDriverFullName;
    private final Email newDriverEmail;
    private final PhoneNumber newDriverPhoneNumber;
    private final Salary newDriverSalary;

    public DriverChanged(TransportId transportId, EmployeeId previousDriverId, EmployeeId newManagerId, DNI newDriverDNI, FullName newDriverFullName, Email newDriverEmail, PhoneNumber newDriverPhoneNumber, Salary newDriverSalary) {
        super("Servientrega.offices.DriverChanged");
        this.transportId = transportId;
        this.previousDriverId = previousDriverId;
        this.newDriverId = newManagerId;
        this.newDriverDNI = newDriverDNI;
        this.newDriverFullName = newDriverFullName;
        this.newDriverEmail = newDriverEmail;
        this.newDriverPhoneNumber = newDriverPhoneNumber;
        this.newDriverSalary = newDriverSalary;
    }

    public TransportId transportId() {
        return transportId;
    }

    public EmployeeId previousDriverId() {
        return previousDriverId;
    }

    public EmployeeId newDriverId() {
        return newDriverId;
    }

    public DNI newDriverDNI() {
        return newDriverDNI;
    }

    public FullName newDriverFullName() {
        return newDriverFullName;
    }

    public Email newDriverEmail() {
        return newDriverEmail;
    }

    public PhoneNumber newDriverPhoneNumber() {
        return newDriverPhoneNumber;
    }

    public Salary newDriverSalary() {
        return newDriverSalary;
    }
}
