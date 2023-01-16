import java.awt.*;
import java.io.Serializable;

public class MyDrawing implements Cloneable, Serializable {
	int x, y, w, h;
	Color lineColor, fillColor;
	int lineWidth, blightness;
	boolean isDashed, hasShadow, isSelected, select;
	Shape region;
	final int SIZE = 7;
	
	public MyDrawing() {
		x = y = 0;
		w = h = 40;
		lineColor = Color.black;
		fillColor = Color.white;
		blightness = 255;
		lineWidth = 1;
		setRegion();
	}
	
	public MyDrawing(int xpt, int ypt) {
		this();
		setLocation(xpt,ypt);
	}
	
	public MyDrawing(int xpt, int ypt, int width, int height) {
		this(xpt,ypt);
		setSize(width,height);
	}
	
	public MyDrawing(int xpt, int ypt, int width, int height, Color lineColor, Color fillColor){
		this(xpt, ypt, width, height);
		setColor(lineColor,fillColor);
	}
	
	
	public void draw(Graphics g) {
		if(isSelected) {
			g.setColor(new Color(0,0,0,100));
			// g.drawLine(x, y, x+w, y);
			// g.drawLine(x, y, x, y+h);
			// g.drawLine(x+w, y, x+w, y+h);
			// g.drawLine(x, y+h, x+w, y+h);
			g.fillRect(x+w/2-SIZE/2,y-SIZE/2,SIZE,SIZE);
			g.fillRect(x-SIZE/2,y+h/2-SIZE/2,SIZE,SIZE);
			g.fillRect(x+w/2-SIZE/2,y+h-SIZE/2,SIZE,SIZE);
			g.fillRect(x+w-SIZE/2,y+h/2-SIZE/2,SIZE,SIZE);
			g.fillRect(x-SIZE/2,y-SIZE/2,SIZE,SIZE);
			g.fillRect(x+w-SIZE/2,y-SIZE/2,SIZE,SIZE);
			g.fillRect(x-SIZE/2,y+h-SIZE/2,SIZE,SIZE);
			g.fillRect(x+w-SIZE/2,y+h-SIZE/2,SIZE,SIZE);
		}
	}
	
	public void move(int dx, int dy) {
		this.x += dx;
		this.y += dy;
	}
	
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setSize(int w, int h) {
		this.w = w;
		this.h = h;
	}
	
	public void setColor(Color lineColor, Color fillColor) {
		this.lineColor = lineColor;
		this.fillColor = fillColor;
		setBlightness(this.blightness);
	}
	
	public void setLineWidth(int lineWidth) {
		if(lineWidth>0) {
			this.lineWidth= lineWidth;
		}
	}

	public void setBlightness(int blightness) {
		if(0 <= blightness && blightness < 256) {
			this.blightness = blightness;
			int fR = this.fillColor.getRed();
    		int fG = this.fillColor.getGreen();
    		int fB = this.fillColor.getBlue();
    		this.fillColor = new Color(fR, fG, fB, blightness);
			int lR = this.lineColor.getRed();
    		int lG = this.lineColor.getGreen();
    		int lB = this.lineColor.getBlue();
    		this.lineColor = new Color(lR, lG, lB, blightness);
		}
	}

	public void setDashed(boolean b) {
		this.isDashed = b;
	}

	public void setShadow(boolean s) {
		this.hasShadow = s;
	}

	public void setRegion() {
		if(w<0) {
			x += w;
			w *= -1;
		}
		if(h<0) {
			y += h;
			h *= -1;
		}
		region = new Rectangle(x,y,w,h);
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public void setSelect(boolean select) {
		this.select = select;
	}
	
	public int getX() {
		int x = this.x;
		return x;
	}
	
	public int getY() {
		int y = this.y;
		return y;
	}
	
	public int getW() {
		int w = this.w;
		return w;
	}
	
	public int getH() {
		int h = this.h;
		return h;
	}
	
	public Color getLineColor() {
		Color lineColor = this.lineColor;
		return lineColor;
	}
	
	public Color getFillColor() {
		Color fillColor = this.fillColor;
		return fillColor;
	}
	
	public int getLineWidth() {
		int lineWidth = this.lineWidth;
		return lineWidth;
	}

	public int getBlightness() {
		int blightness = this.blightness;
		return blightness;
	}

	public boolean getDashed() {
		return isDashed;
	}

	public boolean getShadow() {
		return hasShadow;
	}

	public boolean getSelected() {
		return isSelected;
	}

	public boolean contains(int x, int y) {
		return region.contains(x, y);
	}

	public MyDrawing clone() {
		MyDrawing d = null;
		try {
			d = (MyDrawing) super.clone();
		} catch(Exception e) {
			System.out.println("clone failed!");
		}
		return d;
	}
}
