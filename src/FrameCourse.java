import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import adapters.CRUDCourse;
import models.Course;

public class FrameCourse extends JInternalFrame{

   private JTable tableCourse = new JTable();
   private CRUDCourse crudCourse = new CRUDCourse();
   private JTextField texfieldIdCourse;
   private JTextField texfieldNameCourse;
   private JTextField texfieldScoreCourse;;
   private int filaSelect;
   private Course courseSel = new Course();

  public FrameCourse(String arg0,boolean arg1,boolean arg2,boolean arg3,boolean arg4) {
    //Creamos el Frame donde estaremos mostrando el crude
    super(arg0,arg1,arg2,arg3,arg4);
    this.setSize(730,500);
    this.setVisible(false);
    this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

    this.confInterface();   

    }

  public void confInterface(){
    JLabel labelIdCourse = new JLabel("ID");
    texfieldIdCourse = new JTextField(10);

    JLabel labelNameCourse = new JLabel("Nombre");
     texfieldNameCourse = new JTextField(10);

    JLabel labelScoreCourse = new JLabel("Calificación");
    texfieldScoreCourse = new JTextField(10);

    JButton btnAddStudentOnCourse = new JButton("Agregar");
    btnAddStudentOnCourse.setBounds(100,150,100,30);
    btnAddStudentOnCourse.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String dataNameCourse = texfieldNameCourse.getText();
        String dataScoreCourse = texfieldScoreCourse.getText();
        String dataIdCourse = texfieldIdCourse.getText();
	if (dataNameCourse != null && dataScoreCourse != null && dataIdCourse != null) {
		if (dataNameCourse.length() > 0 && dataScoreCourse.length() > 0 && dataIdCourse.length() > 0) {
			Course course = new Course(dataIdCourse.trim(),dataNameCourse.trim(),Integer.parseInt(dataScoreCourse.trim()));
			crudCourse.add(course);
			System.out.println(course.toStringCourse());
			if(crudCourse.agregarCourse(course.toStringCourse())){
				clearTextField();
			}
		}	
	}
      }
    });

    JButton btnDeleteStudentOnCourse = new JButton("Eliminar");
    btnDeleteStudentOnCourse.setBounds(100,150,100,30);
    btnDeleteStudentOnCourse.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
	if (courseSel.getName().length() > 0) {
		if (crudCourse.eliminarCourse(courseSel.toStringCourse())) {
			crudCourse.removeRow(filaSelect);	
			clearTextField();
		}	
	}
      }
    });

    JButton btnUpdateCourse  = new JButton("Actualizar");
    btnUpdateCourse.setBounds(5,30,80,30);
    btnUpdateCourse.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
	Course updateCourse = new Course(texfieldIdCourse.getText(),texfieldNameCourse.getText(),Integer.parseInt( texfieldScoreCourse.getText()));
	if(courseSel.getName().length() > 0){
		if(crudCourse.actualizarCourse(courseSel.toStringCourse(), updateCourse.toStringCourse())){
		  crudCourse.setValueAt(updateCourse.getIdCourse(), filaSelect, 0);
		  crudCourse.setValueAt(updateCourse.getName(), filaSelect, 1);
		  crudCourse.setValueAt(updateCourse.getScore(), filaSelect, 2);
		  clearTextField();
		}

	}
      }
    });

    tableCourse.setModel(crudCourse);
    tableCourse.addMouseListener(new MouseListener() {
      @Override
      public void mouseClicked(MouseEvent e) {
        int indexRow = tableCourse.getSelectedRow();
	filaSelect = tableCourse.getSelectedRow();

        String courseID = tableCourse.getValueAt(indexRow, 0).toString();
        String courseName = tableCourse.getValueAt(indexRow, 1).toString();
        String courseScore = tableCourse.getValueAt(indexRow,2).toString();

        texfieldIdCourse.setText(courseID);
        texfieldNameCourse.setText(courseName);
        texfieldScoreCourse.setText(courseScore);

	courseSel.setIdCourse(courseID);
	courseSel.setName(courseName);
	courseSel.setScore(Integer.parseUnsignedInt(courseScore));

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

    JPanel JPanel_course = new JPanel();
    JPanel_course.add(labelIdCourse);
    JPanel_course.add(texfieldIdCourse);
    JPanel_course.add(labelNameCourse);
    JPanel_course.add(texfieldNameCourse);
    JPanel_course.add(labelScoreCourse);
    JPanel_course.add(texfieldScoreCourse);
    JPanel_course.add(btnAddStudentOnCourse);
    JPanel_course.add(btnDeleteStudentOnCourse);
    JPanel_course.add(btnUpdateCourse);

    JScrollPane scrollPane_course = new JScrollPane(tableCourse);
    this.add(scrollPane_course, BorderLayout.CENTER);
    this.add(JPanel_course, BorderLayout.PAGE_END);

  }

   public void clearTextField(){
	 texfieldIdCourse.setText("");
	 texfieldNameCourse.setText("");
	 texfieldScoreCourse.setText("");
  }

}
