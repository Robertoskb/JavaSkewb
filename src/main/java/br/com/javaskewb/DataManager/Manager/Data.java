package br.com.javaskewb.DataManager.Manager;

import java.util.HashMap;

public class Data {
    private HashMap<Long, StateInfo> data;

    public Data(){}

    public Data(HashMap<Long, StateInfo> data) {
        this.data = data;
    }

    public HashMap<Long, StateInfo> getData() {
        return data;
    }

    public void setData(HashMap<Long, StateInfo> data) {
        this.data = data;
    }
}
