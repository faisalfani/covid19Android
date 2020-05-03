package id.my.ter.covid19.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.my.ter.covid19.R;
import id.my.ter.covid19.model.Province;

/*
* class ini merupakan adapter yang digunakan di bagian statistic
* */
public class ListProvinceAdapter extends RecyclerView.Adapter<ListProvinceAdapter.ListViewHolder> {
    // inisial variabel yang digunakan
    ArrayList<Province> provinces;
    Context context;

    // konstruktor
    public ListProvinceAdapter(ArrayList<Province> provinces, Context context){
        this.provinces = provinces;
        this.context = context;
    }

    // fungsi untuk menetapkan layout yang digunakan ( province_item.xml )
    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.province_item,parent,false);
        return new ListProvinceAdapter.ListViewHolder(view);
    }

    // pengisian value
    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        Province province = provinces.get(position); // pengambilan data provinsi
        //pengisian value
        holder.text_province.setText(province.getProvinsi());
        holder.text_affected.setText(String.valueOf(province.getKasus_Posi()));
        holder.text_death.setText(String.valueOf(province.getKasus_Meni()));
        holder.text_recovered.setText(String.valueOf(province.getKasus_Semb()));
    }

    @Override
    public int getItemCount() {
        return provinces.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        // mendefinisikan objek yang akan digunakan
        TextView text_province, text_affected, text_death, text_recovered;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            // pengisian objek java dengan selector object pada province_item.xml berdasarkan ID
            text_province = itemView.findViewById(R.id.text_province);
            text_affected = itemView.findViewById(R.id.text_affected);
            text_death = itemView.findViewById(R.id.text_death);
            text_recovered = itemView.findViewById(R.id.text_recovered);
        }
    }
}
