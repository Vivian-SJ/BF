package serviceImpl;

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

		
//		if (count == 0) {
//			idAndPassword = new HashMap<String, String>();
//			try {
////				FileWriter fw = new FileWriter(new File("idAndPassword.txt"));
////				BufferedWriter bw = new BufferedWriter(fw);
////				String line = "";
////				bw.
//				FileOutputStream fs = new FileOutputStream("idAndPassword.ser");
//				ObjectOutputStream os = new ObjectOutputStream(fs);
//				os.writeObject(idAndPassword);
//				System.out.println("There new an idandpass");
//				os.close();
//			} catch (Exception ex) {
//				ex.printStackTrace();
//			}
//		} else {
//			try {
//				FileInputStream fs = new FileInputStream("idAndPassword.ser");
//				ObjectInputStream os = new ObjectInputStream(fs);
//				idAndPassword = (HashMap<String, String>) os.readObject();
//				System.out.println("there is");
//				os.close();
//			} catch (Exception ex) {
//				ex.printStackTrace();
//			}
//		}
//		count++;

	}

	@Override
	public boolean login(String username, String password) throws RemoteException {
		if (idAndPassword.containsKey(username)) {
			if (idAndPassword.get(username).equals(password)) {
				System.out.println("match success");
				System.out.println(idAndPassword);
				try {
					FileOutputStream fo = new FileOutputStream("idAndPassword.ser");
					ObjectOutputStream oo = new ObjectOutputStream(fo);
					oo.writeObject(idAndPassword);
					oo.close();
				} catch (Exception ex1) {
					ex1.printStackTrace();
				}
				return true;
			} else {
				return false;
			}
		} else {
			idAndPassword.put(username, password);
			System.out.println("add success");
			return true;
		}
	}

	@Override
	public boolean logout(String username) throws RemoteException {
		return true;
	}

}
