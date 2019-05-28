package sample;

import java.util.*;

public class MaxFlow {
    public static int maxflow = 0;
    public static ArrayList<int[][]> residualgraphs = new ArrayList<int[][]>();

    public static int getrandom(int max, int min) {

        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }

    public static void main(String[] args) {


        int random = getrandom(12,6);
        System.out.print(random + "  ");
        System.out.println();
        int autoGraph[][] = new int[random][random];
        for (int i = 0; i < random - 1; i++) {
            int count = 0;
            for (int j = 1; j < random; j++) {

                if (i != j) {
                    if (autoGraph[j][i] == 0) {
                        autoGraph[i][j] = getrandom(20, 5);


                    }
                }
            }
        }
        for (int i = 0; i < random; i++) {
            for (int j = 0; j < autoGraph[i].length; j++) {
                System.out.print(autoGraph[i][j] + " ");
            }
            System.out.println();
        }


       long start = System.currentTimeMillis();
        findMaxFlow(autoGraph);
       long end = System.currentTimeMillis();
       System.out.println("Time taken for  " +
               (end - start) + "ms");
    }

    public static int[][] findMaxFlow(int[][] graph) {

        maxflow = 0;
        residualgraphs.clear();
        int[][] rGraph = new int[graph.length][graph.length];
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                rGraph[i][j] = graph[i][j];         //Generating the inital residual graph
            }
        }

        HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
        // while(
        while (bfs(rGraph, 0, rGraph.length - 1, hashMap)) {  //passing the arguments residual graph,initial node, final node
            System.out.println("Hash map getting..." + hashMap.get(rGraph.length - 1));         //This loop runs until a augmented route exists
            boolean x = true;
            LinkedList linkedList = new LinkedList();
            linkedList.add(rGraph.length - 1);    //this list holds the path flow
            int y = rGraph.length - 1;
            while (x) {

                y = hashMap.get(y);
                linkedList.add(y);          //Populating the path list by hashmap values
                if (y == 0) {
                    x = false;
                }


            }
            int d = 0;
            int size = linkedList.size() - 1;
            System.out.println("LINK LIST : " + linkedList);  // printing path of flow....
            int bottleneckCapacity = 25;
            for (int i = 0; i < graph.length; i++) {
                for (int j = 0; j < graph.length; j++) {

                    if (size != 0) {

                        d = rGraph[(int) linkedList.get(size--)][(int) linkedList.get(size)];
                        System.out.println(d);  //Calculating the bottleneck capacity to send the flow through the route
                        if (d < bottleneckCapacity) {

                            bottleneckCapacity = d;
                        }
                    }
                }
            }
            System.out.println(bottleneckCapacity);
            maxflow += bottleneckCapacity;
            int d2 = 0;
            size = linkedList.size() - 1;
            for (int i = 0; i < graph.length; i++) {
                for (int j = 0; j < graph.length; j++) {

                    if (size != 0) {
//Updating the residual capacities in the residual graph
                        d = rGraph[(int) linkedList.get(size--)][(int) linkedList.get(size++)];//Getting the present value
                        System.out.println("d" + d);
                        rGraph[(int) linkedList.get(size--)][(int) linkedList.get(size)] = d - bottleneckCapacity;//Subtracting the flow value
                        d2 = rGraph[(int) linkedList.get(size++)][(int) linkedList.get(size--)];                //sent through the path and
                        System.out.println("d2" + d2);                                                          //updating it
                        rGraph[(int) linkedList.get(size++)][(int) linkedList.get(size--)] = d2 + bottleneckCapacity;
                        //Updating the backward edge value in here
                    }
                }
            }
            int[][] newresi = new int[rGraph.length][rGraph.length];
            for (int i = 0; i < rGraph.length; i++) {
                for (int j = 0; j < rGraph[i].length; j++) {
                    System.out.print(rGraph[i][j] + " ");
                    newresi[i][j] = rGraph[i][j];
                }
                System.out.println();
            }
            residualgraphs.add(newresi);
            System.out.println(hashMap);

            hashMap.clear();            //Clearing the hashmap for new augmented path calculation


        }
        System.out.println("\n");
        for (int i = 0; i < rGraph.length; i++) {
            for (int j = 0; j < rGraph[i].length; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("***************************************************************");
        for (int i = 0; i < rGraph.length; i++) {
            for (int j = 0; j < rGraph[i].length; j++) {
                System.out.print(rGraph[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(maxflow);
        int[][] maxflowgraph = new int[graph.length][graph.length];
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {

                if (graph[i][j] > 0) {
                    maxflowgraph[i][j] = graph[i][j] - rGraph[i][j];
                }
            }
        }
        System.out.println("Max Flow Graph");
        for (int i = 0; i < rGraph.length; i++) {
            for (int j = 0; j < rGraph[i].length; j++) {
                System.out.print(maxflowgraph[i][j] + " ");
            }
            System.out.println();
        }

        return maxflowgraph;
    }


    static boolean bfs(int rGraph[][], int s, int t, HashMap<Integer, Integer> hashMap) {

        ArrayList<Integer> visitedList = new ArrayList<Integer>(6);  //list to hold the visited elements
        Queue<Integer> q = new LinkedList<>();      //Queue to store the values visted to do breadthfirst search

        q.add(0);
        visitedList.add(0);
        while (!q.isEmpty()) {
            for (int i = 0; i < rGraph.length; i++) {
                if (rGraph[q.peek()][i] > 0 && !visitedList.contains(i)) { //if the node is already visted it wont be visted again
                    System.out.println("Peek " + q.peek());                 //if the capacity>0 this loop allows for a visit
                    System.out.println(i);
                    q.add(i);                   //Getting the neighbours of the node and updating them in visitedlist and queue
                    hashMap.put(i, q.peek());
                    visitedList.add(i);
                }
            }
            q.remove();

        }
        System.out.println(q);
        System.out.println(hashMap);
        System.out.println(visitedList);

        return visitedList.contains(t);
// //If no more path found this value will be returned thus terminating the search and returning max flow value
    }
}
/*
*    int graph2[][] = new int[][]{
                {0, 10, 5, 15, 0, 0, 0, 0},
                {0, 0, 4, 0, 9, 15, 0, 0},
                {0, 0, 0, 4, 0, 8, 0, 0},
                {0, 0, 0, 0, 0, 0, 16, 0},
                {0, 0, 0, 0, 0, 15, 0, 10},
                {0, 0, 0, 0, 0, 0, 15, 10},
                {0, 0, 6, 0, 0, 0, 0, 10},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };
        int graph[][] = new int[][]{
                {0, 16, 13, 0, 0, 0},
                {0, 0, 10, 12, 0, 0},
                {0, 4, 0, 0, 14, 0},
                {0, 0, 9, 0, 0, 20},
                {0, 0, 0, 7, 0, 4},
                {0, 0, 0, 0, 0, 0}
        };
        int rGraph[][] = new int[][]{
                {0, 16, 13, 0, 0, 0},
                {0, 0, 10, 12, 0, 0},
                {0, 4, 0, 0, 14, 0},
                {0, 0, 9, 0, 0, 20},
                {0, 0, 0, 7, 0, 4},
                {0, 0, 0, 0, 0, 0}
        };
        int graph5[][] = new int[][]{
                {0, 5, 7, 0,},
                {0, 0, 0, 12},
                {0, 3, 0, 4},
                {0, 0, 0, 0,}
        };*/