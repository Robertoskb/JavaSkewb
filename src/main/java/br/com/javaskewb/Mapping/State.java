package br.com.javaskewb.Mapping;

import br.com.javaskewb.Mapping.parts.Center;
import br.com.javaskewb.Mapping.parts.Corner;

import java.util.ArrayList;

public class State {
    private ArrayList<Center> centers;
    private ArrayList<Corner> corners;

    public State(ArrayList<Center> centers, ArrayList<Corner> corners){
        setCenters(centers);
        setCorners(corners);
    }

    public static State getSolvedStage(){
        ArrayList<Center> new_centers = new ArrayList<>();
        ArrayList<Corner> new_corners = new ArrayList<>();

        for (int i=0; i<6; i++)
            new_centers.add(new Center(i));

        int cont = 0;
        for (int i=0; i<24; i+=3)
            new_corners.add(new Corner(cont++, new int[] {i, i+1, i+2}));


        return new State(new_centers, new_corners);
    }

    public State cloneStage(){
        ArrayList<Center> new_centers = new ArrayList<>();
        ArrayList<Corner> new_corners = new ArrayList<>();

        for (int i=0; i<6; i++)
            new_centers.add(new Center(centers.get(i).getId()));

        for (int i=0; i<8; i++)
            new_corners.add(new Corner(corners.get(i).getId(), corners.get(i).getFaces()));


        return new State(new_centers, new_corners);
    }

    public boolean equals(State other){
        return centers.equals(other.getCenters()) && corners.equals(other.getCorners());
    }

    public ArrayList<Integer> getFaces(){
        ArrayList<Integer> faces = new ArrayList<>();
        for (Corner corner: corners)
            faces.addAll(corner.getFaces());

        return faces;
    }

    public ArrayList<Integer> getIntCenters(){
        ArrayList<Integer> centers = new ArrayList<>();

        for (Center center: this.centers)
            centers.add(center.getValue());

        return centers;
    }

    public ArrayList<Center> getCenters() {
        return centers;
    }

    public void setCenters(ArrayList<Center> centers) {
        this.centers = centers;
    }

    public ArrayList<Corner> getCorners() {
        return corners;
    }

    public void setCorners(ArrayList<Corner> corners) {
        this.corners = corners;
    }

    @Override
    public String toString(){
        return "Centros: " + centers + "\n" + "Cantos: " + corners;
    }
}
