package com.akulinski.core.strategies;

import com.akulinski.core.IHost;

import java.util.List;

public abstract class AbstractBalancingStrategy implements IBalancingStrategy {
    protected List<IHost> hosts;

    public AbstractBalancingStrategy(List<IHost> hosts) {
        this.hosts = hosts;
    }
}
