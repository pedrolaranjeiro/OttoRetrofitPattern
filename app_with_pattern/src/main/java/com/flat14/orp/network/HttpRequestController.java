package com.flat14.orp.network;

import com.flat14.orp.composed.GetPosts;
import com.flat14.orp.composed.GetUsers;
import com.flat14.orp.network.models.Post;
import com.flat14.orp.network.models.User;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;

public class HttpRequestController {

    private static final String API_URL = "http://jsonplaceholder.typicode.com";
    private ApiRequests request;

    public HttpRequestController() {
        RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setEndpoint(API_URL);
        builder.setLogLevel(RestAdapter.LogLevel.FULL);
        RestAdapter controller = builder.build();
        request = controller.create(ApiRequests.class);
    }

    public void getPosts(){
        // The result is going to be delivered via an event so the callback
        // can always be the same.
        request.posts(new GetPosts.Callback());
    }

    public void getUsers(){
        request.users(new GetUsers.Callback());
    }

}
