import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Config extends JFrame implements ActionListener{


	
	JFrame frame;
	JPanel bpanel;
	boolean FileChosen;
	JPanel MenuPanel;
	JButton NewProjectButton;
	JButton ExistingProjectButton;
	
	String NewFilename;
	File New_iniFile;
	
	

	Config(Dimension screenSize){
		FileChosen = false;
		
		this.setLayout(new BorderLayout());
		this.setBackground(Color.GRAY);
		this.setPreferredSize(new Dimension(screenSize));

		StartSelection();
		menuOptions();
		
		this.add(MenuPanel,BorderLayout.WEST);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		this.setVisible(true);
		this.pack();
			
		
		
	}

	private void StartSelection() {
		
		
	}
	private void menuOptions() {
		/*
		 * Menu for choosing New Or Load existing Project
		 */
		MenuPanel = new JPanel();
		MenuPanel.setPreferredSize(new Dimension ((int)(Main.Screen_Width * .2), Main.Screen_Height));
		MenuPanel.setBackground(Color.DARK_GRAY);
		NewProjectButton = new JButton("Sart New Porject");
		NewProjectButton.setVisible(true);
		NewProjectButton.addActionListener(this);
		MenuPanel.add(NewProjectButton);
		ExistingProjectButton = new JButton("Load Existing Project");
		ExistingProjectButton.addActionListener(this);
		MenuPanel.add(ExistingProjectButton);
		MenuPanel.setVisible(true);
	}
	
	private void BlankPanel() {
		/*
		 * Blank panel used to remove no needed Panels from main Panel.
		 */
		JPanel bpanel = new JPanel();
		this.add(bpanel,BorderLayout.WEST);
		bpanel.setPreferredSize(new Dimension ((int)(Main.Screen_Width * .2),(int) Main.Screen_Height));
		
		this.pack();
	}
	
	private void CreateNewProject() {
		/*
		 * Creating New Files to Save to for a new project.
		 */
		String s3 =".Inim";
		NewFilename = "name needed";
		while (NewFilename.equals("name needed")) {
			NewFilename = JOptionPane.showInputDialog("Please enter a name for project","name needed");
			if (NewFilename == null) {
				NewFilename = "name needed";
			}
			New_iniFile = new File(NewFilename.concat(s3));
			if (New_iniFile.exists()) {
				NewFilename = JOptionPane.showInputDialog("Project Already exisits. \n Try a new name please .","name needed");
			}
		}
		String s1 =".Idbm";
		String s2 =".Idbc"; 
		String IniFile = NewFilename.concat(s3);
		String FileMain = NewFilename.concat(s1);
		String fileCod = NewFilename.concat(s2);
		Main.CodeFile = fileCod;
		Main.IniFile = IniFile;
		Main.Referencefile = FileMain;
		
        try {
			if(New_iniFile.createNewFile()){
			 	FileWriter writer3 = new FileWriter(IniFile);
			 	writer3.write(FileMain + "," + fileCod);
			 	writer3.close();
			 }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		File New_Main = new File(FileMain);
		try {
			if (New_Main.createNewFile()) {
				FileWriter writer1 = new FileWriter(FileMain);
				writer1.close();
			}
		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
			
		
		File New_CFile = new File(fileCod);
        try {
			if(New_CFile.createNewFile()){
			 	FileWriter writer2 = new FileWriter(fileCod);
			 	writer2.close();
			 }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
		JOptionPane.showMessageDialog(null, "Setup of project \n" +NewFilename + "\n Complete. \nYou may now open it.");
		
	}
	private void selectFileName() {
		/*
		 * Select file  of type .Inim and sets Static Absolute Path
		 */
		String absolutePath ="";
		 
		JFileChooser openref = new JFileChooser();
		openref.addChoosableFileFilter(new FileNameExtensionFilter("Iris Database File", "Inim"));
		openref.setCurrentDirectory(null);
		openref.setAcceptAllFileFilterUsed(true);
			int returnValue = openref.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION){
				File FileRef = openref.getSelectedFile();
				absolutePath = FileRef.getAbsolutePath();
				
				Main.IniFile = absolutePath ;
				this.remove(MenuPanel);
				
				try {
					ReadIniFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				BlankPanel();
				//frame.add(bpanel,BorderLayout.WEST);
				this.pack();
			} else {
				JOptionPane.showMessageDialog(null, "No File Selected, \n Try create a new Project");
			}
			System.out.println(Main.IniFile +" "+Main.CodeFile+" "+Main.Referencefile);
	 }
	
	private void ReadIniFile() throws IOException {
		
	
		FileReader readcode = new FileReader(Main.IniFile);
		BufferedReader bufincode = new BufferedReader(readcode);
		String codeline= null;
		
		while ((codeline = bufincode.readLine()) != null) {
			String[] linecode = codeline.split(",");
			Main.Referencefile = (linecode[0]);
			Main.CodeFile =(linecode[1]);
		}
		
		bufincode.close();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ExistingProjectButton) {
			selectFileName();
			new MainFrame();
			this.dispose();
	
			
		}
		if(e.getSource() == NewProjectButton) {
			CreateNewProject();
		}
		
	}
	
	
}
