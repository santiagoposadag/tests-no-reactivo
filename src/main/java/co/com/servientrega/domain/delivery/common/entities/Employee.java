package co.com.servientrega.domain.delivery.common.entities;

import co.com.servientrega.domain.delivery.common.identity.EmployeeId;
import co.com.servientrega.domain.delivery.common.values.*;
import co.com.sofka.domain.generic.Entity;

public abstract class Employee extends Entity<EmployeeId> {
    protected final DNI dni;
    protected final FullName fullName;
    protected Email email;
    protected PhoneNumber phoneNumber;
    protected Salary salary;

    protected Employee(EmployeeId entityId, DNI dni, FullName fullName, Email email, PhoneNumber phoneNumber, Salary salary) {
        super(entityId);
        this.dni = dni;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
    }

    protected Employee(DNI dni, FullName fullName, Email email, PhoneNumber phoneNumber, Salary salary) {
        super(new EmployeeId());
        this.dni = dni;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
    }

    public void changeEmail(Email newEmail) {
        this.email = newEmail;
    }

    public void changePhoneNumber(PhoneNumber newPhoneNumber) {
        this.phoneNumber = newPhoneNumber;
    }

    public void changeSalary(Salary newSalary) {
        this.salary = newSalary;
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
