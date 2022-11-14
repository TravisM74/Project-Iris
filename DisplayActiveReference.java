
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
<<<<<<< HEAD
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

=======
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
>>>>>>> fb0841f4219ef36db11d4eb40be73bd1a34c6acf

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class DisplayActiveReference extends JPanel implements ActionListener{
	
	JTextField ReferenceFile;
	JTextField AuthorTf;
	JTextField ReferenceTf;
	JTextField PublicationTf;
	JTextField ReferenceCode;
	JTextField Page;
	JTextField StartPt;
	JTextField EndPt;
	
	JTextArea decArea;
	JScrollPane scrollPane;
	
<<<<<<< HEAD
=======
	JFrame midframe;
	
>>>>>>> fb0841f4219ef36db11d4eb40be73bd1a34c6acf
	JPanel refDataPanel;
	JPanel RefPan;
	JPanel North_Panel;
	
	JPanel refPanel;
	JPanel Next_Prev_Panel;
	JTextField numberOf;
	JPanel refOp;
	JPanel np_Buttons_Panel;
	
	String fileName;
	JButton saveDatabase;
	JButton next;
	JButton last;
	JButton edit;
	JButton addRef;
	JButton sRef;
	JButton refEbutton;
	JButton editCodesButton;
	int index = 0;
	References ListOfReferences = new References();
	
	public DisplayActiveReference() {

		this.setLayout(new FlowLayout());
		this.setPreferredSize(new Dimension(Main.Screen_Width,(int)(Main.Screen_Height * .98)));
		this.setLayout(new BorderLayout());
		this.setBackground(Color.BLUE);
		//ReferenceFile.setSize(200,200);
	
		Create_Display_Panel();
		this.setVisible(true);
	}

	
	
	private void Create_Display_Panel() {
		JPanel Main_Panel = new JPanel();
		Creat_North_Panel();
		ReferencePanel();
		RefData();
		check_If_Empty();
		Main_Panel.setLayout(new BorderLayout());
		Main_Panel.setVisible(true);
		Main_Panel.add(North_Panel,BorderLayout.NORTH);
		Main_Panel.add(RefPan,BorderLayout.CENTER);
		Main_Panel.add(refDataPanel,BorderLayout.SOUTH);
		this.add(Main_Panel,BorderLayout.CENTER);
		
	}
	
	private void check_If_Empty() {
		if (!ListOfReferences.References.isEmpty()) {
			decArea.setText(ListOfReferences.References.get(index).selectedText);
			DisplayRecord();
		}else {
			decArea.setText("No Data Avalable");
		}
	}
	
	public void refPanel() {
		refPanel = new JPanel();
		refPanel.setLayout(new GridLayout(4,1));
		addRef = new JButton();
		addRef.setText("Add a new Reference");
		addRef.addActionListener(this);
		sRef = new JButton();
		sRef.setText("Find reference");
		sRef.addActionListener(this);
		editCodesButton = new JButton("Edit Codes Description");
		editCodesButton.addActionListener(this);
		Next_Prev_Panel();
	
		refPanel.add(addRef);
		refPanel.add(sRef);
		refPanel.add(editCodesButton);
		refPanel.add(Next_Prev_Panel);
	}
	
	private void Creat_North_Panel() {
		North_Panel = new JPanel();
		
		
		North_Panel.setBackground(Color.darkGray);
		refPanel();
		
		
		saveDatabase = new JButton();
		saveDatabase.setText("Save my Reference's");
		saveDatabase.addActionListener(this);
		
		ReferenceFile = new JTextField();
		ReferenceFile.setEditable(false);
		ReferenceFile.setText(Main.Referencefile);
		
		JPanel innerPanel = new JPanel();
		innerPanel.setBackground(Color.LIGHT_GRAY);
		innerPanel.setLayout(new GridLayout(2,1));
		innerPanel.setVisible(true);
		innerPanel.add(saveDatabase);
		innerPanel.add(ReferenceFile);
		North_Panel.add(innerPanel);
		North_Panel.add(refPanel);
		
	}
	
	private void RefData() {	
		refDataPanel = new JPanel();
		refDataPanel.setBackground(Color.LIGHT_GRAY);
		refDataPanel.setLayout(new GridLayout(7,1));
		numberOf = new JTextField();
		numberOf.setEditable(false);
		
		AuthorTf = new JTextField();
		AuthorTf.setEditable(false);
		AuthorTf.setName("Author :");
		
		
		ReferenceTf = new JTextField();
		ReferenceTf.setEditable(false);
		
		ReferenceTf.setName("Reference :");
		
		
		PublicationTf = new JTextField();
		PublicationTf.setName("Publication :");
		PublicationTf.setEditable(false);
		ReferenceCode = new JTextField() ;
		ReferenceCode.setEditable(false);
		Page = new	JTextField() ;
		Page.setEditable(false);
		StartPt = new	JTextField() ;
		StartPt.setEditable(false);
		EndPt	= new JTextField() ;
		EndPt.setEditable(false);
		
		refEbutton = new JButton();
		refEbutton.setText("Additional References");
		refEbutton.addActionListener(this);
		
		
		refDataPanel.add(AuthorTf);
		refDataPanel.add(PublicationTf);
		
		refOp = new JPanel();
		refOp.setLayout(new BorderLayout());
		refOp.add(refEbutton,BorderLayout.WEST);
		refOp.add(ReferenceCode,BorderLayout.CENTER);
		refDataPanel.add(refOp);
		refDataPanel.add(Page);
		refDataPanel.add(StartPt);
		refDataPanel.add(EndPt);
	}
	private void ReferencePanel() {	
		RefPan = new JPanel();
		decArea = new JTextArea(); //3,10
		decArea.setEditable(false);
		
		decArea.setLineWrap(true);
		decArea.setWrapStyleWord(true);
		
		//decArea.setPreferredSize(new Dimension(600,100));
		decArea.setVisible(true);
		scrollPane = new JScrollPane(decArea);
		scrollPane.setPreferredSize(new Dimension((int)(MainFrame.Side_Panel_Width *.95 ) ,((int)(MainFrame.Side_Panel_Height* .72 ))));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVisible(true);
		RefPan.add(scrollPane);
	}
	
	
	
	private void Next_Prev_Panel() {
		Next_Prev_Panel = new JPanel();
		Next_Prev_Panel.setLayout(new BorderLayout());
		
		np_Buttons_Panel= new JPanel();
		next = new JButton();
		next.setText(">>>>");
		next.addActionListener(this);
		last  = new JButton();
		last.setText("<<<<");
		last.addActionListener(this);
		np_Buttons_Panel.add(last);
		np_Buttons_Panel.add(next);

		if (ListOfReferences.DataLoaded) {
			next.setEnabled(true);
			last.setEnabled(true);
			refPanel.setEnabled(true);
			editCodesButton.setEnabled(true);
			
		} else {
			next.setEnabled(false);
			last.setEnabled(false);
			refPanel.setEnabled(false);
			editCodesButton.setEnabled(false);
		}
		
		Next_Prev_Panel.add(np_Buttons_Panel);
		
	}

	private void DisplayRecord() {
		 numberOf.setText(Integer.toString(ListOfReferences.References.size()));
		 AuthorTf.setText(ListOfReferences.References.get(index).leadAuthor);
		 ReferenceTf.setText(ListOfReferences.References.get(index).selectedText);
		 PublicationTf.setText(ListOfReferences.References.get(index).fileName);
		 ReferenceCode.setText(ListOfReferences.References.get(index).assignedCodes);
		 Page.setText(ListOfReferences.References.get(index).pageFound);
		 StartPt.setText(ListOfReferences.References.get(index).startCharacter);
		 EndPt.setText(ListOfReferences.References.get(index).endCharacter);
		 decArea.setText(ListOfReferences.References.get(index).selectedText);	
		 refDataPanel.updateUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == saveDatabase) {
			
			
			}
		if(e.getSource() == next) {
			index++;
			if (index >= ListOfReferences.References.size()-1) {
				index = ListOfReferences.References.size()-1;
			}
			DisplayRecord();
		
			}

		if(e.getSource() == last) {
			index--;
			if (index <= 0) {
				index = 0;
			}
			DisplayRecord();
			}

		if (e.getSource() == addRef) {
			new FileViewer(); //ReaderPanel.add(new FileViewer());
		}
	}
	

}
