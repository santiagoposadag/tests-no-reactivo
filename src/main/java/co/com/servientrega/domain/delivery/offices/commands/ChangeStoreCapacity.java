package co.com.servientrega.domain.delivery.offices.commands;

import co.com.servientrega.domain.delivery.common.values.Size;
import co.com.servientrega.domain.delivery.offices.identity.OfficeId;
import co.com.sofka.domain.generic.Command;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ChangeStoreCapacity extends Command {
    private final OfficeId officeId;
    private final Size newSize;

    public OfficeId officeId() {
        return officeId;
    }

    public Size newSize() {
        return newSize;
    }
}
