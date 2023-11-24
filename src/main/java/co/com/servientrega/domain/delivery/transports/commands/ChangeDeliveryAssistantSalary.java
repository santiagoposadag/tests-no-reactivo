package co.com.servientrega.domain.delivery.transports.commands;

import co.com.servientrega.domain.delivery.common.identity.EmployeeId;
import co.com.servientrega.domain.delivery.shipments.values.Money;
import co.com.servientrega.domain.delivery.transports.identity.TransportId;
import co.com.sofka.domain.generic.Command;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ChangeDeliveryAssistantSalary extends Command {
    private final TransportId transportId;
    private final EmployeeId employeeId;
    private final Money newSalary;

    public TransportId transportId() {
        return transportId;
    }

    public EmployeeId employeeId() {
        return employeeId;
    }

    public Money newSalary() {
        return newSalary;
    }
}
