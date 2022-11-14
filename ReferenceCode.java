import java.util.ArrayList;

public class ReferenceCode {

	 String CodeNumber;
	 String Description;
	 
	 
	 ReferenceCode(String CodeNumber,String Description){
		 this.CodeNumber = CodeNumber;
		 this.Description = Description;
		 
	 }


	public String getCodeNumber() {
		return CodeNumber;
	}


	public void setCodeNumber(String codeNumber) {
		CodeNumber = codeNumber;
	}


	public String getDescription() {
		return Description;
	}


	public void setDescription(String description) {
		Description = description;
	}
}
