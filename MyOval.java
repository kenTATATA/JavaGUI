import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class MyOval extends MyDrawing{
	public MyOval(int xpt, int ypt) {
		super(xpt, ypt);
	}
	
	public MyOval(int xpt, int ypt, int width, int height) {
		super(xpt,ypt,width,height);
	}
	
	public MyOval(int xpt, int ypt, int width, int height, Color lc, Color fc) {
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
		
		
		Graphics2D g2 = (Graphics2D) g;
		if(getDashed())
			g2.setStroke(new MyDashStroke(getLineWidth()));
		else
			g2.setStroke(new BasicStroke(getLineWidth()));
		if(getShadow()) {
			g2.setColor(Color.black);
			g2.fillOval(x+5, y+5, w, h);
			g2.drawOval(x+5, y+5, w, h);
		}
		g2.setColor(getFillColor());
		g2.fillOval(x, y, w, h);
		g2.setColor(getLineColor());
		g2.drawOval(x, y, w, h);

		if(isSelected){
			super.draw(g2);
		}
	}
}
