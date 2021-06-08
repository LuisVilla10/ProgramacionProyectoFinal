import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import adapters.CRUDCourseTeacher;
import models.CourseTeacher;

public class FrameCourseTeacher extends JInternalFrame{

   private JTable tableCourseTeacher = new JTable();
   private CRUDCourseTeacher crudCourseTeacher = new CRUDCourseTeacher();
   private JTextField texfieldIdCourseCourseTeacher;
   private JTextField texfieldIdTeacherCourseTeacher;
   private int filaSelect;
   private CourseTeacher courseTeacherSel = new CourseTeacher();

  public FrameCourseTeacher(String arg0,boolean arg1,boolean arg2,boolean arg3,boolean arg4) {
    //Creamos el Frame donde estaremos mostrando el crude
    super(arg0,arg1,arg2,arg3,arg4);
    this.setSize(730,500);
    this.setVisible(false);
    this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

    this.confInterface();   

    }

  public void confInterface(){
    JLabel labelIdCourseCourseTeacher = new JLabel("Curso");
    texfieldIdCourseCourseTeacher = new JTextField(10);

    JLabel labelIdTeacherCourseTeacher = new JLabel("Curso Maestro");
     texfieldIdTeacherCourseTeacher = new JTextField(10);


    JButton btnAddStudentOnCourseTeacher = new JButton("Agregar");
    btnAddStudentOnCourseTeacher.setBounds(100,150,100,30);
    btnAddStudentOnCourseTeacher.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

        String dataIdTeacherCourseTeacher = texfieldIdTeacherCourseTeacher.getText();
        String dataIdCourseCourseTeacher = texfieldIdCourseCourseTeacher.getText();

	if (dataIdTeacherCourseTeacher != null && dataIdCourseCourseTeacher != null) {
		if (dataIdTeacherCourseTeacher.length() > 0 && dataIdCourseCourseTeacher.length() > 0) {
			CourseTeacher courseTeacher = new CourseTeacher(dataIdCourseCourseTeacher.trim(),dataIdTeacherCourseTeacher.trim());
			crudCourseTeacher.add(courseTeacher);
			System.out.println(courseTeacher.toStringCourseTeacher());
			if(crudCourseTeacher.agregarCourseTeacher(courseTeacher.toStringCourseTeacher())){
				clearTextField();
			}
		}	
	}
      }
    });

    JButton btnDeleteStudentOnCourseTeacher = new JButton("Eliminar");
    btnDeleteStudentOnCourseTeacher.setBounds(100,150,100,30);
    btnDeleteStudentOnCourseTeacher.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
	if (courseTeacherSel.getIdCourse().length() > 0) {
		if (crudCourseTeacher.eliminarCourseTeacher(courseTeacherSel.toStringCourseTeacher())) {
			crudCourseTeacher.removeRow(filaSelect);	
			clearTextField();
		}	
	}
      }
    });

    JButton btnUpdateCourseTeacher  = new JButton("Actualizar");
    btnUpdateCourseTeacher.setBounds(5,30,80,30);
    btnUpdateCourseTeacher.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
	CourseTeacher updateCourseTeacher = new CourseTeacher(texfieldIdCourseCourseTeacher.getText(),texfieldIdTeacherCourseTeacher.getText());
	if(courseTeacherSel.getIdCourse().length() > 0){
		if(crudCourseTeacher.actualizarCourseTeacher(courseTeacherSel.toStringCourseTeacher(), updateCourseTeacher.toStringCourseTeacher())){
		  crudCourseTeacher.setValueAt(updateCourseTeacher.getIdCourse(), filaSelect, 0);
		  crudCourseTeacher.setValueAt(updateCourseTeacher.getIdTeacher(), filaSelect, 1);
		  clearTextField();
		}

	}
      }
    });

    tableCourseTeacher.setModel(crudCourseTeacher);
    tableCourseTeacher.addMouseListener(new MouseListener() {
      @Override
      public void mouseClicked(MouseEvent e) {
        int indexRow = tableCourseTeacher.getSelectedRow();
	filaSelect = tableCourseTeacher.getSelectedRow();

        String courseTeacherID = tableCourseTeacher.getValueAt(indexRow, 0).toString();
        String courseTeacherIdTeacher = tableCourseTeacher.getValueAt(indexRow, 1).toString();

        texfieldIdCourseCourseTeacher.setText(courseTeacherID);
        texfieldIdTeacherCourseTeacher.setText(courseTeacherIdTeacher);

	courseTeacherSel.setIdCourse(courseTeacherID);
	courseTeacherSel.setIdTeacher(courseTeacherIdTeacher);

      }

      @Override
      public void mousePressed(MouseEvent e) {
      }

      @Override
      public void mouseReleased(MouseEvent e) {
      }

      @Override
      public void mouseEntered(MouseEvent e) {
      }

      @Override
      public void mouseExited(MouseEvent e) {
      }
    });

    JPanel JPanel_courseTeacher = new JPanel();
    JPanel_courseTeacher.add(labelIdCourseCourseTeacher);
    JPanel_courseTeacher.add(texfieldIdCourseCourseTeacher);
    JPanel_courseTeacher.add(labelIdTeacherCourseTeacher);
    JPanel_courseTeacher.add(texfieldIdTeacherCourseTeacher);
    JPanel_courseTeacher.add(btnAddStudentOnCourseTeacher);
    JPanel_courseTeacher.add(btnDeleteStudentOnCourseTeacher);
    JPanel_courseTeacher.add(btnUpdateCourseTeacher);

    JScrollPane scrollPane_courseTeacher = new JScrollPane(tableCourseTeacher);
    this.add(scrollPane_courseTeacher, BorderLayout.CENTER);
    this.add(JPanel_courseTeacher, BorderLayout.PAGE_END);

  }

   public void clearTextField(){
	 texfieldIdCourseCourseTeacher.setText("");
	 texfieldIdTeacherCourseTeacher.setText("");
  }

}
