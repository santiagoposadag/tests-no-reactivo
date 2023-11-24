package co.com.servientrega.domain.delivery.shipments.values;

import co.com.sofka.domain.generic.ValueObject;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Customer implements ValueObject<Customer.Props> {
    protected String fullName;
    protected String phoneNumber;
    protected String DNI;
    protected String address;

    public interface Props {
        String fullName();

        String phoneNumber();

        String DNI();

        String address();
    }

    @Override
    public Props value() {
        return new Sender.Props() {
            @Override
            public String fullName() {
                return fullName;
            }

            @Override
            public String phoneNumber() {
                return phoneNumber;
            }

            @Override
            public String DNI() {
                return DNI;
            }

            @Override
            public String address() {
                return address;
            }
        };
    }
}
