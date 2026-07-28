package br.com.javaskewb.Mapping.Solve.Matrices;

public class CentersFaces {
    private int[][] centersMatrix;
    private int[][] facesMatrix;

    public CentersFaces(int[][] centersMatrix, int[][] facesMatrix){
        setCentersMatrix(centersMatrix);
        setFacesMatrix(facesMatrix);
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
}
