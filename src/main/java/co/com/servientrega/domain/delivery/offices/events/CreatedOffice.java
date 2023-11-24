package co.com.servientrega.domain.delivery.offices.events;

import co.com.servientrega.domain.delivery.common.values.Date;
import co.com.servientrega.domain.delivery.common.values.Location;
import co.com.servientrega.domain.delivery.offices.identity.OfficeId;
import co.com.sofka.domain.generic.DomainEvent;

public class CreatedOffice extends DomainEvent {
    private final OfficeId officeId;
    private final Date createdAt;
    private final Location location;

    public CreatedOffice(OfficeId officeId, Date createdAt, Location location) {
        super("Servientrega.offices.CreatedOffice");
        this.officeId = officeId;
        this.createdAt = createdAt;
        this.location = location;
    }

    public OfficeId officeId() {
        return officeId;
    }

    public Date createdAt() {
        return createdAt;
    }

    public Location location() {
        return location;
    }
}
