package com.akulinski;

import java.util.List;

public abstract class AbstractBalancingStrategy implements IBalancingStrategy {
    protected List<IHost> hosts;

    public AbstractBalancingStrategy(List<IHost> hosts) {
        this.hosts = hosts;
    }
}
