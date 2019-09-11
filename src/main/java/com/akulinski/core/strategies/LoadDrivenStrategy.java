package com.akulinski.core.strategies;

import com.akulinski.core.IHost;
import lombok.Builder;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Strategy that takes first host with
 * load lower than 0.75 or picks smallest
 * if all hosts are above 0.75
 */
public class LoadDrivenStrategy extends AbstractBalancingStrategy {

    @Builder
    public LoadDrivenStrategy(List<IHost> hosts) {
        super(hosts);
    }


    /**
     * If all hosts have load above
     * 0.75 picks smallest
     *
     * @return nullable
     */
    @Override
    public Optional<IHost> pickHost() {

        final Optional<IHost> hostsUnder = checkForHostsWithLowLoad();

        if (hostsUnder.isPresent()) {
            return hostsUnder;
        }

        return hosts.stream().min(Comparator.comparing(IHost::getLoad));
    }

    @Override
    public LoadBalancingStrategy getType() {
        return LoadBalancingStrategy.LOAD_DRIVEN;
    }


    /**
     * Filters hosts looking for
     * that, that have load smaller
     * than 0.75
     *
     * @return
     */
    private Optional<IHost> checkForHostsWithLowLoad() {
        final var hostsUnder = hosts.stream()
                .filter(iHost -> iHost.getLoad() < 0.75)
                .collect(Collectors.toList());

        if (hostsUnder.size() > 0) {
            return Optional.ofNullable(hostsUnder.get(0));
        }

        return Optional.empty();
    }
}
