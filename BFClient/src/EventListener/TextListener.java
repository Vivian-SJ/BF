package EventListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.text.JTextComponent;

public class TextListener implements KeyListener {
	JFrame frame;
	JTextComponent textArea;
	ArrayList<String> text;
	public TextListener(JFrame f, JTextComponent area, ArrayList<String> s){
		frame = f;
		textArea = area;
		text = s;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


}
