package com.snappmi.covid19_ng;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Api {

    OkHttpClient client = new OkHttpClient();
    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url("https://nigeria-covid-19.p.rapidapi.com/api/states")
                .get()
                .addHeader("x-rapidapi-host", "nigeria-covid-19.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "e58930395dmsh45bbd51068513f1p1f27adjsn07f32ecab847")
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
