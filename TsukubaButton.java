import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TsukubaButton extends JButton {
    StateManager stateManager;

    public TsukubaButton(StateManager stateManager) {
        super("Tsukuba");

        addActionListener(new TsukubaListener());
        
        this.stateManager = stateManager;
    }

    class TsukubaListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            stateManager.setState(new TsukubaState(stateManager));
        }
    }
}

class TsukubaState implements State{
    StateManager stateManager;
    int x1,y1,x2,y2;
    MyTsukuba tsukuba;
    MyTsukuba shadow;

    public TsukubaState(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    public void mouseDown(int x, int y) {
        this.x1 = x;
        this.y1 = y;
        if(stateManager.hasShadow) {
            shadow = new MyTsukuba(x1+5, y1+5, 0, 0, Color.black, Color.black);
            stateManager.addDrawing(shadow);
        }
        tsukuba = new MyTsukuba(x, y, 0, 0);
        stateManager.addDrawing(tsukuba);
    }

    public void mouseUp(int x, int y) {
        this.x2 = x;
        this.y2 = y;
        if(stateManager.hasShadow)
            shadow.setSize(x2-x1, y2-y1);
        tsukuba.setSize(x2-x1, y2-y1);
        if(Math.abs(x2-x1) < 1 || Math.abs(y2-y1) < 1) {
            if(stateManager.hasShadow)
                stateManager.removeDrawing(shadow);
            stateManager.removeDrawing(tsukuba);
        } else {
            if(stateManager.hasShadow)
                stateManager.addDrawing(shadow);
            stateManager.addDrawing(tsukuba);
        }
    }

    public void mouseDrag(int x, int y) {
        this.x2 = x;
        this.y2 = y;
        if(stateManager.hasShadow)
            shadow.setSize(x2-x1,y2-y1);
        tsukuba.setSize(x-x1,y-y1);
    }
}