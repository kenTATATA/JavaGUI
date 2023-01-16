import java.awt.event.*;
import java.io.*;
import java.util.Vector;

import javax.swing.*;

public class SaveButton extends JButton {
    StateManager stateManager;
 
    public SaveButton(StateManager stateManager) {
        super("Save");
  
        addActionListener(new SaveListener());
  
        this.stateManager = stateManager;
    }
 
    class SaveListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Vector<MyDrawing> v = stateManager.mediator.drawings;
            // File出力
            try {
                // 4-3
                JFileChooser fc = new JFileChooser();
                int returnVal = fc.showSaveDialog(null);  // ファイルセーブ用のダイアログを開く
            
                if (returnVal == JFileChooser.APPROVE_OPTION) {  // OKボタンが押されたとき
                    File file = fc.getSelectedFile();
                    FileOutputStream fout = new FileOutputStream(file);
                    ObjectOutputStream out = new ObjectOutputStream(fout);
                    out.writeObject(v);
                    out.flush();
                    out.close();
                    fout.close();
                    JOptionPane.showMessageDialog(null, "Saveing successed.");
                }
                
                // 4-2
                // FileOutputStream fout = new FileOutputStream("file.txt");
                // ObjectOutputStream out = new ObjectOutputStream(fout);
                // out.writeObject(v);
                // out.flush();
                // out.close();
                // fout.close();
                // JOptionPane.showMessageDialog(null, "Saving successed.");

            } catch (Exception ex) {
                System.out.println("Saving failed.");
            }
            stateManager.mediator.canvas.requestFocusInWindow();
        }
    }
}