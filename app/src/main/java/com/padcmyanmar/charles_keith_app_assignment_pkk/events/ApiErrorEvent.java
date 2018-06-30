package com.padcmyanmar.charles_keith_app_assignment_pkk.events;

/**
 * Created by paikhantko on 6/29/18.
 */

public class ApiErrorEvent {

    private String message;

    public ApiErrorEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
