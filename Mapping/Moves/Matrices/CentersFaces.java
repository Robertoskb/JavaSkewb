package br.com.javaskewb.core.Mapping.Moves.Matrices;

import java.util.Arrays;
import java.util.Objects;

public class CentersFaces {
    private int[][] centersMatrix;
    private int[][] facesMatrix;

    public CentersFaces(int[][] centersMatrix, int[][] facesMatrix){
        setCentersMatrix(centersMatrix);
        setFacesMatrix(facesMatrix);
    }

    public CentersFaces copy(){
        return new CentersFaces(centersMatrix.clone(), facesMatrix.clone());
    }

    public int[][] getCentersMatrix() {
        return centersMatrix;
    }

    public void setCentersMatrix(int[][] centersMatrix) {
        this.centersMatrix = centersMatrix;
    }

    public int[][] getFacesMatrix() {
        return facesMatrix;
    }

    public void setFacesMatrix(int[][] facesMatrix) {
        this.facesMatrix = facesMatrix;
    }

    @Override
    public boolean equals(Object object){
        if (!object.getClass().isAssignableFrom(CentersFaces.class))
            return false;

        CentersFaces other = (CentersFaces) object;

        return Arrays.deepEquals(other.getCentersMatrix(), centersMatrix) && Arrays.deepEquals(other.getFacesMatrix(), facesMatrix);
    }

    @Override
    public int hashCode(){
        return Objects.hash(Arrays.deepHashCode(centersMatrix), Arrays.deepHashCode(facesMatrix));
    }
}
