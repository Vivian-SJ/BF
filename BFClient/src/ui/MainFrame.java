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
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import EventListener.MainFrameTextListener;
import EventListener.TextListener;
import rmi.RemoteHelper;
/*
 * BF的主界面，包含了IDE所要求的各项基本操作以及代码区、输入区和结果显示区
 * 
 */
public class MainFrame extends JFrame {
	private static int count = 0;
	private JTextArea codeArea;
	private JTextArea inputArea;
	private JTextArea resultArea;
	private String username = "";
	private boolean logLabel = false;
	private MainFrame mf;
	private ArrayList<String> code;
	private ArrayList<String> input;

	public MainFrame(String s) {
		mf = this;
		username = s;
		// 创建窗体
		JFrame frame = new JFrame("BF Client");
		frame.setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setLocation(0, 0);
		frame.setJMenuBar(menuBar);

		// 主操作序列
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		JMenuItem newMenuItem = new JMenuItem("New");
		fileMenu.add(newMenuItem);
		JMenuItem saveMenuItem = new JMenuItem("Save");
		fileMenu.add(saveMenuItem);
		JMenuItem exitMenuItem = new JMenuItem("Exit");
		fileMenu.add(exitMenuItem);

		// 执行
		JMenu runMenu = new JMenu("Run");
		menuBar.add(runMenu);
		JMenuItem executeMenuItem = new JMenuItem("Execute");
		runMenu.add(executeMenuItem);

		// 版本
		JMenu versionMenu = new JMenu("Version");
		menuBar.add(versionMenu);
		JMenuItem versionMenuItem = new JMenuItem("Version");
		versionMenu.add(versionMenuItem);

		// 登陆
		JMenu logMenu = new JMenu("LOG");
		JMenuItem loginMenuItem = new JMenuItem("Login");
		logMenu.add(loginMenuItem);
		JMenuItem logoutMenuItem = new JMenuItem("Logout");
		logMenu.add(logoutMenuItem);
		menuBar.add(logMenu);

		// 编辑
		JMenu editMenu = new JMenu("Edit");
		JMenuItem redoMenuItem = new JMenuItem("Redo");
		editMenu.add(redoMenuItem);
		JMenuItem undoMenuItem = new JMenuItem("Undo");
		editMenu.add(undoMenuItem);
		menuBar.add(editMenu);

		// 加监听
		newMenuItem.addActionListener(new MenuItemActionListener());
		saveMenuItem.addActionListener(new MenuItemActionListener());
		exitMenuItem.addActionListener(new MenuItemActionListener());
		executeMenuItem.addActionListener(new MenuItemActionListener());
		versionMenuItem.addActionListener(new MenuItemActionListener());
		redoMenuItem.addActionListener(new MenuItemActionListener());
		undoMenuItem.addActionListener(new MenuItemActionListener());
		loginMenuItem.addActionListener(new loginActionListener());
		logoutMenuItem.addActionListener(new MenuItemActionListener());

		// 代码区
		codeArea = new JTextArea();
		codeArea.setBorder(BorderFactory.createTitledBorder("Codes"));
		codeArea.setBounds(0, 0, 500, 250);
		codeArea.setBackground(new Color(Integer.decode("#FFF0F5")));
		codeArea.setText("Codes Here");
		code = new ArrayList<String>();
		codeArea.addFocusListener(new textListener());
		codeArea.addKeyListener(new MainFrameTextListener(mf, codeArea, code));
		frame.add(codeArea);

		// 输入区
		inputArea = new JTextArea("Input Section");
		inputArea.setBorder(BorderFactory.createTitledBorder("Input"));
		inputArea.addFocusListener(new InputListener());
		inputArea.setBounds(0, 250, 250, 150);
		inputArea.setBackground(new Color(Integer.decode("#FFF5EE")));
		input = new ArrayList<String>();
		inputArea.addKeyListener(new MainFrameTextListener(mf, inputArea, input));
		frame.add(inputArea);

		// 显示结果
		resultArea = new JTextArea();
		resultArea.setBorder(BorderFactory.createTitledBorder("Result"));
		resultArea.setText("result");
		resultArea.setBounds(250, 250, 250, 150);
		resultArea.setBackground(new Color(Integer.decode("#F0FFFF")));
		resultArea.setEditable(false);
		frame.add(resultArea);

		// 框体置中
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screensize = kit.getScreenSize();
		int screenheight = screensize.height;
		int screenwidth = screensize.width;
		frame.setSize(500, 400);
		frame.setLocation(screenwidth / 2 - 250, screenheight / 2 - 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

	// 存一个新文件
	public void saveFile(String fileName) {
		String code = codeArea.getText();
		try {
			boolean result = RemoteHelper.getInstance().getIOService().writeFile(code, username, fileName);
			if(result){
				WarningPanel warning = new WarningPanel("Save succesfully");
			}else{
				WarningPanel warning = new WarningPanel("Save Failed");
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	// 读取文件
	public void openFile(String fileName) {
		try {
			String filename = fileName;
			String code = RemoteHelper.getInstance().getIOService().readFile(username, filename);
			codeArea.setText(code);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// setter
	public void setLogLabel(boolean b) {
		logLabel = b;
	}

	public void setCode(ArrayList<String> s) {
		code = s;
	}

	public void setInput(ArrayList<String> s) {
		input = s;
	}

	public void setCount(int i) {
		count = i;
	}

	// 监听内部类
	class textListener implements FocusListener {
		public void focusGained(FocusEvent e) {
			// TODO Auto-generated method stub
			if (codeArea.getText().equals("Codes Here")) {
				codeArea.setText("");
				codeArea.requestFocus();
			}
		}

		@Override
		public void focusLost(FocusEvent e) {
			// TODO Auto-generated method stub
			if (codeArea.getText().equals("")) {
				codeArea.setText("Codes Here");
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
			if (logLabel) {
				String cmd = e.getActionCommand();
				if (cmd.equals("New")) {
					codeArea.setText("Codes Here");
					inputArea.setText("Input Section");
					resultArea.setText("result");
				} else if (cmd.equals("Save")) {
					SavePanel save = new SavePanel(mf);
				} else if (cmd.equals("Version")) {
					try {
						ArrayList<String> fileList = RemoteHelper.getInstance().getIOService().readFileList(username);
						System.out.println("get fileList");
						VersionSelectPanel versionSelect = new VersionSelectPanel(fileList, mf);
						System.out.println("show filePanel");
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}

				} else if (cmd.equals("Exit")) {
					System.exit(0);
				} else if (cmd.equals("Execute")) {
					String code = codeArea.getText();
					String param = inputArea.getText();
					System.out.println(inputArea.getText());
					try {
						String result = RemoteHelper.getInstance().getExecuteService().execute(code, param);
						resultArea.setText(result);
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
				} else if (cmd.equals("Logout")) {
					try {
						boolean result = RemoteHelper.getInstance().getUserService().logout(username);
						if (result) {
							logLabel = false;
						}
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
				} else if (cmd.equals("Redo")) {
					if (count >= 2) {
						codeArea.setText(code.get(count - 2));
						count--;
					} else if (count == 1) {
						codeArea.setText("");
						count--;
					}

				} else if (cmd.equals("Undo")) {
					if (count < code.size()) {
						codeArea.setText(code.get(count));
						count++;
					}
				} 
			}else {
				WarningPanel warning = new WarningPanel("Please login first");
					System.out.println("Please login first");
				}
		}
	}

	class loginActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			LoginPanel login = new LoginPanel();
		}

	}

}
