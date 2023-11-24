package co.com.servientrega.domain.delivery.transports.events;

import co.com.servientrega.domain.delivery.common.identity.EmployeeId;
import co.com.servientrega.domain.delivery.common.values.PhoneNumber;
import co.com.servientrega.domain.delivery.transports.identity.TransportId;
import co.com.sofka.domain.generic.DomainEvent;

public class DriverPhoneNumberChanged extends DomainEvent {
    private final TransportId transportId;
    private final EmployeeId driverId;
    private final PhoneNumber newPhoneNumber;


    public DriverPhoneNumberChanged(TransportId transportId, EmployeeId driverId, PhoneNumber newPhoneNumber) {
        super("Servientrega.offices.DriverPhoneNumberChanged");
        this.transportId = transportId;
        this.driverId = driverId;
        this.newPhoneNumber = newPhoneNumber;
    }

    public TransportId transportId() {
        return transportId;
    }

    public EmployeeId driverId() {
        return driverId;
    }

    public PhoneNumber newPhoneNumber() {
        return newPhoneNumber;
    }
}
