package com.develogical;
import com.weather.Forecast;

import java.util.Calendar;
import java.util.Date;

public class TimedForecast extends Forecast {

    public Date RecordTime;
    public String Key;

    public TimedForecast(String summary, int temperature, String key) {
        super(summary, temperature);

        RecordTime = Calendar.getInstance().getTime();
        Key = key;
    }

    public TimedForecast(Forecast forecast, String key) {
        super(forecast.summary(), forecast.temperature());

        RecordTime = Calendar.getInstance().getTime();
        Key = key;
    }
}