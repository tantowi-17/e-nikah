package com.ahmfarisi.laundrypalembang.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroServer {
    private static final String baseURL = "https://tantowibowo.com/nikah/";
//    private static final String baseURL = "http://10.0.2.2/laundry/";
    private static Retrofit retro;

    public static Retrofit getRetrofit(){
        if(retro == null){
            retro = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retro;
    }
}
