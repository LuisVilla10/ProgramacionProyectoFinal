import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import adapters.CRUDTeacher;
import models.Teacher;

public class FrameTeachers extends JInternalFrame{
  private JTable tableTeacher = new JTable();
  private CRUDTeacher crudTeacher = new CRUDTeacher();
  private JTextField textFieldDNITeacher;
  private JTextField textFieldNameTeacher;
  private int filaSelect;
  private Teacher teacherSel = new Teacher();

  public FrameTeachers(String arg0,boolean arg1,boolean arg2,boolean arg3,boolean arg4) {
    //Creamos el Frame donde estaremos mostrando el crude
    super(arg0,arg1,arg2,arg3,arg4);
    this.setSize(730,500);
    this.setVisible(false);
    this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

    this.confInterface();   

    }

  public void confInterface(){
    JLabel labelNameTeacher = new JLabel("Nombre");
     textFieldNameTeacher = new JTextField(10);

    JLabel labelDNITeacher = new JLabel("DNI");
     textFieldDNITeacher = new JTextField(10);

    JButton btnAddTeacher = new JButton("Agregar");
    btnAddTeacher.setBounds(100,150,100,30);
    // Evento de agrager maestro
    btnAddTeacher.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String dataNameTeacher = textFieldNameTeacher.getText();
        String dataDNITeacher = textFieldDNITeacher.getText();
	if (dataDNITeacher != null && dataNameTeacher != null ) {
	  if (dataDNITeacher.length() > 0 && dataNameTeacher.length() > 0 ) {
			Teacher teacher = new Teacher(dataDNITeacher.trim(),dataNameTeacher.trim());
			crudTeacher.add(teacher);
			System.out.println(teacher.toStringTeacher());
			if(crudTeacher.agregarTeacher(teacher.toStringTeacher())){
				clearTextField();
			}
		}	
	}

      }
    });

    JButton btnDeleteTeacher = new JButton("Eliminar");
    btnDeleteTeacher.setBounds(100,150,100,30);
    btnDeleteTeacher.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
	if (teacherSel.getName().length() > 0) {
		if (crudTeacher.eliminarTeacher(teacherSel.toStringTeacher())) {
			crudTeacher.removeRow(filaSelect);	
			clearTextField();
		}	
	}
      }
    });

    JButton btnUpdateTeacher  = new JButton("Actualizar");
    btnUpdateTeacher.setBounds(5,30,80,30);
    btnUpdateTeacher.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
	Teacher updateTeacher = new Teacher(textFieldDNITeacher.getText(),textFieldNameTeacher.getText()); 
	if(teacherSel.getName().length() > 0){
		if(crudTeacher.actualizarTeacher(teacherSel.toStringTeacher(), updateTeacher.toStringTeacher())){
		  crudTeacher.setValueAt(updateTeacher.getPersonaNumber(), filaSelect, 0);
		  crudTeacher.setValueAt(updateTeacher.getName(), filaSelect, 1);
		  clearTextField();
		}

	}
      }
    });


    tableTeacher.setModel(crudTeacher);
    tableTeacher.addMouseListener(new MouseListener() {
      @Override
      public void mouseClicked(MouseEvent e) {
        int indexRow = tableTeacher.getSelectedRow();
	filaSelect = tableTeacher.getSelectedRow();

        String teacherDNI = tableTeacher.getValueAt(indexRow, 0).toString();
        String teacherName = tableTeacher.getValueAt(indexRow, 1).toString();

        textFieldDNITeacher.setText(teacherDNI);
        textFieldNameTeacher.setText(teacherName);

	teacherSel.setPersonaNumber(teacherDNI);
	teacherSel.setName(teacherName);
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

    JScrollPane scrollPane_teacher = new JScrollPane(tableTeacher);
    JPanel JPanel_teacher = new JPanel();
    JPanel_teacher.add(labelDNITeacher);
    JPanel_teacher.add(textFieldDNITeacher);
    JPanel_teacher.add(labelNameTeacher);
    JPanel_teacher.add(textFieldNameTeacher);
    JPanel_teacher.add(btnAddTeacher);
    JPanel_teacher.add(btnDeleteTeacher);
    JPanel_teacher.add(btnUpdateTeacher);

    this.add(scrollPane_teacher, BorderLayout.CENTER);
    this.add(JPanel_teacher, BorderLayout.PAGE_END);
  }
   public void clearTextField(){
	 textFieldDNITeacher.setText("");
	 textFieldNameTeacher.setText("");
  }
}
