import java.awt.event.*;
import javax.swing.*;

public class ShadowButton extends JButton {
    StateManager stateManager;

    public ShadowButton(StateManager stateManager) {
        super("Shadow");

        addActionListener(new ShadowListener());
        
        this.stateManager = stateManager;
    }

    class ShadowListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            stateManager.mediator.setShadow();
            stateManager.mediator.repaint();
        }
    }
}