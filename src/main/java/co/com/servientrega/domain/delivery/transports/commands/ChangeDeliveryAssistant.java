package co.com.servientrega.domain.delivery.transports.commands;

import co.com.servientrega.domain.delivery.common.values.DNI;
import co.com.servientrega.domain.delivery.common.values.Email;
import co.com.servientrega.domain.delivery.common.values.PhoneNumber;
import co.com.servientrega.domain.delivery.common.values.Salary;
import co.com.servientrega.domain.delivery.transports.identity.TransportId;
import co.com.sofka.domain.generic.Command;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class ChangeDeliveryAssistant extends Command {
    private final TransportId transportId;
    private final DNI dni;
    private final Email email;
    private final PhoneNumber phoneNumber;
    private final Salary salary;

    public TransportId transportId() {
        return transportId;
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
