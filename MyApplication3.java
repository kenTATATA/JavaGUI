import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MyApplication3 extends JFrame {
	StateManager stateManager;
	MyCanvas canvas;
	Mediator med;

	public MyApplication3() {
		super("My Paint Application3");

		canvas = new MyCanvas();
		canvas.setBackground(Color.white);
		med = canvas.mediator;
		
		JPanel jp = new JPanel();
		jp.setLayout(new FlowLayout());
		
		stateManager = new StateManager(med);
		
		RectButton rectButton = new RectButton(stateManager);
		jp.add(rectButton);
		OvalButton ovalButton = new OvalButton(stateManager);
		jp.add(ovalButton);
		HendButton hendButton = new HendButton(stateManager);
		jp.add(hendButton);
		TsukubaButton tsukubaButton = new TsukubaButton(stateManager);
		jp.add(tsukubaButton);
 
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(jp, BorderLayout.NORTH);
		getContentPane().add(canvas, BorderLayout.CENTER);

		canvas.addMouseListener(
			new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					stateManager.mouseDown(e.getX(), e.getY());
				}
				public void mouseReleased(MouseEvent e) {
					stateManager.mouseUp(e.getX(), e.getY());
				}
			}
		);
		canvas.addMouseMotionListener(
			new MouseMotionAdapter() {
				public void mouseDragged(MouseEvent e) {
					stateManager.mouseDrag(e.getX(), e.getY());
				}
			}
		);
		
		// WindowEvent リスナを設定(無名クラスを利用している)
		this.addWindowListener(
			new WindowAdapter() {
				//ウインドウが閉じたら終了する処理
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			}
		);

	}

	public Dimension getPreferredSize() {
		return new Dimension(1000,1000);
	}
	
	public static void main(String[] args) {
		MyApplication3 app = new MyApplication3();
		app.pack();
		app.setVisible(true);
	}
}