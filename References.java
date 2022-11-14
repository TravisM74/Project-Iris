import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class References {
	String fileName;
	int refnum;
	String dataFilePath = Main.Referencefile;
	boolean DataLoaded = false;
	

	ArrayList<Reference> References = new ArrayList<Reference>();
	
	References() {
				
		
		try {
			loadArrayLists();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
	
	public int get_Size() {
		return References.size();
	}
	
	public JList<String> ListOfReferences() {
		JList<String> resultsJList;
		DefaultListModel<String> resultsList = new DefaultListModel<>();
		
		for (int i =0; i < References.size(); i++) {
			resultsList.add(i, References.get(i).getSelectedText());
		}
		resultsJList = new JList<>(resultsList);
	return resultsJList;
	}
	
	public JList<String> ListOfLeadAuthors() {
		JList<String> resultsJList;
		DefaultListModel<String> resultsList = new DefaultListModel<>();
		
		for (int i =0; i < References.size(); i++) {
			resultsList.add(i, References.get(i).getLeadAuthor());
		}
		resultsJList = new JList<>(resultsList);
	return resultsJList;
	}
	
	public void addNewRecord(int i,String fileName,String selectedText, String pageFound,
							 String datePublished, String startCharacter,String endCharacter,
							 String leadAuthor , String coAuthors, String assignedCodes) {
		
		Reference r1 = new Reference(i, fileName,selectedText, pageFound, datePublished,
				 startCharacter,endCharacter,leadAuthor,coAuthors,assignedCodes);
		
		References.add(r1);
		DataLoaded=(true);
		
		
	}


	public void DeleteRecord(int index) {
	
		References.remove(index);
	}
	
	public String[] getCodes (int index) {
		String[] ReferenceCodes = References.get(index).getAssignedCodes().split(",");
		
		return ReferenceCodes;
	}
	
	public void putCodes (int index, ArrayList<String> Codes) {
		String concatCodes = "";
		for (int i = 0; i < Codes.size() ; i++) {
			concatCodes = concatCodes.concat(Codes.get(i)+",");
		}
		References.set(index, null).setAssignedCodes(concatCodes);		
	}

	public String ConcatCodes(String[] codeList) {
		String ConcatCode = "";
		for (int i = 0; i < codeList.length;i++) {
			ConcatCode = ConcatCode.concat(codeList[i]+",");
		}
		return ConcatCode;
	}
	public String[] DecatCodes(String codes){
		String DeCodes[] = codes.split(",");
		
		return DeCodes;
	}

	private boolean loadArrayLists() throws IOException {
		boolean arraysLoaded = false;
		FileReader reader = new FileReader(dataFilePath);
		BufferedReader InPutLine = new BufferedReader(reader);
		String line = null;
		int i = 0;
		try {	
			while ((line = InPutLine.readLine()) != null) {
				String[] lineparts = line.split("<n#d>");
				addNewRecord(i , lineparts[0], lineparts[1], lineparts[2], lineparts[3], lineparts[4],
				lineparts[5],lineparts[6],lineparts[7],lineparts[8]);		
				i++;
				}
			}  
			catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (InPutLine != null) {
				InPutLine.close();
			}
	return arraysLoaded;	 
	}
	
	public void saveArrayLists() {
		/*
		 * String dataFilePath, ArrayList<String> fileName,ArrayList<String> selectedText, ArrayList<String> pageFound,
			 ArrayList<String> datePublished, ArrayList<String> startCharacter,ArrayList<String> endCharacter,
			 ArrayList<String> leadAuthor , ArrayList<String> coAuthors, ArrayList<String> assignedCodes
		 */
		 try { 
			 FileWriter codWriter = new FileWriter(dataFilePath);
		 
			 for(int i = 0; i < References.size(); i++){
				
				 codWriter.write(References.get(i).fileName);
				 codWriter.write("<n#d>");
				 codWriter.write(References.get(i).selectedText);
				 codWriter.write("<n#d>");
				 codWriter.write(References.get(i).pageFound);
				 codWriter.write("<n#d>");
				 codWriter.write(References.get(i).datePublished);
				 codWriter.write("<n#d>");
				 codWriter.write(References.get(i).startCharacter);
				 codWriter.write("<n#d>");
				 codWriter.write(References.get(i).endCharacter);
				 codWriter.write("<n#d>");
				 codWriter.write(References.get(i).leadAuthor);
				 codWriter.write("<n#d>");
				 codWriter.write(References.get(i).coAuthors);
				 codWriter.write("<n#d>");
				 codWriter.write(References.get(i).assignedCodes);
				 codWriter.write("\n");
				 }
			 
			codWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
	}
	
	
}
