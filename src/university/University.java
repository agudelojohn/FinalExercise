package university;

import interfaces.IUniversity;
import university.asset.Lesson;
import university.crew.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class University implements IUniversity {
    private List<Teacher> teachersList;
    private List<Student> studentsList;
    private Set<Lesson> lessonsList;

    public University() {
        this.teachersList = new ArrayList<>();
        this.studentsList = new ArrayList<>();
        this.lessonsList = new HashSet<>();
    }

    public List<Teacher> getTeachersList() {
        return teachersList;
    }

    public Set<Lesson> getLessonsList() {
        return lessonsList;
    }

    public List<Lesson> getLessonsByStudent(Student student){
        List<Lesson> classesPerStudent = new ArrayList<>();
        for(Lesson oneLesson :lessonsList){
            if(oneLesson.getAssistants().contains(student))
                classesPerStudent.add(oneLesson);
        }
        return classesPerStudent;
    }

    public Student addStudent(int age, String name){
        int id = Student.addAndGetCount();
        Student newStudent = new Student(name, id, age);
        this.studentsList.add(newStudent);

        return newStudent;
    }

    public Lesson addLesson(String name, int classRoom, Teacher teacher){
        Lesson lessonToAdd = new Lesson(name, classRoom);
        lessonToAdd.setTeacher(teacher);
        this.lessonsList.add(lessonToAdd);
        return lessonToAdd;
    }


    public Teacher addFullTimeTeacher(String name, int experienceYears, float baseSalary){
        FullTimeTeacher teacher = new FullTimeTeacher(name, baseSalary, experienceYears);
        this.teachersList.add(teacher);
        return teacher;
    }

    public Teacher addPartTimeTeacher(String name, int activeHoursPerWeek, float baseSalary){
        PartTimeTeacher teacher = new PartTimeTeacher(name, baseSalary, activeHoursPerWeek);
        teachersList.add(teacher);
        return teacher;
    }
    public Lesson getLessonByIndex(int index){
        int currentIndex = 0;
        Lesson lessonRequested = null;
        for(Lesson oneLesson : this.lessonsList){
            if(index == currentIndex)
                lessonRequested = oneLesson;
            currentIndex++;
        }
        return lessonRequested;
    }

    public Student getStudentByIndex(int index){
        int currentIndex = 0;
        Student studentRequested = null;
        for(Student student : this.studentsList){
            if(index == currentIndex)
                studentRequested = student;
            currentIndex++;
        }
        return studentRequested;
    }

    public Teacher getTeacherByIndex(int index){
        int currentIndex = 0;
        Teacher teacherRequested = null;
        for(Teacher teacher : this.teachersList){
            if(index == currentIndex)
                teacherRequested = teacher;
            currentIndex++;
        }
        return teacherRequested;
    }

    public List<Student> getStudentsList() {
        return studentsList;
    }

    public void addAssistantToLesson(Student studentToFound, Lesson lessonToFound){
        Lesson lesson = null;
        for(Lesson oneLesson:lessonsList){
            if(oneLesson.equals(lessonToFound))
                lesson = oneLesson;
        }
        Student student = null;
        for(Student oneStudent: studentsList){
            if(oneStudent.equals(studentToFound))
                student = oneStudent;
        }
        if(student != null && lesson != null){
            lesson.addAssistant(student);
        }
    }
}
