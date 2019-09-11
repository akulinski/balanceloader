package com.akulinski;

import java.util.Optional;

public interface IBalancingStrategy {
    Optional<IHost> pickHost();
}
