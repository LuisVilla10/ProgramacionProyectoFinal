package adapters;

import models.Admin;
import connects.TextFile;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.io.File;

public class CRUDAdmin extends AbstractTableModel {
  String [] columnAdmins = {"id","Nombre", "Puesto"};
  ArrayList<Admin> list;

  public CRUDAdmin(){
   list = this.obtenerAdminsTabla();
  }

  @Override
  public int getRowCount() {
    return list.size();
  }

  public String getColumnName(int columnIndex) {
    return columnAdmins[columnIndex];
  }

  @Override
  public int getColumnCount() {
    return columnAdmins.length;
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    switch (columnIndex){
      case 0:
        return this.list.get(rowIndex).getId();
      case 1:
        return this.list.get(rowIndex).getName();
      case 2:
        return this.list.get(rowIndex).getJob();
      default:
        return null;
    }
  }

  @Override
  public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
      Admin admin = list.get(rowIndex);
      switch (columnIndex) {
	  case 0:
	      if (aValue instanceof String) {
		  admin.setId(aValue.toString());
	      }
	      break;
	  case 1:
	      if (aValue instanceof String) {
		  admin.setName(aValue.toString());
	      }
	      break;
	 case 2:
	      if (aValue instanceof String) {
		  admin.setJob(aValue.toString());
	      }
	      break;
      }
      fireTableCellUpdated(rowIndex, columnIndex);
  }

  public void add(Admin admin) {

            this.list.add(admin);
            fireTableRowsInserted(list.size() - 1, list.size() - 1);

   }
  // eliminar el  director pero solo en la tabla. no en el txt
  public void removeRow(int row) {
    // remove a row from your internal data structure
    list.remove(row);
    fireTableRowsDeleted(row, row);
  }

// se agrega el objeto utilizando toSTring del objeto
  public boolean agregarAdmin(String adminText){
    	boolean guardado = false;
	TextFile textFile = new TextFile("admin.txt");
	guardado = textFile.insertOnFileText(adminText);
	return guardado;
  }

  public boolean actualizarAdmin(String oldText, String newText){
    	boolean guardado = false;
	TextFile textFile = new TextFile("admin.txt");
	guardado = textFile.updateLineFileText(oldText, newText);
	return guardado;
  }

  public boolean eliminarAdmin(String adminText){
    	boolean guardado = false;
	TextFile textFile = new TextFile("admin.txt");
	guardado = textFile.deleteLineFileText(adminText);
	return guardado;
  }


  public ArrayList<Admin> obtenerAdminsTabla(){
    	File tempFile = new File("connects/admin.txt");
        ArrayList<Admin> listaFinal = new ArrayList<Admin>();
	if(tempFile.exists()){
	TextFile textFile = new TextFile("admin.txt");
	String[] segundoSplit = null;
	String adminsString = textFile.readFileText();
	  if(adminsString.length() > 0){
	  String[] primerSplit = adminsString.split(";");
		  for (String string : primerSplit) {
		      segundoSplit = string.split(",");
		      System.out.println(Arrays.toString(segundoSplit));
		      listaFinal.add(new Admin(segundoSplit[0],segundoSplit[1],segundoSplit[2]));
		  }
	  }
	}
	return listaFinal;
  }


}
