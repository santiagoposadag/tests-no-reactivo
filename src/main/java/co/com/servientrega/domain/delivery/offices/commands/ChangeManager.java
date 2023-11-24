package co.com.servientrega.domain.delivery.offices.commands;

import co.com.servientrega.domain.delivery.common.values.DNI;
import co.com.servientrega.domain.delivery.common.values.Email;
import co.com.servientrega.domain.delivery.common.values.PhoneNumber;
import co.com.servientrega.domain.delivery.common.values.Salary;
import co.com.servientrega.domain.delivery.offices.identity.OfficeId;
import co.com.sofka.domain.generic.Command;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ChangeManager extends Command {
    private final OfficeId officeId;
    private final DNI dni;
    private final Email email;
    private final PhoneNumber phoneNumber;
    private final Salary salary;

    public OfficeId officeId() {
        return officeId;
    }

    public DNI dni() {
        return dni;
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
