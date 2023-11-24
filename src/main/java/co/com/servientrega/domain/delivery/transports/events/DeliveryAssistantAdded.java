package co.com.servientrega.domain.delivery.transports.events;

import co.com.servientrega.domain.delivery.common.values.*;
import co.com.servientrega.domain.delivery.transports.identity.TransportId;
import co.com.sofka.domain.generic.DomainEvent;

public class DeliveryAssistantAdded extends DomainEvent {
    private final TransportId transportId;
    private final DNI dni;
    private final FullName fullName;
    private final Email email;
    private final PhoneNumber phoneNumber;
    private final Salary salary;

    public DeliveryAssistantAdded(TransportId transportId, DNI dni, FullName fullName, Email email, PhoneNumber phoneNumber, Salary salary) {
        super("Servientrega.transports.DeliveryAssistantAdded");
        this.transportId = transportId;
        this.dni = dni;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
    }

    public TransportId transportId() {
        return transportId;
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
