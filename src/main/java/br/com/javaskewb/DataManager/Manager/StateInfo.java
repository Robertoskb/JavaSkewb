package br.com.javaskewb.DataManager.Manager;
import java.util.ArrayList;
import java.util.HashSet;

public class StateInfo {
    private Long id;
    private boolean favorite;
    private String status;
    private HashSet<String> algorithms;

    public StateInfo(){}

    public StateInfo(boolean favorite, String status, HashSet<String> algorithms) {
        this.favorite = favorite;
        this.status = status;
        this.algorithms = algorithms;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public HashSet<String> getAlgorithms() {
        return algorithms;
    }

    public void setAlgorithms(HashSet<String> algorithms) {
        this.algorithms = algorithms;
    }
}
