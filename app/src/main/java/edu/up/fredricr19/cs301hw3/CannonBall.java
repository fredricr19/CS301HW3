package edu.up.fredricr19.cs301hw3;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * @author Ryan Fredrickson
 */

public class CannonBall extends Spot{
    //private int x, y, radius;
    private Paint myPaint = new Paint();
    private Paint outlinePaint = new Paint();

    public CannonBall(int x, int y, int r, int color){
        super(x, y);
        setSize(r);

        myPaint.setColor(color);
        outlinePaint.setColor(0xFF000000);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(x, y, this.getSize()+2, outlinePaint);  //outline around circle
        canvas.drawCircle(x, y, this.getSize(), myPaint);  //main circle
    }

    public void setVX(float a){ this.vx = a; }
    public void setVY(float a){ this.vx = a; }

    public float getX(){ return this.x; }
}