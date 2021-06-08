package adapters;

import models.CourseTeacher;
import connects.TextFile;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.io.File;

public class CRUDCourseTeacher extends AbstractTableModel {
  String [] columnCourseTeachers = {"curso","curso maestro" };
  ArrayList<CourseTeacher> list;

  public CRUDCourseTeacher(){
   list = this.obtenerCourseTeachersTabla();
  }

  @Override
  public int getRowCount() {
    return list.size();
  }

  public String getColumnName(int columnIndex) {
    return columnCourseTeachers[columnIndex];
  }

  @Override
  public int getColumnCount() {
    return columnCourseTeachers.length;
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    switch (columnIndex){
      case 0:
        return this.list.get(rowIndex).getIdCourse();
      case 1:
        return this.list.get(rowIndex).getIdTeacher();
      default:
        return null;
    }
  }

  @Override
  public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
      CourseTeacher courseTeacher = list.get(rowIndex);
      switch (columnIndex) {
	  case 0:
	      if (aValue instanceof String) {
		  courseTeacher.setIdCourse(aValue.toString());
	      }
	      break;
	  case 1:
	      if (aValue instanceof String) {
		  courseTeacher.setIdTeacher(aValue.toString());
	      }
	      break;
      }
      fireTableCellUpdated(rowIndex, columnIndex);
  }

  public void add(CourseTeacher courseTeacher) {

            this.list.add(courseTeacher);
            fireTableRowsInserted(list.size() - 1, list.size() - 1);

   }
  // eliminar el  director pero solo en la tabla. no en el txt
  public void removeRow(int row) {
    // remove a row from your internal data structure
    list.remove(row);
    fireTableRowsDeleted(row, row);
  }

// se agrega el objeto utilizando toSTring del objeto
  public boolean agregarCourseTeacher(String courseTeacherText){
    	boolean guardado = false;
	TextFile textFile = new TextFile("courseTeacher.txt");
	guardado = textFile.insertOnFileText(courseTeacherText);
	return guardado;
  }

  public boolean actualizarCourseTeacher(String oldText, String newText){
    	boolean guardado = false;
	TextFile textFile = new TextFile("courseTeacher.txt");
	guardado = textFile.updateLineFileText(oldText, newText);
	return guardado;
  }

  public boolean eliminarCourseTeacher(String courseTeacherText){
    	boolean guardado = false;
	TextFile textFile = new TextFile("courseTeacher.txt");
	guardado = textFile.deleteLineFileText(courseTeacherText);
	return guardado;
  }


  public ArrayList<CourseTeacher> obtenerCourseTeachersTabla(){
    	File tempFile = new File("connects/courseTeacher.txt");
        ArrayList<CourseTeacher> listaFinal = new ArrayList<CourseTeacher>();
	if(tempFile.exists()){
	TextFile textFile = new TextFile("courseTeacher.txt");
	String[] segundoSplit = null;
	String courseTeachersString = textFile.readFileText();
	  if(courseTeachersString.length() > 0){
	  String[] primerSplit = courseTeachersString.split(";");
		  for (String string : primerSplit) {
		      segundoSplit = string.split(",");
		      System.out.println(Arrays.toString(segundoSplit));
		      listaFinal.add(new CourseTeacher(segundoSplit[0],segundoSplit[1]));
		  }
	  }
	}
	return listaFinal;
  }


}
