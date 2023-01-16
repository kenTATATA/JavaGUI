import java.awt.event.*;
import javax.swing.JButton;

public class Button extends JButton implements ActionListener{
	private Circle circle;
	private Canvas canvas;

	public Button(Circle circle, Canvas canvas){
		super("Click Me to Make Circle Bigger");
		this.circle = circle;
		this.canvas = canvas;
		this.addActionListener(this);
	}

    public void actionPerformed(ActionEvent actionEvent){
    	circle.inclementSize();
		canvas.repaint();
	}
}
