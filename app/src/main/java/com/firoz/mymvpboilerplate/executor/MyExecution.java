package com.firoz.mymvpboilerplate.executor;

import android.os.Handler;
import android.os.Looper;

import com.firoz.mymvpboilerplate.interactor.AbstractInteractor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by firoz on 1/4/17.
 */

public class MyExecution {

    /**
     * This singleton class will make sure that each interactor operation gets a background thread.
     */
    public static class MyBackgroundExecutor implements Execution.MyBackExecutor {

        private static MyBackgroundExecutor myBackgroundExecutor;
        private ThreadPoolExecutor threadPoolExecutor;

        private static final int CORE_POOL_SIZE = 2;
        private static final int MAX_POOL_SIZE = 2;
        private static final int KEEP_ALIVE_TIME = 120;
        private static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;
        private static final BlockingQueue<Runnable> WORK_QUEUE = new LinkedBlockingQueue<Runnable>();

        private MyBackgroundExecutor() {
            threadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE,
                    MAX_POOL_SIZE,
                    KEEP_ALIVE_TIME,
                    TIME_UNIT,
                    WORK_QUEUE);
        }

        public static MyBackgroundExecutor getInstance() {
            if (myBackgroundExecutor == null) {
                myBackgroundExecutor = new MyBackgroundExecutor();
            }
            return myBackgroundExecutor;
        }

        @Override
        public void execute(final AbstractInteractor interactor) {
            threadPoolExecutor.submit(new Runnable() {
                @Override
                public void run() {
                    interactor.run();

                }
            });
        }
    }

    /**
     * This class makes sure that the runnable we provide will be run on the main UI thread.
     */
    public static class MyForegroundExecutor implements Execution.MyUIExecutor {

        private static MyForegroundExecutor myForegroundExecutor;
        private Handler handler;

        private MyForegroundExecutor() {
            handler = new Handler(Looper.getMainLooper());
        }

        public static MyForegroundExecutor getInstance() {
            if (myForegroundExecutor == null) {
                myForegroundExecutor = new MyForegroundExecutor();
            }
            return myForegroundExecutor;
        }

        @Override
        public void post(Runnable runnable) {
            handler.post(runnable);
        }
    }
}
