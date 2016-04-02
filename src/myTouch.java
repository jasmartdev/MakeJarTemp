/**
 * 
 */
package jasmartdev.untils;

import android.view.MotionEvent;

public class myTouch {

	public static int x;
	public static int y;
	public static int last_x;
	public static int last_y;
	public static float x_scale;
	public static float y_scale;
	public static int btnState;
	public static boolean istouch = false;
	public static boolean isdrag = false;
	
	public interface touchState {
		final static int UNTOUCH = 0;
		final static int TOUCH = 1;
	}
	
	public myTouch() {
		this.x = -1;
		this.y = -1;
		btnState = touchState.UNTOUCH;
		istouch = false;
		isdrag = false;
		x_scale = 1;
		y_scale = 1;
	}

	public myTouch(float x_scale, float y_scale) {
		this.x = -1;
		this.y = -1;
		btnState = touchState.UNTOUCH;
		istouch = false;
		isdrag = false;
		this.x_scale = x_scale;
		this.y_scale = y_scale;
	}
	
	public boolean onTouchEvent(MotionEvent event) {

		x = (int)(event.getX()*x_scale);
		y = (int)(event.getY()*y_scale);
		int action = event.getAction();
		switch(action){
			case MotionEvent.ACTION_DOWN:
				last_x = x;
				last_y = y;
				istouch = true;
				isdrag = false;
				break;
			case MotionEvent.ACTION_MOVE:
				istouch = true;
				isdrag = true;
				break;
			case MotionEvent.ACTION_UP:
			case MotionEvent.ACTION_CANCEL:
			case MotionEvent.ACTION_OUTSIDE:
				istouch = false;
				isdrag = false;
				break;
			default:
		}
		return istouch;
	}
	public static void resetTouch()
	{
		x = -1;
		y = -1;
		btnState = touchState.UNTOUCH;
		istouch = false;
		isdrag = false;
	}
	public boolean getDrag() {
		return isdrag;
	}
	public int getDragDeltaX() {
		return (int)((x - last_x)*x_scale);
	}
	public boolean getTouch() {
		return istouch;
	}
	public void setTouch(boolean touch) {
		this.istouch = touch;
	}
	public int getState() {
		return btnState;
	}
	public void setState(int state) {
		this.btnState = state;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = (int)(x*x_scale);
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = (int)(y*y_scale);
	}
}
