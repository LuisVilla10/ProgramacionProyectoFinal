import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import adapters.CRUDStudent;
import models.Student;

public class FrameStudents extends JInternalFrame{
    private JTable tableStudent = new JTable();
    private CRUDStudent crudStudent = new CRUDStudent();
    private Student studentSel = new Student();
    private JTextField texfieldStudentDNI; 
    private JTextField textfieldNameStudent; 
    private JTextField textfieldDateStudent; 
    private JTextField textfieldCourseStudent; 
    private int filaSelect;
    
  public FrameStudents(String arg0,boolean arg1,boolean arg2,boolean arg3,boolean arg4) {
    //Creamos el Frame donde estaremos mostrando el crude
    super(arg0,arg1,arg2,arg3,arg4);
    this.setSize(700,500);
    this.setVisible(false);
    this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

    this.confInterface();   

    }

  public void confInterface(){

    JLabel labelDNIStudent = new JLabel("Matricula");
    texfieldStudentDNI = new JTextField(10);

    JLabel labelNameStudent = new JLabel("Nombre");
    textfieldNameStudent = new JTextField(10);

    JLabel labelDateStudent = new JLabel("Fecha");
    textfieldDateStudent = new JTextField(10);

    JLabel labelCourseStudent = new JLabel("Id curso maestro");
    textfieldCourseStudent = new JTextField(10);

    //Agregando los botones
    JButton btnAddStudent = new JButton("Agregar");
    btnAddStudent.setBounds(5,30,80,30);
    btnAddStudent.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

        String dataTextfieldStudentDNI = texfieldStudentDNI.getText();
        String dataTextfieldNameStudent = textfieldNameStudent.getText();
        String dateTexfieldDateStudent = textfieldDateStudent.getText();
        String dataTextfieldCourseStudent = textfieldCourseStudent.getText();
	if (dataTextfieldStudentDNI != null && dataTextfieldNameStudent != null && dateTexfieldDateStudent != null && dataTextfieldCourseStudent != null) {
	  if (dataTextfieldStudentDNI.length() > 0 && dataTextfieldNameStudent.length() > 0 && dateTexfieldDateStudent.length() > 0 && dataTextfieldCourseStudent.length() > 0) {
			Student student = new Student(dataTextfieldStudentDNI.trim(),dataTextfieldNameStudent.trim(), dateTexfieldDateStudent.trim(), dataTextfieldCourseStudent.trim());
			crudStudent.add(student);
			System.out.println(student.toStringStudent());
			if(crudStudent.agregarStudent(student.toStringStudent())){
				clearTextField();
			}
		}	
	}
      }
    });

    JButton btnDeleteStudent = new JButton("Eliminar");
    btnDeleteStudent.setBounds(5,30,80,30);
    btnDeleteStudent.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
	if(studentSel.getName().length() > 0){
		if(crudStudent.eliminarStudent(studentSel.toStringStudent())){
		  crudStudent.removeRow(filaSelect);
		  clearTextField();
		}

	}
      }
    });

    JButton btnUpdateStudent  = new JButton("Actualizar");
    btnUpdateStudent.setBounds(5,30,80,30);
    btnUpdateStudent.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
	Student updateStudent = new Student(texfieldStudentDNI.getText(),textfieldNameStudent.getText(), textfieldDateStudent.getText(),textfieldCourseStudent.getText());
	if(studentSel.getName().length() > 0){
		if(crudStudent.actualizarStudent(studentSel.toStringStudent(), updateStudent.toStringStudent())){
		  crudStudent.setValueAt(updateStudent.getStudentDNI(), filaSelect, 0);
		  crudStudent.setValueAt(updateStudent.getName(), filaSelect, 1);
		  crudStudent.setValueAt(updateStudent.getDate(), filaSelect, 2);
		  crudStudent.setValueAt(updateStudent.getidCourseTeacher(), filaSelect, 3);
		  clearTextField();
		}

	}
      }
    });

    tableStudent.setModel(crudStudent);
    tableStudent.addMouseListener(new MouseListener() {
      @Override
      public void mouseClicked(MouseEvent e) {
        int indexRow = tableStudent.getSelectedRow();
	filaSelect = tableStudent.getSelectedRow();

        String studentDNI = tableStudent.getValueAt(indexRow, 0).toString();
        String namestudent = tableStudent.getValueAt(indexRow, 1).toString();
        String datestudent = tableStudent.getValueAt(indexRow, 2).toString();
        String coursestudent =  tableStudent.getValueAt(indexRow, 3).toString();

        texfieldStudentDNI.setText(studentDNI);
        textfieldNameStudent.setText(namestudent);
        textfieldDateStudent.setText(datestudent);
        textfieldCourseStudent.setText(coursestudent);

	studentSel.setStudentDNI(studentDNI);
	studentSel.setName(namestudent);
	studentSel.setDate(datestudent);
	studentSel.setCourseteacher(coursestudent);

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

    JScrollPane scrollPane_student = new JScrollPane(tableStudent);
    this.add(scrollPane_student);
    JPanel JPanel_student = new JPanel();

    JPanel_student.add(labelDNIStudent);
    JPanel_student.add(texfieldStudentDNI);

    JPanel_student.add(labelNameStudent);
    JPanel_student.add(textfieldNameStudent);

    JPanel_student.add(labelDateStudent);
    JPanel_student.add(textfieldDateStudent);

    JPanel_student.add(labelCourseStudent);
    JPanel_student.add(textfieldCourseStudent);


    JPanel_student.add(btnAddStudent);
    JPanel_student.add(btnDeleteStudent);
    JPanel_student.add(btnUpdateStudent);

    JPanel_student.setBorder(new EmptyBorder(0,0,40,0));
    this.add(scrollPane_student, BorderLayout.CENTER);
    this.add(JPanel_student, BorderLayout.PAGE_END);

  }
   public void clearTextField(){
	 texfieldStudentDNI.setText("");
	 textfieldNameStudent.setText("");
	 textfieldDateStudent.setText("");
	 textfieldCourseStudent.setText("");
  }

}
