package co.com.servientrega.domain.delivery.transports.events;

import co.com.servientrega.domain.delivery.common.identity.EmployeeId;
import co.com.servientrega.domain.delivery.common.values.Email;
import co.com.servientrega.domain.delivery.offices.identity.OfficeId;
import co.com.servientrega.domain.delivery.transports.identity.TransportId;
import co.com.sofka.domain.generic.DomainEvent;

public class DriverEmailChanged extends DomainEvent {
    private final TransportId transportId;
    private final EmployeeId driverId;
    private final Email newEmail;


    public DriverEmailChanged(TransportId transportId, EmployeeId driverId, Email newEmail) {
        super("Servientrega.offices.DriverEmailChanged");
        this.transportId = transportId;
        this.driverId = driverId;
        this.newEmail = newEmail;
    }

    public TransportId transportId() {
        return transportId;
    }

    public EmployeeId driverId() {
        return driverId;
    }

    public Email newEmail() {
        return newEmail;
    }
}
