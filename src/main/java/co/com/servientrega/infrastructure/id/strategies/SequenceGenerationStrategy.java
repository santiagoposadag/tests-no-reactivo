package co.com.servientrega.infrastructure.id.strategies;

import co.com.servientrega.infrastructure.id.IdGenerationStrategy;

public class SequenceGenerationStrategy implements IdGenerationStrategy<Long> {
    private static Long counter = 0L;
    @Override
    public Long generate() {
        Long id = counter;
        SequenceGenerationStrategy.incrementCounter();
        return id;
    }

    private static void incrementCounter() {
        counter++;
    }
}
