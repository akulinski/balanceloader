package com.akulinski;

public interface IHost {
    float getLoad();
    void handleRequest(Request $request);
}
