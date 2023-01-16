import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class MyTsukuba extends MyDrawing{
	boolean color = false;
	Color lc,fc;
	int blightness;
	public MyTsukuba(int xpt, int ypt) {
		super(xpt,ypt);
	}
	
	public MyTsukuba(int xpt, int ypt, int width, int height) {
		super(xpt,ypt,width,height);
	}
	
	public MyTsukuba(int xpt, int ypt, int width, int height, Color lc, Color fc) {
		super(xpt,ypt,width,height,lc,fc);
		color = true;
		this.lc = lc;
		this.fc = fc;
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
		
		w *= 3;
		w = (int) w/4;
		int w3 = (int) w/3;
		int w6 = (int) w/6;
		int h3 = (int) h/3;
		int w2 = (int) w/2;
		
		// light green
		// Color c1 = new Color(144,238,144);
		// light blue
		// Color c2 = new Color(173,216,230);
		// Color lc = Color.white;
		Color c1 = fillColor;
		Color c2 = fillColor;
		Color lc = lineColor;
		if(color) {
			c1 = this.fc;
			c2 = this.fc;
			lc = this.lc;
		}
 		
		Graphics2D g2 = (Graphics2D) g;
		if(getDashed())
			g2.setStroke(new MyDashStroke(getLineWidth()));
		else
			g2.setStroke(new BasicStroke(getLineWidth()));
		g2.setColor(c1);
		g2.fillOval(x, y, w, h);
		g2.setColor(c1);
		g2.drawOval(x, y, w, h);
		g2.setColor(c2);
		g2.fillOval(x+w2-w6, y, w, h);
		g2.setColor(c2);
		g2.drawOval(x+w2-w6, y, w, h);
		
		g2.setColor(lc);
		g2.drawArc(x, y, w, h, 180, 180);
		g2.setColor(lc);
		g2.drawArc(x+w2-w6, y, w, h, 0, 180);
		
		g2.setColor(c1);
		g2.fillOval(x+w2+w6, y+h3, w3, h3);
		g2.setColor(lc);
		g2.drawArc(x+w2+w6, y+h3, w3, h3, 0, 180);
		g2.setColor(c2);
		g2.fillOval(x+w2-w6, y+h3, w3, h3);
		g2.setColor(lc);
		g2.drawArc(x+w2-w6, y+h3, w3, h3, 180, 180);

		g2.setColor(c1);
		g2.fillArc(x, y, w, h, 60, 270);
		g2.setColor(c2);
		g2.fillArc(x+w2-w6, y, w, h, 210, 270);

		if(isSelected){
			super.draw(g2);
		}
	}
}
