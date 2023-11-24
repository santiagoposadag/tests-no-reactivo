package co.com.servientrega.domain.delivery.common.values;

import co.com.sofka.domain.generic.ValueObject;
import lombok.AllArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
public class Weight implements ValueObject<Double> {
    private final Double value;

    @Override
    public Double value() {
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weight weight = (Weight) o;
        return value.equals(weight.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
