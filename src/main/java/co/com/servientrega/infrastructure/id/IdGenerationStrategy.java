package co.com.servientrega.infrastructure.id;

public interface IdGenerationStrategy<T> {
    T generate();
}
