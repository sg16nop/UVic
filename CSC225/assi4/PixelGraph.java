/* PixelGraph.java
   CSC 225 - Summer 2015
   Programming Assignment 4 - Pixel Graph Data Structure

   B. Bird - 04/08/2015
*/ 

import java.awt.Color;

public class PixelGraph{
    
    private PixelVertex[][] graph;
    private int height;
    private int width;

	/* PixelGraph constructor
	   Given a 2d array of colour values (where element [x][y] is the colour 
	   of the pixel at position (x,y) in the image), initialize the data
	   structure to contain the pixel graph of the image. 
	*/
	public PixelGraph(Color[][] imagePixels){
		this.width = imagePixels.length;
        this.height = imagePixels[0].length;
        // this is assuming the canvas is rectangular
        
        this.graph = new PixelVertex[this.width][this.height];
        
        for (int i = 0; i < this.width; i++){
            for (int j = 0; j < this.height; j++){
                
                graph[i][j] = new PixelVertex(i,j);
                
                if (i > 0){
                    if (imagePixels[i][j].equals(imagePixels[i-1][j])){
                        // look back in the x-axis to see if equal
                        graph[i][j].addNeighbour(graph[i-1][j]);
                        graph[i-1][j].addNeighbour(graph[i][j]);
                    }
                }
                
                if (j>0){
                    if (imagePixels[i][j].equals(imagePixels[i][j-1])){
                        // look back in the y-axis to see if equal
                        graph[i][j].addNeighbour(graph[i][j-1]);
                        graph[i][j-1].addNeighbour(graph[i][j]);
                    }
                    
                }
            }
            
        }
        
        
	}
	
	/* getPixelVertex(x,y)
	   Given an (x,y) coordinate pair, return the PixelVertex object 
	   corresponding to the pixel at the provided coordinates.
	   This method is not required to perform any error checking (and you may
	   assume that the provided (x,y) pair is always a valid point in the 
	   image).
	*/
	public PixelVertex getPixelVertex(int x, int y){
		return graph[x][y];
	}
	
	/* getWidth()
	   Return the width of the image corresponding to this PixelGraph 
	   object.
	*/
	public int getWidth(){
		return this.width;
	}
	
	/* getHeight()
	   Return the height of the image corresponding to this PixelGraph 
	   object.
	*/
	public int getHeight(){
		return this.height;
	}
	
}