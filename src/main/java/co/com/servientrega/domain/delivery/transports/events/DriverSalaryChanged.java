package co.com.servientrega.domain.delivery.transports.events;

import co.com.servientrega.domain.delivery.common.identity.EmployeeId;
import co.com.servientrega.domain.delivery.common.values.Salary;
import co.com.servientrega.domain.delivery.transports.identity.TransportId;
import co.com.sofka.domain.generic.DomainEvent;

public class DriverSalaryChanged extends DomainEvent {
    private final TransportId transportId;
    private final EmployeeId driverId;
    private final Salary newSalary;

    public DriverSalaryChanged(TransportId transportId, EmployeeId driverId, Salary newSalary) {
        super("Servientrega.offices.DriverSalaryChanged");
        this.transportId = transportId;
        this.driverId = driverId;
        this.newSalary = newSalary;
    }

    public TransportId transportId() {
        return transportId;
    }

    public EmployeeId driverId() {
        return driverId;
    }

    public Salary newSalary() {
        return newSalary;
    }
}
