package id.my.ter.covid19.model;

import org.json.JSONObject;

public class Cases {
    private String country;
    private int cases;
    private int todayCases;
    private int deaths;
    private int todayDeaths;
    private int recovered;
    private int active;
    private int critical;
    private int casesPerOneMillion;
    private int deathsPerOneMillion;
    private int totalTests;
    private int testsPerOneMillion;

    public Cases(){};

    public Cases(JSONObject jsonObject){
        try {
            this.country = jsonObject.getString("country");
            this.cases = jsonObject.getInt("cases");
            this.todayCases = jsonObject.getInt("todayCases");
            this.deaths = jsonObject.getInt("deaths");
            this.todayDeaths = jsonObject.getInt("todayDeaths");
            this.recovered = jsonObject.getInt("recovered");
            this.active = jsonObject.getInt("active");
            this.critical = jsonObject.getInt("critical");
            this.casesPerOneMillion = jsonObject.getInt("casesPerOneMillion");
            this.deathsPerOneMillion = jsonObject.getInt("deathsPerOneMillion");
            this.totalTests = jsonObject.getInt("totalTests");
            this.testsPerOneMillion = jsonObject.getInt("testsPerOneMillion");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getCases() {
        return cases;
    }

    public void setCases(int cases) {
        this.cases = cases;
    }

    public int getTodayCases() {
        return todayCases;
    }

    public void setTodayCases(int todayCases) {
        this.todayCases = todayCases;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getTodayDeaths() {
        return todayDeaths;
    }

    public void setTodayDeaths(int todayDeaths) {
        this.todayDeaths = todayDeaths;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getCritical() {
        return critical;
    }

    public void setCritical(int critical) {
        this.critical = critical;
    }

    public int getCasesPerOneMillion() {
        return casesPerOneMillion;
    }

    public void setCasesPerOneMillion(int casesPerOneMillion) {
        this.casesPerOneMillion = casesPerOneMillion;
    }

    public int getDeathsPerOneMillion() {
        return deathsPerOneMillion;
    }

    public void setDeathsPerOneMillion(int deathsPerOneMillion) {
        this.deathsPerOneMillion = deathsPerOneMillion;
    }

    public int getTotalTests() {
        return totalTests;
    }

    public void setTotalTests(int totalTests) {
        this.totalTests = totalTests;
    }

    public int getTestsPerOneMillion() {
        return testsPerOneMillion;
    }

    public void setTestsPerOneMillion(int testsPerOneMillion) {
        this.testsPerOneMillion = testsPerOneMillion;
    }
}
