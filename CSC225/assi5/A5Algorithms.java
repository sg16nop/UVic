/* A5Algorithms.java
   CSC 225 - Summer 2015
   Programming Assignment 5 - Image Algorithms


   B. Bird - 06/22/2015
*/ 

import java.awt.Color;

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
		/* Your code here */
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
		/* Your code here */
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