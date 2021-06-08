import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import adapters.CRUDAdmin;
import models.Admin;

public class FrameAdmin extends JInternalFrame{

   private JTable tableAdmin = new JTable();
   private CRUDAdmin crudAdmin = new CRUDAdmin();
   private JTextField texfieldIdAdmin;
   private JTextField texfieldNameAdmin;
   private JTextField texfieldJobAdmin;;
   private int filaSelect;
   private Admin adminSel = new Admin();

  public FrameAdmin(String arg0,boolean arg1,boolean arg2,boolean arg3,boolean arg4) {
    //Creamos el Frame donde estaremos mostrando el crude
    super(arg0,arg1,arg2,arg3,arg4);
    this.setSize(730,500);
    this.setVisible(false);
    this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

    this.confInterface();   

    }

  public void confInterface(){
    JLabel labelIdAdmin = new JLabel("ID");
    texfieldIdAdmin = new JTextField(10);

    JLabel labelNameAdmin = new JLabel("Nombre");
     texfieldNameAdmin = new JTextField(10);

    JLabel labelJobAdmin = new JLabel("Puesto");
    texfieldJobAdmin = new JTextField(10);

    JButton btnAddStudentOnAdmin = new JButton("Agregar");
    btnAddStudentOnAdmin.setBounds(100,150,100,30);
    btnAddStudentOnAdmin.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String dataNameAdmin = texfieldNameAdmin.getText();
        String dataJobAdmin = texfieldJobAdmin.getText();
        String dataIdAdmin = texfieldIdAdmin.getText();
	if (dataNameAdmin != null && dataJobAdmin != null && dataIdAdmin != null) {
		if (dataNameAdmin.length() > 0 && dataJobAdmin.length() > 0 && dataIdAdmin.length() > 0) {
			Admin admin = new Admin(dataIdAdmin.trim(),dataNameAdmin.trim(), dataJobAdmin.trim());
			crudAdmin.add(admin);
			System.out.println(admin.toStringAdmin());
			if(crudAdmin.agregarAdmin(admin.toStringAdmin())){
				clearTextField();
			}
		}	
	}
      }
    });

    JButton btnDeleteStudentOnAdmin = new JButton("Eliminar");
    btnDeleteStudentOnAdmin.setBounds(100,150,100,30);
    btnDeleteStudentOnAdmin.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
	if (adminSel.getName().length() > 0) {
		if (crudAdmin.eliminarAdmin(adminSel.toStringAdmin())) {
			crudAdmin.removeRow(filaSelect);	
			clearTextField();
		}	
	}
      }
    });

    JButton btnUpdateAdmin  = new JButton("Actualizar");
    btnUpdateAdmin.setBounds(5,30,80,30);
    btnUpdateAdmin.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
	Admin updateAdmin = new Admin(texfieldIdAdmin.getText(),texfieldNameAdmin.getText(), texfieldJobAdmin.getText());
	if(adminSel.getName().length() > 0){
		if(crudAdmin.actualizarAdmin(adminSel.toStringAdmin(), updateAdmin.toStringAdmin())){
		  crudAdmin.setValueAt(updateAdmin.getId(), filaSelect, 0);
		  crudAdmin.setValueAt(updateAdmin.getName(), filaSelect, 1);
		  crudAdmin.setValueAt(updateAdmin.getJob(), filaSelect, 2);
		  clearTextField();
		}

	}
      }
    });

    tableAdmin.setModel(crudAdmin);
    tableAdmin.addMouseListener(new MouseListener() {
      @Override
      public void mouseClicked(MouseEvent e) {
        int indexRow = tableAdmin.getSelectedRow();
	filaSelect = tableAdmin.getSelectedRow();

        String adminID = tableAdmin.getValueAt(indexRow, 0).toString();
        String adminName = tableAdmin.getValueAt(indexRow, 1).toString();
        String adminJob = tableAdmin.getValueAt(indexRow,2).toString();

        texfieldIdAdmin.setText(adminID);
        texfieldNameAdmin.setText(adminName);
        texfieldJobAdmin.setText(adminJob);

	adminSel.setId(adminID);
	adminSel.setName(adminName);
	adminSel.setJob(adminJob);

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

    JPanel JPanel_administrator = new JPanel();
    JPanel_administrator.add(labelIdAdmin);
    JPanel_administrator.add(texfieldIdAdmin);
    JPanel_administrator.add(labelNameAdmin);
    JPanel_administrator.add(texfieldNameAdmin);
    JPanel_administrator.add(labelJobAdmin);
    JPanel_administrator.add(texfieldJobAdmin);
    JPanel_administrator.add(btnAddStudentOnAdmin);
    JPanel_administrator.add(btnDeleteStudentOnAdmin);
    JPanel_administrator.add(btnUpdateAdmin);

    JScrollPane scrollPane_admin = new JScrollPane(tableAdmin);
    this.add(scrollPane_admin, BorderLayout.CENTER);
    this.add(JPanel_administrator, BorderLayout.PAGE_END);

  }

   public void clearTextField(){
	 texfieldIdAdmin.setText("");
	 texfieldNameAdmin.setText("");
	 texfieldJobAdmin.setText("");
  }

}
