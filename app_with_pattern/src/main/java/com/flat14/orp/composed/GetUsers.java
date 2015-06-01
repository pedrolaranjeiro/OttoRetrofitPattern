package com.flat14.orp.composed;

import com.flat14.orp.event.BusManager;
import com.flat14.orp.event.RetrofitErrorEvent;
import com.flat14.orp.network.models.User;

import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;

public class GetUsers {

    // Not applicable in this example
    public static final class Request {
        // This can be used to send data as POST to the server.
        // This object will be converted to JSON by retrofit
        // For more information about Requests check
        // http://square.github.io/retrofit/ Section REQUEST BODY
        public Request() {
        }
    }

    public static final class Callback implements retrofit.Callback<List<User>> {

        @Override
        public void success(List<User> usersList, Response retrofitResponse) {
            String text = "";
            for (User user : usersList) {
                text = text.concat(
                        user.getId() + " : " +
                        user.getUsername() + " aka " + user.getName()
                        + "\n");
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
