package com.akulinski.core;

import com.akulinski.Request;
import com.akulinski.core.strategies.IBalancingStrategy;
import com.akulinski.core.strategies.LoadBalancingStrategy;
import lombok.Getter;

import java.util.List;

/**
 * Passes requests to hosts based on
 * balancing strategy
 */
public class LoadBalancer implements ILoadBalancer {

    @Getter
    private IBalancingStrategy balancingStrategy;

    public LoadBalancer(List<IHost> hosts, LoadBalancingStrategy loadBalancingStrategy) {
        BalancingStrategyFactory factory = new BalancingStrategyFactory(hosts);

        balancingStrategy = factory.balancingStrategy(loadBalancingStrategy);
    }

    public void handleRequest(Request request) {
        balancingStrategy.pickHost()
                .orElseThrow(() -> new IllegalStateException("No Host to execute request"))
                .handleRequest(request);
    }
}
