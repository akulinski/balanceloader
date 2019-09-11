package com.akulinski.core.strategies;

import com.akulinski.core.IHost;

import java.util.List;

/**
 * Abstraction layer for all strategies
 * since all use list of hosts
 */
public abstract class AbstractBalancingStrategy implements IBalancingStrategy {
    protected List<IHost> hosts;

    public AbstractBalancingStrategy(List<IHost> hosts) {
        this.hosts = hosts;
    }
}
