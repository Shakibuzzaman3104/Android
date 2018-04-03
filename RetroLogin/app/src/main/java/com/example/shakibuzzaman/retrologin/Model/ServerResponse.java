package com.example.shakibuzzaman.retrologin.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Shakibuzzaman on 3/28/2018.
 */

public class ServerResponse {

    @SerializedName("message")
    String Message;

    public String getMessage()
    {
        return Message;
    }
}
