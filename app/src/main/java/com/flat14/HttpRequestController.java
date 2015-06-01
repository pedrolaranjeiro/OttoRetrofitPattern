package com.flat14;

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

    public void getPosts(Callback<List<Post>> cb){
        request.posts(cb);
    }

    public void getUsers(Callback<List<User>> cb){
        request.users(cb);
    }

}
