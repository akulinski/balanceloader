package com.akulinski;

public class Host implements IHost {

    private float load;

    public Host(float load) {
        this.load = load;
    }

    @Override
    public float getLoad() {
        return load;
    }

    @Override
    public void handleRequest(Request $request) {

    }
}
