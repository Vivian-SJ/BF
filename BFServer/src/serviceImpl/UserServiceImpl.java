package serviceImpl;
/*
 * 实现用户的注册、登陆和登出
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

import service.UserService;

public class UserServiceImpl implements UserService {
	HashMap<String, String> idAndPassword;
	private static int count = 0;

	public UserServiceImpl() {
		idAndPassword = new HashMap<String, String>();
		try {
			FileInputStream fs = new FileInputStream("idAndPassword.ser");
			ObjectInputStream os = new ObjectInputStream(fs);
			idAndPassword = (HashMap<String, String>) os.readObject();
			System.out.println("there is a idandpass");
			System.out.println(idAndPassword);
			os.close();
		} catch (Exception ex) {
			try {
				FileOutputStream fo = new FileOutputStream("idAndPassword.ser");
				ObjectOutputStream oo = new ObjectOutputStream(fo);
				oo.writeObject(idAndPassword);
				System.out.println("There new an idandpass");
				oo.close();
				FileInputStream fi = new FileInputStream("idAndPassword.ser");
				ObjectInputStream oi = new ObjectInputStream(fi);
				idAndPassword = (HashMap<String, String>) oi.readObject();
				System.out.println("there is a idandpass");
				oi.close();
			} catch (Exception ex1) {
				ex1.printStackTrace();
			}
		}

	}

	//登录
	@Override
	public boolean login(String username, String password) throws RemoteException {
		if (idAndPassword.containsKey(username)) {
			if (idAndPassword.get(username).equals(password)) {
				System.out.println("match success");
				return true;
			} else {
				return false;
			}
		} else {
			System.out.println("Please register first");
			return false;
		}
	}
	
	//注册
	public boolean register(String username, String password) throws RemoteException {
		idAndPassword.put(username, password);
		try {
			FileOutputStream fo = new FileOutputStream("idAndPassword.ser");
			ObjectOutputStream oo = new ObjectOutputStream(fo);
			oo.writeObject(idAndPassword);
			oo.close();
		} catch (Exception ex1) {
			ex1.printStackTrace();
		}
		System.out.println("add success");
		return true;
	}
	

	//退出登陆
	@Override
	public boolean logout(String username) throws RemoteException {
		return true;
	}

}
