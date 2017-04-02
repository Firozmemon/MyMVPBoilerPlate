package com.firoz.mymvpboilerplate;

/**
 * Created by firoz on 1/4/17.
 */

public interface IConnections {

    interface MyView {
        void updateText(String text);
    }

    interface MyPresenter {
        void buttonClicked();
    }

    interface MyInteractor {
        void execute();
    }

    /**
     * A repository that is responsible for getting our message.
     */
    interface MyRepository {
        int incrementCounter();
    }

    interface Callback {
        void onSuccess(String message);

        void onFail(String error);
    }
}
