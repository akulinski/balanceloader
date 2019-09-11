package com.akulinski.core.strategies;

import com.akulinski.core.IHost;
import lombok.Builder;

import java.util.List;
import java.util.Optional;

public class SequentialStrategy extends AbstractBalancingStrategy {


    private int currentHostIndex = 0;

    @Builder
    public SequentialStrategy(List<IHost> hosts) {
        super(hosts);
    }


    public Optional<IHost> pickHost() {

        checkForReset();

        try {
            return getCurrentHostAndIncrementState();
        } catch (IndexOutOfBoundsException ex) {
            return Optional.empty();
        }
    }

    @Override
    public LoadBalancingStrategy getType() {
        return LoadBalancingStrategy.SEQUENTIAL;
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
