package br.com.javaskewb.Mapping.parts;

import java.util.ArrayList;
import java.util.Objects;

public class Corner {
    private int id;
    private ArrayList<Integer> faces;

    public Corner(int id, int[] faces){
        setId(id);
        setFaces(faces);
    }

    public Corner(int id, ArrayList<Integer> faces){
        setId(id);
        setFaces(faces);
    }

    @Override
    public boolean equals(Object obj){
        Corner other = (Corner) obj;
        boolean idb = other.getId() == id;

        if (idb) {
            ArrayList<Integer> otherFaces = other.getFaces();
            for (int i = 0; i < 3; i++)
                if (!(otherFaces.get(i) == -1 || otherFaces.get(i) == faces.get(i)))
                    return false;
        }
        else
            return false;
        return true;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setFaces(int[] faces) {
        this.faces = new ArrayList<>();
        for (int face: faces)
            this.faces.add(face);
    }

    public void setFaces(ArrayList<Integer> faces) {
        this.faces = faces;
    }

    public ArrayList<Integer> getFaces() {
        return faces;
    }

    @Override
    public String toString(){
        return "(" + id + ", " + faces + ")";
    }
}
