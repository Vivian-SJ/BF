package ui;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class VersionSelectPanel extends JPanel{
	private int width = 200;
	private int height = 100;
	
	public VersionSelectPanel(){
		JFrame f = new JFrame();
		f.getContentPane().add(this); 
		JButton b = new JButton();
		this.add(b);
		this.setSize(width, height); 
		this.setBackground(Color.BLACK);
		f.setBounds(100, 100, width, height);
		f.setVisible(true);
	}

}
