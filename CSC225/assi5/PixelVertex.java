/* PixelVertex.java
   CSC 225 - Summer 2015
   Programming Assignment 4 - Pixel Vertex Data Structure


   B. Bird - 04/08/2015
*/

import java.util.LinkedList;

public class PixelVertex{
    
    private int x;
    private int y;
    private int degree;
    private LinkedList<PixelVertex> neighbours;
    
    
    public PixelVertex(int x, int y){
        this.degree = 0;
        this.neighbours = new LinkedList<PixelVertex>();
        this.x = x;
        this.y = y;
    }
	
	
	/* getX()
	   Return the x-coordinate of the pixel corresponding to this vertex.
	*/
	public int getX(){
		return this.x;
	}
	
	/* getY()
	   Return the y-coordinate of the pixel corresponding to this vertex.
	*/
	public int getY(){
		return this.y;
	}
	
	/* getNeighbours()
	   Return an array containing references to all neighbours of this vertex.
	*/
	public PixelVertex[] getNeighbours(){
        if (this.degree == 0)
            return null;
		PixelVertex[] neighbourArray = this.neighbours.toArray(new PixelVertex[this.degree]);
		return neighbourArray;
	}
	
	/* addNeighbour(newNeighbour)
	   Add the provided vertex as a neighbour of this vertex.
	*/
	public void addNeighbour(PixelVertex newNeighbour){
		this.neighbours.add(newNeighbour);
        this.degree++;
	}
	
	/* removeNeighbour(neighbour)
	   If the provided vertex object is a neighbour of this vertex,
	   remove it from the list of neighbours.
	*/
	public void removeNeighbour(PixelVertex neighbour){
		this.neighbours.remove(neighbour);
        this.degree--;
	}
	
	/* getDegree()
	   Return the degree of this vertex.
	*/
	public int getDegree(){
		return this.degree;
	}
	
	/* isNeighbour(otherVertex)
	   Return true if the provided PixelVertex object is a neighbour of this
	   vertex and false otherwise.
	*/
	public boolean isNeighbour(PixelVertex otherVertex){
		if (this.neighbours.contains(otherVertex))
            return true;
        else
            return false;
	}
	
}