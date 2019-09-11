package com.akulinski;

import com.akulinski.core.Host;
import com.akulinski.core.IHost;
import com.akulinski.core.LoadBalancer;
import com.akulinski.core.strategies.LoadBalancingStrategy;
import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class LoadBalancerTest {
    private LoadBalancer loadBalancer;
    private Random random;
    private List<IHost> hosts;

    @Before
    public void setUp() throws Exception {
        random = new Random();
    }

    @Test
    public void checkSequentialType() {
        setUpHosts(100, 0, 1);

        this.loadBalancer = new LoadBalancer(hosts, LoadBalancingStrategy.SEQUENTIAL);
        assertEquals(loadBalancer.getBalancingStrategy().getType(), LoadBalancingStrategy.SEQUENTIAL);
    }

    @Test
    public void checkLoadDrivenType() {
        setUpHosts(100, 0, 1);
        this.loadBalancer = new LoadBalancer(hosts, LoadBalancingStrategy.LOAD_DRIVEN);
        assertEquals(loadBalancer.getBalancingStrategy().getType(), LoadBalancingStrategy.LOAD_DRIVEN);
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
