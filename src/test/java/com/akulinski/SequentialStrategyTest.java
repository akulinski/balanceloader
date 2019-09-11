package com.akulinski;


import com.akulinski.core.Host;
import com.akulinski.core.strategies.IBalancingStrategy;
import com.akulinski.core.IHost;
import com.akulinski.core.strategies.SequentialStrategy;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class SequentialStrategyTest {

    private IBalancingStrategy balancingStrategy;
    private Random random;
    private List<IHost> hosts;


    @Before
    public void init() {
        random = new Random();
    }

    @Test
    public void pickFirstHost() {
        setUpHosts(10);

        balancingStrategy = SequentialStrategy
                .builder()
                .hosts(hosts)
                .build();

        final var host = balancingStrategy.pickHost();
        assertEquals(host.get(), hosts.get(0));
    }

    private void setUpHosts(int i) {

        hosts = new LinkedList<>();

        Stream.generate(() -> new Host(random.nextFloat()))
                .limit(i).forEach(hosts::add);
    }


    @Test
    public void pickThreeHosts() {
        setUpHosts(10);

        balancingStrategy = SequentialStrategy
                .builder()
                .hosts(hosts)
                .build();

        balancingStrategy.pickHost();
        balancingStrategy.pickHost();
        var host = balancingStrategy.pickHost();
        assertEquals(host.get(), hosts.get(2));
    }


    @Test
    public void pickEmptyList() {
        balancingStrategy = SequentialStrategy
                .builder()
                .hosts(new LinkedList<>())
                .build();

        final var host = balancingStrategy.pickHost();
        assertTrue(host.isEmpty());
    }

    @Test
    public void pickMoreHostsThanInList() {
        setUpHosts(2);

        balancingStrategy = SequentialStrategy
                .builder()
                .hosts(hosts)
                .build();

        balancingStrategy.pickHost();
        balancingStrategy.pickHost();

        var host = balancingStrategy.pickHost();

        assertEquals(host.get(), hosts.get(0));
    }

}
