package co.com.servientrega.infrastructure.id.strategies;

import co.com.servientrega.infrastructure.id.IdGenerationStrategy;

import java.util.UUID;

public class UUIDGenerationStrategy implements IdGenerationStrategy<String> {
    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
