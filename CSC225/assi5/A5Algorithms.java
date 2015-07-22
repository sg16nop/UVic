/* A5Algorithms.java
   CSC 225 - Summer 2015
   Programming Assignment 5 - Image Algorithms


   B. Bird - 06/22/2015
*/ 

import java.awt.Color;
import java.util.List;
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
		LinkedList<PixelVertex> BFSQ = new LinkedList<PixelVertex>();
		LinkedList<PixelVertex> visited = new LinkedList<PixelVertex>();
		viewer.setPixel(v.getX(),v.getY(),fillColour);
		BFSQ.add(v);
		FloodFillBFS(viewer,fillColour,visited,BFSQ);
	}
	
	private static void FloodFillBFS(ImageViewerA5 viewer, Color fillColour, LinkedList<PixelVertex> visited, LinkedList<PixelVertex> BFSQueue){
		PixelVertex v = BFSQueue.removeFirst();
		if (visited.contains(v))
			return;
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
			BFSQueue.add(w);
		}
		FloodFillBFS(viewer,fillColour,visited,BFSQueue);
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
		/* Your code here */
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
		/* Your code here */
	}

	/* CountComponents(G)
	   Count the number of connected components in the provided PixelGraph 
	   object.
	*/
	public static int CountComponents(PixelGraph G){
		/* Your code here */
		return 0;
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