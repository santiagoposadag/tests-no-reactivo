package co.com.servientrega.domain.delivery.transports.entities;

import co.com.servientrega.domain.delivery.common.entities.Employee;
import co.com.servientrega.domain.delivery.common.identity.EmployeeId;
import co.com.servientrega.domain.delivery.common.values.*;

public class DeliveryAssistant extends Employee {
    public DeliveryAssistant(EmployeeId entityId, DNI dni, FullName fullName, Email email, PhoneNumber phoneNumber, Salary salary) {
        super(entityId, dni, fullName, email, phoneNumber, salary);
    }

    public DeliveryAssistant(DNI dni, FullName fullName, Email email, PhoneNumber phoneNumber, Salary salary) {
        super(new EmployeeId(), dni, fullName, email, phoneNumber, salary);
    }
}
