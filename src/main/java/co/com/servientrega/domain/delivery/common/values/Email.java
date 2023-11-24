package co.com.servientrega.domain.delivery.common.values;

import co.com.sofka.domain.generic.ValueObject;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Email implements ValueObject<String> {
    private final String value;

    @Override
    public String value() {
        return value;
    }
}
