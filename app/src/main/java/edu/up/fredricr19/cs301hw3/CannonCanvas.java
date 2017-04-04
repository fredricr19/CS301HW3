package edu.up.fredricr19.cs301hw3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceView;
import android.view.View;

import java.util.ArrayList;

/**
 * @author Ryan Fredrickson
 */

public class CannonCanvas extends SurfaceView implements View.OnClickListener {
    CannonAnimator animator;
    private Paint backgroundPaint = new Paint();

    Cannon cannon = new Cannon(125, 1600, 0xFF000FFF);

    public CannonCanvas(Context context, Animator anim) {
        super(context);

        setWillNotDraw(false);
        this.setBackgroundColor(0xFFFFFFFF);
        this.animator = (CannonAnimator)anim;

        //this.setOnTouchListener(this);

        backgroundPaint.setColor(this.animator.backgroundColor());

    }

    /**
     * I got this from AnimationCanvas
     * causes this thread to pause for a given interval.
     *
     * @param interval
     *            duration in milliseconds
     */
    private void sleep(int interval) {
        try {
            Thread.sleep(interval); // use sleep to avoid busy wait
        } catch (InterruptedException ie) {
            // don't care if we're interrupted
        }
    }// sleep

    @Override
    protected void onDraw(Canvas canvas){
        cannon.drawMe(canvas);

        Paint targetPaint = new Paint();
        targetPaint.setColor(0xFFFF000F);


        ArrayList<Spot> spotList = new ArrayList<Spot>();

        spotList.add(new Spot(1000, 1600));
        spotList.add(new Spot(1300, 1100));

        for(int i = 0; i < spotList.size(); i++){
            spotList.get(i).setColor(0xFFFF000F);
            spotList.get(i).draw(canvas);
        }

        while(!animator.doQuit()){
            animator.tick(canvas);
            sleep(animator.interval());
            animator.setQuit(true);
        }


    }
    @Override
    public void onClick(View v) {
        this.animator.onClick(v);
    }
}
