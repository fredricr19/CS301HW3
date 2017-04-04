package edu.up.fredricr19.cs301hw3;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * @author Ryan Fredrickson
 */

public class CannonAnimator implements Animator {
    private int x = 125;
    private int y = 1600;
    private boolean apex = false;
    private CannonBall ball = new CannonBall(this.x, this.y, 40, 0xFFFFFFFF);

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
        if(this.y < 1300){
            apex = true;
        }

        if(!apex){
            y--;
        }else{
            y++;
        }


        ball.setPos(this.x, (float)(this.y*0.85));
        ball.draw(canvas);
    }

    @Override
    public void onTouch(MotionEvent event) {

    }
}
