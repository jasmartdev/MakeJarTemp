/**
 * 
 */
package jasmartdev.untils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Color;
import android.graphics.Rect;
import android.content.Context;

import jasmartdev.untils.Align;


public class myButtons {

	private Bitmap bitmap;
	private Bitmap bitmap_touched;
	private int dataID;
	private int dataID2;
	private Rect sourceRect;
	private Rect curRect;
	private int btnWidth;
	private int btnHeight;	
	private int x;
	private int y;
	private int align;
	private int btnState;
	private boolean btnActive;
	private int btnID;
	private String text;
	private int textAlign;
	private int textColor;
	private int textSize;
	private boolean isCache;
	private Context context;
	
	public myButtons(int dataID, int dataID2, int id, int x, int y, int align, String text, int textColor, int textSize, int textAlign) {
		this.dataID = dataID;
		this.dataID2 = dataID2;
		this.x = x;
		this.y = y;
		this.align = align;
		this.btnID = id;
		btnState = buttonState.UNTOUCH;
		btnActive = false;
		isCache = false;
		this.text = new String(text);
		this.textColor = textColor;
		this.textSize = textSize;
		this.textAlign = textAlign;
	}

	public myButtons(int dataID, int dataID2, int id, int x, int y, int align) {
		this.dataID = dataID;
		this.dataID2 = dataID2;
		this.x = x;
		this.y = y;
		this.align = align;
		this.btnID = id;
		btnState = buttonState.UNTOUCH;
		btnActive = false;
		isCache = false;
		this.text = null;
		this.textColor = 0xff000000;
		this.textSize = 14;
		this.textAlign = Align.CENTER;
	}
	public void Cache(boolean isCache)
	{
		this.isCache = isCache;
	}
	public void Load(Context context)
	{
		this.context = context;
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inPreferredConfig = Bitmap.Config.ARGB_8888;
		bitmap = BitmapFactory.decodeResource(context.getResources(), dataID, options);
		bitmap_touched = BitmapFactory.decodeResource(context.getResources(), dataID2, options);
		btnWidth = bitmap.getWidth();
		btnHeight = bitmap.getHeight();
		sourceRect = new Rect(0, 0, btnWidth, btnHeight);
		if(!isCache)
		{
			bitmap = null;
			bitmap_touched = null;
		}
		curRect = new Rect(x, y, x + btnWidth, y + btnHeight);
		if((align & Align.HCENTER) != 0)
		{
			curRect.left -= getWidth()/2;
			curRect.right -= getWidth()/2;
		}
		else if((align & Align.RIGHT) != 0)
		{
			curRect.left -= getWidth();
			curRect.right -= getWidth();
		}
		if((align & Align.VCENTER) != 0)
		{
			curRect.top -= getHeight()/2;
			curRect.bottom -= getHeight()/2;
		}
		else if((align & Align.BOTTOM) != 0)
		{
			curRect.top -= getHeight();
			curRect.bottom -= getHeight();
		}
	}
	
	public interface buttonState {
		final static int UNTOUCH = 0;
		final static int TOUCH = 1;
	}	
	public Bitmap getBitmap() {
		return bitmap;
	}
	public Bitmap getBitmap_touched() {
		return bitmap_touched;
	}
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	public void setBitmap_touched(Bitmap bitmap) {
		this.bitmap_touched = bitmap_touched;
	}
	public Rect getRect() {
		return new Rect(curRect);
	}
	public Rect getSourceRect() {
		return new Rect(sourceRect);
	}
	public void setSourceRect(Rect sourceRect) {
		this.sourceRect = sourceRect;
	}
	public int getState() {
		return btnState;
	}
	public void setState(int state) {
		this.btnState = state;
	}
	public int getID() {
		return btnID;
	}
	public void setID(int id) {
		this.btnID = id;
	}
	public boolean getActive() {
		return btnActive;
	}
	public void setActive(boolean active) {
		this.btnActive = active;
	}
	public int getLeft() {
		return getRect().left;
	}
	public int getRight() {
		return getRect().right;
	}
	public int getTop() {
		return getRect().top;
	}
	public int getBottom() {
		return getRect().bottom;
	}
	public int getCenterX() {
		return getRect().centerX();
	}
	public int getCenterY() {
		return getRect().centerY();
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = new String(text);
	}
	public int getTextColor() {
		return textColor;
	}
	public void setTextColor(int textColor) {
		this.textColor = textColor;
	}
	public int gettextSize() {
		return textSize;
	}
	public void settextSize(int textSize) {
		this.textSize = textSize;
	}
	public int getTextAlign() {
		return textAlign;
	}
	public void setTextAlign(int textAlign) {
		this.textAlign = textAlign;
	}
	public int getWidth() {
		return btnWidth;
	}
	public void setWidth(int btnWidth) {
		this.btnWidth = btnWidth;
	}
	public int getHeight() {
		return btnHeight;
	}
	public void setHeight(int btnHeight) {
		this.btnHeight = btnHeight;
	}
	public void update(boolean touch, int x, int y) {
		if(touch)
		{
			if(Untils.isTouchInRect(x, y, getRect()))
				setState(buttonState.TOUCH);
			else
				setState(buttonState.UNTOUCH);
		}
		else
		{
			if(getState() == buttonState.TOUCH && Untils.isTouchInRect(x, y, getRect()))
			{
				setActive(true);
			}
			else
			{
				setActive(false);
			}			
			setState(buttonState.UNTOUCH);
		}
	}
	
	public void draw(Canvas canvas) {
		if(getState() == buttonState.UNTOUCH)
		{
			if(bitmap == null)
			{
				BitmapFactory.Options options = new BitmapFactory.Options();
				options.inPreferredConfig = Bitmap.Config.ARGB_8888;
				bitmap = BitmapFactory.decodeResource(context.getResources(), dataID, options);
			}
			canvas.drawBitmap(bitmap, sourceRect, getRect(), null);
			if(!isCache)
			{
				bitmap = null;
			}
		}
		else
		{
			if(bitmap_touched == null)
			{
				BitmapFactory.Options options = new BitmapFactory.Options();
				options.inPreferredConfig = Bitmap.Config.ARGB_8888;
				bitmap_touched = BitmapFactory.decodeResource(context.getResources(), dataID2, options);
			}
			canvas.drawBitmap(bitmap_touched, sourceRect, getRect(), null);
			if(!isCache)
			{
				bitmap_touched = null;
			}
		}
		if(text != null)
		{
			Untils.drawString(canvas, text, getCenterX(), getCenterY(), textColor, textSize, textAlign);
		}
		if(Configs.debug_Rect)
			Untils.drawRect(canvas, getRect());
	}
}
