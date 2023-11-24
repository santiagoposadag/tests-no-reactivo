package co.com.servientrega.domain.delivery.offices.events;

import co.com.servientrega.domain.delivery.common.identity.EmployeeId;
import co.com.servientrega.domain.delivery.offices.identity.OfficeId;
import co.com.servientrega.domain.delivery.common.values.PhoneNumber;
import co.com.sofka.domain.generic.DomainEvent;

public class ManagerPhoneNumberChanged extends DomainEvent {
    private final OfficeId officeId;
    private final EmployeeId managerId;
    private final PhoneNumber newPhoneNumber;


    public ManagerPhoneNumberChanged(OfficeId officeId, EmployeeId managerId, PhoneNumber newPhoneNumber) {
        super("Servientrega.offices.ManagerPhoneNumberChanged");
        this.officeId = officeId;
        this.managerId = managerId;
        this.newPhoneNumber = newPhoneNumber;
    }

    public OfficeId officeId() {
        return officeId;
    }

    public EmployeeId managerId() {
        return managerId;
    }

    public PhoneNumber newPhoneNumber() {
        return newPhoneNumber;
    }
}
