package br.usjt.ciclodevidagpsemapas;

import com.google.gson.annotations.SerializedName;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class Forecast {

    @SerializedName("dt")
    private Long dayOfWeek;

    @SerializedName("main")
    private Main main;

    @SerializedName("weather")
    private List<Weather> weather;

    public Long getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(long timestamp) {
        this.dayOfWeek = timestamp;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }
}
