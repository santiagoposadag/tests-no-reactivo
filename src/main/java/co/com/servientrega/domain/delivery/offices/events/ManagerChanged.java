package co.com.servientrega.domain.delivery.offices.events;

import co.com.servientrega.domain.delivery.common.identity.EmployeeId;
import co.com.servientrega.domain.delivery.common.values.*;
import co.com.servientrega.domain.delivery.offices.identity.OfficeId;
import co.com.sofka.domain.generic.DomainEvent;

public class ManagerChanged extends DomainEvent {
    private final OfficeId officeId;
    private final EmployeeId previousManagerId;
    private final EmployeeId newManagerId;
    private final DNI newManagerDNI;
    private final FullName newManagerFullName;
    private final Email newManagerEmail;
    private final PhoneNumber newManagerPhoneNumber;
    private final Salary newManagerSalary;

    public ManagerChanged(OfficeId officeId, EmployeeId previousManagerId, EmployeeId newManagerId, DNI newManagerDNI, FullName newManagerFullName, Email newManagerEmail, PhoneNumber newManagerPhoneNumber, Salary newManagerSalary) {
        super("Servientrega.offices.ManagerChanged");
        this.officeId = officeId;
        this.previousManagerId = previousManagerId;
        this.newManagerId = newManagerId;
        this.newManagerDNI = newManagerDNI;
        this.newManagerFullName = newManagerFullName;
        this.newManagerEmail = newManagerEmail;
        this.newManagerPhoneNumber = newManagerPhoneNumber;
        this.newManagerSalary = newManagerSalary;
    }

    public OfficeId officeId() {
        return officeId;
    }

    public EmployeeId previousManagerId() {
        return previousManagerId;
    }

    public EmployeeId newManagerId() {
        return newManagerId;
    }

    public DNI newManagerDNI() {
        return newManagerDNI;
    }

    public FullName newManagerFullName() {
        return newManagerFullName;
    }

    public Email newManagerEmail() {
        return newManagerEmail;
    }

    public PhoneNumber newManagerPhoneNumber() {
        return newManagerPhoneNumber;
    }

    public Salary newManagerSalary() {
        return newManagerSalary;
    }
}
