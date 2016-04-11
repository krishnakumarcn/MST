/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kruskals;
import prim.PrimTest;

/**
 *
 * @author krishnakumar
 */
public class Main {
    public static String inputfile="input1.txt";
    public static long timeKruskel,timePrim,numVertex,numEdge;
    public static void main(String[] arg ){
       Graph.mainGraph();
       PrimTest.primStart();
       System.out.println("-----------------\n\nNo.of Vertices: "+numVertex+"\nNo.of Edges: "+numEdge);
       System.out.println("\n-----------------\nExecution Time\n----------------\n"
               + "Kruskel's Algorithm: "+timeKruskel+"ms");
       System.out.println("Prim's Algorithm: "+timePrim+"ms"+"\n\n");
    }
    
}
