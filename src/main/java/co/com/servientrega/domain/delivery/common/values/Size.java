package co.com.servientrega.domain.delivery.common.values;

import co.com.sofka.domain.generic.ValueObject;
import lombok.AllArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
public class Size implements ValueObject<Size.Props> {
    private final Double height;
    private final Double width;
    private final Double length;

    public interface Props {
        Double height();
        Double width();
        Double length();
    }

    @Override
    public Props value() {
        return new Props() {
            @Override
            public Double height() {
                return height;
            }

            @Override
            public Double width() {
                return width;
            }

            @Override
            public Double length() {
                return length;
            }
        };
    }

    public Double calculateVolume() {
        return this.height * this.width * this.length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Size size = (Size) o;
        return height.equals(size.height) && width.equals(size.width) && length.equals(size.length);
    }

    @Override
    public int hashCode() {
        return Objects.hash(height, width, length);
    }
}
