package co.com.servientrega.domain.delivery.common.values;

import co.com.sofka.domain.generic.ValueObject;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor()
public class Date implements ValueObject<LocalDateTime> {
    private LocalDateTime value;

    public Date() {
        this.value = LocalDateTime.now();
    }

    public static Date of(LocalDateTime value) {
        return new Date(value);
    }

    public static Date now() {
        return Date.of(LocalDateTime.now());
    }

    @Override
    public LocalDateTime value() {
        return value;
    }
}
