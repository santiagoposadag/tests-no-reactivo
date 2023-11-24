package co.com.servientrega.domain.delivery.transports.events;

import co.com.servientrega.domain.delivery.common.identity.EmployeeId;
import co.com.servientrega.domain.delivery.common.values.PhoneNumber;
import co.com.servientrega.domain.delivery.transports.identity.TransportId;
import co.com.sofka.domain.generic.DomainEvent;

public class DeliveryAssistantPhoneNumberChanged extends DomainEvent {
    private final TransportId transportId;
    private final EmployeeId deliveryAssistantId;
    private final PhoneNumber newPhoneNumber;


    public DeliveryAssistantPhoneNumberChanged(TransportId transportId, EmployeeId deliveryAssistantId, PhoneNumber newPhoneNumber) {
        super("Servientrega.offices.DeliveryAssistantPhoneNumberChanged");
        this.transportId = transportId;
        this.deliveryAssistantId = deliveryAssistantId;
        this.newPhoneNumber = newPhoneNumber;
    }

    public TransportId transportId() {
        return transportId;
    }

    public EmployeeId deliveryAssistantId() {
        return deliveryAssistantId;
    }

    public PhoneNumber newPhoneNumber() {
        return newPhoneNumber;
    }
}
