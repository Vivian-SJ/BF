import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;



public class m {
	private JTextArea textArea;
	private JTextArea inputArea;
	private JLabel resultLabel;
	private JPanel versionPanel;
	private int width = 500;
	private int height = 400;

	public m() {
		// 创建窗体
		String[] s = new String[2];
		s[1] = "a";
		s[0] = "b";
		JFrame frame = new JFrame("BF Client");
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		 
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setLocation(0, 0);
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		JMenuItem newMenuItem = new JMenuItem("New");
		fileMenu.add(newMenuItem);
		JMenuItem openMenuItem = new JMenuItem("Open");
		fileMenu.add(openMenuItem); 
		JMenuItem saveMenuItem = new JMenuItem("Save");
		fileMenu.add(saveMenuItem);
		JMenuItem runMenuItem = new JMenuItem("Exit"); 
		fileMenu.add(runMenuItem);
		frame.setJMenuBar(menuBar);
		JMenu runMenu = new JMenu("Run");
		menuBar.add(runMenu);
		JMenuItem execute = new JMenuItem("Execute");
		runMenu.add(execute);
		JMenu versionMenu = new JMenu("Version");
		
		menuBar.add(versionMenu); 
		versionMenu.addActionListener(new versionListener());
		openMenuItem.addActionListener(new openListener());
		
		//版本选择界面
	    versionPanel = new JPanel();
//		JComboBox versionSelect = new JComboBox(s);
//		JCheckBox versionSelect = new JCheckBox("Please select a version");
//		JLabel select = new JLabel("Please select a vision");
//	    ButtonGroup versionSelect = new ButtonGroup();
//	    JRadioButton b1 = new JRadioButton(s[0]);
//	    JRadioButton b2 = new JRadioButton(s[1]);
//	    versionSelect.add(b1);
//	    versionSelect.add(b2);
		versionPanel.setLayout(new BorderLayout());
		versionPanel.setBackground(Color.lightGray);
		versionPanel.setBounds(width/2, height/2, 200, 100);
//		versionPanel.add(versionSelect);
//		versionPanel.add(BorderLayout.NORTH,select);
		versionPanel.setVisible(false); 
//		frame.getContentPane().add(versionPanel);
		
		//代码区
		textArea = new JTextArea();
		textArea.setBounds(0, 0, 500, 250);
		textArea.setBackground(Color.pink);
		textArea.setText("Codes Here");
		textArea.addMouseListener(new TextListener());
//		frame.getContentPane().add(textArea);
		
		//输入区
		inputArea = new JTextArea("Input Section");
		inputArea.setBounds(0, 250, 250, 150);
		inputArea.addFocusListener(new InputListener()); 
//		frame.getContentPane().add(inputArea);
		

		// 显示结果
		resultLabel = new JLabel(); 
		resultLabel.setText("result");
		resultLabel.setBounds(250, 250, 250, 150);
		//frame.add(resultLabel);
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension screensize=kit.getScreenSize();
		int screenheight=screensize.height;
		int screenwidth=screensize.width;
		frame.setSize(500, 400);
		frame.setLocation(screenwidth/2-250, screenheight/2-200);


		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible(true);
		ButtonGroup versionSelect = new ButtonGroup();
	    JRadioButton b1 = new JRadioButton(s[0]);
	    JRadioButton b2 = new JRadioButton(s[1]);
	    versionSelect.add(b1);
	    versionSelect.add(b2);
	    frame.getContentPane().add(b1);
	    frame.getContentPane().add(b2);
}
	public static void main(String args[]){
		m mm = new m();
		
	}
	class openListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			versionPanel.setVisible(true);
		}
		
	}
	
	class versionListener implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			versionPanel.setVisible(true);
		}
		
	}
	
	class TextListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			textArea.setText("");
			textArea.requestFocus();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			if(textArea.getText().equals("")){
				textArea.setText("Codes Here");
			}
		}
	}
	
	class InputListener implements FocusListener{

		@Override
		public void focusGained(FocusEvent e) {
			// TODO Auto-generated method stub
			inputArea.setText("");
			inputArea.requestFocus();
		}

		@Override
		public void focusLost(FocusEvent e) {
			// TODO Auto-generated method stub
			inputArea.setText("Input Section");
		}
		
	}
}
