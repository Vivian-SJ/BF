package ui;
/*
 * 用户登陆界面
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import rmi.RemoteHelper;

public class LoginPanel extends JPanel{
	private int width = 250;
	private int height = 200;
	private JTextField idFeild;
	private JTextField passwordFeild;
	private JFrame frame;
	
	public LoginPanel(){
		//窗体创建及设置
		frame = new JFrame("Hi! BF");
		frame.getContentPane().add(this);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screensize = kit.getScreenSize();
		int screenheight = screensize.height;
		int screenwidth = screensize.width;
		frame.setSize(width, height);
		frame.setLocation(screenwidth / 2 - width/2, screenheight / 2 - height/2);
		
		//账号、密码、登录注册按钮
		this.setLayout(new GridLayout(3,2));
		JLabel idLabel = new JLabel("Your ID");
		idFeild = new JTextField();
		JLabel passwordLabel = new JLabel("Your Password");
		passwordFeild = new JTextField(); 
		this.add(idLabel);
		this.add(idFeild);
		this.add(passwordLabel);
		this.add(passwordFeild);
		
		JButton login = new JButton("LOGIN");
		login.addActionListener(new loginActionListener());
		this.add(login);
		
		JButton register = new JButton("REGISTER");  
		register.addActionListener(new registerActionListener());
		this.add(register);
		this.setBackground(new Color(Integer.decode("#F0FFFF"))); 
		frame.getContentPane().add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	//监听内部类
	class loginActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				if(idFeild.getText()!=null&&passwordFeild.getText()!=null){
				boolean result = RemoteHelper.getInstance().getUserService().login(idFeild.getText(), passwordFeild.getText());
				if(result){
					MainFrame mainFrame = new MainFrame(idFeild.getText());
					mainFrame.setLogLabel(result);
					frame.dispose();
				}else{
					WarningPanel warning = new WarningPanel("Please check id and password");
				}
				}else{
					WarningPanel warning = new WarningPanel("Please check id and password");
				}
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	class registerActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				boolean result = RemoteHelper.getInstance().getUserService().register(idFeild.getText(), passwordFeild.getText());
				if(result){
					WarningPanel warning = new WarningPanel("You can login now");
					System.out.println("You can login now");
				}else{
				}
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		}
		
	}

}
