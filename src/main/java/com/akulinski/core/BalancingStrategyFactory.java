package com.akulinski.core;

import com.akulinski.core.strategies.IBalancingStrategy;
import com.akulinski.core.strategies.LoadBalancingStrategy;
import com.akulinski.core.strategies.LoadDrivenStrategy;
import com.akulinski.core.strategies.SequentialStrategy;

import java.util.List;

/**
 * Factory of all strategies
 */
public class BalancingStrategyFactory {
    private List<IHost> hosts;

    public BalancingStrategyFactory(List<IHost> hosts) {
        this.hosts = hosts;
    }

    public IBalancingStrategy balancingStrategy(LoadBalancingStrategy loadBalancingStrategy) {

        if (loadBalancingStrategy == LoadBalancingStrategy.SEQUENTIAL) {
            return SequentialStrategy.builder().hosts(hosts).build();
        } else {
            return LoadDrivenStrategy.builder().hosts(hosts).build();
        }
    }
}
