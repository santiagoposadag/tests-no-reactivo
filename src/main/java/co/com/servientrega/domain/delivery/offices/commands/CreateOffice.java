package co.com.servientrega.domain.delivery.offices.commands;

import co.com.servientrega.domain.delivery.common.values.Date;
import co.com.servientrega.domain.delivery.common.values.Location;
import co.com.sofka.domain.generic.Command;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateOffice extends Command {
    private final Date createdAt;
    private final Location location;

    public Date createdAt() {
        return createdAt;
    }

    public Location location() {
        return location;
    }
}
