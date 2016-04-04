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

import jasmartdev.untils.Untils;
import jasmartdev.untils.Configs;

/**
 * @author impaler
 *
 */
public class mySprites {
	
	private static final String TAG = mySprites.class.getSimpleName();

	private Bitmap bitmap;
	private Rect sourceRect;
	private Rect curRect;
	private int frameNr;
	private int currentFrame;
	private long frameTicker;
	private int framePeriod;	
	private int spriteWidth;
	private int spriteHeight;	
	private int x;
	private int y;
	private int dataID;
	private int loop;
	private Rect scaleRect;
	private boolean isCache;
	private Context context;
	
	public mySprites(int dataID, int x, int y, int frameCount, int fps) {
		this.dataID = dataID;
		this.x = x;
		this.y = y;
		currentFrame = 0;
		frameNr = frameCount;
		framePeriod = 1000 / fps;
		frameTicker = 0l;
		loop = -1;
		isCache = false;
	}
	
	public mySprites(int dataID, int x, int y, int frameCount) {
		this.dataID = dataID;
		this.x = x;
		this.y = y;
		currentFrame = 0;
		frameNr = frameCount;
		framePeriod = 1000 / 15;
		frameTicker = 0l;
		loop = -1;
		isCache = false;
	}
	
	public mySprites(int dataID, int x, int y) {
		this.dataID = dataID;
		this.x = x;
		this.y = y;
		currentFrame = 0;
		frameNr = 1;		
		framePeriod = 1000 / 15;
		frameTicker = 0l;
		loop = -1;
		isCache = false;
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
		spriteWidth = this.bitmap.getWidth() / frameNr;
		spriteHeight = this.bitmap.getHeight();
		if(!isCache)
		{
			bitmap = null;
		}
		sourceRect = new Rect(0, 0, spriteWidth, spriteHeight);
	}
	
	public Bitmap getBitmap() {
		return bitmap;
	}
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	public Rect getRect() {
		return new Rect(sourceRect);
	}
	public Rect getSourceRect() {
		return new Rect(sourceRect);
	}
	public void setSourceRect(Rect sourceRect) {
		this.sourceRect = sourceRect;
	}
	public int getFrameNr() {
		return frameNr;
	}
	public void setFrameNr(int frameNr) {
		this.frameNr = frameNr;
	}
	public int getCurrentFrame() {
		return currentFrame;
	}
	public void setCurrentFrame(int currentFrame) {
		this.currentFrame = currentFrame;
	}
	public int getFramePeriod() {
		return framePeriod;
	}
	public void setFramePeriod(int framePeriod) {
		this.framePeriod = framePeriod;
	}
	public int getSpriteWidth() {
		return spriteWidth;
	}
	public void setSpriteWidth(int spriteWidth) {
		this.spriteWidth = spriteWidth;
	}
	public int getSpriteHeight() {
		return spriteHeight;
	}
	public void setSpriteHeight(int spriteHeight) {
		this.spriteHeight = spriteHeight;
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
	public int getLoop() {
		return loop;
	}
	public void setLoop(int loop) {
		this.loop = loop;
		currentFrame = 0;
	}
	public boolean getFinish()
	{
		return (loop == 0);
	}
	public void resetFrame()
	{
		currentFrame = 0;
	}
	public void reset()
	{
		resetFrame();
		sourceRect = new Rect(0, 0, spriteWidth, spriteHeight);
	}
	public void update(long gameTime) {
		if (gameTime > frameTicker + framePeriod) {
			frameTicker = gameTime;
			currentFrame++;
			if (currentFrame >= frameNr) {
				if(loop > 0)
					loop--;
				if(loop != 0)
					currentFrame = 0;
			}
		}
		this.sourceRect.left = currentFrame * spriteWidth;
		this.sourceRect.right = this.sourceRect.left + spriteWidth;
	}
	
	public void update() {
		update(System.currentTimeMillis());
	}
	public void draw(Canvas canvas) {
		Rect destRect;
		destRect = new Rect(getX(), getY(), getX() + this.getSpriteWidth(), getY() + this.getSpriteHeight());
		if(bitmap == null)
		{
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inPreferredConfig = Bitmap.Config.ARGB_8888;
			bitmap = BitmapFactory.decodeResource(context.getResources(), dataID, options);
		}
		canvas.drawBitmap(bitmap, sourceRect, destRect, null);
		if(!isCache)
		{
			bitmap = null;
		}
		if(Configs.debug_Rect)
			Untils.drawRect(canvas, this.getRect());
		if(Configs.debug_Sprite)
		{
			Paint p = new Paint();
			p.setColor(Color.RED);
			canvas.drawText("l:"+loop+"f:"+currentFrame+"/"+frameNr, getX(), getY() + 10, p);
		}
	}
	public void draw(Canvas canvas, int x, int y) {
		Rect destRect;
		destRect = new Rect(getX() + x, getY() + y, getX() + x + this.getSpriteWidth(), getY() + y + this.getSpriteHeight());
		if(bitmap == null)
		{
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inPreferredConfig = Bitmap.Config.ARGB_8888;
			bitmap = BitmapFactory.decodeResource(context.getResources(), dataID, options);
		}
		canvas.drawBitmap(bitmap, sourceRect, destRect, null);
		if(!isCache)
		{
			bitmap = null;
		}
		if(Configs.debug_Rect)
		{
			Rect r = new Rect(getX() + x, getY() + y, getX() + x + this.getSpriteWidth(), getY() + y + this.getSpriteHeight());
			Untils.drawRect(canvas, r);
		}
		if(Configs.debug_Sprite)
		{
			Paint p = new Paint();
			p.setColor(Color.RED);
			canvas.drawText("l:"+loop+"f:"+currentFrame+"/"+frameNr, getX(), getY() + 10, p);
		}
	}
}
