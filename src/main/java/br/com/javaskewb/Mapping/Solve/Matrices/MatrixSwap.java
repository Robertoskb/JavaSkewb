package br.com.javaskewb.Mapping.Solve.Matrices;

import java.util.Arrays;

public class MatrixSwap {
    private int[][] matrix;

    public MatrixSwap(int[][] matrix){
        setMatrix(matrix);
    }

    public void swap(int origin, int destination, boolean decrement){
        if (decrement){
            origin--;
            destination--;
        }

        Arrays.fill(matrix[destination], 0);

        matrix[destination][origin] = 1;
    }

    public void swap(int origin, int destination){
        swap(origin, destination, true);
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }
}
