package com.limmath;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AdjMatrixGraph implements Graph {

    private final int vertex_count;
    private final int edge_count;
    private int[][] adjMatrix;


    AdjMatrixGraph(int count) {
        Random random = new Random();

        this.vertex_count = count;
        this.edge_count = count * count;
        this.adjMatrix = new int[count][count];

        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count; j++) {

                if ((adjMatrix[i][j] == 0) && (i != j)) {
                    int weight = random.nextInt(1000) + 1;
                    adjMatrix[i][j] = weight;
                    adjMatrix[j][i] = weight;
                }

            }
        }


    }
    AdjMatrixGraph( int[][] matrix) {

        this.vertex_count = matrix[0].length;
        this.edge_count = vertex_count*vertex_count;
        this.adjMatrix = matrix;



    }



    public int[][] getAdjMatrix() {
        return adjMatrix;
    }

    @Override
    public int vertexCount() {
        return this.vertex_count;
    }

    @Override
    public int edgeCount() {
        return this.edge_count;
    }

    @Override
    public void addAdge(int v1, int v2) {

    }

    @Override
    public void removeAdge(int v1, int v2) {

    }

    @Override
    public Iterable<Integer> adjacencies(int v) {

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < vertex_count; i++) {

            if (adjMatrix[v][i] !=0)
            {
                result.add(i);
            }

        }
        return result;
    }


    @Override
    public String toString() {

        StringBuilder matrix = new StringBuilder();
        for (int[] i : adjMatrix) {

            for (int j : i) {
                if (j > 9) {
                    matrix.append(j).append(" ");
                } else {
                    matrix.append(" ").append(j).append(" ");
                }
            }
            matrix.append("\n");

        }

        return "Матрица смежности: \n" + matrix +
                "\n Vertex: " + vertex_count
                + "\n Edge: " + edge_count;
    }
}
