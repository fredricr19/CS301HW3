package edu.up.fredricr19.cs301hw3;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * @author Ryan Fredrickson
 */

public class CannonAnimator implements Animator {
    private int x = 125;
    private int y = 1600;
    private boolean apex = false;
    private boolean quit = false;
    private CannonBall ball;

    private Paint cBallColor = new Paint();

    Cannon cannon = new Cannon(125, 1600, 0xFF000FFF);

    @Override
    public int interval() { return 33; }

    @Override
    public int backgroundColor(){ return 0xFF00FFFF; }

    @Override
    public boolean doPause() {
        return false;
    }

    @Override
    public boolean doQuit() { return this.quit; }

    public void setQuit(boolean q){ this.quit = q; }

    @Override
    public void tick(Canvas canvas) {
        cannon.drawMe(canvas);

        if(ball == null){ return; }

        cBallColor.setColor(Color.BLACK);

        if(this.x > 750) apex = true;

        this.x++;
        if(!apex) this.y--;
        else this.y++;

        ball = new CannonBall(this.x, this.y, 35, 0xFFFFFFFF);
        ball.draw(canvas);
    }

    @Override
    public void onClick(View v) {
        ball = new CannonBall(this.x, this.y, 35, 0xFFFFFFFF);

        ball.setVX(15f);
        ball.setVY(15f);
    }
}
