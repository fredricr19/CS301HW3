package edu.up.fredricr19.cs301hw3;

import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author Ryan Fredrickson
 */

public class CannonAnimator implements Animator {
    private int x = 30;
    private int y = 30;
    CannonBall ball;

    @Override
    public int interval() { return 10; }

    @Override
    public int backgroundColor(){ return 0xFFFFFFFF; }

    @Override
    public boolean doPause() {
        return false;
    }

    @Override
    public boolean doQuit() {
        return false;
    }

    @Override
    public void tick(Canvas canvas) {
        ball = new CannonBall(this.x, (int)(this.y*0.85), 40, 0xFFFFFFFF);
        ball.drawMe(canvas);
    }

    @Override
    public void onTouch(MotionEvent event) {

    }

    public void onClick(View v, int x, int y) {
        this.x = x;
        this.y = y;
        ball = new CannonBall(this.x, this.y, 40, 0xFFFFFFFF);
    }
}
