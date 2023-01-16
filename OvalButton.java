import java.awt.event.*;


import javax.swing.*;

public class OvalButton extends JButton {
    StateManager stateManager;

    public OvalButton(StateManager stateManager) {
        super("Oval");

        addActionListener(new OvalListener());
        
        this.stateManager = stateManager;
    }

    class OvalListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            stateManager.setState(new OvalState(stateManager));
        }
    }
}

class OvalState implements State{
    StateManager stateManager;
    int x1,y1,x2,y2;
    MyOval oval;

    public OvalState(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    public void mouseDown(int x, int y) {
        this.x1 = x;
        this.y1 = y;
        oval = new MyOval(x1, y1, 0, 0);
        if(stateManager.hasShadow) {
            oval.setShadow(true);
        }
        stateManager.addDrawing(oval);
    }

    public void mouseUp(int x, int y) {
        this.x2 = x;
        this.y2 = y;
        oval.setSize(x2-x1, y2-y1);
        if(Math.abs(x2-x1) < 1 || Math.abs(y2-y1) < 1) {
            stateManager.removeDrawing(oval);
        } else {
            stateManager.addDrawing(oval);
        }
    }

    public void mouseDrag(int x, int y) {
        this.x2 = x;
        this.y2 = y;
        oval.setSize(x2-x1,y2-y1);
    }
}