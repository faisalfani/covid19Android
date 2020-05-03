package id.my.ter.covid19.model;

public class Country {
    private String name;
    private String iso2;
    private String iso3;

    public Country(String name, String iso2, String iso3){
        this.name = name;
        this.iso2 = iso2;
        this.iso3 = iso3;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIso2() {
        return iso2;
    }

    public void setIso2(String iso2) {
        this.iso2 = iso2;
    }

    public String getIso3() {
        return iso3;
    }

    public void setIso3(String iso3) {
        this.iso3 = iso3;
    }
}

