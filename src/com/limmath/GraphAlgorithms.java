package com.limmath;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Consumer;

public class GraphAlgorithms {

    public static int[][] Dijkstra(@NotNull AdjMatrixGraph graph, int start)
    {


        List<Vertex> vertexList = new ArrayList<>();
        Stack<Vertex> stack = new Stack<>();

        for (int i = 0; i < graph.vertexCount(); i++) {

            vertexList.add(new Vertex(i,1000000000,false,-1));
        }

        //Матрица стоимости постройки дорог
        int[][] adjMatrix = graph.getAdjMatrix();

        //Итоговая матрица смежностей (1 - строить дорогу, 0 - не строить)
        int [][] result = new int[graph.vertexCount()][graph.vertexCount()];


        //Накопитель
        List<Vertex> storage = new ArrayList<>();


        vertexList.get(start).weight =0;
        vertexList.get(start).visited = true;
        stack.push(vertexList.get(start));

        while (!stack.empty())
        {
                    for (int j :graph.adjacencies(stack.peek().vertex_num))
                    {
                        if (!vertexList.get(j).visited)
                        {
                            storage.add(vertexList.get(j));
                        }
                    }

                    if (storage.size() == 0)
                    {
                        stack.pop();
                    }
                    else
                    {
                        for (Vertex v: storage)
                        {
                            if(v.weight > stack.peek().weight + adjMatrix[v.vertex_num][stack.peek().vertex_num])
                            {
                                v.weight = stack.peek().weight + adjMatrix[v.vertex_num][stack.peek().vertex_num];
                                v.parent = stack.peek().vertex_num;
                            }
                        }

                        storage.sort(Comparator.comparingInt(o -> o.weight));

                        int index = storage.get(0).vertex_num;
                        vertexList.get(index).visited = true;
                        stack.push(vertexList.get(index));
                        storage.clear();


                    }





        }

        ///Задаем результативную матрицу смежностей
        for (Vertex v: vertexList)
        {
            if (v.parent != -1)
            {
                result[v.vertex_num][v.parent] = 1;
                result[v.parent][v.vertex_num] = 1;
            }

            System.out.println("\n" + v.vertex_num + " : " + v.weight);
        }

ываыва
       return result;



    }

    private static boolean visitedCheck(List<Vertex> vertexList)
    {
        for(Vertex v: vertexList)
        {
            if (!v.visited)
            {
                return false;
            }
        }
        return true;
    }






    /**
     * Поиск в глубину, реализованный рекурсивно
     * (начальная вершина также включена)
     * @param graph граф
     * @param from Вершина, с которой начинается поиск
     * @param visitor Посетитель
     */
    public static void dfsRecursion(Graph graph, int from, Consumer<Integer> visitor) {
        boolean[] visited = new boolean[graph.vertexCount()];

        class Inner {
            void visit(Integer curr) {
                visitor.accept(curr);
                visited[curr] = true;
                for (Integer v : graph.adjacencies(curr)) {
                    if (!visited[v]) {
                        visit(v);
                    }
                }
            }
        }
        new Inner().visit(from);
    }

    /**
     * Поиск в глубину, реализованный с помощью стека
     * (не совсем "правильный"/классический, т.к. "в глубину" реализуется только "план" обхода, а не сам обход)
     * @param graph граф
     * @param from Вершина, с которой начинается поиск
     * @param visitor Посетитель
     */
    public static void dfs(Graph graph, int from, Consumer<Integer> visitor) {
        boolean[] visited = new boolean[graph.vertexCount()];
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(from);
        visited[from] = true;
        while (!stack.empty()) {
            Integer curr = stack.pop();
            visitor.accept(curr);
            for (Integer v : graph.adjacencies(curr)) {
                if (!visited[v]) {
                    stack.push(v);
                    visited[v] = true;
                }
            }
        }
    }

    /**
     * Поиск в ширину, реализованный с помощью очереди
     * (начальная вершина также включена)
     * @param graph граф
     * @param from Вершина, с которой начинается поиск
     * @param visitor Посетитель
     */
    public static void bfs(Graph graph, int from, Consumer<Integer> visitor) {
        boolean[] visited = new boolean[graph.vertexCount()];
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(from);
        visited[from] = true;
        while (queue.size() > 0) {
            Integer curr = queue.remove();
            visitor.accept(curr);
            for (Integer v : graph.adjacencies(curr)) {
                if (!visited[v]) {
                    queue.add(v);
                    visited[v] = true;
                }
            }
        }
    }

    /**
     * Поиск в глубину в виде итератора
     * (начальная вершина также включена)
     * @param graph граф
     * @param from Вершина, с которой начинается поиск
     * @return Итератор
     */
    public static Iterable<Integer> dfs(Graph graph, int from) {
        return new Iterable<Integer>() {
            private Stack<Integer> stack = null;
            private boolean[] visited = null;

            @Override
            public Iterator<Integer> iterator() {
                stack = new Stack<>();
                stack.push(from);
                visited = new boolean[graph.vertexCount()];
                visited[from] = true;

                return new Iterator<Integer>() {
                    @Override
                    public boolean hasNext() {
                        return ! stack.isEmpty();
                    }

                    @Override
                    public Integer next() {
                        Integer result = stack.pop();
                        for (Integer adj : graph.adjacencies(result)) {
                            if (!visited[adj]) {
                                visited[adj] = true;
                                stack.add(adj);
                            }
                        }
                        return result;
                    }
                };
            }
        };
    }

    /**
     * Поиск в ширину в виде итератора
     * (начальная вершина также включена)
     * @param from Вершина, с которой начинается поиск
     * @return Итератор
     */
    public static Iterable<Integer> bfs(Graph graph, int from) {
        return new Iterable<Integer>() {
            private Queue<Integer> queue = null;
            private boolean[] visited = null;

            @Override
            public Iterator<Integer> iterator() {
                queue = new LinkedList<>();
                queue.add(from);
                visited = new boolean[graph.vertexCount()];
                visited[from] = true;

                return new Iterator<Integer>() {
                    @Override
                    public boolean hasNext() {
                        return ! queue.isEmpty();
                    }

                    @Override
                    public Integer next() {
                        Integer result = queue.remove();
                        for (Integer adj : graph.adjacencies(result)) {
                            if (!visited[adj]) {
                                visited[adj] = true;
                                queue.add(adj);
                            }
                        }
                        return result;
                    }
                };
            }
        };
    }
}
