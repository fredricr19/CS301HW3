package edu.up.fredricr19.cs301hw3;

import android.graphics.Canvas;
import android.view.View;

/**
 * @author Ryan Fredrickson
 */

public class CannonAnimator implements Animator {
    private int x = 125;
    private int y = 1600;
    private boolean apex = false;
    private CannonBall ball;

    @Override
    public int interval() { return 100; }

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
        if(this.x > 750){
            apex = true;
        }

        ball.vy = (float)(ball.vy*.9);
        if(!apex){
            y -= y*ball.vy;
        }else{
            y += y*ball.vy;
        }

        x += ball.vx;


        ball.setPos(this.x, (float)(this.y*0.85));
        ball.draw(canvas);
    }

    @Override
    public void onClick(View v) {
        ball = new CannonBall(this.x, this.y, 40, 0xFFFFFFFF);
        ball.vx = 15;
        ball.vy = 15;
    }
}
