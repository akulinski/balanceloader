package com.akulinski.core.strategies;

import com.akulinski.core.IHost;

import java.util.Optional;


/**
 * Interface defining actions for all
 * strategies
 */
public interface IBalancingStrategy {

    /**
     * Picks hosts according to
     * Implemented algorithm
     *
     * @return Host that can be nullable
     */
    Optional<IHost> pickHost();

    /**
     * @return type of used strategy
     */
    LoadBalancingStrategy getType();
}
