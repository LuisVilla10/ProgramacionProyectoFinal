package adapters;

import java.util.Arrays;
import models.Director;
import connects.TextFile;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.io.File;

public class CRUDDirector extends AbstractTableModel {
  String[] colunmDirectors = {"id","Nombre", "Puesto"};
  private ArrayList<Director> list; 

  public CRUDDirector(){
    this.list = this.obtenerDirectoresTabla();
  }


  @Override
  public int getRowCount() {
    return list.size();
  }
  public String getColumnName(int columnIndex) {
    return colunmDirectors[columnIndex];
  }

  @Override
  public int getColumnCount() {
    return colunmDirectors.length;
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    switch (columnIndex){
      case 0:
        return this.list.get(rowIndex).getPersonalNum();
      case 1:
        return this.list.get(rowIndex).getName();
      case 2:
        return this.list.get(rowIndex).getJob();
      default:
        return null;
    }
  }
  // actualiza el director pero solo en la tabla. no en el txt
  @Override
  public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
      Director director = list.get(rowIndex);
      switch (columnIndex) {
	  case 0:
	      if (aValue instanceof String) {
		  director.setPersonalNum(aValue.toString());
	      }
	      break;
	  case 1:
	      if (aValue instanceof String) {
		  director.setName(aValue.toString());
	      }
	      break;
	 case 2:
	      if (aValue instanceof String) {
		  director.setJob(aValue.toString());
	      }
	      break;
      }
      fireTableCellUpdated(rowIndex, columnIndex);
  }
  // Agrega director pero solo en la tabla. no en el txt
 public void add(Director director) {
            this.list.add(director);
            fireTableRowsInserted(list.size() - 1, list.size() - 1);

        }

  // eliminar el  director pero solo en la tabla. no en el txt
public void removeRow(int row) {
    // remove a row from your internal data structure
    list.remove(row);
    fireTableRowsDeleted(row, row);
}
 // se agrega el objeto utilizando toSTring del objeto
  public boolean agregarDirector(String directorText){
    	boolean guardado = false;
	TextFile textFile = new TextFile("director.txt");
	guardado = textFile.insertOnFileText(directorText);
	return guardado;
  }

  public boolean actualizarDirector(String oldText, String newText){
    	boolean guardado = false;
	TextFile textFile = new TextFile("director.txt");
	guardado = textFile.updateLineFileText(oldText, newText);
	return guardado;
  }

  public boolean eliminarDirector(String directorText){
    	boolean guardado = false;
	TextFile textFile = new TextFile("director.txt");
	guardado = textFile.deleteLineFileText(directorText);
	return guardado;
  }


  public ArrayList<Director> obtenerDirectoresTabla(){
    	File tempFile = new File("connects/director.txt");
        ArrayList<Director> listaFinal = new ArrayList<Director>();
	if(tempFile.exists()){
	TextFile textFile = new TextFile("director.txt");
	String[] segundoSplit = null;
	String directoresString = textFile.readFileText();
	if (directoresString.length()>0) {
	  	 String[] primerSplit = directoresString.split(";");
		  for (String string : primerSplit) {
		    segundoSplit = string.split(",");
		    System.out.println(Arrays.toString(segundoSplit));
		    listaFinal.add(new Director(segundoSplit[0],segundoSplit[1],segundoSplit[2]));
		  }
	}
	}
	return listaFinal;
  }


}
