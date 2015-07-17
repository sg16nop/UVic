/* PixelVertex.java
   CSC 225 - Summer 2015
   Programming Assignment 4 - Pixel Vertex Data Structure


   B. Bird - 04/08/2015
*/

import java.util.List;

public class PixelVertex{

	/* Add a constructor here (if necessary) */
	
	
	/* getX()
	   Return the x-coordinate of the pixel corresponding to this vertex.
	*/
	public int getX(){
		/* Your code here */
		return -1;
	}
	
	/* getY()
	   Return the y-coordinate of the pixel corresponding to this vertex.
	*/
	public int getY(){
		/* Your code here */
		return -1;
	}
	
	/* getNeighbours()
	   Return an array containing references to all neighbours of this vertex.
	*/
	public PixelVertex[] getNeighbours(){
		/* Your code here */
		return null;
	}
	
	/* addNeighbour(newNeighbour)
	   Add the provided vertex as a neighbour of this vertex.
	*/
	public void addNeighbour(PixelVertex newNeighbour){
		/* Your code here */
	}
	
	/* removeNeighbour(neighbour)
	   If the provided vertex object is a neighbour of this vertex,
	   remove it from the list of neighbours.
	*/
	public void removeNeighbour(PixelVertex neighbour){
		/* Your code here */
	}
	
	/* getDegree()
	   Return the degree of this vertex.
	*/
	public int getDegree(){
		/* Your code here */
		return -1;
	}
	
	/* isNeighbour(otherVertex)
	   Return true if the provided PixelVertex object is a neighbour of this
	   vertex and false otherwise.
	*/
	public boolean isNeighbour(PixelVertex otherVertex){
		/* Your code here */
		return false;
	}
	
}