import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


import javax.swing.JButton;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;



public class FileViewer extends JFrame implements ActionListener, MouseListener{

	JTextArea textArea;
	JScrollPane scrollPane;
	JPanel tPanel;
	JTextField fileName;
	JLabel fileN;
	
	JPanel cPanel;

	JPanel lPanel;
	JButton saveButton;
	JButton openButton;
	JButton selectTextDButtton;
	JButton closeButton;
	JButton createNewRef;
	
	JFrame westFrame; 
	
	JPanel rPanel;
	JTextArea selectCodes;
	JScrollPane rScroll;
	JButton rComplete;
	JButton appendPoss;
	

	ArrayList<String> alselDesc;
	ArrayList<String> alselCode;
	int selIndex;
	int getIndex;
	boolean referenceSet = false;
	JButton addCode;
	
	JTextField sPoint;
	JTextField ePoint;
	int sp;
	int ep;
	JPanel sPanel;
	JTextField Reference;
	JTextField Author;
	JTextField RefCodes;
	
	File FilePdf;
	
	int newCode;
	
	String[] selCodes;
	String[] codes;
	
	References activeReferences;
	ReferenceCodes activeCodes;
	FileViewer() {
				
		this.setLayout(new BorderLayout());
		this.setSize(new Dimension((int)(Main.Screen_Width),(int)(Main.Screen_Height)));
		activeReferences = new References();
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		

		TopPanel();
		bodyPanel();
		eastPanel();
		westPanel();
		southPanel();
		
		
		//this.add(scrollPane,BorderLayout.CENTER);
		this.add(sPanel,BorderLayout.SOUTH);
		this.add(lPanel,BorderLayout.WEST);
		this.add(tPanel,BorderLayout.NORTH);
		this.add(rPanel,BorderLayout.EAST);
		this.setVisible(true);
	}
	
	private void bodyPanel() {
		JPanel cPanel = new JPanel();	
		//cPanel.setPreferredSize(new Dimension((int)(Main.Screen_Width *.45),(int)(Main.Screen_Height*.30)));
		cPanel.setBackground(Color.YELLOW);
		textArea = new JTextArea("Please Read a File");
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setVisible(true);
		
		scrollPane = new JScrollPane(textArea);
		scrollPane.setPreferredSize(new Dimension((int)(Main.Screen_Width *.7),(int)(Main.Screen_Height*.90)));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVisible(true);
		cPanel.add(scrollPane);
		cPanel.setVisible(true);
		this.add(cPanel,BorderLayout.CENTER);
		
	}
	
	public void TopPanel() {
		
		tPanel = new JPanel();
		tPanel.setPreferredSize(new Dimension((int)(Main.Screen_Width *.6),(int)(Main.Screen_Height*.03)));
		fileName = new JTextField("Please Read a File");
		fileName.setEditable(false);
		fileN = new JLabel();
		fileN.setText("File Name:");
		tPanel.add(fileN);
		tPanel.add(fileName);
		
		sPoint = new JTextField();
		sPoint.setEditable(false);
		ePoint = new JTextField();
		ePoint.setEditable(false);
		tPanel.add(sPoint);
		tPanel.add(ePoint);
		
	}
	
	
	public void southPanel() {
		sPanel = new JPanel();
		//sPanel.setPreferredSize(new Dimension(800,50));
		Author = new JTextField("Unknown");
		Author.setEditable(false);
		Reference = new JTextField("Select Text to define a Reference");
		Reference.setEditable(false);
		sPanel.add(Author);
		sPanel.add(Reference);
	}
	
	public void eastPanel() {
		rPanel = new JPanel();
		
		rPanel.setPreferredSize(new Dimension((int)(Main.Screen_Width *.175),(int)(Main.Screen_Height*.65)));
			
		
		
		rPanel.add(activeCodes = new ReferenceCodes());
		//rPanel.add(comboBox);
		//rPanel.add(rScroll);
		//rPanel.add(rComplete);
	}
	
	
	public void westPanel() {
	

		lPanel= new JPanel();
		lPanel.setPreferredSize(new Dimension((int)(Main.Screen_Width *.075),(int)(Main.Screen_Height*.65)));
		
		saveButton =new JButton();
		saveButton.setText("Save Reference");
		saveButton.addActionListener(this);
		
		openButton = new JButton();
		openButton.setText("Read a File");
		openButton.addActionListener(this);
		
		selectTextDButtton = new JButton();
		selectTextDButtton.setText("Select Text");
		selectTextDButtton.addActionListener(this);
		selectTextDButtton.setEnabled(false);
		
		createNewRef = new JButton();
		createNewRef.setText("Create new Reference ");
		createNewRef.addActionListener(this);
		createNewRef.setEnabled(true);
		
		appendPoss = new JButton();
		appendPoss.setText("Add existing Codes");
		appendPoss.addActionListener(this);
		appendPoss.setEnabled(false);
		
		
		//lPanel.add(saveButton);
		lPanel.add(openButton);
		lPanel.add(selectTextDButtton);
		lPanel.add(createNewRef);
		//lPanel.add(appendPoss);
		
		closeButton = new JButton();
		closeButton.setText("Close Reader");
		closeButton.addActionListener(this);
		//lPanel.add(closeButton);
		
	
	}
	
	
	public void createNewRef()  {
		
		boolean dataError = false;
		
		
		String FileName = fileName.getText();
		String leadAuthor = Author.getText();
		String selectedText = Reference.getText();
		String pageFound = "UKNOWN";
		String startCharacter = sPoint.getText();
		String endCharacter = ePoint.getText();
		String datePublished = "UKNOWN";
		String coAuthors = "UNKNOWN";
		
		String assignedCodes = activeCodes.CodeDescToCodes(activeCodes.getSelected());
		System.out.println("test point data from ReferenceCodes :" +assignedCodes);
		Integer x =activeReferences.References.size()+1;
		String refnum = x.toString();
		
		activeReferences.addNewRecord(x ,FileName, selectedText, pageFound, datePublished, startCharacter, endCharacter, leadAuthor, coAuthors, assignedCodes);
		activeReferences.saveArrayLists();
		//rPanel.updateUI();
		
		
	}
	
	
	
	public void resetForm() {
		
		rComplete.setEnabled(false);
		referenceSet =false;
		selectTextDButtton.setEnabled(true);
		addCode.setEnabled(true);
		
		sp = 0;
		ep = 0;
		Reference.setText("");
		alselCode.clear();
		alselDesc.clear();
		selectCodes.setText("");
		updatePanels();
		
	}
	
	public void updatePanels() {
		sPanel.updateUI();
		rPanel.updateUI();
		lPanel.updateUI();
		tPanel.updateUI();
	}
	
	
	public void Highlighttext(int start, int end,Color c) throws BadLocationException {
		
		Highlighter hiText = textArea.getHighlighter();
		HighlightPainter hiPainter = new DefaultHighlighter.DefaultHighlightPainter(c);
		hiText.addHighlight(start,end,hiPainter);
			
	}
	
	public void readFile() throws IOException {
		JFileChooser openpdf = new JFileChooser();
		int returnValue = openpdf.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION){
			textArea.setText("");
			selectTextDButtton.setEnabled(true);
			
			FilePdf = openpdf.getSelectedFile();
			File pdfRef = FilePdf.getAbsoluteFile();
			fileName.setText(pdfRef.getPath());
			tPanel.updateUI();
		
			FileReader inreader = new FileReader(pdfRef);
			BufferedReader pdfbuf = new BufferedReader(inreader);
			String bufLine = null;
				while ((bufLine = pdfbuf.readLine()) != null) {
					textArea.append(bufLine + "\n");
				}
			pdfbuf.close();
			}
			
		try {
			HighlightOthers();
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		}
	
	private void HighlightOthers() throws BadLocationException  {
	
				for (int i = 0; i < activeReferences.References.size() ; i++) {
					if (fileName.getText().equals(activeReferences.References.get(i).fileName)){
						int st = Integer.valueOf(activeReferences.References.get(i).startCharacter);
						int en = Integer.valueOf(activeReferences.References.get(i).endCharacter);
						Highlighttext(st ,en ,Color.CYAN);
					}
				}
		
	}
	
	private void setHilightedReferenceData() {
			Reference.setText(textArea.getSelectedText());
			//System.out.println(textArea.getSelectedText());
			ep =textArea.getSelectionEnd();
			ePoint.setText(Integer.toString(ep));
			sp =textArea.getSelectionStart();
			sPoint.setText(Integer.toString(sp));	
	}

	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() ==selectTextDButtton) {
		
			setHilightedReferenceData();
			
			try {
				Highlighttext(sp,ep,Color.ORANGE);
			} catch (BadLocationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//sPanel.updateUI();
			//addCode.setEnabled(true);
			referenceSet = true;
			tPanel.updateUI();
			//comboBox.setEnabled(true);
		
			
			
			//cNewRef();
		
		appendPoss.setEnabled(true);
		}
		
		
		if (e.getSource() == closeButton) {
			
			
		}
		
		if (e.getSource() == openButton) {
			
					try {
						readFile();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			
			}
			
		if (e.getSource() == createNewRef) {
			 createNewRef();
			}
			
			
	
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method 
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		 if (textArea.getSelectedText() != null) { 
		        Reference.setText(textArea.getSelectedText());
		        ep =textArea.getSelectionEnd();
				ePoint.setText(Integer.toString(ep));
				sp =textArea.getSelectionStart();
				sPoint.setText(Integer.toString(sp));	
				try {
					Highlighttext(sp,ep,Color.ORANGE);
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        sPanel.updateUI();
		        
		 }
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
