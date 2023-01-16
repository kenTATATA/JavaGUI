import java.awt.event.*;
import javax.swing.*;

public class RectButton extends JButton {
    StateManager stateManager;

    public RectButton(StateManager stateManager) {
        super("Rectangle");

        addActionListener(new RectListener());
        
        this.stateManager = stateManager;
    }

    class RectListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            stateManager.setState(new RectState(stateManager));
        }
    }
}

class RectState implements State{
    StateManager stateManager;
    int x1,y1,x2,y2;
    MyRectangle rectangle;

    public RectState(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    public void mouseDown(int x, int y) {
        this.x1 = x;
        this.y1 = y;
        rectangle = new MyRectangle(x1, y1, 0, 0);
        if(stateManager.hasShadow) {
            rectangle.setShadow(true);
        }
        stateManager.addDrawing(rectangle);
    }

    public void mouseUp(int x, int y) {
        this.x2 = x;
        this.y2 = y;
        rectangle.setSize(x2-x1, y2-y1);
        if(Math.abs(x2-x1) < 1 || Math.abs(y2-y1) < 1) {
            stateManager.removeDrawing(rectangle);
        } else {
            stateManager.addDrawing(rectangle);
        }
    }

    public void mouseDrag(int x, int y) {
        this.x2 = x;
        this.y2 = y;
        rectangle.setSize(x2-x1,y2-y1);
    }
}