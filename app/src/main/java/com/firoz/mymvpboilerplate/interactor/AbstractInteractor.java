package com.firoz.mymvpboilerplate.interactor;

import com.firoz.mymvpboilerplate.executor.Execution;
import com.firoz.mymvpboilerplate.executor.MyExecution;

/**
 * Created by firoz on 1/4/17.
 */

public abstract class AbstractInteractor {

    protected final Execution.MyBackExecutor myBackExecutor;
    protected final Execution.MyUIExecutor myUIExecutor;

    public AbstractInteractor() {
        this(MyExecution.MyBackgroundExecutor.getInstance(), MyExecution.MyForegroundExecutor.getInstance());
    }

    public AbstractInteractor(Execution.MyBackExecutor myBackExecutor) {
        this(myBackExecutor, MyExecution.MyForegroundExecutor.getInstance());
    }

    public AbstractInteractor(Execution.MyUIExecutor myUIExecutor) {
        this(MyExecution.MyBackgroundExecutor.getInstance(), myUIExecutor);
    }

    public AbstractInteractor(Execution.MyBackExecutor myBackExecutor, Execution.MyUIExecutor myUIExecutor) {
        this.myBackExecutor = myBackExecutor;
        this.myUIExecutor = myUIExecutor;
    }

    /**
     * This method contains the actual business logic of the interactor.
     * It SHOULD NOT BE USED DIRECTLY but, instead,
     * a developer should call the execute() method of an interactor
     * to make sure the operation is done on a background thread.
     * <p>
     * This method should only be called directly while doing unit/integration tests.
     * That is the only reason it is declared public as to help with easier testing.
     */
    public abstract void run();

    public void execute() {
        // start running this interactor in a background thread
        myBackExecutor.execute(this);
    }
}
