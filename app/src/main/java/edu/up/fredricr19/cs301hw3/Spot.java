package edu.up.fredricr19.cs301hw3;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/** This class represents a single round spot (circle) on the screen */
public class Spot {
	
	public static final int INIT_SIZE = 20;
	public static final int MIN_SIZE = 3;
	public static final int MAX_SIZE = 100;

	protected float x; // x-coord
	protected float y; // y-coord
	protected float vx;
	protected float vy;
	private int size = INIT_SIZE; // all spots begin at size 20
	protected Paint myPaint; // how the spot is drawn
	protected int maxX; // width of the screen (pixels)
	protected int maxY; // height of the screen (pixels)

	/** gives the spot a random colored paint */
	protected void setRandomPaint() {
		int color = Color.rgb((int) (Math.random() * 256),
				(int) (Math.random() * 256), (int) (Math.random() * 256));
		myPaint = new Paint();
		myPaint.setColor(color);
	}

	/** this ctor initializes a spot with random values */
	public Spot() {
		// place a spot in a random location
		x = (int) (Math.random() * 500) + 5;
		y = (int) (Math.random() * 500) + 5;
		vx = vy = 0;
		setRandomPaint();
	}

	/** this ctor creates a spot at a specified location */
	public Spot(int initX, int initY) {
		// place a spot in a random location
		x = initX;
		y = initY;
		vx = vy = 0;
		setRandomPaint();
	}

	/** changes the spot's color */
	public void setColor(int newColor) {
		myPaint.setColor(newColor);
	}
	
	/**
	 * setPos
	 * 
	 * sets the spot's x,y position.
	 * 
	 * CAVEAT:  Caller is responsible for giving valid values!
	 * 
	 * @param newX
	 * 	the new x,y position
	 */
	public void setPos(int newX, int newY)
	{
		this.x = newX;
		this.y = newY;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int newSize) {
		if ((newSize >= MIN_SIZE) && (newSize <= MAX_SIZE))
		{
			this.size = newSize;
		}
	}

	/**
	 * adjusts the spot's position by a given delta. If the change would move
	 * the spot outside of a given boundary, it is stopped at that boundary
	 * 
	 * @param aX
	 *            how much to change the spots x,y position by
	 */
	public void moveSpot(float aX, float aY, int maxX, int maxY) {

		//apply the requested change
		vx += aX;
		vy += aY;
		
		vx *= 0.99f;
		vy *= 0.99f;
		
		x += vx;
		y += vy;

		// adjust the spot if it goes off an edge
		if (x + getSize() > maxX) {
			x = maxX - getSize();
			vx = -0.8f * vx;
		}
		if (y + getSize() > maxY) {
			y = maxY - getSize();
			vy = -0.8f * vy;
		}
		if (x - getSize() < 0) {
			x = getSize();
			vx = -0.8f * vx;
		}
		if (y - getSize() < 0) {
			y = getSize();
			vy = -0.8f * vy;
		}
	}//moveSpot
	
	/**
	 * determines whether this spot overlaps another one
	 * 
	 * @param other  the other spot that I may overlap
	 * @return true if this spot overlaps the given spot
	 */
	public boolean overlaps(Spot other)
	{
		//Determine the distance between the two spots
		float xDist = Math.abs (other.x - this.x);
		float yDist = Math.abs (other.y - this.y);
		float dist = (int)Math.sqrt(xDist*xDist + yDist*yDist);

		//which spot is largest?
		float largeSize = Math.max(this.getSize(), other.getSize());
		
		return dist <= largeSize;
	}//overlaps
	

	/**
	 * a spot knows how to draw itself on a given canvas
	 * 
	 * @param canvas
	 *            is the canvas to draw upon
	 */
	public void draw(Canvas canvas) {
		canvas.drawCircle(x, y, getSize(), myPaint);
	}
}// class Spot

