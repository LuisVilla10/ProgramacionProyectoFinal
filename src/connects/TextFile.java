package connects;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;

public class TextFile {
  	private String filename;
	private String tempFileName;
  	
	public TextFile(String filename){
		this.filename = "connects/" +filename;
		this.tempFileName = "connects/temp" +filename;
	}


	public boolean insertOnFileText(String text){
		try {
		      BufferedWriter writer = new BufferedWriter(new FileWriter(this.filename,true));
		      writer.write(text + System.lineSeparator());
		      writer.close();
		      return true;
		} catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		}
		return false;
	}	  

	  public String readFileText(){
	  	String readItText = "";
		try {
		     BufferedReader reader = new BufferedReader(new FileReader(this.filename)); 
		    
		      StringBuilder builder = new StringBuilder();
		      String aux = "";

		      while ((aux = reader.readLine()) != null) {
			  builder.append(aux+";");
		      }
		      readItText = builder.toString();
		      reader.close();
		     
		} catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		}
		return readItText;
	}
	
	public String readLineText(String queryLine){
	  	String readItLine  = "";
		try {
		     BufferedReader reader = new BufferedReader(new FileReader(this.filename)); 
		      String currentLine; 
		      String trimmedLine = "";

		      while ((currentLine = reader.readLine()) != null) {
				trimmedLine = currentLine.trim();		  
				if(trimmedLine.equals(queryLine)){
					readItLine = trimmedLine;				    
					break;
				}
		      }
		      reader.close();
		     
		} catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		}
		return readItLine;
	}

	public boolean deleteLineFileText(String lineToRemove){
		try {
		    File inputFile = new File(this.filename);
		    File tempFile = new File(this.tempFileName);

		    BufferedReader reader = new BufferedReader(new FileReader(inputFile));
		    BufferedWriter writer = new BufferedWriter(new FileWriter(tempFileName));

		    String currentLine;

		    while((currentLine = reader.readLine()) != null) {
			// trim newline when comparing with lineToRemove
			String trimmedLine = currentLine.trim();
			if(trimmedLine.equals(lineToRemove)) continue;
			writer.write(currentLine + System.lineSeparator());
		    }
		    writer.close(); 
		    reader.close(); 		     
		    if (inputFile.delete()) {
			tempFile.renameTo(inputFile);
		    }
		    return true;
		} catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		}
		return false;
	}

	public boolean updateLineFileText(String lineToUpdate, String updatedLine){
		try {
		    File inputFile = new File(this.filename);
		    File tempFile = new File(this.tempFileName);

		    BufferedReader reader = new BufferedReader(new FileReader(inputFile));
		    BufferedWriter writer = new BufferedWriter(new FileWriter(tempFileName));

		    String currentLine;

		    while((currentLine = reader.readLine()) != null) {
			// trim newline when comparing with lineToRemove
			String trimmedLine = currentLine.trim();
			if(trimmedLine.equals(lineToUpdate)) 
			   currentLine = updatedLine;
			writer.write(currentLine + System.lineSeparator());
		    }
		    writer.close(); 
		    reader.close(); 		     
		    if (inputFile.delete()) {
			tempFile.renameTo(inputFile);
		    }
		    return true;
		} catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		}
		return false;
	}

}
