package com.implemica.task2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Task 2
 * Dijkstra's algorithm
 * @author Artem Dementiev
 */
public class MainTask2 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new FileInputStream("src/com/implemica/task2/input.txt"));
        int s; // number of tests
        s = Integer.parseInt(sc.nextLine());
        for(int iterationTest=0;iterationTest<s;iterationTest++){
            int n=Integer.parseInt(sc.nextLine()); // number of cities
            String[] citiesName = new String[n]; //array of cities
            int[][] cost = new int[n][n]; //price matrix
            int neighbors; //the number of neighbors of city
            int item, transportationCost, numberOfPath=0;

            for(int j=0;j<n;j++){
                citiesName[j]=sc.nextLine(); //reading city name
                neighbors=Integer.parseInt(sc.nextLine()); // the number of neighbors of city
                String temp;
                for(int k=0; k<neighbors;k++){
                    temp = sc.nextLine();
                    item = Integer.parseInt(String.valueOf(temp.charAt(0))); // index of a city connected to 'item'
                    transportationCost = Integer.parseInt(String.valueOf(temp.charAt(2))); //the transportation cost
                    cost[j][item-1]= transportationCost;
                }
            }
            numberOfPath=Integer.parseInt(sc.nextLine()); // the number of paths to find
            String[] from = new String[numberOfPath]; //source array
            String[] to = new String[numberOfPath]; //array of directions
            for(int k=0; k<numberOfPath*2;k++){
                if(k%2!=0){
                    to[k/2]=sc.next();
                }else{
                    from[k/2]=sc.next();
                }
            }
            sc.nextLine(); //to avoid "/n" when reading next test

            if(!isSymmetric(cost)) throw new RuntimeException("The array of costs must be symmetric!");

            int[] path;
            int indexFrom = -1;
            int indexTo = -1;
            //find the index in the array "citiesName" of the corresponding strings
            for(int pp=0; pp<numberOfPath; pp++){
                for (int ppp=0; ppp<citiesName.length; ppp++) {
                    if (from[pp].equals(citiesName[ppp])) {
                        indexFrom = ppp;
                    }
                    if (to[pp].equals(citiesName[ppp])) {
                        indexTo = ppp;
                    }
                }
                path = Dijkstra(cost, n, indexFrom);
//                System.out.println("From "+ citiesName[indexFrom]+" to " +citiesName[indexTo]);
//                System.out.println("Distance:"+path[indexTo]);
                System.out.println(path[indexTo]);
            }
        }
        sc.close();
    }

    /**
     * A function that checks if the array is symmetric about the main diagonal.
     * @param a checked array
     * @return comparison result
     */
    private static boolean isSymmetric(int[][] a)
    {
        for (int i = 0; i < a.length; i++)
        {
            for (int j = 0; j < a.length; j++)
            {
                if (a[i][j] != a[j][i]) return false;
            }
        }
        return true;
    }

    /**
     * Dijkstra's algorithm
     * @param graphMatrix price matrix
     * @param V number of vertices
     * @param start start from this top
     * @return array of shortest distances from the starting vertex to all the others
     *
     * P.S. This function was written by me a long time ago in C ++
     * to find the minimum path from the initial index to all other vertices (that's why it returns an array),
     * I decided not to reinvent the wheel again and implemented it. \_(ツ)_/
     */
    public static int[] Dijkstra(int[][] graphMatrix, int V, int start){
        int[] distance;
        boolean[] visited;
        int count, index=0;
        int i, u;
        distance = new int [V];
        visited = new boolean [V];
        for (i = 0; i < V; i++){
            distance[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }
        distance[start] = 0;
        for (count = 0; count < V; count++){
            int min = Integer.MAX_VALUE;
            while(true){
                for (i = 0; i < V; i++){
                    if (!visited[i] && distance[i] <= min) //if we have not been at this vertex before and the cost is less than infinity
                    {
                        min = distance[i]; //the cost value for the vertex changes
                        index = i; //indicates the index of the vertex from which we came
                    }
                }
                u = index;
                visited[u] = true; //have visited vertex U
                for (i = 0; i < V; i++){
                    // if we have not been on current vertex and the cost from vertex to next vertex exists
                    // and it doesnt equal infinity
                    // and the previous edge + the cost of the current edge is less than the cost of the vertex,
                    // then we define a new vertex.
                    if (!visited[i] && graphMatrix[u][i] != 0 && distance[u] != Integer.MAX_VALUE && distance[u] + graphMatrix[u][i] < distance[i]){
                        distance[i] = distance[u] + graphMatrix[u][i];
                    }
                }
                break;
            }
        }
        return distance;
    }
}
