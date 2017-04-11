package edu.up.fredricr19.cs301hw3;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;

/**
 * CannonMainActivity
 * 
 * This is the activity for the cannon animation. It creates a AnimationCanvas
 * containing a particular Animator object
 * 
 * @author Andrew Nuxoll
 * @version September 2012
 * 
 */
public class CannonMainActivity extends Activity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

	SeekBar cannonAngle;
	Button fire;
	CannonCanvas myCanvas;

	/**
	 * creates an AnimationCanvas containing a TestAnimator.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cannon_main);

		// Create an animation canvas and place it in the main layout
		Animator cannonAnim = new CannonAnimator();
		myCanvas = new CannonCanvas(this, cannonAnim);
		LinearLayout mainLayout = (LinearLayout)this.findViewById(R.id.topLevelLayout);

		cannonAngle = (SeekBar)this.findViewById(R.id.cannonAngle);
		cannonAngle.setProgress(45);
		cannonAngle.setOnSeekBarChangeListener(this);

		fire = (Button)this.findViewById(R.id.fire);
		fire.setOnClickListener(this);

		mainLayout.addView(myCanvas);
	}

	@Override
	public void onClick(View v) {
		myCanvas.onClick(v);
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		myCanvas.animator.cannon.setDegrees((float)(-progress));
		myCanvas.invalidate();
		this.invalidateOptionsMenu();
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar){ }

	@Override
	public void onStopTrackingTouch(SeekBar seekBar){ }
}
