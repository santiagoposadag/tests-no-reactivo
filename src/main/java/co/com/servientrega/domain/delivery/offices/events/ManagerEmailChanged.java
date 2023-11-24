package co.com.servientrega.domain.delivery.offices.events;

import co.com.servientrega.domain.delivery.common.identity.EmployeeId;
import co.com.servientrega.domain.delivery.offices.identity.OfficeId;
import co.com.servientrega.domain.delivery.common.values.Email;
import co.com.sofka.domain.generic.DomainEvent;

public class ManagerEmailChanged extends DomainEvent {
    private final OfficeId officeId;
    private final EmployeeId managerId;
    private final Email newEmail;


    public ManagerEmailChanged(OfficeId officeId, EmployeeId managerId, Email newEmail) {
        super("Servientrega.offices.ManagerEmailChanged");
        this.officeId = officeId;
        this.managerId = managerId;
        this.newEmail = newEmail;
    }

    public OfficeId officeId() {
        return officeId;
    }

    public EmployeeId managerId() {
        return managerId;
    }

    public Email newEmail() {
        return newEmail;
    }
}
