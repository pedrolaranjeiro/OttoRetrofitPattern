package com.flat14.orp.event;

import retrofit.RetrofitError;

public class RetrofitErrorEvent {

    private RetrofitError error;

    public RetrofitErrorEvent(RetrofitError error) {
        this.error = error;
    }

    public RetrofitError getError() {
        return error;
    }

}
