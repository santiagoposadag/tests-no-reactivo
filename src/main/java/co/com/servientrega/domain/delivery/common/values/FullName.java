package co.com.servientrega.domain.delivery.common.values;

import co.com.sofka.domain.generic.ValueObject;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FullName implements ValueObject<FullName.Props> {
    private final String name;
    private final String lastName;

    public interface Props {
        String name();
        String lastName();
    }

    @Override
    public Props value() {
        return new Props() {
            @Override
            public String name() {
                return name;
            }

            @Override
            public String lastName() {
                return lastName;
            }
        };
    }
}
