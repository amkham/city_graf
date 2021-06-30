package com.limmath;

public class Vertex {

    int vertex_num;
    int weight;
    boolean visited;
    int parent;

    public Vertex(int vertex_num, int weight, boolean visited, int parent) {
        this.vertex_num = vertex_num;
        this.weight = weight;
        this.visited = visited;
        this.parent = parent;
    }
}
