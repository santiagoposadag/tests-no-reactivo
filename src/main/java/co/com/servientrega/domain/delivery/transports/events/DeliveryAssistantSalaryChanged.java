
package co.com.servientrega.domain.delivery.transports.events;

import co.com.servientrega.domain.delivery.common.identity.EmployeeId;
import co.com.servientrega.domain.delivery.common.values.Salary;
import co.com.servientrega.domain.delivery.transports.identity.TransportId;
import co.com.sofka.domain.generic.DomainEvent;

public class DeliveryAssistantSalaryChanged extends DomainEvent {
    private final TransportId transportId;
    private final EmployeeId deliveryAssistantId;
    private final Salary newSalary;

    public DeliveryAssistantSalaryChanged(TransportId transportId, EmployeeId deliveryAssistantId, Salary newSalary) {
        super("Servientrega.offices.deliveryAssistantSalaryChanged");
        this.transportId = transportId;
        this.deliveryAssistantId = deliveryAssistantId;
        this.newSalary = newSalary;
    }

    public TransportId transportId() {
        return transportId;
    }

    public EmployeeId deliveryAssistantId() {
        return deliveryAssistantId;
    }

    public Salary newSalary() {
        return newSalary;
    }
}
