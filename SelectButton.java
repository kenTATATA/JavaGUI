import java.awt.*;
import java.awt.event.*;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.*;

public class SelectButton extends JButton {
    StateManager stateManager;

    public SelectButton(StateManager stateManager) {
        super("Select");

        addActionListener(new SelectListener());
        
        this.stateManager = stateManager;
    }

    class SelectListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            stateManager.setState(new SelectState(stateManager));
        }
    }
}

class SelectState implements State{
    StateManager stateManager;
    Operation operation;

    public SelectState(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    public void mouseDown(int x, int y) {
        Enumeration<MyDrawing> d = stateManager.mediator.drawings.elements();
        boolean contain = false;
        while(d.hasMoreElements()) {
            MyDrawing e = d.nextElement();
            e.setRegion();
            if(e.contains(x, y)) {
                if(!(stateManager.mediator.checkNull()) && !e.getSelected()) {
                    stateManager.mediator.unSelected();
                }
                contain = true;
                stateManager.mediator.setSelectedDrawings(e);
            }
        }

        if(contain) {
            operation = new Move(stateManager.mediator, x, y);
        } else {
            operation = new Drag(stateManager.mediator, x, y);
        }
    }

    public void mouseUp(int x, int y) {
        operation.mouseUp(x, y);
    }

    public void mouseDrag(int x, int y) {
        operation.mouseDrag(x, y);
    }
}

// SelectState の中核: mouseDrag と mouseUp
abstract class Operation {
    public abstract void mouseUp(int x, int y);
    public abstract Operation mouseDrag(int x, int y);
}

// SelectState の仕事１: Drag 時に選択領域を変化させる
class Drag extends Operation {
    MyRectangle rectangle;
    Mediator mediator;

    Drag (Mediator mediator,int x, int y) {
        this.mediator = mediator;
        rectangle = new MyRectangle(x, y);
        rectangle.setSize(1, 1);
        rectangle.setColor(new Color(0,0,0),new Color(0,0,0));
        rectangle.setBlightness(50);
        mediator.setSelectRectangle(rectangle);
    }

    public Operation mouseDrag(int x, int y) {
        // System.out.println("Drag:mouseDrag");
        rectangle.setSize(x-rectangle.getX(), y-rectangle.getY());
        mediator.selectDrawings(rectangle);
        mediator.repaint();
        
        return this;
    }

    public void mouseUp(int x, int y) {
        mediator.setSelectRectangle(null);
    }
}

// SelectState の仕事２: Click 時に図形を選択状態にする
// class Click extends Operation {
//     Mediator mediator;
//     int x,y;

//     Click (Mediator mediator, int x, int y) {
//         this.mediator = mediator;
//         this.x = x;
//         this.y = y;
//     }

//     public Operation mouseDrag(int x,int y) {
//         return new Drag(mediator, x, y);
//     }

//     public void mouseUp(int x,int y) {
//         mediator.setSelected(x, y);
//     }
// }

// SelectState の仕事３: Move 時に選択中の図形を移動する
class Move extends Operation {
    Mediator mediator;
    int x,y;
    Vector<Integer> initialX = new Vector<Integer>();
    Vector<Integer> initialY = new Vector<Integer>();

    Move (Mediator mediator, int x, int y) {
        initialX.clear();
        initialY.clear();
        this.mediator = mediator;
        for(MyDrawing d:mediator.selectedDrawings) {
            initialX.add(d.getX());
            initialY.add(d.getY());
        }
        this.x = x;
        this.y = y;
    }

    public Operation mouseDrag(int x, int y) {
        int count =0;
        for(MyDrawing d:mediator.selectedDrawings) {
            d.setLocation(x-this.x+initialX.get(count), y-this.y+initialY.get(count));
            count ++;
        }

        return this;
    }

    public void mouseUp(int x, int y) {}
}
