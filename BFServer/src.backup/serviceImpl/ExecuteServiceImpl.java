//请不要修改本文件名
package serviceImpl;

import java.rmi.RemoteException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

import service.ExecuteService;
import service.UserService;

public class ExecuteServiceImpl implements ExecuteService {
	char[] codes;
	char[] input;
	ArrayList<Character> output = new ArrayList<Character>();
	ArrayList<Integer> data = new ArrayList<Integer>();
	int codeNum = 0;
	int inputNum = 0;
	int dataNum = 0;
	int latterLabel;
	Deque<Integer> formerLabel = new ArrayDeque<Integer>();

	/**
	 * 请实现该方法
	 */
	@Override

	public String execute(String code, String param) throws RemoteException {
		// TODO Auto-generated method stub
		output.clear();
		data.clear();
		codeNum = 0;
		inputNum = 0;
		dataNum = 0;
		codes = code.toCharArray();
		input = param.toCharArray();
		data.add(0);
		while (codeNum < codes.length) {
			switch (codes[codeNum]) {
			case ',':
				if (inputNum < input.length) {
					char operator = input[inputNum];
					data.set(dataNum, charToAscii(operator));
					if (dataNum + 1 >= data.size()) {
						data.add(0);
					}
					inputNum++;
					codeNum++;
				}
				break;
			case '>':
				if (dataNum + 1 >= data.size()) {
					data.add(0);
				}
				dataNum++;
				codeNum++;
				break;
			case '<':
				dataNum--;
				codeNum++;
				break;
			case '+':
				int currentData1 = data.get(dataNum) + 1;
				data.set(dataNum, currentData1);
				codeNum++;
				break;
			case '-':
				int currentData2 = data.get(dataNum) - 1;
				data.set(dataNum, currentData2);
				codeNum++;
				break;
			case '.':
				int currentData3 = data.get(dataNum);
				output.add(asciiToChar(currentData3));
				codeNum++;
				break;
			case '[':
				formerLabel.push(codeNum);
				if (data.get(dataNum) == 0) {
					if (latterLabel != 0) {
						codeNum = latterLabel++;
					} else {
						codeNum++;
					}
				} else {
					codeNum++;
				}
				break;
			case ']':
				latterLabel = codeNum;
				if (data.get(dataNum) != 0) {
					codeNum = formerLabel.peek()+1;
				} else {
					codeNum++;
					latterLabel = 0;
					formerLabel.pop();
				}
				break;
			}
		}
		String result = "";
		for(int i=0;i<output.size();i++){
			result = result+output.get(i).toString();
		}
		return result; 
	}

	public static int charToAscii(char ch) {
		int ascii = (int) ch;
		return ascii;
	}

	public static char asciiToChar(int ascii) {
		char ch = (char) ascii;
		return ch;
	}

}
