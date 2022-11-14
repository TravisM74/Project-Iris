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

public class Reference {
		
	int referenceNumber;
	String fileName;
	String selectedText;
	String pageFound;
	String datePublished;
	String startCharacter;
	String endCharacter;
	String leadAuthor;
	String coAuthors;
	String assignedCodes;
	

	Reference(int refnum,String fileName,String selectedText,String pageFound,String datePublished,
			String startCharacter,String endCharacter,String leadAuthor,String coAuthors,String assignedCodes){
		this.referenceNumber = refnum;
		this.fileName = fileName;;
		this.selectedText = selectedText;
		this.pageFound = pageFound;
		this.datePublished  = datePublished;
		this.startCharacter = startCharacter;
		this.endCharacter  = endCharacter;
		this.leadAuthor = leadAuthor;
		this.coAuthors = coAuthors;
		this.assignedCodes = assignedCodes;
		
	}


	public int getReferenceNumber() {
		return referenceNumber;
	}


	public void setReferenceNumber(int referenceNumber) {
		this.referenceNumber = referenceNumber;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public String getSelectedText() {
		return selectedText;
	}


	public void setSelectedText(String selectedText) {
		this.selectedText = selectedText;
	}


	public String getPageFound() {
		return pageFound;
	}


	public void setPageFound(String pageFound) {
		this.pageFound = pageFound;
	}


	public String getDatePublished() {
		return datePublished;
	}


	public void setDatePublished(String datePublished) {
		this.datePublished = datePublished;
	}


	public String getStartCharacter() {
		return startCharacter;
	}


	public void setStartCharacter(String startCharacter) {
		this.startCharacter = startCharacter;
	}


	public String getEndCharacter() {
		return endCharacter;
	}


	public void setEndCharacter(String endCharacter) {
		this.endCharacter = endCharacter;
	}


	public String getLeadAuthor() {
		return leadAuthor;
	}


	public void setLeadAuthor(String leadAuthor) {
		this.leadAuthor = leadAuthor;
	}


	public String getCoAuthors() {
		return coAuthors;
	}


	public void setCoAuthors(String coAuthors) {
		this.coAuthors = coAuthors;
	}


	public String getAssignedCodes() {
		return assignedCodes;
	}


	public void setAssignedCodes(String assignedCodes) {
		this.assignedCodes = assignedCodes;
	}
	
	

}
