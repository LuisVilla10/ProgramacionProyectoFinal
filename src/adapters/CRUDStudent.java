package adapters;

import models.Student;
import connects.TextFile;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.io.File;
import java.util.Arrays;

public class CRUDStudent extends AbstractTableModel {
  String[] columnStudents = {"Matricula", "Nombre", "Fecha", "IdcourseTeacher"};
  private ArrayList<Student> list;

  public CRUDStudent(){
    list = this.obtenerStudentsTabla();
  }

  @Override
  public int getRowCount() {
    return list.size();
  }

  public String getColumnName(int columnIndex) {
    return columnStudents[columnIndex];
  }

  @Override
  public int getColumnCount() {
    return columnStudents.length;
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    switch (columnIndex){
      case 0:
        return this.list.get(rowIndex).getStudentDNI();
      case 1:
        return this.list.get(rowIndex).getName();
      case 2:
        return this.list.get(rowIndex).getDate();
      case 3:
        return this.list.get(rowIndex).getidCourseTeacher();
      default:
        return null;
    }
  }
  @Override
  public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
      Student student = list.get(rowIndex);
      switch (columnIndex) {
	  case 0:
	      if (aValue instanceof String) {
		  student.setStudentDNI(aValue.toString());
	      }
	      break;
	  case 1:
	      if (aValue instanceof String) {
		  student.setName(aValue.toString());
	      }
	      break;
	 case 2:
	      if (aValue instanceof String) {
		  student.setDate(aValue.toString());
	      }
	      break;
	 case 3:
	      if (aValue instanceof String) {
		  student.setCourseteacher(aValue.toString());
	      }
	      break;
      }
      fireTableCellUpdated(rowIndex, columnIndex);
  }

  public void add(Student student) {

            this.list.add(student);
            fireTableRowsInserted(list.size() - 1, list.size() - 1);

   }
  // eliminar el  director pero solo en la tabla. no en el txt
  public void removeRow(int row) {
    // remove a row from your internal data structure
    list.remove(row);
    fireTableRowsDeleted(row, row);
  }

// se agrega el objeto utilizando toSTring del objeto
  public boolean agregarStudent(String studentText){
    	boolean guardado = false;
	TextFile textFile = new TextFile("student.txt");
	guardado = textFile.insertOnFileText(studentText);
	return guardado;
  }

  public boolean actualizarStudent(String oldText, String newText){
    	boolean guardado = false;
	TextFile textFile = new TextFile("student.txt");
	guardado = textFile.updateLineFileText(oldText, newText);
	return guardado;
  }

  public boolean eliminarStudent(String studentText){
    	boolean guardado = false;
	TextFile textFile = new TextFile("student.txt");
	guardado = textFile.deleteLineFileText(studentText);
	return guardado;
  }


  public ArrayList<Student> obtenerStudentsTabla(){
    	File tempFile = new File("connects/student.txt");
        ArrayList<Student> listaFinal = new ArrayList<Student>();
	if(tempFile.exists()){
	TextFile textFile = new TextFile("student.txt");
	String[] segundoSplit = null;
	String studentsString = textFile.readFileText();
	  if(studentsString.length() > 0){
	  String[] primerSplit = studentsString.split(";");
		  for (String string : primerSplit) {
		      segundoSplit = string.split(",");
		      System.out.println(Arrays.toString(segundoSplit));
		      listaFinal.add(new Student(segundoSplit[0],segundoSplit[1],segundoSplit[2],segundoSplit[3]));
		  }
	  }
	}
	return listaFinal;
  }

}
