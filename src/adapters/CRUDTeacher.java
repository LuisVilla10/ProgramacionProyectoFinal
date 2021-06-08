package adapters;
import models.Teacher;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import connects.TextFile;
import java.util.Arrays;
import java.io.File;

public class CRUDTeacher extends AbstractTableModel {
  String[] columnTeachers = {"DNI", "Nombre"};
  ArrayList<Teacher> list;

  public CRUDTeacher(){
    list = this.obtenerTeacherTabla();
  }

  @Override
  public int getRowCount() {
    return list.size();
  }

  public String getColumnName(int columnIndex) {
    return columnTeachers[columnIndex];
  }

  @Override
  public int getColumnCount() {
    return columnTeachers.length;
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    switch (columnIndex){
      case 0:
        return this.list.get(rowIndex).getPersonaNumber();
      case 1:
        return this.list.get(rowIndex).getName();
      default:
        return null;
    }
  }

  public boolean isCellEditable(int rowIndex, int columnIndex){
    if(columnIndex > 0){
      return true;
    }
    return false;
  }

@Override
  public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
      Teacher teacher = list.get(rowIndex);
      switch (columnIndex) {
	  case 0:
	      if (aValue instanceof String) {
		  teacher.setPersonaNumber(aValue.toString());
	      }
	      break;
	  case 1:
	      if (aValue instanceof String) {
		  teacher.setName(aValue.toString());
	      }
	      break;
      }
      fireTableCellUpdated(rowIndex, columnIndex);
  }

  public void add(Teacher teacher) {
            this.list.add(teacher);
            fireTableRowsInserted(list.size() - 1, list.size() - 1);
  }
  // eliminar el  director pero solo en la tabla. no en el txt
  public void removeRow(int row) {
    // remove a row from your internal data structure
    list.remove(row);
    fireTableRowsDeleted(row, row);
  }

// se agrega el objeto utilizando toSTring del objeto
  public boolean agregarTeacher(String teacherText){
    	boolean guardado = false;
	TextFile textFile = new TextFile("teacher.txt");
	guardado = textFile.insertOnFileText(teacherText);
	return guardado;
  }

  public boolean actualizarTeacher(String oldText, String newText){
    	boolean guardado = false;
	TextFile textFile = new TextFile("teacher.txt");
	guardado = textFile.updateLineFileText(oldText, newText);
	return guardado;
  }

  public boolean eliminarTeacher(String teacherText){
    	boolean guardado = false;
	TextFile textFile = new TextFile("teacher.txt");
	guardado = textFile.deleteLineFileText(teacherText);
	return guardado;
  }


  public ArrayList<Teacher> obtenerTeacherTabla(){
    	File tempFile = new File("connects/teacher.txt");
        ArrayList<Teacher> listaFinal = new ArrayList<Teacher>();
	if(tempFile.exists()){
	TextFile textFile = new TextFile("teacher.txt");
	String[] segundoSplit = null;
	String teacherString = textFile.readFileText();
	  if(teacherString.length() > 0){
	  String[] primerSplit = teacherString.split(";");
		  for (String string : primerSplit) {
		      segundoSplit = string.split(",");
		      System.out.println(Arrays.toString(segundoSplit));
		      listaFinal.add(new Teacher(segundoSplit[0],segundoSplit[1]));
		  }
	  }
	}
	return listaFinal;
  }


}
