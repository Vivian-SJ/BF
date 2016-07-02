package ui;
/*
 * 一个多处使用的panel，用于在用户进行了不规范操作时提示
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WarningPanel extends JPanel{
	private int width = 200;
	private int height = 50;
	
	public WarningPanel(String s){
		JLabel label = new JLabel(s);
		label.setBounds(0, 25, 200, 50);
		this.add(label);
		
		JFrame frame = new JFrame();
		frame.getContentPane().add(this);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screensize = kit.getScreenSize();
		int screenheight = screensize.height;
		int screenwidth = screensize.width;
		this.setBackground(new Color(Integer.decode("#F0FFFF")));
		frame.setSize(width, height);
		frame.setLocation(screenwidth / 2 - width/2, screenheight / 2 - height/2);
		frame.setVisible(true);
	}

}
