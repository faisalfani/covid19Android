package id.my.ter.covid19.api;


import retrofit2.Call;
import retrofit2.http.GET;

// definisi interface untuk layana data pandemi covid berdasarkan provinsi pada api yang digunakan
public interface ProvinceService {

    @GET("provinsi")
    Call<Object> getProvince();
}
