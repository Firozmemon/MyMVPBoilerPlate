package com.firoz.mymvpboilerplate.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firoz.mymvpboilerplate.IConnections;
import com.firoz.mymvpboilerplate.R;
import com.firoz.mymvpboilerplate.model.MainRepository;
import com.firoz.mymvpboilerplate.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, IConnections.MyView {

    TextView textView;
    Button buttonPanel;

    private IConnections.MyPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        buttonPanel = (Button) findViewById(R.id.buttonPanel);
        buttonPanel.setOnClickListener(this);

        // create a presenter for this view
        presenter = new MainPresenter(this,
                new MainRepository());
    }

    @Override
    public void updateText(String text) {
        textView.setText(text);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonPanel:
                presenter.buttonClicked();
                break;
        }
    }
}
