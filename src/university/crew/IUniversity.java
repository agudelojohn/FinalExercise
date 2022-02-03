package university.crew;

import university.asset.Lesson;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface IUniversity {

    /**
     * Create and add a new Student to the studentsLists attribute.
     * @param age, represents the age of the student
     * @param name, represents the name of the student
     * @return The object just created
     */
    public Student addStudent(int age, String name);

    /**
     * Create and add a new Lesson to the lessonsList attribute.
     * @param name, represents the name of the lesson
     * @param classRoom, represents the classroom assigned of the lesson
     * @param teacher, represents the teacher that dictate the lesson
     * @return The object just created
     */
    public Lesson addLesson(String name, int classRoom, Teacher teacher);

    /**
     *
     * @param name
     * @param experienceYears
     * @param baseSalary
     * @return
     */
    public Teacher addFullTimeTeacher(String name, int experienceYears, float baseSalary);

    public Teacher addPartTimeTeacher(String name, int activeHoursPerWeek, float baseSalary);

    public Lesson getLessonByIndex(int index);

    public Student getStudentByIndex(int index);

    public Teacher getTeacherByIndex(int index);

    public List<Student> getStudentsList();

    public void addAssistantToLesson(Student studentToFound, Lesson lessonToFound);
}
