package EventListener;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.text.JTextComponent;

import ui.MainFrame;

public class MainFrameTextListener extends TextListener {
	MainFrame mainFrame;
	JTextComponent textArea;
	ArrayList<String> text;

	public MainFrameTextListener(MainFrame f, JTextComponent area, ArrayList<String> s) {
		super(f, area, s);
		textArea = area;
		text = s;
		mainFrame = f;
		// TODO Auto-generated constructor stub
	}

	public void keyTyped(KeyEvent e) {
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		text.add(textArea.getText());
		mainFrame.setCode(text);
		mainFrame.setCount(text.size());
	}

}
