package co.com.servientrega.domain.delivery.common.values;

import co.com.sofka.domain.generic.ValueObject;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PhoneNumber implements ValueObject<String> {
    private final String value;

    @Override
    public String value() {
        return null;
    }
}
