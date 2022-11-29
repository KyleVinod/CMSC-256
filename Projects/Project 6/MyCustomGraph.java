/**
 * Kyle Vinod
 * Graph Methods Implementation
 * MyCustomGraph
 * This project uses three given java codes and interface
 * It will build a graph from a text file and determine
 * if that graph is complete, connected, or has a cycle
 * It will also determine if two vertices are neighbors
 * and will find the shortest path from one vertex to another
 */
package cmsc256;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyCustomGraph<V> extends UnweightedGraph<V> {
    //All the given constructors to override the UnweightedGraph class
    public MyCustomGraph() {
        super();
    }
    public MyCustomGraph(int[][] edges, int numberOfVertices) {
        super(edges, numberOfVertices);
    }

    public MyCustomGraph(List<Edge> edges, int numberOfVertices) {
        super(edges, numberOfVertices);
    }

    public MyCustomGraph(List<V> vertices, List<Edge> edges) {
        super(vertices, edges);
    }

    public MyCustomGraph(V[] vertices, int[][] edges) {
        super(vertices, edges);
    }
    public MyCustomGraph<V> readFile(String fileName) throws FileNotFoundException{
        //Using fileName to get the file
        File myFile = new File(fileName);
        MyCustomGraph<V> graph = new MyCustomGraph<>();
        Scanner fileReader = new Scanner(myFile);
        int num = 0;
        while (fileReader.hasNextLine()) {
            //Gets each line in the file
            String p = fileReader.nextLine();
            int i = 0;
            int bop = 0;
            //Will determine how many vertices are there based on the first file line
            if (num == 0) {
                while (i < p.length()) {
                    String n = String.valueOf(p.charAt(i));
                    num = Integer.parseInt(n);
                    i++;
                }
                for (int j = 0; j < num; j++) {
                    graph.addVertex((V)(Integer.valueOf(j)));
                }
            }
            //Determines teh neighbors and will set the edges as followed in the file
            while (i < p.length()) {
                if (i == 0) {
                    String n = String.valueOf(p.charAt(i));
                    bop = Integer.parseInt(n);
                    i++;
                }
                else if (p.charAt(i) == ' ') {
                    i++;
                }
                else {
                    String n = String.valueOf(p.charAt(i));
                    graph.addEdge(bop, Integer.parseInt(n));
                    i++;
                }
            }
        }
        return graph;
    }
    public boolean isComplete() {
        //Gets all the neighbors of one vertex
        for (int i = 0; i <  vertices.size(); i++) {
            List<Integer> bots = getNeighbors(vertices.indexOf(vertices.get(i)));
            //Determines if all the vertices in the graph are neighbors
            for (int j = 0; j < vertices.size(); j++) {
                if (!bots.contains(vertices.indexOf(vertices.get(j))) && j != i) {
                    return false;
                }
            }
        }
        return true;
    }
    public boolean areAdjacent(V v1, V v2) {
        //Makes an arrayList that has all the neighbors of v1
        List<Integer> neighbors = new ArrayList<>();
        int index = getIndex(v1);
        int index2 = getIndex(v2);
        neighbors = getNeighbors(index);
        //If list contains v2, then they are adjacent
        if (neighbors.contains(index2)) {
            return true;
        }
        return false;
    }
    public boolean isConnected() {
        printEdges();
        //Getting tree from root
        SearchTree start = dfs(vertices.indexOf(vertices.get(0)));
        //If tree vertices equal number of vertices in graph, return true
        if (start.getNumberOfVerticesFound() == vertices.size() && vertices.size() != 1) {
            return true;
        }
        return false;
    }
    public List<V> getShortestPath(V begin, V end) {
        List<V> shortPath = new ArrayList<>();
        int beginning = 0;
        int ending = 0;
        //Getting index of begin and end
        if (vertices.contains(begin)) {
            int i = 0;
            int num = 0;
            while (num != 1) {
                if (vertices.get(i) == begin) {
                    beginning = getIndex(vertices.get(i));
                    num++;
                }
                i++;
            }
        }
        if (vertices.contains(end)) {
            int i = 0;
            int num = 0;
            while (num != 1) {
                if (vertices.get(i) == end) {
                    ending = getIndex(vertices.get(i));
                    num++;
                }
                i++;
            }
        }
        //Getting tree starting from begin index
        SearchTree start = bfs(beginning);
        if (begin == end) {
            shortPath.add(begin);
            return shortPath;
        }
        shortPath = start.getPath(ending);
        //If list contains end, then it will sort the list correctly, and return it
        if (shortPath.contains(begin)) {
            for (int i = 0; i != shortPath.size() / 2; i++) {
                 V v1 = shortPath.remove(shortPath.size() - 1 - i);
                 V v2 = shortPath.remove(i);
                 shortPath.add(i, v1);
                 shortPath.add(shortPath.size() - i, v2);
            }
            return shortPath;
        }
        return null;
    }
    public boolean hasCycle() {
        int i = 0;
        while (i < vertices.size()) {
            //Getting the neighbors on whatever vertex it's on
            List<Integer> list = getNeighbors(i);
            //If vertex has only one or no neighbor, it will return false
            if (list.size() == 0 || list.size() == 1) {
                return false;
            }
            //Increment if vertex is a neighbor
            if (list.contains(i)) {
                i++;
            }
            else {
                //Will get neighbors of a vertex that isn't in original list
                //If they have neighbor of other list, then it will return true
                List<Integer> newList = getNeighbors(i);
                for (int j = 0; j < list.size(); j++) {
                    if (newList.contains(list.get(j))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static void main(String[] args){
        MyCustomGraph<Integer> myGraph = new MyCustomGraph<>();
        try{
            MyCustomGraph<Integer> myGraph2 = myGraph.readFile("test2-1.txt");
            System.out.println("Graph of text2-1.txt");
            System.out.println("is complete => " + myGraph2.isComplete());
            System.out.println("is Connected => " + myGraph2.isConnected());
            System.out.println("A path from 0 to 4 is " + myGraph2.getShortestPath(0, 4));
            System.out.println("hasCycle() returns " + myGraph2.hasCycle());
            System.out.println("printEdges() displays: ");
            myGraph2.printEdges();
            System.out.println();

            MyCustomGraph<Integer> myGraph3 = myGraph.readFile("test3.txt");
            System.out.println("Graph of text3.txt");
            System.out.println("is complete => " + myGraph3.isComplete());
            System.out.println("is connected => " + myGraph3.isConnected());
            System.out.println("The shortest path from 0 to 4 is " + myGraph3.getShortestPath(0, 4));
            System.out.println("The shortest path from 5 to 4 is " + myGraph3.getShortestPath(5, 4));
            System.out.println("hasCyclic() returns " + myGraph3.hasCycle());
        } catch (Exception e) {
            System.out.println("Oops, something went wrong.");
            e.printStackTrace();
        }
    }
}
