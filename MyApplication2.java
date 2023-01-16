import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MyApplication2 extends JFrame implements ActionListener{
    private JMenuBar menuBar;
    private JMenu colorMenu;
    private JMenuItem redItem, blueItem, greenItem;

	StateManager stateManager;
	MyCanvas canvas;
	Mediator med;

	public MyApplication2() {
		super("My Paint Application2");

		canvas = new MyCanvas();
		canvas.setBackground(Color.white);
		med = canvas.mediator;
		
		JPanel jp = new JPanel();
		jp.setLayout(new FlowLayout());
		
		stateManager = new StateManager(med);
		
		SelectButton selectButton = new SelectButton(stateManager);
		jp.add(selectButton);
		RectButton rectButton = new RectButton(stateManager);
		jp.add(rectButton);
		OvalButton ovalButton = new OvalButton(stateManager);
		jp.add(ovalButton);
		
 
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
        
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        colorMenu = new JMenu("Color");
        redItem = new JMenuItem("Red");
        blueItem = new JMenuItem("Blue");
        greenItem = new JMenuItem("Green");
        colorMenu.add(redItem);
        colorMenu.add(blueItem);
        colorMenu.add(greenItem);
        redItem.addActionListener(this);
        blueItem.addActionListener(this);
        greenItem.addActionListener(this);

        menuBar.add(colorMenu);
	}

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == redItem) {
            med.setColor(Color.red);
        }
        else if(e.getSource() == blueItem) {
            med.setColor(Color.blue);
        }
        else if(e.getSource() == greenItem) {
            med.setColor(Color.green);
        }
    }

	public Dimension getPreferredSize() {
		return new Dimension(1000,1000);
	}
	
	public static void main(String[] args) {
		MyApplication app = new MyApplication();
		app.pack();
		app.setVisible(true);
	}
}
