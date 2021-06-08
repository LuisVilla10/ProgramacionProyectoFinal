import adapters.CRUDAdmin;
import adapters.CRUDDirector;
import adapters.CRUDStudent;
import adapters.CRUDTeacher;
import datas.Data;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class School implements ActionListener {
  JMenuBar JMenuBar_myMenu;
  JFrame JFrame_myFrame;
  JDesktopPane JDesktopPane_myDesktopPane;

  //Internal Frame
  FrameStudents frameStudents = new FrameStudents("estudiantes", true, true, true, true);
  FrameTeachers frameTeachers = new FrameTeachers("Maestros", true, true, true, true);
  FrameDirect frameDirect = new FrameDirect("Directores", true, true, true, true);
  FrameAdmin frameAdmin = new FrameAdmin("Administradores", true, true, true, true);
  FrameCourse frameCourse = new FrameCourse("Cursos", true, true, true, true);
  FrameCourseTeacher frameCourseTeacher = new FrameCourseTeacher("Cursos Maestros", true, true, true, true);

  School(){
    //Creamos el frame principal
    JFrame_myFrame = new JFrame("Registro escolar");
    JFrame_myFrame.setSize(900,700);
    JFrame_myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //Creamos un menuBar
    JMenuBar_myMenu = new JMenuBar();
    /*
    *Cargando el menu principal
    * */
    makeMenuStudent();
    makeMenuTeacher();
    makeMenuDirector();
    makeMenuAdmin();
    JFrame_myFrame.setJMenuBar(JMenuBar_myMenu);

    //Creamos un JDesktopPane  para nuestro programa MDI, Multiple Document Interface
    JDesktopPane_myDesktopPane = new JDesktopPane();
    JDesktopPane_myDesktopPane.setBackground(Color.GRAY);
    JFrame_myFrame.setContentPane(JDesktopPane_myDesktopPane);
    // Se agregan internal frames
    JDesktopPane_myDesktopPane.add(frameStudents);
    JDesktopPane_myDesktopPane.add(frameTeachers);
    JDesktopPane_myDesktopPane.add(frameDirect);
    JDesktopPane_myDesktopPane.add(frameAdmin);
    JDesktopPane_myDesktopPane.add(frameCourse);
    JDesktopPane_myDesktopPane.add(frameCourseTeacher);

    // Mostramos el Frame.
    JFrame_myFrame.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    //Obtenemos el "Action command del event dispatching thread
    String commandString = actionEvent.getActionCommand();

    //Imprimio en consola esta accion
    System.out.println(commandString);

    //De esta manera podemos saber en donde fue disparado el evento
    if(commandString.equals("Ver estudiantes")){
	frameStudents.setVisible(true);
    }
    if (commandString.equals("Opciones maestro")){
      frameTeachers.setVisible(true);
    }
    if (commandString.equals("Opciones director")){
      frameDirect.setVisible(true);
    }
    if (commandString.equals("Opciones administrador")){
      frameAdmin.setVisible(true);
    }
   if (commandString.equals("Opciones curso maestro")){
      frameCourseTeacher.setVisible(true);
    }
   if (commandString.equals("Opciones curso")){
      frameCourse.setVisible(true);
    }
  }

  void makeMenuStudent(){
    JMenu JMenu_Students = new JMenu("Estudiantes");
    JMenuItem JMenuItem_show = new JMenuItem("Ver estudiantes");
    JMenuItem_show.setToolTipText("Ver estudiantes");
    JMenu_Students.add(JMenuItem_show);
    JMenuItem_show.addActionListener(this);
    JMenuBar_myMenu.add(JMenu_Students);
  }

  void makeMenuTeacher(){
    JMenu JMenu_teachers = new JMenu("Maestros");
    JMenuItem JMenuItem_show = new JMenuItem("Opciones maestro");
    JMenuItem JMenuItem_curso = new JMenuItem("Opciones curso");
    JMenuItem JMenucursoTeacher = new JMenuItem("Opciones curso maestro");
    JMenuItem_show.setToolTipText("Opciones profesor");
    JMenu_teachers.add(JMenuItem_show);
    JMenu_teachers.add(JMenuItem_curso);
    JMenu_teachers.add(JMenucursoTeacher);
    JMenuItem_show.addActionListener(this);
    JMenuItem_curso.addActionListener(this);
    JMenucursoTeacher.addActionListener(this);
    JMenuBar_myMenu.add(JMenu_teachers);
  }

  void makeMenuDirector(){
    JMenu JMenu_directors = new JMenu("Directores");
    JMenuItem JMenuItem_show = new JMenuItem("Opciones director");
    JMenuItem_show.setToolTipText("Opciones director");
    JMenu_directors.add(JMenuItem_show);
    JMenuItem_show.addActionListener(this);
    JMenuBar_myMenu.add(JMenu_directors);
  }

  void makeMenuAdmin(){
    JMenu JMenu_admins = new JMenu("Administradores");
    JMenuItem JMenuItem_show = new JMenuItem("Opciones administrador");
    JMenuItem_show.setToolTipText("Opciones administrador");
    JMenu_admins.add(JMenuItem_show);
    JMenuItem_show.addActionListener(this);
    JMenuBar_myMenu.add(JMenu_admins);
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        new School();
      }
    });
  }
}
