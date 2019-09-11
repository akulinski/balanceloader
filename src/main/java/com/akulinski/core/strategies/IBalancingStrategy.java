package com.akulinski.core.strategies;

import com.akulinski.core.IHost;

import java.util.Optional;

public interface IBalancingStrategy {
    Optional<IHost> pickHost();
    LoadBalancingStrategy getType();
}
