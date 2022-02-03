package university.asset;

import university.crew.Student;
import university.crew.Teacher;

import java.util.ArrayList;
import java.util.List;

public class Lesson {
    private String name;
    private int assignedClassroom;
    private List<Student> assistants;
    private Teacher teacher;

    public Lesson(String name) {
        this.name = name;
    }

    public Lesson(String name, int assignedClassroom) {
        this.name = name;
        this.assignedClassroom = assignedClassroom;
        this.assistants = new ArrayList<>();
        this.teacher = null;
    }

    public void setAssignedClassroom(int assignedClassroom) {
        this.assignedClassroom = assignedClassroom;
    }

    public void setAssistants(List<Student> assistants) {
        this.assistants = assistants;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getName() {
        return name;
    }

    public int getAssignedClassroom() {
        return assignedClassroom;
    }

    public List<Student> getAssistants() {
        return assistants;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void addAssistant(Student student){
        this.assistants.add(student);
    }

    @Override
    public String toString() {
        String assistantsString = "";
        for(Student student:assistants){
            assistantsString+= "["+student.toString()+"], ";
        }
        return "Information of ["+name+"] lesson  : \n" +
                "Assigned classroom = " + assignedClassroom +
                "\nTeacher=" + teacher.getName() +
                "\nAssistants=" + assistantsString+"\n";
    }
}
