/**
 * 
 */
package jasmartdev.untils;

import android.graphics.Canvas;
import android.graphics.Color;
import android.content.Context;

import jasmartdev.untils.Untils;
import jasmartdev.untils.myButtons;
import jasmartdev.untils.mySprites;
import jasmartdev.untils.Align;

public class myDialog {
	
	int x, y, w, h;
	int bg, yes_up, yes_down, no_up, no_down;
	mySprites spr_bg;
	myButtons btn_yes, btn_no;
	String txt_title, txt_content, txt_yes, txt_no;
	int id_yes = 1, id_no = 0;
	int textSize;
	public static boolean isActive;
	int id;
	
	public myDialog(int bg, int yes_up, int yes_down, int no_up, int no_down, String txt_yes, String txt_no, int textSize, int x, int y)
	{
		this.x = x;
		this.y = y;
		this.bg = bg;
		this.yes_up = yes_up;
		this.yes_down = yes_down;
		this.no_up = no_up;
		this.no_down = no_down;
		this.textSize = textSize;
		this.txt_yes = txt_yes;
		this.txt_no = txt_no;
		isActive = false;
	}
	
	public void Load(Context context)
	{
		spr_bg = new mySprites(bg, x, y);
		spr_bg.Load(context);
		w = spr_bg.getSpriteWidth();
		h = spr_bg.getSpriteHeight();
		btn_yes = new myButtons(yes_up, yes_down, id_yes, x - w/8, y + h - 5, Align.RIGHT | Align.BOTTOM, txt_yes, Color.WHITE, textSize, Align.CENTER);
		btn_yes.Load(context);
		btn_no = new myButtons(no_up, no_down, id_no, x + w/8, y + h - 5, Align.BOTTOM, txt_no, Color.WHITE, textSize, Align.CENTER);
		btn_no.Load(context);
	}
	
	public boolean getActive()
	{
		return isActive;
	}
	public void setActive(boolean isActive)
	{
		this.isActive = isActive;
		btn_yes.setActive(false);
		btn_no.setActive(false);
		myTouch.resetTouch();
	}
	public void setID(int id)
	{
		this.id = id;
	}
	public int getID()
	{
		return this.id;
	}
	public void setContent(String txt_content)
	{
		this.txt_content = txt_content;
	}
	public void setTitle(String txt_title)
	{
		this.txt_title = txt_title;
	}
	public boolean isYes()
	{
		return btn_yes.getActive();
	}
	
	public boolean isNo()
	{
		return btn_no.getActive();
	}
	
	public void update(boolean touch, int x, int y)
	{
		if(!isActive) return;
		btn_yes.update(touch, x, y);
		btn_no.update(touch, x, y);
	}
	
	public void draw(Canvas canvas)
	{
		if(!isActive) return;
		spr_bg.draw(canvas, -w/2, 0);
		btn_yes.draw(canvas);
		btn_no.draw(canvas);
		Untils.drawString(canvas, txt_title, x, y + textSize/2, Color.WHITE, textSize, Align.CENTER);
		Untils.drawPage(canvas, Untils.wrapText(txt_content, w - 4, textSize), x, y + h/2, Color.BLACK, textSize, Align.CENTER);
	}
}
