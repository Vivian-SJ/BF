package serviceImpl;
/*
 * 实现代码文件版本的保存、读取
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import service.IOService;

public class IOServiceImpl implements IOService {
	public HashMap<String, ArrayList<File>> fileList;

	public IOServiceImpl() {
		fileList = new HashMap<String, ArrayList<File>>();
		try {
			FileInputStream fs = new FileInputStream("fileList.ser");
			ObjectInputStream os = new ObjectInputStream(fs);
			fileList = (HashMap<String, ArrayList<File>>) os.readObject();
			System.out.println("there is a fileList");
			System.out.println(fileList);
			os.close();
		} catch (Exception ex) {
			try {
				FileOutputStream fo = new FileOutputStream("fileList.ser");
				ObjectOutputStream oo = new ObjectOutputStream(fo);
				oo.writeObject(fileList);
				System.out.println("There new a fileList");
				oo.close();
				FileInputStream fi = new FileInputStream("idAndPassword.ser");
				ObjectInputStream oi = new ObjectInputStream(fi);
				fileList = (HashMap<String, ArrayList<File>>) oi.readObject();
				System.out.println("there is a fileList");
				System.out.println(fileList);
				oi.close();
			} catch (Exception ex1) {
				ex1.printStackTrace();
			}
		}
	}

	//新建文件
	@Override
	public boolean writeFile(String file, String userId, String fileName) {
		ArrayList<File> userFile;
		boolean newOne = false;
		if (!(fileList.containsKey(userId))) {
			userFile = new ArrayList<File>();
			fileList.put(userId, userFile);
			System.out.println("new user");
			newOne = true;
		} else {
			userFile = fileList.get(userId);
			if (userFile.size() <= 10) {
				try {
					boolean label = false;
					for (File f : userFile) {
						FileReader fr = new FileReader(f);
						BufferedReader read = new BufferedReader(fr);
						String line = null;
						while ((line = read.readLine()) != null) {
							if (line.equals(file)) {
								newOne = false;
								label = true;
								break;
							}
						}
						if(label){
							break;
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("You are only allowd to store no more than 10 versions");
			}
		}
		if (newOne) {
			File f = new File(userId + "_" + fileName);
			try {
				FileWriter fw = new FileWriter(f, false);
				fw.write(file);
				fw.flush();
				fw.close();
				userFile.add(f);
				try {
					FileOutputStream fo = new FileOutputStream("fileList.ser");
					ObjectOutputStream oo = new ObjectOutputStream(fo);
					oo.writeObject(fileList);
					oo.close();
					System.out.println("New File save");
				} catch (Exception ex1) {
					ex1.printStackTrace();
				}
				return true;

			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		} else {
			return false;
		}

	}

	//读取文件
	@Override
	public String readFile(String userId, String fileName) {
		// TODO Auto-generated method stub
		String code = "";
		try {
			File f = null;
			ArrayList<File> userFile;
			userFile = fileList.get(userId);
			for (int i = 0; i < userFile.size(); i++) {
				String[] s = userFile.get(i).getName().split("_");
				System.out.println(s[1]);
				if (s[1].equals(fileName)) {
					f = userFile.get(i);
					break;
				}
			}
			FileReader fr = new FileReader(f);
			BufferedReader read = new BufferedReader(fr);
			String line = null;
			while ((line = read.readLine()) != null) {
				code = line;
			}
			read.close();
		} catch (IOException e) {
			e.printStackTrace();
			code = "There is something wrong";
		}
		return code;
	}
	

	//读取某个用户所拥有的所有文件的文件列表
	@Override
	public ArrayList<String> readFileList(String userId) {
		// TODO Auto-generated method stub
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<File> userFile;
		if(fileList.get(userId)!=null){
		userFile = fileList.get(userId);
		for (int i = 0; i < userFile.size(); i++) {
			String[] s = userFile.get(i).getName().split("_");
			String file = s[1];
			result.add(s[1]);
		}
		}else{
			
		}
		return result;
	}

}
