package com.limmath;

public class GraphUtils {


    public static String toDOT(int[][] adjMatrix)
    {
        StringBuilder result = new StringBuilder();
        String nl = System.getProperty("line.separator");
        int rowLength = adjMatrix[0].length;

        result.append("graph ").append("G ").append("{").append(nl);
        for (int i = 0; i <  rowLength ; i++) {

            for (int j = 0; j < rowLength; j++) {

                if (adjMatrix[i][j] == 1)
                {
                    adjMatrix[j][i] = 0;
                    result.append(i).append(" -- ").append(j).append(";").append(nl);
                }
            }
        }
        result.append("}").append(nl);


        return result.toString();
    }
}
