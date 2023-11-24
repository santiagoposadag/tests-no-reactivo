package co.com.servientrega.domain.usecases;

import co.com.servientrega.domain.delivery.offices.commands.StartShipmentsDelivery;
import co.com.servientrega.domain.delivery.offices.entities.root.Office;
import co.com.servientrega.domain.delivery.offices.identity.OfficeId;
import co.com.servientrega.domain.delivery.shipments.identity.ShipmentId;
import co.com.servientrega.domain.delivery.transports.entities.root.Transport;
import co.com.servientrega.domain.delivery.transports.identity.TransportId;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.domain.generic.DomainEvent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StartShipmentsDeliveryUseCase extends UseCase<RequestCommand<StartShipmentsDelivery>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<StartShipmentsDelivery> startShipmentsDeliveryRequestCommand) {
        StartShipmentsDelivery command = startShipmentsDeliveryRequestCommand.getCommand();
        OfficeId officeId = command.officeId();
        Office office = Office.from(officeId, super.retrieveEvents(officeId.value()));
        Set<ShipmentId> shipmentsIds = Set.copyOf(office.store().shipmentsIds());
        TransportId transportId = command.transportId();
        Transport transport = Transport.from(transportId, super.retrieveEvents(transportId.value()));

        office.startShipmentsDelivery(officeId, office.store().identity(), shipmentsIds, transportId);
        transport.pickUpShipments(transportId, officeId, shipmentsIds);
        office.store().empty();

        List<DomainEvent> events = new ArrayList<>();
        events.addAll(office.getUncommittedChanges());
        events.addAll(transport.getUncommittedChanges());
        super.emit().onResponse(new ResponseEvents(events));
    }
}
