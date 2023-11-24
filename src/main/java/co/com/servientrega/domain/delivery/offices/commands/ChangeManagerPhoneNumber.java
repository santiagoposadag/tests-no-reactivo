package co.com.servientrega.domain.delivery.offices.commands;

import co.com.servientrega.domain.delivery.common.identity.EmployeeId;
import co.com.servientrega.domain.delivery.offices.identity.OfficeId;
import co.com.sofka.domain.generic.Command;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ChangeManagerPhoneNumber extends Command {
    private final OfficeId officeId;
    private final EmployeeId employeeId;
    private final String newPhoneNumber;

    public OfficeId officeId() {
        return officeId;
    }

    public EmployeeId employeeId() {
        return employeeId;
    }

    public String newPhoneNumber() {
        return newPhoneNumber;
    }
}
