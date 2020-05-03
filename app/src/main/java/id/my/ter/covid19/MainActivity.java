package id.my.ter.covid19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import id.my.ter.covid19.Fragment.HomeFragment;
import id.my.ter.covid19.Fragment.StatisticFragment;

public class MainActivity extends AppCompatActivity {
    // initial variable yang digunakan
    private final static String TAG = MainActivity.class.getSimpleName();
    ChipNavigationBar main_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // set java object main menu dengan element bottom navigation
        main_menu = (ChipNavigationBar) findViewById(R.id.bottom_navigation);

        // set aksi bottom navigation
        main_menu.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                switch (i){
                    case R.id.home_nav:
                        // menampilkan Fragment Beranda
                        loadFragment(new HomeFragment(MainActivity.this), "home");
                        break;
                    case R.id.stat_nav :
                        // Menampilkan Fragment Statistik
                        loadFragment(new StatisticFragment(MainActivity.this), "statistic");
                        break;
                }
            }
        });
        main_menu.setItemSelected(R.id.home_nav,true);

    }

    // fungsi untuk menampilkan halaman
    public void loadFragment(Fragment fragment, String title){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_frame,fragment,title);
        fragmentTransaction.commit();
    }
}
