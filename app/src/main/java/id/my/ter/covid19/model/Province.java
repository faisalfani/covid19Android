package id.my.ter.covid19.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Province {
    private int FID;
    private int Kode_Provi;
    private String Provinsi;
    private int Kasus_Posi;
    private int Kasus_Semb;
    private int Kasus_Meni;

    public Province(){

    }

    public Province(JSONObject object) throws JSONException {
        this.FID = object.getInt("FID");
        this.Kode_Provi = object.getInt("Kode_Provi");
        this.Provinsi = object.getString("Provinsi");
        this.Kasus_Posi = object.getInt("Kasus_Posi");
        this.Kasus_Semb = object.getInt("Kasus_Semb");
        this.Kasus_Meni = object.getInt("Kasus_Meni");
    }

    public int getFID() {
        return FID;
    }

    public void setFID(int FID) {
        this.FID = FID;
    }

    public int getKode_Provi() {
        return Kode_Provi;
    }

    public void setKode_Provi(int kode_Provi) {
        Kode_Provi = kode_Provi;
    }

    public String getProvinsi() {
        return Provinsi;
    }

    public void setProvinsi(String provinsi) {
        Provinsi = provinsi;
    }

    public int getKasus_Posi() {
        return Kasus_Posi;
    }

    public void setKasus_Posi(int kasus_Posi) {
        Kasus_Posi = kasus_Posi;
    }

    public int getKasus_Semb() {
        return Kasus_Semb;
    }

    public void setKasus_Semb(int kasus_Semb) {
        Kasus_Semb = kasus_Semb;
    }

    public int getKasus_Meni() {
        return Kasus_Meni;
    }

    public void setKasus_Meni(int kasus_Meni) {
        Kasus_Meni = kasus_Meni;
    }
}
