package co.com.servientrega.domain.delivery.offices.events;

import co.com.servientrega.domain.delivery.common.values.Size;
import co.com.servientrega.domain.delivery.offices.identity.OfficeId;
import co.com.servientrega.domain.delivery.offices.identity.StoreId;
import co.com.sofka.domain.generic.DomainEvent;

public class StoreCapacityChanged extends DomainEvent {
    private final OfficeId officeId;
    private final StoreId storeId;
    private final Size newSize;


    public StoreCapacityChanged(OfficeId officeId, StoreId storeId, Size newSize) {
        super("Servientrega.offices.StoreCapacityChanged");
        this.officeId = officeId;
        this.storeId = storeId;
        this.newSize = newSize;
    }

    public OfficeId officeId() {
        return officeId;
    }

    public StoreId storeId() {
        return storeId;
    }

    public Size newSize() {
        return newSize;
    }
}
