package com.akulinski;

import lombok.Builder;

import java.util.List;
import java.util.Optional;

public class SequentialStrategy implements IBalancingStrategy {

    private List<IHost> hosts;

    private int currentHostIndex = 0;

    @Builder
    public SequentialStrategy(List<IHost> hosts) {
        this.hosts = hosts;
    }

    public Optional<IHost> pickHost() {

        checkForReset();

        try {
            return getCurrentHostAndIncrementState();
        } catch (IndexOutOfBoundsException ex) {
            return Optional.empty();
        }
    }

    private void checkForReset() {
        if (currentHostIndex == hosts.size()) {
            currentHostIndex = 0;
        }
    }

    private Optional<IHost> getCurrentHostAndIncrementState() {
        final IHost currentHost = hosts.get(currentHostIndex);
        currentHostIndex++;
        return Optional.of(currentHost);
    }
}
