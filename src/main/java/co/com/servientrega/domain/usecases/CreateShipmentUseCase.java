package co.com.servientrega.domain.usecases;

import co.com.servientrega.domain.delivery.offices.entities.root.Office;
import co.com.servientrega.domain.delivery.offices.identity.OfficeId;
import co.com.servientrega.domain.delivery.shipments.commands.CreateShipment;
import co.com.servientrega.domain.delivery.shipments.entities.root.Shipment;
import co.com.servientrega.domain.delivery.shipments.identity.ShipmentId;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.domain.generic.DomainEvent;

import java.util.ArrayList;
import java.util.List;

public class CreateShipmentUseCase extends UseCase<RequestCommand<CreateShipment>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<CreateShipment> createShipmentRequestCommand) {
        CreateShipment command = createShipmentRequestCommand.getCommand();
        Shipment shipment = new Shipment(new ShipmentId(), command.sentAt(), null);
        OfficeId officeId = command.officeId();
        Office office = Office.from(officeId, super.retrieveEvents(officeId.value()));

        office.addShipmentToStore(officeId, office.store().identity(), shipment.identity());

        List<DomainEvent> events = new ArrayList<>();
        events.addAll(shipment.getUncommittedChanges());
        events.addAll(office.getUncommittedChanges());

        super.emit().onResponse(new ResponseEvents(events));
    }
}
