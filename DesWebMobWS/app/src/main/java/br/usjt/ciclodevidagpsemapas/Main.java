package br.usjt.ciclodevidagpsemapas;

import com.google.gson.annotations.SerializedName;

import java.text.NumberFormat;

class Main {

    @SerializedName("temp_min")
    private String minTemp;

    @SerializedName("temp_max")
    private String maxTemp;

    @SerializedName("humidity")
    private String humidity;

    public String getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(double minTemp) {
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(0);
        this.minTemp = numberFormat.format(minTemp) + "\u00B0C";
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(double maxTemp) {
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(0);
        this.maxTemp = numberFormat.format(maxTemp) + "\u00B0C";
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = NumberFormat.getPercentInstance().format(humidity / 100.0);
    }

}
