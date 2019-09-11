package com.akulinski.core;

import com.akulinski.Request;

public interface ILoadBalancer {
    void handleRequest(Request $request);
}
