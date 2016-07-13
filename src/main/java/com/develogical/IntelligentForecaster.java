package com.develogical;

import com.weather.Day;
import com.weather.Forecast;
import com.weather.Forecaster;
import com.weather.Region;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Sabir on 13/07/2016.
 */
public class IntelligentForecaster extends Forecaster {

    long _validity = 0;
    Integer _maxItems = 0;
    Forecaster _forecaster = null;

    ArrayList<TimedForecast> _cachedForecasts = new ArrayList<TimedForecast>();

    public IntelligentForecaster(Integer validity, Integer maxItems, Forecaster forecaster) {

        _validity = validity;
        _maxItems = maxItems;
        _forecaster = forecaster;
    }

    public void setValidityInterval(long validity) {
        _validity = validity;
    }

    public long getValidityInterval() {
        return _validity;
    }

    public void setMaxItems(Integer maxItems) {
        _maxItems = maxItems;
    }

    public Integer getMaxItems() {
        return _maxItems;
    }

    @Override
    public Forecast forecastFor(Region region, Day day) {
        String key = getKey(region, day);
        TimedForecast forecast = getForecastIfAlreadyExists(key);

        if(forecast == null) {
            forecast = new TimedForecast(super.forecastFor(region, day), key);
            if(_cachedForecasts.size()>= _maxItems)
                _cachedForecasts.remove(_cachedForecasts.size()-1);

            _cachedForecasts.add(0, forecast);
        }

        return forecast;
    }

    TimedForecast getForecastIfAlreadyExists(String key){
        for (Integer index = 0; index<_cachedForecasts.size(); index ++)
            if(_cachedForecasts.get(index).Key.equalsIgnoreCase(key))
                return _cachedForecasts.get(index);

        return null;
    }

    String getKey(Region region, Day day){
        return region.name()+"_"+day.name();
    }

    void invalidateOldValues(){
        Date currentTime = Calendar.getInstance().getTime();

        Integer lastIndex = _cachedForecasts.size();
        while(lastIndex>=0) {
         Date saveTime = _cachedForecasts.get(_cachedForecasts.size()-1).RecordTime;
         long elapsedSeconds = TimeUnit.SECONDS.convert(currentTime.getTime()-saveTime.getTime(), TimeUnit.MILLISECONDS);
            if(elapsedSeconds >= _validity)
                _cachedForecasts.remove(lastIndex--);
            else
                break;
        }
    }
}
