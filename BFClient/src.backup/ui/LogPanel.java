package ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LogPanel extends JPanel{
	private int width = 200;
	private int height = 100;
	
	public LogPanel(){
		JFrame frame = new JFrame();
		frame.getContentPane().add(this);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screensize = kit.getScreenSize();
		int screenheight = screensize.height;
		int screenwidth = screensize.width;
		frame.setSize(width, height);
		frame.setLocation(screenwidth / 2 - width/2, screenheight / 2 - height/2);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		JPanel sonPanel = new JPanel();
		sonPanel.setLayout(new GridLayout(2,2));
		JLabel idLabel = new JLabel("Your ID");
		JTextField idFeild = new JTextField();
		JLabel passwordLabel = new JLabel("Your Password");
		JTextField passwordFeild = new JTextField();
		sonPanel.add(idLabel);
		sonPanel.add(idFeild);
		sonPanel.add(passwordLabel);
		sonPanel.add(passwordFeild);
		
		JLabel log = new JLabel("LOGIN");
	}

}
