package com.firoz.mymvpboilerplate;

import com.firoz.mymvpboilerplate.executor.Execution;
import com.firoz.mymvpboilerplate.interactor.MainInteractor;
import com.firoz.mymvpboilerplate.model.MainRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Created by firoz on 1/4/17.
 * <p>
 * Testing our interactor.
 */
public class MainInteractorTest {

    @Mock
    private IConnections.Callback myCallback;

    private Execution.MyUIExecutor myUIExecutor;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);

        myUIExecutor = new MyUIExecutionTest(); // Class created below, only for testing purpose
    }

    @Test
    public void checkCallbackSuccess() throws Exception {

        MainInteractor interactor = new MainInteractor(myUIExecutor, myCallback, new MainRepository());
        interactor.run();

        /**
         * We are checking here for onSuccess Callback,
         * because on every odd iteration,(in this case, its first)
         * it will return success
         */
        Mockito.verify(myCallback).onSuccess(Mockito.anyString());
    }

}

/**
 * Created for Testing purpose only
 * because we don't have Looper.getMainLooper() to execute our setup
 */
class MyUIExecutionTest implements Execution.MyUIExecutor {

    @Override
    public void post(Runnable runnable) {
        // tests can run on this thread, no need to invoke other threads
        runnable.run();
    }
}
