package interfaces;

import university.asset.Lesson;
import university.crew.Student;
import university.crew.Teacher;

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
     * Create and add a new Full Time Teacher to the teachersList attribute
     * @param name
     * @param experienceYears
     * @param baseSalary
     * @return The object just created
     */
    public Teacher addFullTimeTeacher(String name, int experienceYears, float baseSalary);

    /**
     * Create and add a new Part Time Teacher to the teachersList attribute
     * @param name
     * @param activeHoursPerWeek
     * @param baseSalary
     * @return The object just created
     */
    public Teacher addPartTimeTeacher(String name, int activeHoursPerWeek, float baseSalary);

    /**
     * Return the object of lessonsList give by the index in the list
     * @param index
     * @return A Lesson object
     */
    public Lesson getLessonByIndex(int index);

    /**
     * Return the object of studentsList give by the index in the list
     * @param index
     * @return A Student object
     */
    public Student getStudentByIndex(int index);

    /**
     * Return the object of teachersList give by the index in the list
     * @param index
     * @return A Teacher object
     */
    public Teacher getTeacherByIndex(int index);

    /**
     * Add the Student given as assistant of the Lesson given
     * @param studentToFound
     * @param lessonToFound
     */
    public void addAssistantToLesson(Student studentToFound, Lesson lessonToFound);
}
