package com.firoz.mymvpboilerplate.interactor;

import com.firoz.mymvpboilerplate.IConnections;
import com.firoz.mymvpboilerplate.executor.Execution;

/**
 * Created by firoz on 1/4/17.
 */

public class MainInteractor extends AbstractInteractor implements IConnections.MyInteractor {

    private IConnections.Callback callback;
    private IConnections.MyRepository repository;

    public MainInteractor(IConnections.Callback callback, IConnections.MyRepository repository) {
        super();
        this.callback = callback;
        this.repository = repository;
    }

    public MainInteractor(Execution.MyUIExecutor myUIExecutor, IConnections.Callback callback, IConnections.MyRepository repository) {
        super(myUIExecutor);
        this.callback = callback;
        this.repository = repository;
    }

    public MainInteractor(Execution.MyBackExecutor myBackExecutor, IConnections.Callback callback, IConnections.MyRepository repository) {
        super(myBackExecutor);
        this.callback = callback;
        this.repository = repository;
    }

    private void notifySuccess(final int counter) {
        myUIExecutor.post(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess("S::" + String.valueOf(counter));
            }
        });
    }

    private void notifyFail(final int counter) {
        myUIExecutor.post(new Runnable() {
            @Override
            public void run() {
                callback.onFail("F::" + String.valueOf(counter));
            }
        });
    }

    @Override
    public void run() {
        int getCounter = repository.incrementCounter();

        if (getCounter % 2 == 0) {
            notifyFail(getCounter);
        } else {
            notifySuccess(getCounter);
        }
    }

}
