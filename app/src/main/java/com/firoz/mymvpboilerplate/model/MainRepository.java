package com.firoz.mymvpboilerplate.model;

import com.firoz.mymvpboilerplate.IConnections;

/**
 * Created by firoz on 1/4/17.
 */

public class MainRepository implements IConnections.MyRepository {

    private static int COUNTER = 0;

    @Override
    public int incrementCounter() {
        COUNTER++;

        // Simulating some network/database lag
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return COUNTER;
    }
}
