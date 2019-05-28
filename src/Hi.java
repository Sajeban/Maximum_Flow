import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Hi {
    public static int[][] findMaxFlow(int[][] graph) {

        int maxflow = 0;
        int[][] rGraph = new int[graph.length][graph.length];
        for (int i = 0; i < graph.length; i++) {             //   multidimensional Insertion
            for (int j = 0; j < graph.length; j++) {

                rGraph[i][j] = graph[i][j];         //Generating the inital residual graph
            }
        }
        HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
        // while(
        while (bfs(rGraph, 0, rGraph.length - 1, hashMap)) {  //passing the arguments residual graph,initial node, final node
            boolean x = true;
            LinkedList linkedList = new LinkedList();
            linkedList.add(rGraph.length - 1);    //this list holds the path flow
            int y = rGraph.length - 1;
            while (x) {

                y = hashMap.get(y);                 //linkedlist insertion
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

                    if (size != 0) {//multidimensional array search

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
                for (int j = 0; j < graph.length; j++) {                           //multidimensional array search and insertion
                    if (size != 0) {                                       //Updating the residual capacities in the residual graph
                        d = rGraph[(int) linkedList.get(size--)][(int) linkedList.get(size++)];        //Getting the present value
                        rGraph[(int) linkedList.get(size--)][(int) linkedList.get(size)] = d - bottleneckCapacity;//Subtracting the flow value
                        d2 = rGraph[(int) linkedList.get(size++)][(int) linkedList.get(size--)];                //sent through the path and
                        rGraph[(int) linkedList.get(size++)][(int) linkedList.get(size--)] = d2 + bottleneckCapacity; //Updating the backward edge value
                    }
                }
            }
            hashMap.clear();            //Clearing the hashmap for new augmented path calculation

        }
        int[][] maxflowgraph = new int[graph.length][graph.length];
        for (int i = 0; i < graph.length; i++) {  //multi dimensionnal plotting final max flow graph insertion
            for (int j = 0; j < graph.length; j++) {

                if (graph[i][j] > 0) {
                    maxflowgraph[i][j] = graph[i][j] - rGraph[i][j];
                }
            }
        }
        return maxflowgraph;
    }

    static boolean bfs(int rGraph[][], int s, int t, HashMap<Integer, Integer> hashMap) {
        ArrayList<Integer> visitedList = new ArrayList<Integer>(6);  //list to hold the visited elements
        Queue<Integer> q = new LinkedList<>();      //Queue to store the values visted to do breadthfirst search
        q.add(0);
        visitedList.add(0);
        while (!q.isEmpty()) {   //queue search
            for (int i = 0; i < rGraph.length; i++) { //array
                if (rGraph[q.peek()][i] > 0 && !visitedList.contains(i)) { //if the node is already visted it wont be visted again
                    q.add(i);                   //Getting the neighbours of the node and updating them in visitedlist and queue
                    hashMap.put(i, q.peek());
                    visitedList.add(i);
                }
            }
            q.remove();

        }
        return visitedList.contains(t);//If no more path found this value will be returned thus terminating the search and returning max flow value
    }
}

