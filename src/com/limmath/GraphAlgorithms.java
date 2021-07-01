package com.limmath;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Consumer;

public class GraphAlgorithms {

    public static int[][] Prima(@NotNull AdjMatrixGraph graph, int start) {


        List<Vertex> vertexList = new ArrayList<>();

        List<Vertex> visited = new ArrayList<>();
        Stack<Vertex> stack = new Stack<>();


        for (int i = 0; i < graph.vertexCount(); i++) {

            vertexList.add(new Vertex(i, 1000000000, false, -1));
        }

        //Матрица стоимости постройки дорог
        int[][] adjMatrix = graph.getAdjMatrix();

        //Итоговая матрица смежностей (1 - строить дорогу, 0 - не строить)
        int[][] result = new int[graph.vertexCount()][graph.vertexCount()];


        //Накопитель
        List<Vertex> storage = new ArrayList<>();


        vertexList.get(start).weight = 0;
        vertexList.get(start).visited = true;
        visited.add(vertexList.get(start));

        while (!visitedCheck(vertexList)) {
            for (Vertex v : visited) {
                for (int j : graph.adjacencies(v.vertex_num)) {
                    if (!vertexList.get(j).visited) {
                        Vertex vertex = new Vertex(j, adjMatrix[v.vertex_num][j], false, v.vertex_num);
                        storage.add(vertex);
                    }
                }
            }

            storage.sort(new Comparator<Vertex>() {
                @Override
                public int compare(Vertex o1, Vertex o2) {
                    return o1.weight - o2.weight;
                }
            });

            int index = storage.get(0).vertex_num;
            vertexList.get(index).visited = true;
            vertexList.get(index).weight = storage.get(0).weight;
            vertexList.get(index).parent = storage.get(0).parent;
            visited.add(storage.get(0));
            storage.clear();

        }

        ///Задаем результативную матрицу смежностей
        for (Vertex v : vertexList) {
            if (v.parent != -1) {
                result[v.vertex_num][v.parent] = 1;
                result[v.parent][v.vertex_num] = 1;
            }

            System.out.println("\n" + v.vertex_num + " : " + v.weight);
        }
        return result;
    }

    private static boolean visitedCheck(List<Vertex> vertexList) {
        for (Vertex v : vertexList) {
            if (!v.visited) {
                return false;
            }
        }
        return true;
    }


    public static boolean dfs(int start, int[] color, AdjMatrixGraph graph)
    {
        for (int i: graph.adjacencies(start))
        {
            if  (color[start] == color[i])
            {
                return false;
            }
            else
            {
                if (color[i] == 0)
                {
                    color[i] = 3 - color[start];
                    dfs(i,color,graph);
                }
            }
        }
        return true;
    }
}