package br.usjt.ciclodevidagpsemapas;

import com.google.gson.annotations.SerializedName;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class Weather {

    @SerializedName("description")
    private String description;

    @SerializedName("icon")
    private String iconURL;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIconURL() {
        return "http://openweathermap.org/img/w/" + iconURL + ".png";
    }

    public void setIconURL(String iconURL) {
        this.iconURL = iconURL;
    }
}
