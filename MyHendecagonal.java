import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class MyHendecagonal extends MyDrawing{
	public MyHendecagonal(int xpt, int ypt) {
		super(xpt,ypt);
	}
	
	public MyHendecagonal(int xpt, int ypt, int width, int height) {
		super(xpt,ypt,width,height);
	}
	
	public MyHendecagonal(int xpt, int ypt, int width, int height, Color lc, Color fc) {
		super(xpt,ypt,width,height,lc,fc);
	}
	
	public void draw(Graphics g) {
		int x = getX();
		int y = getY();
		int w = getW();
		int h = getH();
		
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
		int h2 = (int) h/2;
		int x0 = x+w2;
		int y0 = y+h2;
		int[] xpts = new int[11];
		int[] ypts = new int[11];
		int[] sxpts = new int[11];
		int[] sypts = new int[11];
		int dxi,dyi;
		double dx,dy;
		for(int i=0;i<11;i++) {
			dx = Math.cos((Math.PI/180)*32.73*i)*w2;
			dy = Math.sin((Math.PI/180)*32.73*i)*h2;
			dxi = (int) dx;
			dyi = (int) dy;
			xpts[i] = x0+dxi;
			ypts[i] = y0+dyi;
			if(getShadow()) {
				sxpts[i] = x0+dxi+5;
				sypts[i] = y0+dyi+5;
			}
		}
		
		Graphics2D g2 = (Graphics2D) g;
		if(getDashed())
			g2.setStroke(new MyDashStroke(getLineWidth()));
	    else
			g2.setStroke(new BasicStroke(getLineWidth()));

		if(getShadow()) {
			g2.setColor(Color.black);
			g2.fillPolygon(sxpts, sypts, 11);
			g2.drawPolygon(sxpts, sypts, 11);
		}
		g2.setColor(getFillColor());
		g2.fillPolygon(xpts, ypts, 11);
		g2.setColor(getLineColor());
		g2.drawPolygon(xpts, ypts, 11);
		
		if(isSelected){
			super.draw(g2);
		}
	}
}

