package com.kumar.synconextassignment.pojo;

import java.io.Serializable;

public class WatchList implements Serializable {
    private CryptoData data;
    private String currentRate;

    public CryptoData getData() {
        return data;
    }

    public void setData(CryptoData data) {
        this.data = data;
    }

    public String getCurrentRate() {
        return currentRate;
    }

    public void setCurrentRate(String currentRate) {
        this.currentRate = currentRate;
    }
}
