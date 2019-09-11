package com.akulinski;

import com.akulinski.core.Host;
import com.akulinski.core.strategies.IBalancingStrategy;
import com.akulinski.core.IHost;
import com.akulinski.core.strategies.LoadDrivenStrategy;
import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class LoadDrivenStrategyTest {
    private IBalancingStrategy balancingStrategy;
    private Random random;
    private List<IHost> hosts;

    @Before
    public void setUp() throws Exception {
        random = new Random();
    }

    @Test
    public void pickHost() {
        setUpHosts(100, 0, 1);

        balancingStrategy = LoadDrivenStrategy
                .builder()
                .hosts(hosts)
                .build();
        final var host = balancingStrategy.pickHost().get();
        assertTrue(host.getLoad() < 0.75);
    }

    @Test
    public void pickHostFromEmpty() {
        setUpHosts(0, 0, 1);

        balancingStrategy = LoadDrivenStrategy
                .builder()
                .hosts(hosts)
                .build();
        final var host = balancingStrategy.pickHost();
        assertTrue(host.isEmpty());
    }

    @Test
    public void pickHostFromSmallLoads() {
        setUpHosts(10, (float) 0.76, 1);

        balancingStrategy = LoadDrivenStrategy
                .builder()
                .hosts(hosts)
                .build();

        final var host = balancingStrategy.pickHost().get();

        assertEquals(host, findMin());
    }

    private float getFloatInRange(float min, float max) {
        return min + random.nextFloat() * (max - min);
    }

    private void setUpHosts(int i, float min, float max) {

        hosts = new LinkedList<>();

        Stream.generate(() -> new Host(getFloatInRange(min, max)))
                .limit(i).forEach(hosts::add);
    }

    private IHost findMin() {
        return hosts.stream().min(Comparator.comparing(IHost::getLoad)).get();
    }

}
