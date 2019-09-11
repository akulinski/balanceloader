package com.akulinski.core;

import com.akulinski.Request;

public interface IHost {
    float getLoad();
    void handleRequest(Request request);
}
