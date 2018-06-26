package ui_java;

import javax.swing.JFrame;

public class MainUI {
	
	public static void main(String[] args) {
		SimpleFrame mainFrame = new SimpleFrame();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
	}

}

class SimpleFrame extends JFrame {
	
	public SimpleFrame() {
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	public static final int DEFAULT_WIDTH = 600;
	public static final int DEFAULT_HEIGHT = 600;
}