package id.my.ter.covid19.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import id.my.ter.covid19.R;
import id.my.ter.covid19.model.Country;

/*
Adapter ini digunakan untuk menampilkan daftar negara yang ada pada halaman awal

 */

public class CountryAdapter extends ArrayAdapter<Country> {
//    Deklarasi komponen yang digunakan, pada file res/layout/country_item.xml
    private static class ViewHolder{
        CircleImageView country_flag;
        TextView country_name;
    }

    // konstruktor adapter
    public CountryAdapter(@NonNull Context context, ArrayList<Country> countries) {
        super(context, R.layout.country_item, countries);
    }

    // fungsi ini mengatur tampilan ketika akan memilih negara
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parrent){
        Country country = getItem(position); // mengambil data negara
        ViewHolder viewHolder; // initialisasi class ViewHolder

        if(convertView == null){
            viewHolder = new ViewHolder();

            LayoutInflater inflater = LayoutInflater.from(getContext()); //initial layout
            // menetapkan layout ke country_item_selector.xml
            convertView = inflater.inflate(R.layout.country_item_selector,parrent,false);
            viewHolder.country_flag = (CircleImageView) convertView.findViewById(R.id.country_flag); // mengambil komponen yand ada pada country_item_selector.xml
            viewHolder.country_name = (TextView) convertView.findViewById(R.id.country_name); // mengambil komponen yand ada pada country_item_selector.xml

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // penentuan url bendera negara
        String url_flag = country.getIso2() != null ? "https://www.countryflags.io/"+country.getIso2().toLowerCase()+"/flat/64.png" : "https://www.countryflags.io/ps/flat/64.png";
        // pengambilan gambar dengan library glide dan diterapkan pada object country_plag
        Glide.with(convertView.getContext())
                .load(url_flag)
                .apply(new RequestOptions())
                .into(viewHolder.country_flag);
        // pengisian nama negara
        viewHolder.country_name.setText(country.getName());
        return convertView;
    }

    // fungsi ini mengatur tampilan ketika negara selesai dipilih,
    // sama dengan fungsi di atas, yang membedakan dibagian resource
    @Override
    public View getView(int position, View convertView, ViewGroup parrent){
        Country country = getItem(position);
        ViewHolder viewHolder;

        if(convertView == null){
            viewHolder = new ViewHolder();

            LayoutInflater inflater = LayoutInflater.from(getContext());
            // menetapkan layout ke country_item.xml
            convertView = inflater.inflate(R.layout.country_item,parrent,false);
            viewHolder.country_flag = (CircleImageView) convertView.findViewById(R.id.country_flag);
            viewHolder.country_name = (TextView) convertView.findViewById(R.id.country_name);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String url_flag = country.getIso2() != null ? "https://www.countryflags.io/"+country.getIso2().toLowerCase()+"/flat/64.png" : "https://www.countryflags.io/ps/flat/64.png";
        Glide.with(convertView.getContext())
                .load(url_flag)
                .apply(new RequestOptions())
                .into(viewHolder.country_flag);
        viewHolder.country_name.setText(country.getIso3());
        return convertView;
    }
}
