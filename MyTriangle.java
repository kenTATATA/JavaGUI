import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class MyTriangle extends MyDrawing{
	public MyTriangle(int xpt, int ypt) {
		super(xpt,ypt);
	}
	
	public MyTriangle(int xpt, int ypt, int width, int height) {
		super(xpt,ypt,width,height);
	}
	
	public MyTriangle(int xpt, int ypt, int width, int height, Color lc, Color fc) {
		super(xpt,ypt,width,height,lc,fc);
	}
	
	public void draw(Graphics g) {
		int x = getX();
		int y = getY();
		int w = getW();
		int h = getH();
		Color fillColor = getFillColor();
		Color lineColor = getLineColor();
		//高さや横幅が負の時のための処理
		if (w<0) {
			x += w;
			w *= -1;
		}
		if (h<0) {
			y += h;
			h *= -1;
		}
		
		int w2 = (int) w/2;
		int[] xpts = {x+w2,x+w,x};
		int[] ypts = {y,y+h,y+h};
		
		Graphics2D g2 = (Graphics2D) g;
		if(getDashed())
			g2.setStroke(new MyDashStroke(getLineWidth()));
		else
			g2.setStroke(new BasicStroke(getLineWidth()));
		if(getShadow()) {
			int[] sxpts = {x+w2+5,x+w+5,x+5};
			int[] sypts = {y+5,y+h+5,y+h+5};
			g2.setColor(Color.black);
			g2.fillPolygon(sxpts, sypts, 3);
			g2.drawPolygon(sxpts, sypts, 3);
		}
		g2.setColor(fillColor);
		g2.fillPolygon(xpts, ypts, 3);
		g2.setColor(lineColor);
		g2.drawPolygon(xpts, ypts, 3);

		if(isSelected){
			super.draw(g2);
		}
	}
}
