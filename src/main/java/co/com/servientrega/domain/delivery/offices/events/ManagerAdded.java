package co.com.servientrega.domain.delivery.offices.events;

import co.com.servientrega.domain.delivery.common.identity.EmployeeId;
import co.com.servientrega.domain.delivery.common.values.*;
import co.com.servientrega.domain.delivery.offices.identity.OfficeId;
import co.com.sofka.domain.generic.DomainEvent;

public class ManagerAdded extends DomainEvent {
    private final OfficeId officeId;
    private final EmployeeId managerId;
    private final DNI dni;
    private final FullName fullName;
    private final Email email;
    private final PhoneNumber phoneNumber;
    private final Salary salary;


    public ManagerAdded(OfficeId officeId, EmployeeId managerId, DNI dni, FullName fullName, Email email, PhoneNumber phoneNumber, Salary salary) {
        super("Servientrega.offices.ManagerAdded");
        this.officeId = officeId;
        this.managerId = managerId;
        this.dni = dni;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
    }

    public OfficeId officeId() {
        return officeId;
    }

    public EmployeeId managerId() {
        return managerId;
    }

    public DNI dni() {
        return dni;
    }

    public FullName fullName() {
        return fullName;
    }

    public Email email() {
        return email;
    }

    public PhoneNumber phoneNumber() {
        return phoneNumber;
    }

    public Salary salary() {
        return salary;
    }
}
