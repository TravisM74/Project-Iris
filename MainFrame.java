import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame implements ActionListener, MouseListener {
 
	static double panel_width = .18; //screen Percent
	static int Side_Panel_Height = (int)(Main.Screen_Height * .8);
	static int Side_Panel_Width = (int)(Main.Screen_Width * panel_width);
	static int Center_Panel_Width = (int)(Main.Screen_Width * (panel_width * 2) );
	ReferenceCodes activeCodes;
	JPanel ReaderPanel;
	DisplayActiveReference activeReference;
	
	
	MainFrame (){
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(Main.Screen_Width,(int)(Main.Screen_Height*.90));
		this.setLayout(new BorderLayout());
		this.setVisible(true);
		
		DisplayReference();
		System.out.println(Side_Panel_Height + " " +Side_Panel_Width);
		
		this.pack();
		
	}
	
	public void North_Panel() {
		JPanel NavPanel = new JPanel();
		//NavPanel.setPreferredSize(new Dimension(Main.Screen_Width ,(int)(Main.Screen_Height * .15)));
		NavPanel.setBackground(Color.LIGHT_GRAY);
		NavPanel.setVisible(true);
		this.add(NavPanel,BorderLayout.NORTH);
		
		
	}
	
	public void DisplayReference() {
		JPanel ReferancePanel = new JPanel();
		//ReferancePanel.setPreferredSize(new Dimension((Side_Panel_Width),(Side_Panel_Height)));
		ReferancePanel.add(activeReference = new DisplayActiveReference());
		ReferancePanel.setVisible(true);
		this.add(ReferancePanel,BorderLayout.WEST);
	}
	
	public void DisplayCodes() {
		JPanel ReferenceCodesPanel = new JPanel();
		//ReferenceCodesPanel.setPreferredSize(new Dimension((Side_Panel_Width) , (Side_Panel_Height)));
		
		//ReferenceCodesPanel.add(new ReferenceCodes());
		
		
		ReferenceCodesPanel.setBackground(Color.LIGHT_GRAY);
		ReferenceCodesPanel.setVisible(true);
		this.add(ReferenceCodesPanel,BorderLayout.EAST);
	}
	
	public void openReader() {
			// TODO Auto-generated method stub
			ReaderPanel = new JPanel();
			//ReaderPanel.setPreferredSize(new Dimension((int)(Main.Screen_Width *.6) ,(int)( Main.Screen_Height*.4)));
			ReaderPanel.setBackground(Color.magenta);
			//MainFrame.ReaderPanel.add(new FileViewer());
			ReaderPanel.setVisible(true);
			this.add(ReaderPanel,BorderLayout.CENTER);
			
		
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}


}
