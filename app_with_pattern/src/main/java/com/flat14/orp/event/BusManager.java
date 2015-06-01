package com.flat14.orp.event;

import android.os.Handler;
import android.os.Looper;

import com.squareup.otto.Bus;

public enum BusManager {
    INSTANCE;

    private final Bus bus = new MainThreadBus();

    public static void post(Object event) {
        INSTANCE.bus.post(event);
    }

    public static void register(Object target) {
        INSTANCE.bus.register(target);
    }

    public static void unregister(Object target) {
        INSTANCE.bus.unregister(target);
    }


    /**
     * This class allow to send events from any thread.
     */
    class MainThreadBus extends Bus {
        private final Handler mHandler = new Handler(Looper.getMainLooper());

        @Override
        public void post(final Object event) {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                super.post(event);
            } else {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        MainThreadBus.super.post(event);
                    }
                });
            }
        }
    }

}
