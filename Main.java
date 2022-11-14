

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Main {

	static int Screen_Height;
	static int Screen_Width;
	
	
	JFrame frame;
	Config config;
		
	static String Referencefile;
	static String CodeFile;
	static String IniFile;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Screen_Width = (int)screenSize.getWidth();
		Screen_Height =  (int)screenSize.getHeight();
		
		
		new Config(screenSize);
		
	}

}
