package com.develogical;

import com.weather.Forecast;

/**
 * Created by Sabir on 13/07/2016.
 */
public interface Cache {

    void setValidityInterval (Integer validity);
    Integer getValidityInterval ();

    void setMaxItems (Integer maxItems);
    Integer getMaxItems();

    void add(String key, Forecast forecast);
    Forecast get(String key);
}