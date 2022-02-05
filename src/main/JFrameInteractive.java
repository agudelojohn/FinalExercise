package main;

import interfaces.IInteractive;
import university.University;
import university.asset.Lesson;
import university.crew.Student;
import university.crew.Teacher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class JFrameInteractive extends JFrame implements IInteractive {

    final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    final int totalWidth = (int) screenSize.getWidth() / 2;
    final int totalHeight = (int) screenSize.getHeight() / 2;
    final int buttonHeight = 30;
    final int buttonWidth = 90;
    final int axisXMargin = 10;
    final int buttonYAxis = totalHeight - buttonHeight*3;

    JLabel label1;
    List<JButton> buttons;
    public University university;


    public JFrameInteractive(String title) throws HeadlessException {
        super(title);

        setLayout(null);
        setBounds(totalWidth/2,totalHeight/2,totalWidth,totalHeight);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        initResources();

        printMenu();
        this.add(label1);


        this.setVisible(true);
    }

    @Override
    public void printMenu() {
        List<String> linesToShow = new ArrayList<>();
        linesToShow.add("Select one of the following:<br>");
        linesToShow.add("1. Print all the professors with its data");
        linesToShow.add("2. Print all lessons and select one of them (submenu)");
        linesToShow.add("3. Create a new student and add it to an existing class");
        linesToShow.add("4. Create a new lesson and add an existing teacher, existing students and its relevant data");
        linesToShow.add("5. List all the classes in which a given student is included (hint: search by id)");
        linesToShow.add("0. Exit");
        addTextToLabel(linesToShow);


        JButton button1=new JButton(new AbstractAction("1") {
            @Override
            public void actionPerformed( ActionEvent e ) {
                printAllTeachersData();
            }
        });
        button1.setBounds((axisXMargin),buttonYAxis,buttonWidth,buttonHeight);

        JButton button2=new JButton(new AbstractAction("2") {
            @Override
            public void actionPerformed( ActionEvent e ) {
                printSubMenuAndLessonData();
            }
        });
        button2.setBounds((axisXMargin+(buttonWidth)),buttonYAxis,buttonWidth,buttonHeight);

        JButton button3=new JButton(new AbstractAction("3") {
            @Override
            public void actionPerformed( ActionEvent e ) {
                createNewStudent();
            }
        });
        button3.setBounds((axisXMargin+(buttonWidth*2)),buttonYAxis,buttonWidth,buttonHeight);

        JButton button4=new JButton(new AbstractAction("4") {
            @Override
            public void actionPerformed( ActionEvent e ) {
                createNewLesson();
            }
        });
        button4.setBounds((axisXMargin+(buttonWidth*3)),buttonYAxis,buttonWidth,buttonHeight);

        JButton button5=new JButton(new AbstractAction("5") {
            @Override
            public void actionPerformed( ActionEvent e ) {
                printClassesByStudent();
            }
        });
        button5.setBounds((axisXMargin+(buttonWidth*4)),buttonYAxis,buttonWidth,buttonHeight);

        JButton button6 = new JButton(new AbstractAction("0") {
            @Override
            public void actionPerformed( ActionEvent e ) {
                System.exit(0);
            }
        });
        button6.setBounds((axisXMargin+(buttonWidth*6)),buttonYAxis,buttonWidth,buttonHeight);

        List<JButton> buttonList = new ArrayList<>();
        buttonList.add(button1);
        buttonList.add(button2);
        buttonList.add(button3);
        buttonList.add(button4);
        buttonList.add(button5);
        buttonList.add(button6);
        buttonList.add(button6);

        addButtons(buttonList);
        repaint();
    }

    @Override
    public void printSubMenuAndLessonData() {
        List<String> linesToShow = new ArrayList<>();
        linesToShow.add("Select one of the following list");
        int index = 0;
        for(Lesson oneLesson : university.getLessonsList()){
            linesToShow.add(index+". "+ oneLesson.getName());
            index++;
        }

        JTextField input = new JTextField();
        input.setBounds((axisXMargin+(buttonWidth*4)),buttonYAxis,buttonWidth,buttonHeight);
        this.add(input);
        addTextToLabel(linesToShow);

        JButton button = new JButton(new AbstractAction("Continue") {
            @Override
            public void actionPerformed( ActionEvent e ) {
                List<String> linesToShow = new ArrayList<>();
                try{
                    int option = Integer.parseInt(input.getText());
                    if(option < 0 || option >= university.getLessonsList().size()){
                        linesToShow.add("That number is not an option");
                    }else {
                        Lesson lessonRequested = university.getLessonByIndex(option);
                        linesToShow.add(lessonRequested.toString());
                        List<JButton> buttons = new ArrayList<>();
                        addButtons(buttons);
                        addButtonBack();
                        remove(input);
                    }
                }catch (Exception exception){
                    linesToShow.add("Error, ["+input.getText()+"] is not a number");
                }
                addTextToLabel(linesToShow);
            }
        });
        button.setBounds((axisXMargin+(buttonWidth*5)),buttonYAxis,buttonWidth,buttonHeight);

        List<JButton> buttons = new ArrayList<>();
        buttons.add(button);
        addButtons(buttons);
    }

    @Override
    public int scanInt() {
        return 0;
    }

    @Override
    public String scanText() {
        return null;
    }

    @Override
    public float scanFloat() {
        return 0;
    }

    @Override
    public void printAllTeachersData() {
        List<String> linesToShow = new ArrayList<>();
        linesToShow.add("All teachers available data:");
        for(Teacher teacher:university.getTeachersList()){
            linesToShow.add(teacher.toString());
        }
        addTextToLabel(linesToShow);
        addButtonBack();
    }

    @Override
    public void clearScreen() {

    }

    @Override
    public void createNewStudent() {

        String name = JOptionPane.showInputDialog(this, "Student name", null);
        int age = Integer.parseInt(JOptionPane.showInputDialog(this, "Student age", null));
        Student student = university.addStudent(age,name);

        Object[] options = new Object[university.getLessonsList().size()];
        int index = 0;
        for(Lesson lesson : university.getLessonsList()){
            options[index] = index+". "+lesson.getName();
            index++;
        }

        Object lessonName = JOptionPane.showInputDialog(
                this,
                "Select an option",
                "Select option",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                JOptionPane.DEFAULT_OPTION);
        int lessonIndex = Integer.parseInt((String.valueOf(String.valueOf(lessonName).charAt(0))));

        Lesson lesson = university.getLessonByIndex(lessonIndex);
        university.addAssistantToLesson(student, lesson);
    }

    @Override
    public void createNewLesson() {
        String lessonName = JOptionPane.showInputDialog(this, "Lesson name", null);
        int classRoom = Integer.parseInt(JOptionPane.showInputDialog(this, "Class room", null));
        List<Student> assistants = selectAssistants();
        Teacher teacher = selectTeacher();

        Lesson lesson = university.addLesson(lessonName,classRoom,teacher);
        for(Student assistant: assistants){
            university.addAssistantToLesson(assistant, lesson);
        }
        System.out.println("Lesson created :");
        System.out.println(lesson.toString());
    }

    @Override
    public List<Student> selectAssistants() {
        List<Student> assistants = new ArrayList<>();
        List<Integer> alreadySelected = new ArrayList<>();
        int option = 99;
        while(option!=0){
            List<String> optionsList = new ArrayList<>();
            int index = 0;
            for(Student student: university.getStudentsList()){
                if(!alreadySelected.contains(index+1)) {
                    optionsList.add((index + 1) + ". " + student.getName());
                }
                index++;
            }

            Object[] options = new Object[optionsList.size()+1];
            index = 0;
            for(String optionMenu : optionsList){
                options[index] = optionMenu;
                index++;
            }
            options[optionsList.size()] = "0. Exit";

            Object studentSelected = JOptionPane.showOptionDialog(
                    this,
                    "Select assistant",
                    "Select assistant",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    null);



            option = Integer.parseInt(String.valueOf(options[Integer.parseInt(String.valueOf(studentSelected))].toString().charAt(0)));
            if(option!=0) {
                Student student = university.getStudentByIndex(option-1);
                assistants.add(student);
                alreadySelected.add(option);
            }
        }
        return assistants;
    }

    @Override
    public Teacher selectTeacher() {
        Teacher teacher;
        int index = 0;
        Object[] options = new Object[university.getTeachersList().size()];
        for(Teacher oneTeacher: university.getTeachersList()){
            options[index] = (index + 1) + ". " + oneTeacher.getName();
            index++;
        }
        Object teacherSelected = JOptionPane.showOptionDialog(
                this,
                "Select teacher",
                "Select teacher",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                null);
        teacher = university.getTeacherByIndex(Integer.parseInt(String.valueOf(teacherSelected)));
        return teacher;
    }

    @Override
    public Student selectStudent() {
        Student student;
        int index = 0;
        Object[] options = new Object[university.getStudentsList().size()];
        for(Student oneStudent: university.getStudentsList()){
            options[index] = (index + 1) + ". " + oneStudent.getName();
            index++;
        }
        Object studentSelected = JOptionPane.showOptionDialog(
                this,
                "Select student",
                "Select student",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                null);
        student = university.getStudentByIndex(Integer.parseInt(String.valueOf(studentSelected)));
        return student;
    }

    @Override
    public void printClassesByStudent() {
        Student student = selectStudent();
        StringBuilder message = new StringBuilder("Lessons where " + student.getName() + " is assistant:");
        List<Lesson> lessonsByStudent = university.getLessonsByStudent(student);
        for(Lesson lesson: lessonsByStudent){
            message.append("\n").append(lesson.getName());
        }
        JOptionPane.showMessageDialog(this, message.toString());
    }

    @Override
    public void initResources() {
        this.university = new University();
        this.label1 = new JLabel();
        this.buttons = new ArrayList<>();
        label1.setBounds(50,0,totalWidth - 50, 250);

        //Students
        university.addStudent(25, "John");
        university.addStudent(20, "Alejo");
        university.addStudent(22, "Rafael");
        university.addStudent(30, "Mario");
        university.addStudent(32, "Yoly");
        university.addStudent(28, "Yaddy");
        university.addStudent(22, "Lala");


        //Teachers
        university.addFullTimeTeacher("Claudia", 8, 5000000);
        university.addFullTimeTeacher("Pepe", 20, 4990000);
        university.addPartTimeTeacher("Lina",25, 1000000);
        university.addPartTimeTeacher("Monica",40, 1000000);

        //Lessons
        university.addLesson("Math", 1, university.getTeacherByIndex(0));
        university.addLesson("Science", 15, university.getTeacherByIndex(1));
        university.addLesson("Psychics",8,university.getTeacherByIndex(2));
        university.addLesson("Nature", 2, university.getTeacherByIndex(3));

        //Students as assistants
        university.addAssistantToLesson(university.getStudentByIndex(0),university.getLessonByIndex(0));
        university.addAssistantToLesson(university.getStudentByIndex(0),university.getLessonByIndex(1));
        university.addAssistantToLesson(university.getStudentByIndex(1),university.getLessonByIndex(2));
        university.addAssistantToLesson(university.getStudentByIndex(2),university.getLessonByIndex(0));
        university.addAssistantToLesson(university.getStudentByIndex(2),university.getLessonByIndex(2));
        university.addAssistantToLesson(university.getStudentByIndex(2),university.getLessonByIndex(3));
        university.addAssistantToLesson(university.getStudentByIndex(3),university.getLessonByIndex(2));
        university.addAssistantToLesson(university.getStudentByIndex(4),university.getLessonByIndex(2));
        university.addAssistantToLesson(university.getStudentByIndex(5),university.getLessonByIndex(0));
        university.addAssistantToLesson(university.getStudentByIndex(5),university.getLessonByIndex(1));
        university.addAssistantToLesson(university.getStudentByIndex(5),university.getLessonByIndex(2));
        university.addAssistantToLesson(university.getStudentByIndex(5),university.getLessonByIndex(3));
        university.addAssistantToLesson(university.getStudentByIndex(6),university.getLessonByIndex(0));
    }

    private void addButtons(List<JButton> buttonList){
        for(JButton button : this.buttons)
            this.remove(button);
        this.buttons = buttonList;
        for(JButton button : buttons)
            this.add(button);
        this.repaint();
    }

    private void addTextToLabel(List<String> lines){
//        label1 = new JLabel();
        StringBuilder textToAdd = new StringBuilder();
        for(String line:lines){
            textToAdd.append("<br>").append(line).append("<br>");
        }
        label1.setText("<html>" +
                "<body>"+textToAdd+"</body></html>");
    }
    private void addButtonBack(){
        JButton button = new JButton(new AbstractAction("<- Back") {
            @Override
            public void actionPerformed( ActionEvent e ) {
                printMenu();
            }
        });
        button.setBounds((axisXMargin+(buttonWidth*5)),buttonYAxis,buttonWidth,buttonHeight);

        List<JButton> buttonList = new ArrayList<>();
        buttonList.add(button);
        addButtons(buttonList);
    }
}
