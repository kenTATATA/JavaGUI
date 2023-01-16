import java.awt.*;

public class MySelect extends MyDrawing{
	public MySelect(int xpt, int ypt) {
		super(xpt, ypt);
	}
	
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		if(getSelected()) super.draw(g2);
	}
}
