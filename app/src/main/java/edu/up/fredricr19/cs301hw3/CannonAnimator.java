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
    private CannonBall ball;

    @Override
    public int interval() { return 33; }

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
        ball.setPos(this.x, (int)(this.y*0.85));
        ball.draw(canvas);
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
