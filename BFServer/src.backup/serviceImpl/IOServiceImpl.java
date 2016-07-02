package serviceImpl;

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

public class IOServiceImpl implements IOService{
	public HashMap<String,ArrayList<File>> fileList; 
	
	public IOServiceImpl(){
		fileList = new HashMap<String,ArrayList<File>>();
		try {
			FileInputStream fs = new FileInputStream("fileList.ser");
			ObjectInputStream os = new ObjectInputStream(fs);
			fileList = (HashMap<String,ArrayList<File>>) os.readObject();
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
				fileList = (HashMap<String,ArrayList<File>>) oi.readObject();
				System.out.println("there is a fileList");
				oi.close();
			} catch (Exception ex1) {
				ex1.printStackTrace();
			}
		}
//		if(count==0){
//			fileList = new HashMap<String,ArrayList<File>>();
//			try{
//				FileOutputStream fs = new FileOutputStream("fileList.ser");
//				ObjectOutputStream os = new ObjectOutputStream(fs);
//				os.writeObject(fileList);
//				os.close();
//			}catch(Exception ex){
//				ex.printStackTrace();
//			}
//		}else{
//			try{
//				FileInputStream fs = new FileInputStream("fileList.ser");
//				ObjectInputStream os = new ObjectInputStream(fs);
//				fileList = (HashMap<String,ArrayList<File>>)os.readObject();
//				os.close();
//			}catch(Exception ex){
//				ex.printStackTrace();
//			}
//		}
//		count++;
		
	}
	@Override
	public boolean writeFile(String file, String userId, String fileName) {
		ArrayList<File> userFile;
		boolean newOne = false;
		if(!(fileList.containsKey(userId))){
			userFile = new ArrayList<File>();
			fileList.put(userId, userFile);
//			try {
//				FileOutputStream fo = new FileOutputStream("fileList.ser");
//				ObjectOutputStream oo = new ObjectOutputStream(fo);
//				oo.writeObject(fileList);
//				oo.close();
//			} catch (Exception ex1) {
//				ex1.printStackTrace();
//			}
			newOne = true;
		}else{
			userFile = fileList.get(userId);
			try {
			for(File f:userFile){
				FileReader fr = new FileReader(f);
				BufferedReader read = new BufferedReader(fr);
				String line = null;
				while((line=read.readLine())!=null){
					if(!(line.equals(file))){
						newOne = true;
						break;
					}
				}
			}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(newOne){
		File f = new File(userId + "_" + fileName);
		try {
			FileWriter fw = new FileWriter(f, false);
			fw.write(file);
			fw.flush();
			fw.close();
			userFile.add(f);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		}else{
			return false;
		}
		
	}

	@Override
	public String readFile(String userId, String fileName) {
		// TODO Auto-generated method stub
		String code = "";
		try {
		File f = null; 
		ArrayList<File> userFile;
		userFile = fileList.get(userId);
		for(int i=0;i<userFile.size();i++){
			String[] s = userFile.get(i).getName().split("_");
			if(s[1].equals(fileName)){
				f = userFile.get(i);
				break;
			}
		}
			FileReader fr = new FileReader(f); 
			BufferedReader read = new BufferedReader(fr);
			String line = null;
			while((line=read.readLine())!=null){
				code = line;
			}
			read.close();
		} catch (IOException e) {
			e.printStackTrace();
			code =  "There is something wrong";
		}
		return code;
	}

	@Override
	public ArrayList<String> readFileList(String userId) {
		// TODO Auto-generated method stub
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<File> userFile;
		userFile = fileList.get(userId);
		for(int i=0;i<userFile.size();i++){
			String[] s = userFile.get(i).getName().split("_");
			result.add(s[1]);
		}
		return result;  
	}
	
}
