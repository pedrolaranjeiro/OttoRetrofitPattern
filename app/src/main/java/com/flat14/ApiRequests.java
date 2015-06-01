package com.flat14;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

public interface ApiRequests {

    @GET("/posts")
    void posts(Callback<List<Post>> cb);

    @GET("/users")
    void users(Callback<List<User>> cb);

}
