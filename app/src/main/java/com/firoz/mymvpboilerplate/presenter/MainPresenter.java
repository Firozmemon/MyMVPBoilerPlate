package com.firoz.mymvpboilerplate.presenter;

import com.firoz.mymvpboilerplate.IConnections;
import com.firoz.mymvpboilerplate.interactor.MainInteractor;

/**
 * Created by firoz on 1/4/17.
 */

public class MainPresenter implements IConnections.MyPresenter, IConnections.Callback {

    private IConnections.MyView view;
    private IConnections.MyRepository repository;
    private IConnections.MyInteractor interactor;

    public MainPresenter(IConnections.MyView view, IConnections.MyRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void buttonClicked() {
        // initialize the interactor
        interactor = new MainInteractor(this, repository);

        // run the interactor
        interactor.execute();
    }

    @Override
    public void onSuccess(String message) {
        view.updateText(message);
    }

    @Override
    public void onFail(String error) {
        view.updateText(error);
    }
}
