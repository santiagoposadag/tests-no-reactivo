package co.com.servientrega.domain.delivery.transports.commands;

import co.com.servientrega.domain.delivery.common.identity.EmployeeId;
import co.com.servientrega.domain.delivery.transports.identity.TransportId;
import co.com.sofka.domain.generic.Command;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ChangeDriverPhoneNumber extends Command {
    private final TransportId transportId;
    private final EmployeeId employeeId;
    private final String newPhoneNumber;

    public TransportId transportId() {
        return transportId;
    }

    public EmployeeId employeeId() {
        return employeeId;
    }

    public String newPhoneNumber() {
        return newPhoneNumber;
    }
}
