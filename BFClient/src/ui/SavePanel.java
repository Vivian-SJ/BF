package ui;
/*
 * 新文件保存界面
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class SavePanel extends JPanel {
	private int width = 300;
	private int height = 100;
	private JTextField field;
	private MainFrame mf;
	private JFrame frame;

	public SavePanel(MainFrame mainFrame) {
		mf = mainFrame;

		frame = new JFrame("Save");
		frame.setSize(width, height);
		this.setBackground(new Color(Integer.decode("#F0FFFF")));
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screensize = kit.getScreenSize();
		int screenheight = screensize.height;
		int screenwidth = screensize.width;
		frame.setLocation(screenwidth / 2 - width / 2, screenheight / 2 - height / 2);

		this.setSize(width, height);
		this.setBackground(Color.white);

		JLabel filename = new JLabel("Your FileName");
		filename.setLocation(5, 10);
		this.add(filename);

		field = new JTextField(10);
		field.setLocation(50, 10);
		this.add(field);

		JRadioButton button = new JRadioButton("Use Default Name");
		button.setLocation(50, 30);
		button.addActionListener(new buttonActionListener());
		this.add(button);

		JButton sureButton = new JButton("Sure");
		sureButton.setLocation(width / 2, 50);
		sureButton.addActionListener(new sureActionListener());
		this.add(sureButton);

		frame.getContentPane().add(this);
		frame.setVisible(true);
	}

	//监听内部类
	class buttonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			field.setText("aNewFile");
		}

	}

	class sureActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Calendar c = Calendar.getInstance();
			String version = Integer.toString(c.get(Calendar.YEAR)) + Integer.toString(c.get(Calendar.MONTH))
					+ Integer.toString(c.get(Calendar.DATE)) + Integer.toString(c.get(Calendar.HOUR_OF_DAY))
					+ Integer.toString(c.get(Calendar.MINUTE)); 
			mf.saveFile(field.getText()+"."+version);
			frame.dispose();
		}

	}

}
