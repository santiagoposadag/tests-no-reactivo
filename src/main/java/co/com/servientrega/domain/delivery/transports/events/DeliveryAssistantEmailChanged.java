package co.com.servientrega.domain.delivery.transports.events;

import co.com.servientrega.domain.delivery.common.identity.EmployeeId;
import co.com.servientrega.domain.delivery.common.values.Email;
import co.com.servientrega.domain.delivery.transports.identity.TransportId;
import co.com.sofka.domain.generic.DomainEvent;

public class DeliveryAssistantEmailChanged extends DomainEvent {
    private final TransportId transportId;
    private final EmployeeId deliveryAssistantId;
    private final Email newEmail;


    public DeliveryAssistantEmailChanged(TransportId transportId, EmployeeId DeliveryAssistantId, Email newEmail) {
        super("Servientrega.offices.DeliveryAssistantEmailChanged");
        this.transportId = transportId;
        this.deliveryAssistantId = DeliveryAssistantId;
        this.newEmail = newEmail;
    }

    public TransportId transportId() {
        return transportId;
    }

    public EmployeeId DeliveryAssistantId() {
        return deliveryAssistantId;
    }

    public Email newEmail() {
        return newEmail;
    }
}
