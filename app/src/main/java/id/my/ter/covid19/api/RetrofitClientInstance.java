package id.my.ter.covid19.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// initial retrofit instance
public class RetrofitClientInstance {
    private static Retrofit retrofit;
    // end point api yang digunakan
    private static final String BASE_URL = "https://arcane-chamber-95694.herokuapp.com/api/";

    // pembuatan instance retrofit
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}