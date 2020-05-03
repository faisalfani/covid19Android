package id.my.ter.covid19.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import id.my.ter.covid19.R;
import id.my.ter.covid19.adapter.ListProvinceAdapter;
import id.my.ter.covid19.api.ProvinceService;
import id.my.ter.covid19.api.RetrofitClientInstance;
import id.my.ter.covid19.model.Cases;
import id.my.ter.covid19.model.Province;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class CountryFragment extends Fragment {
    private final String TAG = CountryFragment.class.getSimpleName();
    private Context context;
    private TextView affected;
    private TextView death;
    private TextView recovered;
    private TextView active;
    private TextView serious;
    RecyclerView province_data;
    ListProvinceAdapter provinceAdapter;
    LinearLayout layout_province;
    private ArrayList<Province> provinceArrayList;

    public CountryFragment(Context context) {
        this.context = context;
        // constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        // pengisian objek java dengan element yang berada pada fragment_country.xml
        affected = (TextView) view.findViewById(R.id.text_affected);
        recovered = (TextView) view.findViewById(R.id.text_recovered);
        death = (TextView) view.findViewById(R.id.text_death);
        active = (TextView) view.findViewById(R.id.text_active);
        serious = (TextView) view.findViewById(R.id.text_serious);
        province_data = (RecyclerView) view.findViewById(R.id.province_data);
        layout_province = (LinearLayout) view.findViewById(R.id.layout_province);
        provinceArrayList = new ArrayList<>();
        // initial adapter list provinsi
        provinceAdapter = new ListProvinceAdapter(provinceArrayList,context);
        // setting layout
        province_data.setHasFixedSize(true);
        province_data.setLayoutManager(new LinearLayoutManager(context));
        // setting adapter province data dengan adapter list provinsi
        province_data.setAdapter(provinceAdapter);
    }

    // pengisian data yang akan ditampilkan di layar
    public void pushData(Cases cases){
        NumberFormat nf = NumberFormat.getInstance(Locale.US);
        affected.setText(nf.format(cases.getCases()));
        recovered.setText(nf.format(cases.getRecovered()));
        death.setText(nf.format(cases.getDeaths()));
        active.setText(nf.format(cases.getActive()));
        serious.setText(nf.format(cases.getCritical()));

        // jika negara yang dipilih indonesia
        if(cases.getCountry().equals("Indonesia")){
            loadProvince();
        }
    }

    // pengambilan data kasus berdasarkan provinsi di indonesia
    public void loadProvince(){
        // intial layanan provinsi dengan instance retrofit
        ProvinceService provinceService = RetrofitClientInstance.getRetrofitInstance().create(ProvinceService.class);
        // pemanggilan API
        final Call province = provinceService.getProvince();
        // menambahkan ke daftar antrian
        province.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                try {
                    // convert response json ke JSONObject di java
                    JSONObject responseObj = new JSONObject(new Gson().toJson(response.body()));
                    // mengambil data dan disimpan ke JSONArray,
                    JSONArray provinceArr = responseObj.getJSONArray("data");
                    for(int i = 0; i <provinceArr.length(); i++){
                        // pengambilan data dari array dan disimpan ke JSONObject
                        JSONObject atributes = provinceArr.getJSONObject(i);
                        JSONObject provinsi = atributes.getJSONObject("attributes");
                        // penyimpanan data ke kelas Provinsi
                        Province prov = new Province(provinsi);
                        // pembentukan data Provinsi ke bentuk Array Java
                        provinceArrayList.add(prov);
                    }
                    // memberitahu adapter bahwa ada perubahan data
                    provinceAdapter.notifyDataSetChanged();
                    // data kasus pberdasakan provinsi ditampilkan
                    layout_province.setVisibility(View.VISIBLE);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }
}
