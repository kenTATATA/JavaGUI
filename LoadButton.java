import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.swing.*;

public class LoadButton extends JButton {
    StateManager stateManager;
 
    public LoadButton(StateManager stateManager) {
        super("Load");
  
        addActionListener(new LoadListener());
  
        this.stateManager = stateManager;
    }
 
    class LoadListener implements ActionListener {
        @SuppressWarnings("unchecked")
        public void actionPerformed(ActionEvent e) {
            Vector<MyDrawing> v;
            // File入力
            try {
                // 4-3
                JFileChooser fc = new JFileChooser();
                int returnVal = fc.showOpenDialog(null);  // ファイルロード用のダイアログを開く

                if (returnVal == JFileChooser.APPROVE_OPTION) {  // OKボタンが押されたとき
                    File file = fc.getSelectedFile();
                    FileInputStream fin = new FileInputStream(file);
                    ObjectInputStream in = new ObjectInputStream(fin);
 
                    v = (Vector<MyDrawing>)in.readObject();
                    in.close();
                    fin.close();
                
                    if (v != null) {
                        stateManager.mediator.drawings = new Vector<MyDrawing>();
                        stateManager.mediator.repaint();
                        Enumeration<MyDrawing> d = v.elements();
                        while (d.hasMoreElements()) {
                            MyDrawing drawing = d.nextElement();
                            stateManager.mediator.addDrawing(drawing);
                        }
                        JOptionPane.showMessageDialog(null, "Loading successed.");
                    }
                }

                // 4-2
                // FileInputStream fin = new FileInputStream("file.txt");
                // ObjectInputStream in = new ObjectInputStream(fin);

                // v = (Vector<MyDrawing>)in.readObject();
                // in.close();
                // fin.close();

                // if (v != null) {
                //     stateManager.mediator.drawings = new Vector<MyDrawing>();
                //     stateManager.mediator.repaint();
                //     Enumeration<MyDrawing> d = v.elements();
                //     while (d.hasMoreElements()) {
                //         MyDrawing dr = d.nextElement();
                //         stateManager.mediator.addDrawing(dr);
                //     }
                // }
                // JOptionPane.showMessageDialog(null, "Loading successed.");
            } catch (Exception ex) {
                System.out.println("Loading failed.");
            }
            stateManager.mediator.canvas.requestFocusInWindow();
        }
    }
}