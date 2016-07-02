package ui;
/*
 * 版本选择界面，将列出该用户所拥有的所有文件版本
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class VersionSelectPanel extends JPanel implements ItemListener {
	private int width = 300;
	private int height = 300;
	private String versionname = "";
	private MainFrame mf;
	private JFrame frame;

	public VersionSelectPanel(ArrayList<String> versionList, MainFrame mainFrame) { 
		mf = mainFrame;
		frame = new JFrame();
		this.setBorder(BorderFactory.createTitledBorder("Please select one version"));
		this.setSize(width, height);
		this.setBackground(Color.white);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		frame.setSize(width, height);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screensize = kit.getScreenSize();
		int screenheight = screensize.height;
		int screenwidth = screensize.width;
		frame.setLocation(screenwidth / 2 - width / 2, screenheight / 2 - height / 2);

		// 选择框
		ButtonGroup selectButton = new ButtonGroup();
		for (int i = 0; i < versionList.size(); i++) {
			JRadioButton b = new JRadioButton(versionList.get(i));
			b.addItemListener(this);
			selectButton.add(b);
			this.add(b);

		}

		frame.getContentPane().add(this);
		frame.setVisible(true);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		JRadioButton b = (JRadioButton) e.getSource();
		versionname = b.getText();
		mf.openFile(versionname);
		frame.dispose();
	}

}
