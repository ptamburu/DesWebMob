package br.usjt.ciclodevidagpsemapas;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.List;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder> {

    private Context context;
    private List<Forecast> previsoes;

    public ForecastAdapter(Context context, List<Forecast> previsoes) {
        this.context = context;
        this.previsoes = previsoes;
    }

    @NonNull
    @Override
    public ForecastViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View raiz = inflater.inflate(R.layout.list_item_main, viewGroup, false);
        return new ForecastViewHolder(raiz);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastViewHolder weatherViewHolder, int i) {
        Forecast forecast = previsoes.get(i);
        Weather weather = forecast.getWeather().get(0);
        Main main = forecast.getMain();
        Glide.with(context).load(weather.getIconURL()).into(weatherViewHolder.conditionImageView);
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE");
        weatherViewHolder.dayTextView.setText(
                context.getString(
                        R.string.day_description,
                        dateFormat.format(forecast.getDayOfWeek()),
                        weather.getDescription()
                )
        );
        weatherViewHolder.lowTextView.setText(
                context.getString(
                        R.string.low_temp,
                        main.getMinTemp()
                )
        );
        weatherViewHolder.highTextView.setText(
                context.getString(
                        R.string.high_temp,
                        main.getMaxTemp()
                )
        );
        weatherViewHolder.humidityTextView.setText(
                context.getString(
                        R.string.humidity,
                        main.getHumidity()
                )
        );
    }

    @Override
    public int getItemCount() {
        return previsoes.size();
    }

    class ForecastViewHolder extends RecyclerView.ViewHolder {

        private ImageView conditionImageView;
        private TextView dayTextView;
        private TextView lowTextView;
        private TextView highTextView;
        private TextView humidityTextView;

        public ForecastViewHolder(View raiz) {
            super(raiz);
            conditionImageView = raiz.findViewById(R.id.conditionImageView);
            dayTextView = raiz.findViewById(R.id.dayTextView);
            lowTextView = raiz.findViewById(R.id.lowTextView);
            highTextView = raiz.findViewById(R.id.highTextView);
            humidityTextView = raiz.findViewById(R.id.humidityTextView);
        }

    }
}
