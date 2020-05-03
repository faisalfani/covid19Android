package id.my.ter.covid19.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import id.my.ter.covid19.R;
import id.my.ter.covid19.api.CountryService;
import id.my.ter.covid19.api.RetrofitClientInstance;
import id.my.ter.covid19.cache.FileCacher;
import id.my.ter.covid19.model.Cases;
import id.my.ter.covid19.model.Country;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// kontroller untuk halaman statistik
public class StatisticFragment extends Fragment {
    // initial variabel yang digunakan
    Context context;
    ViewPager viewContent;
    CountryFragment myCountry;
    CountryFragment global;
    private final static String cacheName = "country";
    private FileCacher<String> country_cache;

    // konstruktor
    public StatisticFragment(Context context) {
        this.context = context;
    }

    // penetapan layout yang digunakan
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_statistic, container, false);
            // initial penyimpanan cache
            country_cache = new FileCacher<>(context,cacheName);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        // penetapan layout untuk tab My country
        myCountry = new CountryFragment(context);
        // penetapan layout untuk tab Global
        global = new CountryFragment(context);
        // pengisian objeck java dengan element pada fragment_statistic.xml
        viewContent = (ViewPager) view.findViewById(R.id.main_view_pager);
        // penerapan layout ke element viewPager
        setupViewPager(viewContent);
        // setting tab
        TabLayout tabLayout = view.findViewById(R.id.tab_category);
        tabLayout.setupWithViewPager(viewContent);
        // mengambil data untuk ditampilkan di layout global
        loadData("world",false);

        try {
            // pengambilkan nama negara yang di pilih di halaman beranda dari cache
            String country = country_cache.readCache();
            // mengambil data untuk ditampilkan di layout my country
            loadData(country,true);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // setting layout yang akan ditampilkan
    private void setupViewPager(ViewPager viewPager) {
        // membuat objek menyimpan halaman
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        // menambahkan halaman My Country
        adapter.addFragment(myCountry, "My Country");
        // Menambahkan Halaman Global
        adapter.addFragment(global, "Global");
        // set viewPager dengan adapter
        viewPager.setAdapter(adapter);
    }

    //fungsi untuk mengambil data
    private void loadData(final String country, final boolean isCountry){
        // initial layanan negara dengan retrofit instace
        CountryService countryService = RetrofitClientInstance.getRetrofitInstance().create(CountryService.class);
        // penetapan tipe respone yang dihasialkan
        Call<HashMap<String, Object>> call = countryService.getCases(country);
        // menambahkan ke daftar pemanggilan
        call.enqueue(new Callback<HashMap<String, Object>>() {
            @Override
            public void onResponse(Call<HashMap<String, Object>> call, Response<HashMap<String, Object>> response) {
                try {
                    // casting response ke class cases
                    Cases cases = new Gson().fromJson(response.body().get("data").toString(),Cases.class);
                    // pengecekan apakan data yang di muat adalah data negara
                    if(isCountry){
                        // penyetelan data pada layout My Country
                        myCountry.pushData(cases);
                    }else{
                        // penyetelan data pada Layout Global
                        global.pushData(cases);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<HashMap<String, Object>> call, Throwable t) {

            }
        });

    }

    // pembuatan class adapter view pagger
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
