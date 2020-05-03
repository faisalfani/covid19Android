package id.my.ter.covid19.api;

import java.util.ArrayList;
import java.util.HashMap;

import id.my.ter.covid19.model.Cases;
import id.my.ter.covid19.model.Country;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

// definisi interface untuk layana negara pada api yang digunakan
public interface CountryService {
    @GET("list")
    Call<HashMap<String, ArrayList<Country>>> getCountry();

    @GET("countries/{country}")
    Call<HashMap<String, Object>> getCases(@Path("country") String country);
}