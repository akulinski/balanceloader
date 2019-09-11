package com.akulinski;

import lombok.Builder;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LoadDrivenStrategy extends AbstractBalancingStrategy {

    @Builder
    public LoadDrivenStrategy(List<IHost> hosts) {
        super(hosts);
    }

    @Override
    public Optional<IHost> pickHost() {

        final Optional<IHost> hostsUnder = checkForHostsWithLoad();

        if (hostsUnder.isPresent()) {
            return hostsUnder;
        }

        return hosts.stream().min(Comparator.comparing(IHost::getLoad));
    }

    private Optional<IHost> checkForHostsWithLoad() {
        final var hostsUnder = hosts.stream()
                .filter(iHost -> iHost.getLoad() < 0.75)
                .collect(Collectors.toList());

        if (hostsUnder.size() > 0) {
            return Optional.ofNullable(hostsUnder.get(0));
        }

        return Optional.empty();
    }
}
