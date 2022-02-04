package main;

import interfaces.IInteractive;
import university.University;
import university.asset.Lesson;
import university.crew.Student;
import university.crew.Teacher;

import java.util.*;

public class Interactive implements IInteractive {

    public Scanner scan;
    public University university;
    public int option;

    public Interactive() {
        initResources();
        option = 1;
        while(option != 0){
            printMenu();
            option = scanInt();
            switch (option){
                case 1:
                    printAllTeachersData();
                    break;
                case 2:
                    printSubMenuAndLessonData();
                    break;
                case 3:
                    createNewStudent();
                    break;
                case 4:
                    createNewLesson();
                    break;
                case 5:
                    printClassesByStudent();
                    break;
            }
        }
    }

    public void printMenu(){
        System.out.println("Menu -> select one of the following:\n" +
                "1. Print all the professors with its data\n" +
                "2. Print all lessons and select one of them (submenu)\n" +
                "3. Create a new student and add it to an existing class\n" +
                "4. Create a new lesson and add an existing teacher, existing students and its relevant data\n" +
                "5. List all the classes in which a given student is included (hint: search by id)\n" +
                "0. Exit");
    }

    public void printSubMenuAndLessonData(){
        int index = 1;
        System.out.println("Select one of the following list\n");
        for(Lesson oneLesson : university.getLessonsList()){
            System.out.println(index+". "+ oneLesson.getName());
            index++;
        }

        int classIndex = scanInt();
        while(classIndex == 0 || classIndex >= index){
            System.out.println("Please enter a valid options between 1 and "+(index-1));
            classIndex = scanInt();
        }
        Lesson lessonRequested = university.getLessonByIndex(classIndex-1);
        System.out.println(lessonRequested.toString());

    }

    public int scanInt(){
        System.out.println("Integer input: ");
        int number = scan.nextInt();
        scan.nextLine();
        return number;
    }

    public String scanText(){
        System.out.println("Text input: ");
        return scan.nextLine();
    }

    public float scanFloat(){
        System.out.println("Float input: ");
        return scan.nextFloat();
    }

    public void printAllTeachersData(){
        System.out.println("All teachers available data:\n");
        for(Teacher teacher:university.getTeachersList()){
            System.out.println(teacher.toString());
        }
        clearScreen();
    }

    public void clearScreen(){
        System.out.println("\n\n\n\n\n");
    }
    public void createNewStudent(){
        System.out.println("Enter name");
        String name = scanText();
        System.out.println("Enter age");
        int age = scanInt();
//        Student student = new Student(name, Student.addAndGetCount(),age);
        Student student = university.addStudent(age, name);
        System.out.println("\nLesson to add");
        int index = 0;
        for(Lesson lesson : university.getLessonsList()){
            System.out.println(index+". "+lesson.getName());
            index++;
        }
        index = scanInt();
        Lesson lesson = university.getLessonByIndex(index);
        university.addAssistantToLesson(student, lesson);
        System.out.println(student.toString()+"\n\n");
    }

    public void createNewLesson(){
        System.out.println("Enter lesson name");
        String name = scanText();
        System.out.println("Enter classroom to assign");
        int classRoom = scanInt();
        List<Student> assistants = selectAssistants();
        Teacher teacher = selectTeacher();
        Lesson lesson = university.addLesson(name,classRoom,teacher);
        for(Student assistant: assistants){
            university.addAssistantToLesson(assistant, lesson);
        }
        System.out.println("Lesson created :");
        System.out.println(lesson.toString());
    }

    public List<Student> selectAssistants(){

        int index = 1;
        boolean addedMinimOne = false;
        List<Student> assistants = new ArrayList<>();
        Set<Integer> availableIndexes = new HashSet<>();

        System.out.println("Select assistants");
        for(Student student: university.getStudentsList()){
            availableIndexes.add(index);
            System.out.println(index+". "+student.getName());
            index++;
        }
        int maxIndex = index;
        while(index != 0) {
            System.out.println("\nEnter "+availableIndexes.toString()+" to add or '0' to Exit: ");
            index = scanInt();
            if(index!=0){
                if(index >= maxIndex){
                    System.out.println("Please enter a valid number");
                }else{
                    Student st = university.getStudentByIndex(index-1);
                    assistants.add(university.getStudentByIndex(index-1));
                    availableIndexes.remove((Integer) index);
                    addedMinimOne = true;
                }
            }else if(addedMinimOne == false ){
                System.out.println("You have to add at least one student to the lesson");
                index = 1;
            }
        }
        return assistants;
    }

    public Teacher selectTeacher(){
        System.out.println("Select teacher: ");
        int index = 0;
        for(Teacher teacher: university.getTeachersList()){
            System.out.println((index+1)+ ". "+teacher.getName());
            index++;
        }
        int maxIndex = index;
        index = scanInt();
        while(index == 0 || index > maxIndex){
            System.out.println("Please enter a valid number to add a teacher");
            index = scanInt();
        }

        return university.getTeacherByIndex(index-1);

    }

    public Student selectStudent(){
        System.out.println("Select student: ");
        int index = 0;
        for(Student student: university.getStudentsList()){
            System.out.println((index+1)+ ". "+student.getName());
            index++;
        }
        int maxIndex = index;
        index = scanInt();
        while(index == 0 || index > maxIndex){
            System.out.println("Please enter a valid number to show");
            index = scanInt();
        }
        return university.getStudentByIndex(index-1);
    }

    public void printClassesByStudent(){
        Student student = selectStudent();
        System.out.println("Lessons where "+student.getName()+" is assistant:");
        List<Lesson> lessonsByStudent = university.getLessonsByStudent(student);
        for(Lesson lesson: lessonsByStudent){
            System.out.println(lesson.getName());
        }
        System.out.println("\n\n");
    }

    public void initResources(){
        scan = new Scanner(System.in);
        university = new University();


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
}
