package co.com.servientrega.domain.delivery.offices.entities;

import co.com.servientrega.domain.delivery.common.entities.Employee;
import co.com.servientrega.domain.delivery.common.identity.EmployeeId;
import co.com.servientrega.domain.delivery.common.values.*;

public class Manager extends Employee {
    public Manager(EmployeeId entityId, DNI dni, FullName fullName, Email email, PhoneNumber phoneNumber, Salary salary) {
        super(entityId, dni, fullName, email, phoneNumber, salary);
    }

    public Manager(DNI dni, FullName fullName, Email email, PhoneNumber phoneNumber, Salary salary) {
        super(dni, fullName, email, phoneNumber, salary);
    }
}
