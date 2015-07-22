/* A5Algorithms.java
   CSC 225 - Summer 2015
   Programming Assignment 5 - Image Algorithms


   B. Bird - 06/22/2015
*/ 

import java.awt.Color;
import java.util.LinkedList;

public class A5Algorithms{

    /* FloodFillDFS(v, viewer, fillColour)
       Traverse the component the vertex v using DFS and set the colour 
       of the pixels corresponding to all vertices encountered during the 
       traversal to fillColour.
       
       To change the colour of a pixel at position (x,y) in the image to a 
       colour c, use
            viewer.setPixel(x,y,c);
    */
    public static void FloodFillDFS(PixelVertex v, ImageViewerA5 viewer, Color fillColour){
        LinkedList<PixelVertex> visited = new LinkedList<PixelVertex>();
        // pass in an empty container for to keep track of visited vertices
        FloodFillDFS(v,viewer,fillColour,visited);
    }
    
    private static void FloodFillDFS(PixelVertex v, ImageViewerA5 viewer, Color fillColour, LinkedList<PixelVertex> visited){
        int x = v.getX();
        int y = v.getY();
        int deg = v.getDegree();
        viewer.setPixel(x,y,fillColour);
        visited.add(v);
        if (deg == 0)
            return;
        PixelVertex[] neighbours = v.getNeighbours();
        for (int i=0; i<deg; i++){
            PixelVertex w = neighbours[i];
            if (!visited.contains(w))
                FloodFillDFS(w,viewer,fillColour,visited);
        }
    }
    
    /* FloodFillBFS(v, viewer, fillColour)
       Traverse the component the vertex v using BFS and set the colour 
       of the pixels corresponding to all vertices encountered during the 
       traversal to fillColour.
       
       To change the colour of a pixel at position (x,y) in the image to a 
       colour c, use
            viewer.setPixel(x,y,c);
    */
    
    public static void FloodFillBFS(PixelVertex v, ImageViewerA5 viewer, Color fillColour){
        LinkedList<PixelVertex> BFSQueue = new LinkedList<PixelVertex>();
        LinkedList<PixelVertex> visited = new LinkedList<PixelVertex>();
        int x;
        int y;
        
        x = v.getX();
        y = v.getY();
        BFSQueue.addLast(v);
        visited.add(v);
        viewer.setPixel(x,y,fillColour);
        while (!BFSQueue.isEmpty()){
            PixelVertex u = BFSQueue.removeFirst();
            int deg = u.getDegree();
            PixelVertex[] neigh = u.getNeighbours();
            for (int i=0; i<deg; i++){
                PixelVertex w = neigh[i];
                if (!visited.contains(w)){
                    x = w.getX();
                    y = w.getY();
                    visited.add(w);
                    BFSQueue.addLast(w);
                    viewer.setPixel(x,y,fillColour);
                }
            }
        }
    }
    
    /* OutlineRegionDFS(v, viewer, outlineColour)
       Traverse the component the vertex v using DFS and set the colour 
       of the pixels corresponding to all vertices with degree less than 4
       encountered during the traversal to outlineColour.
       
       To change the colour of a pixel at position (x,y) in the image to a 
       colour c, use
            viewer.setPixel(x,y,c);
    */
    public static void OutlineRegionDFS(PixelVertex v, ImageViewerA5 viewer, Color outlineColour){
        LinkedList<PixelVertex> visited = new LinkedList<PixelVertex>();
        // pass in an empty container for to keep track of visited vertices
        OutlineRegionDFS(v,viewer,outlineColour,visited);
    }
    
    private static void OutlineRegionDFS(PixelVertex v, ImageViewerA5 viewer, Color outlineColour, LinkedList<PixelVertex> visited){
        int x = v.getX();
        int y = v.getY();
        int deg = v.getDegree();
        if (deg < 4)
            viewer.setPixel(x,y,outlineColour);
        visited.add(v);
        if (deg == 0)
            return;
        PixelVertex[] neighbours = v.getNeighbours();
        for (int i=0; i<deg; i++){
            PixelVertex w = neighbours[i];
            if (!visited.contains(w))
                OutlineRegionDFS(w,viewer,outlineColour,visited);
        }
    }
    
    /* OutlineRegionBFS(v, viewer, outlineColour)
       Traverse the component the vertex v using BFS and set the colour 
       of the pixels corresponding to all vertices with degree less than 4
       encountered during the traversal to outlineColour.
       
       To change the colour of a pixel at position (x,y) in the image to a 
       colour c, use
            viewer.setPixel(x,y,c);
    */
    public static void OutlineRegionBFS(PixelVertex v, ImageViewerA5 viewer, Color outlineColour){
        LinkedList<PixelVertex> BFSQueue = new LinkedList<PixelVertex>();
        LinkedList<PixelVertex> visited = new LinkedList<PixelVertex>();
        int x;
        int y;
        
        x = v.getX();
        y = v.getY();
        BFSQueue.addLast(v);
        visited.add(v);
        if (v.getDegree() < 4)
            viewer.setPixel(x,y,outlineColour);
        while (!BFSQueue.isEmpty()){
            PixelVertex u = BFSQueue.removeFirst();
            int deg = u.getDegree();
            PixelVertex[] neigh = u.getNeighbours();
            for (int i=0; i<deg; i++){
                PixelVertex w = neigh[i];
                if (!visited.contains(w)){
                    x = w.getX();
                    y = w.getY();
                    visited.add(w);
                    BFSQueue.addLast(w);
                    if (w.getDegree() < 4)
                        viewer.setPixel(x,y,outlineColour);
                }
            }
        }
    }

    /* CountComponents(G)
       Count the number of connected components in the provided PixelGraph 
       object.
    */
    public static int CountComponents(PixelGraph G){
        int width = G.getWidth();
        int height = G.getHeight();
        int components = width*height;
		boolean bridges = 0;
        
        for (int j = 0; j < height; j++){
            for (int i = 0; i < width; i++){
                
                PixelVertex v = G.getPixelVertex(i,j);
                
                if (i > 0){
                    if(v.isNeighbour(G.getPixelVertex(i-1,j)))
                        components--;
                }
                
                if (j > 0){
                    if(v.isNeighbour(G.getPixelVertex(i,j-1)))
                        components--;
                }
				
				if (i > 0 && j > 0){
					if(v.isNeighbour(G.getPixelVertex(i-1,j)) && v.isNeighbour(G.getPixelVertex(i,j-1)))
						components++;
				}
            }
            
        }
        return components;
    }
    
    /* A5Bonus(G, v, viewer, selectedColour)
       [optional; up to 5 bonus marks available]
       Given a PixelGraph G, a PixelVertex v (which has been selected by the 
       user), an ImageViewerA5 instance and the currently selected colour, 
       perform some kind of interesting graph-based image manipulation.
       If you have an idea for an interesting bonus feature, contact the
       instructor before the due date to determine how many bonus marks
       your algorithm would receive.
       
       To change the colour of a pixel at position (x,y) in the image to a 
       colour c, use
            viewer.setPixel(x,y,c);
    */
    public static void A5Bonus(PixelGraph G, PixelVertex v, ImageViewerA5 viewer, Color selectedColour){
        /* Your code here */
    }
}