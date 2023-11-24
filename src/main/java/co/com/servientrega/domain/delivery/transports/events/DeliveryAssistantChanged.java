package co.com.servientrega.domain.delivery.transports.events;

import co.com.servientrega.domain.delivery.common.identity.EmployeeId;
import co.com.servientrega.domain.delivery.common.values.*;
import co.com.servientrega.domain.delivery.transports.identity.TransportId;
import co.com.sofka.domain.generic.DomainEvent;

public class DeliveryAssistantChanged extends DomainEvent {
    private final TransportId transportId;
    private final EmployeeId previousDeliveryAssistantId;
    private final EmployeeId newDeliveryAssistantId;
    private final DNI newDeliveryAssistantDNI;
    private final FullName newDeliveryAssistantFullName;
    private final Email newDeliveryAssistantEmail;
    private final PhoneNumber newDeliveryAssistantPhoneNumber;
    private final Salary newDeliveryAssistantSalary;

    public DeliveryAssistantChanged(TransportId transportId, EmployeeId previousDeliveryAssistantId, EmployeeId newManagerId, DNI newDeliveryAssistantDNI, FullName newDeliveryAssistantFullName, Email newDeliveryAssistantEmail, PhoneNumber newDeliveryAssistantPhoneNumber, Salary newDeliveryAssistantSalary) {
        super("Servientrega.offices.DeliveryAssistantChanged");
        this.transportId = transportId;
        this.previousDeliveryAssistantId = previousDeliveryAssistantId;
        this.newDeliveryAssistantId = newManagerId;
        this.newDeliveryAssistantDNI = newDeliveryAssistantDNI;
        this.newDeliveryAssistantFullName = newDeliveryAssistantFullName;
        this.newDeliveryAssistantEmail = newDeliveryAssistantEmail;
        this.newDeliveryAssistantPhoneNumber = newDeliveryAssistantPhoneNumber;
        this.newDeliveryAssistantSalary = newDeliveryAssistantSalary;
    }

    public TransportId transportId() {
        return transportId;
    }

    public EmployeeId previousDeliveryAssistantId() {
        return previousDeliveryAssistantId;
    }

    public EmployeeId newDeliveryAssistantId() {
        return newDeliveryAssistantId;
    }

    public DNI newDeliveryAssistantDNI() {
        return newDeliveryAssistantDNI;
    }

    public FullName newDeliveryAssistantFullName() {
        return newDeliveryAssistantFullName;
    }

    public Email newDeliveryAssistantEmail() {
        return newDeliveryAssistantEmail;
    }

    public PhoneNumber newDeliveryAssistantPhoneNumber() {
        return newDeliveryAssistantPhoneNumber;
    }

    public Salary newDeliveryAssistantSalary() {
        return newDeliveryAssistantSalary;
    }
}
