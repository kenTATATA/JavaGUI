import java.awt.event.*;
import javax.swing.*;

public class DashButton extends JButton {
    StateManager stateManager;

    public DashButton(StateManager stateManager) {
        super("Dash");

        addActionListener(new DashListener());
        
        this.stateManager = stateManager;
    }

    class DashListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(!stateManager.isDashed) {
                stateManager.isDashed = true;
            } else {
                stateManager.isDashed = false;
            }
            stateManager.mediator.setDashed(stateManager.isDashed);
            stateManager.mediator.repaint();
        }
    }
}