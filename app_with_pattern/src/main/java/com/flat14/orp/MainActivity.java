package com.flat14.orp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.flat14.orp.composed.GetPosts;
import com.flat14.orp.composed.GetUsers;
import com.flat14.orp.event.BusManager;
import com.flat14.orp.event.RetrofitErrorEvent;
import com.flat14.orp.network.HttpRequestController;
import com.squareup.otto.Subscribe;

public class MainActivity extends Activity{

    private HttpRequestController controller;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.text);
        Button postsBtn = (Button) findViewById(R.id.postsBtn);
        postsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // No callback defined at this point
                controller.getPosts();
            }
        });

        Button usersBtn = (Button) findViewById(R.id.usersBtn);
        usersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // No callback defined at this point
                controller.getUsers();
            }
        });
        BusManager.register(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        controller = new HttpRequestController();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BusManager.unregister(this);
    }

    // Otto Events
    @Subscribe
    public void onGetPostsEvent(GetPosts.Event event){
        textView.setText(event.getText());
    }

    @Subscribe
    public void onGetUserEvent(GetUsers.Event event){
        textView.setText(event.getText());
    }

    @Subscribe
    public void onRetrofitErrorEvent(RetrofitErrorEvent event){
        textView.setText(event.getError().getMessage());
    }

}
