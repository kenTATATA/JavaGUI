import java.awt.event.*;
import javax.swing.*;

public class TriButton extends JButton {
    StateManager stateManager;

    public TriButton(StateManager stateManager) {
        super("Triangle");

        addActionListener(new TriListener());
        
        this.stateManager = stateManager;
    }

    class TriListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            stateManager.setState(new TriState(stateManager));
        }
    }
}

class TriState implements State{
    StateManager stateManager;
    int x1,y1,x2,y2;
    MyTriangle tri;
    MyTriangle shadow;

    public TriState(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    public void mouseDown(int x, int y) {
        this.x1 = x;
        this.y1 = y;
        tri = new MyTriangle(x1, y1, 0, 0);
        if(stateManager.hasShadow) {
            tri.setShadow(true);
        }
        stateManager.addDrawing(tri);
    }

    public void mouseUp(int x, int y) {
        this.x2 = x;
        this.y2 = y;
        if(stateManager.hasShadow)
            shadow.setSize(x2-x1, y2-y1);
        tri.setSize(x2-x1, y2-y1);
        if(Math.abs(x2-x1) < 1 || Math.abs(y2-y1) < 1) {
            if(stateManager.hasShadow)
                stateManager.removeDrawing(shadow);
            stateManager.removeDrawing(tri);
        } else {
            if(stateManager.hasShadow)
                stateManager.addDrawing(shadow);
            stateManager.addDrawing(tri);
        }
    }

    public void mouseDrag(int x, int y) {
        this.x2 = x;
        this.y2 = y;
        if(stateManager.hasShadow)
            shadow.setSize(x2-x1,y2-y1);
        tri.setSize(x2-x1,y2-y1);
    }
}