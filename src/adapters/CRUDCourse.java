package adapters;

import models.Course;
import connects.TextFile;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;

public class CRUDCourse extends AbstractTableModel {
  String [] columnCourses = {"id","Nombre", "Calificacion"};
  private ArrayList<Course> list;

  public CRUDCourse(){
   list = this.obtenerCoursesTabla();
  }

  @Override
  public int getRowCount() {
    return list.size();
  }

  public String getColumnName(int columnIndex) {
    return columnCourses[columnIndex];
  }

  @Override
  public int getColumnCount() {
    return columnCourses.length;
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    switch (columnIndex){
      case 0:
        return this.list.get(rowIndex).getIdCourse();
      case 1:
        return this.list.get(rowIndex).getName();
      case 2:
        return this.list.get(rowIndex).getScore();
      default:
        return null;
    }
  }

  @Override
  public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
      Course course = list.get(rowIndex);
      switch (columnIndex) {
	  case 0:
	      if (aValue instanceof String) {
		  course.setIdCourse(aValue.toString());
	      }
	      break;
	  case 1:
	      if (aValue instanceof String) {
		  course.setName(aValue.toString());
	      }
	      break;
	 case 2:
		  course.setScore((int) aValue); 
	      break;
      }
      fireTableCellUpdated(rowIndex, columnIndex);
  }

  public void add(Course course) {

            this.list.add(course);
            fireTableRowsInserted(list.size() - 1, list.size() - 1);

   }
  // eliminar el  director pero solo en la tabla. no en el txt
  public void removeRow(int row) {
    // remove a row from your internal data structure
    list.remove(row);
    fireTableRowsDeleted(row, row);
  }

// se agrega el objeto utilizando toSTring del objeto
  public boolean agregarCourse(String courseText){
    	boolean guardado = false;
	TextFile textFile = new TextFile("course.txt");
	guardado = textFile.insertOnFileText(courseText);
	return guardado;
  }

  public boolean actualizarCourse(String oldText, String newText){
    	boolean guardado = false;
	TextFile textFile = new TextFile("course.txt");
	guardado = textFile.updateLineFileText(oldText, newText);
	return guardado;
  }

  public boolean eliminarCourse(String courseText){
    	boolean guardado = false;
	TextFile textFile = new TextFile("course.txt");
	guardado = textFile.deleteLineFileText(courseText);
	return guardado;
  }


  public ArrayList<Course> obtenerCoursesTabla(){
    	File tempFile = new File("connects/course.txt");
        ArrayList<Course> listaFinal = new ArrayList<Course>();
	if(tempFile.exists()){
	TextFile textFile = new TextFile("course.txt");
	String[] segundoSplit = null;
	String coursesString = textFile.readFileText();
	  if(coursesString.length() > 0){
	  String[] primerSplit = coursesString.split(";");
		  for (String string : primerSplit) {
		      segundoSplit = string.split(",");
		      System.out.println(Arrays.toString(segundoSplit));
		      listaFinal.add(new Course(segundoSplit[0],segundoSplit[1],Integer.parseInt(segundoSplit[2])));
		  }
	  }
	}
	return listaFinal;
  }


}
