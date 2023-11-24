package co.com.servientrega.domain.delivery.common.values;

import co.com.sofka.domain.generic.ValueObject;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Location implements ValueObject<Location.Props> {
    private final String state;
    private final String city;
    private final String address;

    public interface Props {
        String state();
        String city();
        String address();
    }

    @Override
    public Props value() {
        return new Props() {
            @Override
            public String state() {
                return state;
            }

            @Override
            public String city() {
                return city;
            }

            @Override
            public String address() {
                return address;
            }
        };
    }
}
