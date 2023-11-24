package co.com.servientrega.domain.delivery.common.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.regex.Pattern;

public class DNI implements ValueObject<String> {
    private final String value;

    public DNI(String value) {
        DNI.validateString(value);
        this.value = value.trim();
    }

    private static void validateString(String value) {
        boolean matches = Pattern.matches("[a-zA-Z]", value);
        if(matches) {
            throw new IllegalArgumentException("Invalid DNI value: " + value);
        }
    }

    @Override
    public String value() {
        return value;
    }
}
