import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MyApplication extends JFrame implements ActionListener{
	private JMenuBar menuBar;
    private JMenu colorMenu;
    private JMenuItem redItem, orangeItem, yellowItem, greenyellowItem, 
	greenItem, cyanItem, blueItem, purpleItem, magentaItem, blackItem;

	StateManager stateManager;
	MyCanvas canvas;
	Mediator med;
	boolean selectedLineColor,selectedFillColor,selectedLineWidth;

	public MyApplication() {
		super("My Paint Application");

		canvas = new MyCanvas();
		canvas.setBackground(Color.white);
		
		med = canvas.mediator;
		
		JPanel jp = new JPanel();
		jp.setLayout(new FlowLayout());
		
		stateManager = new StateManager(med);
		
		// 選択、各図形ボタンの追加
		SaveButton saveButton = new SaveButton(stateManager);
		jp.add(saveButton);
		LoadButton loadButton = new LoadButton(stateManager);
		jp.add(loadButton);
		SelectButton selectButton = new SelectButton(stateManager);
		jp.add(selectButton);
		ShadowButton shadowButton = new ShadowButton(stateManager);
		jp.add(shadowButton);
		DashButton dashButton = new DashButton(stateManager);
		jp.add(dashButton);
		TriButton triButton = new TriButton(stateManager);
		jp.add(triButton);
		RectButton rectButton = new RectButton(stateManager);
		jp.add(rectButton);
		OvalButton ovalButton = new OvalButton(stateManager);
		jp.add(ovalButton);
		HendButton hendButton = new HendButton(stateManager);
		jp.add(hendButton);

		JCheckBox lineColorCheck = new JCheckBox("lineColor");
		lineColorCheck.addItemListener(new CheckListener(){
			public void itemStateChanged(ItemEvent e) {
				int state = e.getStateChange();
				if(state == ItemEvent.SELECTED)
					selectedLineColor = true;
				else
					selectedLineColor = false;
			}
		});
		jp.add(lineColorCheck);

		JCheckBox fillColorCheck = new JCheckBox("fillColor");
		fillColorCheck.addItemListener(new CheckListener(){
			public void itemStateChanged(ItemEvent e) {
				int state = e.getStateChange();
				if(state == ItemEvent.SELECTED)
					selectedFillColor = true;
				else
					selectedFillColor = false;
			}
		});
		jp.add(fillColorCheck);
 
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(jp, BorderLayout.NORTH);
		getContentPane().add(canvas, BorderLayout.CENTER);

		// マウスクリック、リリースイベントの処理
		canvas.addMouseListener(
			new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					canvas.requestFocusInWindow();
					stateManager.mouseDown(e.getX(), e.getY());
				}
				public void mouseReleased(MouseEvent e) {
					canvas.requestFocusInWindow();
					stateManager.mouseUp(e.getX(), e.getY());
				}
			}
		);

		// マウスドラッグイベントの処理
		canvas.addMouseMotionListener(
			new MouseMotionAdapter() {
				public void mouseDragged(MouseEvent e) {
					canvas.requestFocusInWindow();
					stateManager.mouseDrag(e.getX(), e.getY());
				}
			}
		);
		
		// キーボードイベントの処理
		canvas.setFocusable(true);
		canvas.addKeyListener(
			new KeyAdapter() {
				@Override public void keyPressed(KeyEvent e) {
					switch(e.getKeyCode()) {
						// 削除
						case KeyEvent.VK_1:
							med.removeSelectedDrawings(med.selectedDrawings);
							break;
						// コピー
						case KeyEvent.VK_2:
							med.copy();
							break;
						// カット
						case KeyEvent.VK_3:
							med.cut();
							break;
						// ペースト
						case KeyEvent.VK_4:
							med.paste();
							break;
						// 線を太くする
						case KeyEvent.VK_5:
							med.setLineWidthes(1);
							break;
						// 線を細くする
						case KeyEvent.VK_6:
							med.setLineWidthes(-1);
							break;
						// 上下左右の移動の
						case KeyEvent.VK_UP:
							med.move(0, -3);
							break;
						case KeyEvent.VK_DOWN:
							med.move(0, 3);
							break;
						case KeyEvent.VK_RIGHT:
							med.move(3, 0);
							break;
						case KeyEvent.VK_LEFT:
							med.move(-3, 0);
							break;
						case KeyEvent.VK_7:
							med.setBlightness(1);
							break;
						case KeyEvent.VK_8:
							med.setBlightness(-1);
							break;
						case KeyEvent.VK_9:
							med.setLayer(med.selectedDrawings);
							break;
					}
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
		orangeItem = new JMenuItem("Orange");
		yellowItem = new JMenuItem("Yellow");
		greenyellowItem = new JMenuItem("Greenyellow");
        greenItem = new JMenuItem("Green");
		cyanItem = new JMenuItem("Cyan");
		blueItem = new JMenuItem("Blue");
		purpleItem = new JMenuItem("Purple");
		magentaItem = new JMenuItem("Magenta");
		blackItem = new JMenuItem("Black");
        colorMenu.add(redItem);
		colorMenu.add(orangeItem);
		colorMenu.add(yellowItem);
		colorMenu.add(greenyellowItem);
        colorMenu.add(greenItem);
		colorMenu.add(cyanItem);
		colorMenu.add(blueItem);
		colorMenu.add(purpleItem);
		colorMenu.add(magentaItem);
		colorMenu.add(blackItem);
        redItem.addActionListener(this);
		orangeItem.addActionListener(this);
		yellowItem.addActionListener(this);
		greenyellowItem.addActionListener(this);
        greenItem.addActionListener(this);
		cyanItem.addActionListener(this);
		blueItem.addActionListener(this);
		purpleItem.addActionListener(this);
		magentaItem.addActionListener(this);
		blackItem.addActionListener(this);

        menuBar.add(colorMenu);
	}

	public void actionPerformed(ActionEvent e) {
        if(e.getSource() == redItem) {
			if(selectedLineColor) med.setLineColors(Color.red);
            if(selectedFillColor) med.setFillColors(Color.red);
			med.repaint();
        }
		else if(e.getSource() == orangeItem) {
            if(selectedLineColor) med.setLineColors(Color.orange);
            if(selectedFillColor) med.setFillColors(Color.orange);
			med.repaint();
        }
		if(e.getSource() == yellowItem) {
            if(selectedLineColor) med.setLineColors(Color.yellow);
            if(selectedFillColor) med.setFillColors(Color.yellow);
			med.repaint();
        }
		else if(e.getSource() == greenyellowItem) {
			if(selectedLineColor) med.setLineColors(new Color(173,255,47));
            if(selectedFillColor) med.setFillColors(new Color(173,255,47));
			med.repaint();
        }
        else if(e.getSource() == greenItem) {
            if(selectedLineColor) med.setLineColors(Color.green);
            if(selectedFillColor) med.setFillColors(Color.green);
			med.repaint();
        }
		else if(e.getSource() == cyanItem) {
            if(selectedLineColor) med.setLineColors(Color.cyan);
            if(selectedFillColor) med.setFillColors(Color.cyan);
			med.repaint();
        }
		else if(e.getSource() == blueItem) {
            if(selectedLineColor) med.setLineColors(Color.blue);
            if(selectedFillColor) med.setFillColors(Color.blue);
			med.repaint();
        }
		else if(e.getSource() == purpleItem) {
			if(selectedLineColor) med.setLineColors(new Color(128, 0, 128));
            if(selectedFillColor) med.setFillColors(new Color(128, 0, 128));
			med.repaint();
        }
		else if(e.getSource() == magentaItem) {
            if(selectedLineColor) med.setLineColors(Color.magenta);
            if(selectedFillColor) med.setFillColors(Color.magenta);
			med.repaint();
        }
		else if(e.getSource() == blackItem) {
			if(selectedLineColor) med.setLineColors(Color.black);
            if(selectedFillColor) med.setFillColors(Color.black);
			med.repaint();
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

class CheckListener implements ItemListener {
	public void itemStateChanged(ItemEvent e) {
	}
}

class KeyListener implements ItemListener {
	public void itemStateChanged(ItemEvent e) {
	}
}