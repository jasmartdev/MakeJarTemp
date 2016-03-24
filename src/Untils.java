/**
 * 
 */
package Untils;

import android.graphics.Rect;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Color;
import android.graphics.Typeface;
import java.lang.StringBuffer;

public class Untils {

	public static boolean isTouchInRect(int x, int y, int left, int top, int w, int h)
	{
		return (x > left && x < left + w && y > top && y < top + h);
	}
	
	public static boolean isTouchInRect(int x, int y, Rect r)
	{
		return r.contains(x,y);
	}
	
	public static Bitmap RotateBitmap(Bitmap source, float angle)
	{
		  Matrix matrix = new Matrix();
		  matrix.postRotate(angle);
		  return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
	}
	
	public static void Dbg(String s)
	{
		System.out.println("+++ DEBUG +++ : "+s);
	}
	public static void Dbg_scr(Canvas s_canvas, String s, int x, int y)
	{
		s_canvas.drawText(s, x, y, new Paint());
	}
	public static void drawRect(Canvas canvas, Rect r)
	{
		Paint myPaint = new Paint();
		myPaint.setColor(Color.RED);
		myPaint.setStyle(Paint.Style.STROKE);
		myPaint.setStrokeWidth(2);
		canvas.drawRect(r, myPaint);
	}
	public static void drawString(Canvas canvas, String s, int x, int y, int cl, int size, int align)
	{
		Paint myPaint = new Paint(Paint.FAKE_BOLD_TEXT_FLAG);		
		myPaint.setColor(cl);
		myPaint.setTextSize(size);
		myPaint.setStyle(Paint.Style.STROKE);
		if(align == Align.LEFT || align == Align.NONE)
			myPaint.setTextAlign(Paint.Align.LEFT);
		else if(align == Align.RIGHT)
			myPaint.setTextAlign(Paint.Align.RIGHT);
		else if(align == Align.CENTER)
			myPaint.setTextAlign(Paint.Align.CENTER);
		canvas.drawText(s, x, (int)(y + size/2), myPaint);
	}
	public static void drawPage(Canvas canvas, String s, int x, int y, int cl, int size, int linespace, int align)
	{
		Paint myPaint = new Paint(Paint.FAKE_BOLD_TEXT_FLAG);
		myPaint.setColor(cl);
		myPaint.setTextSize(size);
		myPaint.setStyle(Paint.Style.STROKE);
		if(align == Align.LEFT || align == Align.NONE)
			myPaint.setTextAlign(Paint.Align.LEFT);
		else if(align == Align.RIGHT)
			myPaint.setTextAlign(Paint.Align.RIGHT);
		else if(align == Align.CENTER)
			myPaint.setTextAlign(Paint.Align.CENTER);
		int countline = countLine(s);
		int dy = -(countline*(size+linespace)/2);
		for(String line: s.split("\n")){			
			canvas.drawText(line, (int)(x), (int)(y + dy), myPaint);
			dy+=(size+linespace);
		}
	}
	public static void drawPage(Canvas canvas, String s, int x, int y, int cl, int size, int align)
	{
		drawPage(canvas, s, x, y, cl, size, 0, align);
	}
	public static String wrapText(String s, int w, int size)
	{
		Paint myPaint = new Paint(Paint.FAKE_BOLD_TEXT_FLAG);
		myPaint.setTextSize(size);
		myPaint.setStyle(Paint.Style.STROKE);
		StringBuffer sbf = new StringBuffer(s);
		int start = 0, last = 0;
		for(int i = 0; i < sbf.length(); i++)
		{
			if(myPaint.measureText(sbf, start, i) < w)
			{
				if(sbf.charAt(i) == ' ' || sbf.charAt(i) == '\t')
				{
					last = i;
				}
			}			
			else
			{
				sbf.setCharAt(last,'\n');
				start = last;
				last = i;					
			}
		}
		return sbf.toString();
	}
	public static int countLine(String s)
	{
		int count = 1;
		for(int i = 0; i < s.length(); i++)
		{
			if(s.charAt(i) == '\n')
			{
				count++;
			}
		}
		return count;
	}
	public static void sleep(int time)
	{
		try {
			Thread.sleep(time);
		} catch(Exception ec){}
	}
}
