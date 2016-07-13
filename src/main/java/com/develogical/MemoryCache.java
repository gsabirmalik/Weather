package com.develogical;
import com.weather.Forecast;

import java.util.ArrayList;
import java.util.HashMap;

public class MemoryCache implements Cache {

    Integer _validity = 0;
    Integer _maxItems = 0;
    HashMap<String, Forecast> _forecasts = new HashMap<String, Forecast>();

    @Override
    public void setValidityInterval(Integer validity) {
        _validity = validity;
    }

    @Override
    public Integer getValidityInterval() {
        return _validity;
    }

    @Override
    public void setMaxItems(Integer maxItems) {
        _maxItems = maxItems;
    }

    @Override
    public Integer getMaxItems() {
        return _maxItems;
    }

    @Override
    public void add(java.lang.String key, Forecast forecast) {

    }

    @Override
    public Forecast get(java.lang.String key) {
        return null;
    }

}