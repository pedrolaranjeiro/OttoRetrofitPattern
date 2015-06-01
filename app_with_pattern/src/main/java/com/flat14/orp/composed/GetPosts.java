package com.flat14.orp.composed;

import com.flat14.orp.event.BusManager;
import com.flat14.orp.event.RetrofitErrorEvent;
import com.flat14.orp.network.models.Post;

import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;

public class GetPosts {

    // Not applicable in this example
    public static final class Request {
        // This can be used to send data as POST to the server.
        // This object will be converted to JSON by retrofit
        // For more information about Requests check
        // http://square.github.io/retrofit/ Section REQUEST BODY
        public Request(){};

    }

    public static final class Callback implements retrofit.Callback<List<Post>> {

        @Override
        public void success(List<Post> postsList, Response retrofitResponse) {
            // Process the data
            // In a real project this could be the place to save data in the database.
            String text = "";
            for (Post post: postsList){
                text = text.concat(post.getId()+": "+post.getTitle()+"\n");
            }
            BusManager.post(new Event(text));
        }

        @Override
        public void failure(RetrofitError error) {
            BusManager.post(new RetrofitErrorEvent(error));
        }

    }

    // Otto Event
    public static final class Event {

        private final String text;

        public Event(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }
}
