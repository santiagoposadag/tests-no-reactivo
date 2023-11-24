package co.com.servientrega.domain.delivery.offices.events;

import co.com.servientrega.domain.delivery.common.identity.EmployeeId;
import co.com.servientrega.domain.delivery.offices.identity.OfficeId;
import co.com.servientrega.domain.delivery.common.values.Salary;
import co.com.sofka.domain.generic.DomainEvent;

public class ManagerSalaryChanged extends DomainEvent {
    private final OfficeId officeId;
    private final EmployeeId managerId;
    private final Salary newSalary;

    public ManagerSalaryChanged(OfficeId officeId, EmployeeId managerId, Salary newSalary) {
        super("Servientrega.offices.ManagerSalaryChanged");
        this.officeId = officeId;
        this.managerId = managerId;
        this.newSalary = newSalary;
    }

    public OfficeId officeId() {
        return officeId;
    }

    public EmployeeId managerId() {
        return managerId;
    }

    public Salary newSalary() {
        return newSalary;
    }
}
