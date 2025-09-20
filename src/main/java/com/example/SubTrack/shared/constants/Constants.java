package com.example.SubTrack.shared.constants;

public final class Constants {
    public static final String [] ENDPOINTS_WITHOUT_AUTHENTICATION = {
        "/auth/login",
        "/auth/register"
    };
    public static final String [] ENDPOINTS_WITH_AUTHENTICATION = {
        "/auth/me",
        "/subscriptions/**"
    };

  
}
