package co.com.servientrega.domain.delivery.offices.commands;

import co.com.servientrega.domain.delivery.offices.identity.OfficeId;
import co.com.servientrega.domain.delivery.offices.identity.StoreId;
import co.com.servientrega.domain.delivery.transports.identity.TransportId;
import co.com.sofka.domain.generic.Command;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StartShipmentsDelivery extends Command {
    private final TransportId transportId;
    private final OfficeId officeId;
    private final StoreId storeId;

    public TransportId transportId() {
        return transportId;
    }

    public OfficeId officeId() {
        return officeId;
    }

    public StoreId storeId() {
        return storeId;
    }
}
