package com.example.hasib.travelermate;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;

/**
 * Created by Hasib on 12/3/2016.
 */
public class FetchAddressIntentService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public FetchAddressIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }
}
