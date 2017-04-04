package edu.up.fredricr19.cs301hw3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;

/**
 * @author Ryan Fredrickson
 */

public class CannonCanvas extends SurfaceView implements View.OnTouchListener {
    CannonAnimator animator;
    private Paint backgroundPaint = new Paint();

    Cannon cannon = new Cannon(125, 1600, 0xFF000FFF);

    public CannonCanvas(Context context, Animator anim) {
        super(context);

        setWillNotDraw(false);
        this.setBackgroundColor(0xFFFFFFFF);
        this.animator = (CannonAnimator)anim;

        this.setOnTouchListener(this);

        backgroundPaint.setColor(this.animator.backgroundColor());

    }

    @Override
    protected void onDraw(Canvas canvas){
        cannon.drawMe(canvas);

        Paint target = new Paint();
        target.setColor(0xFFFF000F);

        canvas.drawCircle(1000, 1600, 50, target);
        canvas.drawCircle(1300, 1100, 75, target);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        this.animator.onTouch(event);
        return true;
    }
}
