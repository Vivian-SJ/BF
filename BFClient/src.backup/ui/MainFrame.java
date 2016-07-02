package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import rmi.RemoteHelper;

public class MainFrame extends JFrame {
	private JTextArea textArea;
	private JTextArea inputArea;
	private JTextArea resultArea;
	private String username = "";
	private boolean logLabel = false;

	public MainFrame() {
		// 创建窗体
		JFrame frame = new JFrame("BF Client");
		frame.setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setLocation(0, 0);
		frame.setJMenuBar(menuBar);
		
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		JMenuItem newMenuItem = new JMenuItem("New");
		fileMenu.add(newMenuItem);
		JMenuItem openMenuItem = new JMenuItem("Open");
		fileMenu.add(openMenuItem);
		JMenuItem saveMenuItem = new JMenuItem("Save");
		fileMenu.add(saveMenuItem);
		JMenuItem exitMenuItem = new JMenuItem("Exit");
		fileMenu.add(exitMenuItem);
		
		JMenu runMenu = new JMenu("Run");
		menuBar.add(runMenu);
		JMenuItem executeMenuItem = new JMenuItem("Execute");
		runMenu.add(executeMenuItem);
		
		JMenu versionMenu = new JMenu("Version");
		menuBar.add(versionMenu);
		
		JMenu logMenu = new JMenu("LOG");
		JMenuItem loginMenuItem = new JMenuItem("LogIn"); 
		logMenu.add(loginMenuItem);
		JMenuItem logoutMenuItem = new JMenuItem("LogOut");
		logMenu.add(logoutMenuItem);
		menuBar.add(logMenu);

		newMenuItem.addActionListener(new MenuItemActionListener());
		openMenuItem.addActionListener(new MenuItemActionListener());
		saveMenuItem.addActionListener(new MenuItemActionListener());
		exitMenuItem.addActionListener(new MenuItemActionListener());
		executeMenuItem.addActionListener(new ExecuteActionListener());
		loginMenuItem.addActionListener(new loginActionListener()); 
		logoutMenuItem.addActionListener(new logoutActionListener()); 

		// 代码区
		textArea = new JTextArea();
		textArea.setBounds(0, 0, 500, 250);
		textArea.setBackground(new Color(Integer.decode("#FFF0F5")));
		textArea.setText("Codes Here");
		textArea.addMouseListener(new TextListener());
		frame.add(textArea);

		// 输入区
		inputArea = new JTextArea("Input Section");
		inputArea.addFocusListener(new InputListener());
		inputArea.setBounds(0, 250, 250, 150);
		inputArea.setBackground(new Color(Integer.decode("#FFF5EE")));
		frame.add(inputArea);

		// 显示结果
		resultArea = new JTextArea();
		resultArea.setText("result");
		resultArea.setBounds(250, 250, 250, 150);
		resultArea.setBackground(new Color(Integer.decode("#F0FFFF")));
		frame.add(resultArea);

		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screensize = kit.getScreenSize();
		int screenheight = screensize.height;
		int screenwidth = screensize.width;
		frame.setSize(500, 400);
		frame.setLocation(screenwidth / 2 - 250, screenheight / 2 - 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	class TextListener implements MouseListener {

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
			if (textArea.getText().equals("")) {
				textArea.setText("Codes Here");
			}
		}
	}

	class InputListener implements FocusListener {

		@Override
		public void focusGained(FocusEvent e) {
			// TODO Auto-generated method stub
			if (inputArea.getText().equals("Input Section")) {
				inputArea.setText("");
				inputArea.requestFocus();
			}
		}

		@Override
		public void focusLost(FocusEvent e) {
			// TODO Auto-generated method stub
			if (inputArea.getText().equals("")) {
				inputArea.setText("Input Section");
			}
		}

	}

	class MenuItemActionListener implements ActionListener {
		/**
		 * 子菜单响应事件
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			if (cmd.equals("New")) {
				textArea.setText("Codes Here");
				inputArea.setText("Input Section");
				resultArea.setText("result");
			} else if (cmd.equals("Save")) {
				String code = textArea.getText();
				Calendar c = Calendar.getInstance();
				String fileName = Integer.toString(c.get(Calendar.YEAR)) + Integer.toString(c.get(Calendar.MONTH))
						+ Integer.toString(c.get(Calendar.DATE)) + Integer.toString(c.get(Calendar.HOUR_OF_DAY))
						+ Integer.toString(c.get(Calendar.MINUTE));
				try {
					RemoteHelper.getInstance().getIOService().writeFile(code, "admin", fileName);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			} else if (cmd.equals("Open")) {

			} else if (cmd.equals("Exit")) {
				System.exit(0);
		}
		}
	}

	// class SaveActionListener implements ActionListener {
	//
	// @Override
	// public void actionPerformed(ActionEvent e) {
	// String code = textArea.getText();
	// try {
	// RemoteHelper.getInstance().getIOService().writeFile(code, "admin",
	// "code");
	// } catch (RemoteException e1) {
	// e1.printStackTrace();
	// }
	// }
	//
	// }

	class ExecuteActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String code = textArea.getText();
			String param = inputArea.getText();
			System.out.println(inputArea.getText());
			try {
				String result = RemoteHelper.getInstance().getExecuteService().execute(code, param);
				System.out.println(result);
				resultArea.setText(result);
			} catch (RemoteException e1) {
				e1.printStackTrace(); 
			}
		}

	}

	class loginActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				boolean result1 = RemoteHelper.getInstance().getUserService().login("v", "2"); 
				System.out.println(result1);
				boolean result2 = RemoteHelper.getInstance().getUserService().login("m", "1");
				System.out.println(result2);
			} catch (RemoteException e1) {
				e1.printStackTrace(); 
			}
		}

	}
	
	class logoutActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				boolean result = RemoteHelper.getInstance().getUserService().logout("v"); 
				System.out.println(result);
			} catch (RemoteException e1) {
				e1.printStackTrace(); 
			}
		}

	}
}
