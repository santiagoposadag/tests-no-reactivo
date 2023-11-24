package co.com.servientrega.domain.delivery.common.identity;

import co.com.servientrega.infrastructure.id.IdGenerationStrategy;
import co.com.servientrega.infrastructure.id.strategies.UUIDGenerationStrategy;
import co.com.sofka.domain.generic.Identity;

import java.util.Objects;

public class EmployeeId extends Identity {
    private static final IdGenerationStrategy<String> idGenerationStrategy = new UUIDGenerationStrategy();
    private final String value;

    public EmployeeId() {
        this.value = idGenerationStrategy.generate();
    }

    public EmployeeId(String value) {
        super(value);
        this.value = value;
    }

    @Override
    public String value() {
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EmployeeId that = (EmployeeId) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), value);
    }
}
