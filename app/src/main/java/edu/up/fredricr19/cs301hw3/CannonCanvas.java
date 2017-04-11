package edu.up.fredricr19.cs301hw3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.util.ArrayList;

/**
 * @author Ryan Fredrickson
 */

public class CannonCanvas extends SurfaceView implements View.OnClickListener, View.OnTouchListener {
    CannonAnimator animator;
    private CannonThread cannonThread = null;
    private Paint backgroundPaint = new Paint();

    Cannon cannon = new Cannon(125, 1600, 0xFF000FFF);

    public CannonCanvas(Context context, Animator anim) {
        super(context);

        setWillNotDraw(false);
        this.setBackgroundColor(0xFFFFFFFF);
        this.animator = (CannonAnimator)anim;

        this.setOnTouchListener((OnTouchListener) this);

        this.cannonThread = new CannonThread(getHolder());
        cannonThread.start();

        backgroundPaint.setColor(this.animator.backgroundColor());

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        this.animator.onClick(v);
        return true;
    }

    /**
     * I borrowed this from the AnimationCanvas provides to us
     *
     * Thread subclass to control the game loop
     *
     * Code adapted from Android:How to Program by Deitel, et.al., first edition
     * copyright (C)2013.
     *
     */
    private class CannonThread extends Thread {

        // a reference to a SurfaveView's holder. This is used to "lock" the
        // canvas when we want to write to it
        private SurfaceHolder surfaceHolder;

        // controls animation stop/go based upon instructions from the Animator
        private boolean threadIsRunning = true;

        /** ctor inits instance variables */
        public CannonThread(SurfaceHolder holder) {
            surfaceHolder = holder;
            setName("CannonThread");
        }

        /**
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

        /**
         * This is the main animation loop. It calls the Animator's draw()
         * method at regular intervals to creation the animation.
         */
        @Override
        public void run() {

            Canvas canvas = null;// ref to canvas animator draws upon
            long lastTickEnded = 0; // when the last tick ended

            while (threadIsRunning) {

                // stop if the animator asks for it
                if (animator.doQuit())
                    break;

                // pause while the animator wishes it
                while (animator.doPause()) {
                    sleep(animator.interval());
                }// while

                // Pause to honor the animator's tick frequency specification
                long currTime = System.currentTimeMillis();
                long remainingWait = animator.interval()
                        - (currTime - lastTickEnded);
                if (remainingWait > 0) {
                    sleep((int) remainingWait);
                }

                // Ok! We can draw now.
                try {
                    // lock the surface for drawing
                    canvas = surfaceHolder.lockCanvas(null);

                    //paint the background
                    if (canvas != null)
                    {
                        canvas.drawRect(0, 0, getWidth(), getHeight(), backgroundPaint);

                        // tell the animator to draw the next frame
                        synchronized (surfaceHolder) {
                            animator.tick(canvas);
                        }// synchronized
                    }
                }// try
                finally {
                    // release the canvas
                    if (canvas != null) {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                }

                // Note when this tick ended
                lastTickEnded = System.currentTimeMillis();

            }// while
        }// run
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
            spotList.get(i).setColor(0xFFF0000F);
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
