package com.firoz.mymvpboilerplate.executor;


import com.firoz.mymvpboilerplate.interactor.AbstractInteractor;

/**
 * Created by firoz on 1/4/17.
 */

public interface Execution {

    /**
     * This executor is responsible for running interactors on background threads.
     */
    interface MyBackExecutor {
        void execute(final AbstractInteractor interactor);
    }

    /**
     * This interface will define a class
     * that will enable interactors to run certain operations on the main (UI) thread.
     */
    interface MyUIExecutor {
        void post(Runnable runnable);
    }
}
