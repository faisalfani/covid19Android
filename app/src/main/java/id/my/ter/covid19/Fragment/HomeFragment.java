package id.my.ter.covid19.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;

import id.my.ter.covid19.MainActivity;
import id.my.ter.covid19.R;
import id.my.ter.covid19.adapter.CountryAdapter;
import id.my.ter.covid19.api.CountryService;
import id.my.ter.covid19.api.RetrofitClientInstance;
import id.my.ter.covid19.cache.FileCacher;
import id.my.ter.covid19.model.Cases;
import id.my.ter.covid19.model.Country;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// kontroller untuk halaman beranda
public class HomeFragment extends Fragment {
    // initial variabel yang digunakan
    Context context;
    Spinner spinner_country;
    ArrayList<Country> countries;
    FileCacher<String> country_cache;
    private final static String cacheName = "country";

    // konstruktor
    public HomeFragment(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        spinner_country = v.findViewById(R.id.spinner_country);
        // pemanggilan funsi get
        getCountry();
        // membuat cache nama negara
        country_cache = new FileCacher<>(context,cacheName);
        try {
            // pengecekan apakah cache belum ada
            if(!country_cache.hasCache()){
                // maka secara default akan di setel ke indonesia
                country_cache.writeCache("Indonesia");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        // penambahan aksi ketika memilih negara
        spinner_country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // data negara di ambil
                Country country = countries.get(position);
                try {
                    // pengecekan nama negara untuk menyesuaikan pemanggilan data kasus
                    String countryName = country.getName().equals("US") ? country.getIso3() : country.getName();
                    // penyimpanan nama negara kedalam cache
                    country_cache.writeCache(countryName);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void getCountry(){
        // pembuatan layanan negara dengan retrofit instance
        CountryService countryService = RetrofitClientInstance.getRetrofitInstance().create(CountryService.class);
        // pendefinisian response yang akan dihasikan kedalam objek java, sekaligus memanggil api
        Call<HashMap<String, ArrayList<Country>>> call = countryService.getCountry();
        // menambahkan ke daftar pemanggilan
        call.enqueue(new Callback<HashMap<String, ArrayList<Country>>>() {
            @Override
            public void onResponse(Call<HashMap<String, ArrayList<Country>>> call, Response<HashMap<String, ArrayList<Country>>> response) {
                // pengisian ArrayList countries dengan data yang dihasilkan
                countries = response.body().get("countries");
                // memanggil generate country
                generateCountry();
            }

            @Override
            public void onFailure(Call<HashMap<String, ArrayList<Country>>> call, Throwable t) {
                Log.d("RETROFIT", t.toString());
            }
        });
    }

    private void generateCountry(){
        // membuat adapter list negara
        CountryAdapter countryAdapter = new CountryAdapter(context,countries);
        // set adapter negara ke spinner (option list negara)
        spinner_country.setAdapter(countryAdapter);
        try {
            // pengambilan nama negara yang disimpan dicache
            String country = country_cache.readCache();
            int index = 0;
            // pencarian negara tersebut berada pada index keberapa
            for (Country c : countries){
                String countryName = country.equals("USA") ? "US" : country;
                if(c.getName().equals(countryName)){
                    // jika ditemukan maka option list akan di set sesuai nama negara yang ada di cache
                    spinner_country.setSelection(index);
                    break;
                }
                index++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
