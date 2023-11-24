package co.com.servientrega.domain.delivery.common.values;

import co.com.sofka.domain.generic.ValueObject;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public class Salary implements ValueObject<BigDecimal> {
    private final BigDecimal value;

    @Override
    public BigDecimal value() {
        return value;
    }
}
