package com.flat14;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends Activity{

    private HttpRequestController controller;
    private TextView textView;

    private Callback<List<Post>> postCallback = new Callback<List<Post>>() {
        @Override
        public void success(List<Post> postsList, Response response) {
            String postsText = "";
            for (Post post: postsList){
                postsText = postsText.concat(post.getId()+": "+post.getTitle()+"\n");
            }
            textView.setText(postsText);
        }

        @Override
        public void failure(RetrofitError error) {
            textView.setText(error.getMessage());
        }
    };

    private Callback<List<User>> usersCallback = new Callback<List<User>>() {

        @Override
        public void success(List<User> usersList, Response response) {
            String userText = "";
            for (User user: usersList){
                userText = userText.concat(
                        user.getId()+" : "+
                        user.getUsername()+" aka "+user.getName()
                                +"\n");
            }
            textView.setText(userText);
        }

        @Override
        public void failure(RetrofitError error) {
            textView.setText(error.getMessage());
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.text);
        Button postsBtn = (Button) findViewById(R.id.postsBtn);
        postsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.getPosts(postCallback);
            }
        });

        Button usersBtn = (Button) findViewById(R.id.usersBtn);
        usersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.getUsers(usersCallback);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        controller = new HttpRequestController();
    }

}
