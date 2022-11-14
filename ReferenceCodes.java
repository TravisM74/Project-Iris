

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class ReferenceCodes extends JPanel implements ActionListener , MouseListener, KeyListener{

	JPanel DisplayPanel;
	JPanel RefreshListPanel;
	JPanel ListPanel;
	JButton addNewCodeButton;
	JButton add_Seleceted_Code_Button;
	JButton remove_Selected_Code_Button;
	JTextField codeTextField;
	
	JList<String> resultsJList;
	JList<String> selected_Codes_JList;
	DefaultListModel<String> resultsList;
	DefaultListModel<String> selected_Codes_List;

	ArrayList<ReferenceCode> ReferenceCodes = new ArrayList<ReferenceCode>();
	
	ArrayList<String> selectedCodes = new ArrayList<>();
	
	int Panel_Width = (int)(Main.Screen_Width *.14);
	int Panel_Height = (int)(Main.Screen_Height * .5);
	
	ReferenceCodes(){

		try {
			LoadCodes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setLayout(new BorderLayout());
		this.setBackground(Color.RED);
		CreateInteractiveMenu();
		Create_Selected_Codes_Panel();
		this.setVisible(true);
	}
	
	private void CreateInteractiveMenu() {
		DisplayPanel();
		CreateList();
		CreateDisplayPanelContent();
		//create_Selected_Codes_Panel();
		this.add(DisplayPanel,BorderLayout.CENTER);
		RefreshListPanel();
		
	}
	
	private void Create_Selected_Codes_Panel() {
		JPanel selected_Codes_Panel = new JPanel();
		selected_Codes_Panel.setPreferredSize(new Dimension(Panel_Width ,(int)(Panel_Height *.5)));
		selected_Codes_Panel.setLayout(new BorderLayout());
		selected_Codes_Panel.setVisible(true);
		JPanel selected_Codes_Title = new JPanel();
		
		JLabel selected_Codes_Label = new JLabel();
		selected_Codes_Label.setText("Selected Codes for reference.");
		selected_Codes_Label.setBackground(Color.LIGHT_GRAY);
		selected_Codes_Label.setVisible(true);
		selected_Codes_Title.add(selected_Codes_Label);
		
		
		selected_Codes_JList = new JList<String>(selected_Codes_List);
		selected_Codes_JList.addMouseListener(this);
		JScrollPane selected_Code_Scroll = new JScrollPane(selected_Codes_JList);
		selected_Code_Scroll.setPreferredSize(new Dimension((int)(Panel_Width),(int)(Panel_Height *.8)));
		selected_Code_Scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		selected_Code_Scroll.setVisible(true);
		
		selected_Codes_Panel.add(selected_Codes_Title,BorderLayout.NORTH);
		selected_Codes_Panel.add(selected_Code_Scroll,BorderLayout.CENTER);
		
		this.add(selected_Codes_Panel,BorderLayout.SOUTH);
	}
	
	public ArrayList<String> getSelected() {
		ArrayList <String> selected_Codes_array = new ArrayList<String>();
		for (int i = 0 ; i < selected_Codes_List.getSize(); i++) {
			selected_Codes_array.add(selected_Codes_List.get(i));
		}
		
		return selected_Codes_array;
	}
	
	public int getSelectedSize() {
		return selected_Codes_List.getSize();
	}
	                          
	                          
	private void CreateDisplayPanelContent() {
		InputCodePanel();
		ListToPanel();
		ButtonPanel();
		
	}
	
		
		
	
	public void DisplayPanel(){
		DisplayPanel = new JPanel();
		DisplayPanel.setPreferredSize(new Dimension(Panel_Width,Panel_Height));
		DisplayPanel.setLayout(new BorderLayout());
		DisplayPanel.setBackground(Color.blue);
		DisplayPanel.setVisible(true);
		
		
	}
	
	
	private void InputCodePanel() {
		JPanel InputCodePanel = new JPanel();
		InputCodePanel.setBackground(Color.gray);
		InputCodePanel.setPreferredSize(new Dimension((int)(Panel_Width *.95),(int)(Panel_Height *.1)));
		JLabel codeLabel = new JLabel();
		codeLabel.setText("Code Filter:");
		codeTextField = new JTextField();
		codeTextField.setPreferredSize(new Dimension((int)(Panel_Width *.65),(int)(Panel_Height *.04)));
		codeTextField.addKeyListener(this);
		InputCodePanel.add(codeLabel);
		InputCodePanel.add(codeTextField);
		InputCodePanel.setVisible(true);
		DisplayPanel.add(InputCodePanel,BorderLayout.NORTH);
	}
	
	private void ButtonPanel() {
		JPanel ButtonPanel = new JPanel();
		ButtonPanel.setBackground(Color.DARK_GRAY);
		ButtonPanel.setPreferredSize(new Dimension((int)(Panel_Width *.95),(int)(Panel_Height *.1)));
		
		addNewCodeButton = new JButton("Add New Code");
		addNewCodeButton.addActionListener(this);
		addNewCodeButton.setVisible(true);
		ButtonPanel.add(addNewCodeButton);
		
		add_Seleceted_Code_Button = new JButton(" \\/ ");
		add_Seleceted_Code_Button.addActionListener(this);
		add_Seleceted_Code_Button.setVisible(true);
		ButtonPanel.add(add_Seleceted_Code_Button);
		
		remove_Selected_Code_Button = new JButton(" /\\ ");
		remove_Selected_Code_Button.addActionListener(this);
		remove_Selected_Code_Button.setVisible(true);
		ButtonPanel.add(remove_Selected_Code_Button);
		ButtonPanel.setVisible(true);
		
		DisplayPanel.add(ButtonPanel,BorderLayout.SOUTH);
	}
	
	
	private void CreateList() {
		// Creates the new Lists
		 resultsList = new DefaultListModel<>();
		 selected_Codes_List = new DefaultListModel<>();
		 resultsJList = new JList<String>(resultsList);
		 CloseList();
	}
	
	
	private void listupdate() {
		//populate lists with data
		String[] results = ListCodeDescriptions();
		RefreshListPanel.setBackground(Color.green);
		Arrays.sort(results);
		System.out.println("Listupdate results.length:"+ results.length);
		resultsList.clear();
		for(int i =0 ; i < results.length ; i++) {
			resultsList.addElement(results[i]);
			// selected_Codes_List.addElement(results[i]);
			System.out.println("listupdate-resultsList :"+i+": "+resultsList.getElementAt(i));
		}
		if (resultsList.size() < 0) {
			resultsList.addElement("NO_Data_Avalable");
		}
	System.out.println("Listupdate resultsList.getSize() :"+ resultsList.getSize());
	}
	
	/* building a new panel to take a list of Selected Reference codes
	 * to solve losing choices if you edit search criteria
	 * 
	 * this check needs to be performed from DisplayActiveRecord
	 */
	
	private void Check_for_Existing_Reference(String ReferenceTF){
		References references = new References();
		String[] ref_Desc;
		int references_Size = references.get_Size();
		for (int i = 0; i < references_Size;i++) {
			if(references.References.get(i).selectedText.equals(ReferenceTF)) {
				ref_Desc = references.DecatCodes(references.References.get(i).assignedCodes);
				for(int j =0; j < ref_Desc.length;j++) {
					selected_Codes_List.addElement(ref_Desc[j]);
				}
			}
		}
			
	}
	
	private void CloseList() {
		//finalise the lists ready for scrollPanenes/display
				
		RefreshListPanel = new JPanel();
		resultsJList.addMouseListener(this);
		JScrollPane rScroll = new JScrollPane(resultsJList);
		rScroll.setPreferredSize(new Dimension((int)(Panel_Width),(int)(Panel_Height *.8)));
		rScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		System.out.println("CloseList resultsList:"+resultsList.getSize()+ " : resultsJList :"+ resultsJList.getSize());
		rScroll.setVisible(true);
		RefreshListPanel.add(rScroll);
		RefreshListPanel.setVisible(true);
		
		RefreshListPanel.updateUI();
	}
	
	
	private void RefreshListPanel(){
		//creata a new clean panel then populate the Lists and close them for display
		listupdate();
		//CloseList();
		ListPanel.add(RefreshListPanel);
		
	}
	
	private void ListToPanel() {
		//package the Lists 
		ListPanel = new JPanel();
		//RefreshListPanel();
		DisplayPanel.add(ListPanel,BorderLayout.CENTER);
	}
	
	
	public void RemoveCode(){
		
	}

	private void addCode(String Code, String Desc) {
		//adding a new Code and writing it to the file
		ReferenceCode newCode = new ReferenceCode(Code,Desc);
		ReferenceCodes.add(newCode);
		System.out.println("Added :"+Desc);		
	}

	
	public void Refresh_Code_List() {
		// dead code ???
		ListPanel.removeAll();
		ListToPanel();
		ListPanel.validate();
		ListPanel.repaint();
	}
	
	public void CreateNewCodeEntry() {
		// creating/collecting the new data for the code
		int ncode = ReferenceCodes.size()+1;
		boolean dataError = true;
		String newDesc = null;
		
		while (dataError) {
			if (newDesc == null) {
				newDesc = JOptionPane.showInputDialog(null,"Please enter a Code Description or EXIT ");
			} else {
				dataError = false;
				addCode(Integer.toString(ncode),newDesc);
			}
			if (newDesc.equalsIgnoreCase("exit")){
				break;
			}
		}
	}
	
	private void LoadCodes() throws IOException {
		//Loading the arrays with the Saved data 
		File codefile = new File(Main.CodeFile);
		System.out.println(codefile.getAbsolutePath());
		String coderef = codefile.getAbsolutePath();
		FileReader readcode = new FileReader(coderef);
		BufferedReader bufincode = new BufferedReader(readcode);
		String codeline= null;
		
		System.out.println ("Starting code load..");
		while ((codeline = bufincode.readLine()) != null) {
			String[] linecode = codeline.split("<n#d>");
			
			ReferenceCode code1 = new ReferenceCode(linecode[0],linecode[1]);
			ReferenceCodes.add(code1);
			
			
		}
		System.out.println("code load finished");
		bufincode.close();
		
	}

	public void SaveCodes() {
		 try { 
			 FileWriter codWriter = new FileWriter(Main.CodeFile);
		 
			 for(int i = 0; i < ReferenceCodes.size(); i++){
				 System.out.println( "saving code -->" + ReferenceCodes.get(i).Description);
				 codWriter.write(ReferenceCodes.get(i).CodeNumber);
				 codWriter.write("<n#d>");
				 codWriter.write(ReferenceCodes.get(i).Description);
				 codWriter.write("\n");
				
				 }
			 
			codWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
	}	
	
	public String[] ListCodeDescriptions() {
		// removing duplicated of References converting codes into descriptions
		ArrayList<String>FilteredResults = new ArrayList<String>();
		String code = codeTextField.getText();
		for (int i = 0; i < ReferenceCodes.size(); i++) {
			if (ReferenceCodes.get(i).Description.toUpperCase().contains(code.toUpperCase())) {
				FilteredResults.add(ReferenceCodes.get(i).Description);
				
			}
		}
		System.out.println("ReferenceCodes-ListCodeDescriptions FilterResults.isempty "+FilteredResults.isEmpty());
		String[]results_List;
		results_List = new String[FilteredResults.size()];
		for (int i =0; i < FilteredResults.size(); i++) {
			results_List[i] = FilteredResults.get(i);
			System.out.println("ReferenceCodes-ListCodeDescriptions results_List ["+i+"] "+results_List[i]);
		}
		System.out.println("ListCodeDescriptions :"+ results_List +" :" +results_List.length +" : " + FilteredResults);
		return results_List;
	}
	
	
	public String CodeDescToCodes(ArrayList<String> codes) {
		String concatCode = "";
		for( int i = 0; i < codes.size(); i++) {
			for (int j = 0; j < ReferenceCodes.size() ;j++) {
				if (codes.get(i).equals(ReferenceCodes.get(j).Description)) {
					concatCode = concatCode + ReferenceCodes.get(j).CodeNumber+",";
				}
			}
		}
		return concatCode;
		
	}
	
	private void UpdateSelectedCodes() {
		selectedCodes.clear();
		
		selectedCodes.addAll(resultsJList.getSelectedValuesList());
		//List<String> selectedCodesA = resultsJList.getSelectedValuesList();
		System.out.println("ReferenceCodes.UpdateSelectedCodes results output:" +selectedCodes.size() + " resultsJList :" +resultsJList.getSelectedIndex());
		System.out.println("UpdateSelectedCodes "+selectedCodes);
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//UpdateSelectedCodes();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
		UpdateSelectedCodes();
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
		if (e.getSource() == addNewCodeButton) {
			CreateNewCodeEntry();
			SaveCodes();
			//resultsList.clear();
			//RefreshListPanel();
			//UpdateSelectedCodes();
		}
		if (e.getSource() == add_Seleceted_Code_Button) {
			if (selected_Codes_List.contains(selected_Codes_JList.getSelectedValue())) {
				
			} else {
				selected_Codes_List.addAll(selectedCodes);
			}
		}
		if (e.getSource() == remove_Selected_Code_Button) {
			selected_Codes_List.removeElement(selected_Codes_JList.getSelectedValue());
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
		RefreshListPanel();
		
		
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

