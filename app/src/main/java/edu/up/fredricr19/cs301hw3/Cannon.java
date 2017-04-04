package edu.up.fredricr19.cs301hw3;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * @author Ryan Fredrickson
 */

public class Cannon {
    int x, y;
    float degrees = -45f;

    Paint myPaint = new Paint();
    Paint outlinePaint = new Paint();

    public Cannon(int x, int y, int color){
        this.x = x;
        this.y = y;

        myPaint.setColor(color);
        outlinePaint.setColor(0xFF000000);
    }

    public void drawMe(Canvas canvas) {
        canvas.rotate(degrees, this.x, this.y+35);
        canvas.drawRect(this.x-5, this.y-5, this.x+255, this.y+85, outlinePaint);
        canvas.drawCircle(this.x, this.y+40, 85, outlinePaint);
        canvas.drawCircle(this.x, this.y+40, 80, myPaint);
        canvas.drawRect(this.x, this.y, this.x+250, this.y+80, myPaint);
        canvas.rotate(-degrees, this.x, this.y+35);
    }

    public void setDegrees(float d){ this.degrees = d; }
}
