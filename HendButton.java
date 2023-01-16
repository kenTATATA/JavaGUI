import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HendButton extends JButton {
    StateManager stateManager;

    public HendButton(StateManager stateManager) {
        super("Hendecagonal");

        addActionListener(new HendListener());
        
        this.stateManager = stateManager;
    }

    class HendListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            stateManager.setState(new HendState(stateManager));
        }
    }
}

class HendState implements State{
    StateManager stateManager;
    int x1,y1,x2,y2;
    MyHendecagonal hend;

    public HendState(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    public void mouseDown(int x, int y) {
        this.x1 = x;
        this.y1 = y;
        hend = new MyHendecagonal(x, y, 0, 0);
        if(stateManager.hasShadow) {
            hend.setShadow(true);
        }
        stateManager.addDrawing(hend);
    }

    public void mouseUp(int x, int y) {
        this.x2 = x;
        this.y2 = y;
        hend.setSize(x2-x1, y2-y1);
        if(Math.abs(x2-x1) < 1 || Math.abs(y2-y1) < 1) {
            stateManager.removeDrawing(hend);
        } else {
            stateManager.addDrawing(hend);
        }
    }

    public void mouseDrag(int x, int y) {
        this.x2 = x;
        this.y2 = y;
        hend.setSize(x2-x1,y2-y1);
    }
}