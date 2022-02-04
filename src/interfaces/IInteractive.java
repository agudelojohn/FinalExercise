package interfaces;

import university.asset.Lesson;
import university.crew.Student;
import university.crew.Teacher;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface IInteractive {

    /**
     * Shows the main application's menu
     */
    void printMenu();

    /**
     * Shows the submenu needed to select a Lesson
     */
    void printSubMenuAndLessonData();

    /**
     * Scans the user input for integers data
     * @return user input
     */
    int scanInt();

    /**
     * Scans the user input for String data
     * @return user input
     */
    String scanText();

    /**
     * Scans the user input for float data
     * @return user input
     */
   float scanFloat();

    /**
     * Shows all data from teachers
     */
    void printAllTeachersData();

    /**
     * Prints some blanc spaces on the screen to clean it
     */
    void clearScreen();

    /**
     * Receives all information needed to create a new Student and save it
     */
    void createNewStudent();

    /**
     * Receives all information needed to create a new Lesson and save it
     */
    void createNewLesson();

    /**
     * Shows all the Students' information to allow the user select them
     * @return List of students selected
     */
    List<Student> selectAssistants();

    /**
     * Shows all Teachers available and allows to select one of them
     * @return Teacher selected by the user
     */
    Teacher selectTeacher();

    /**
     * Shows all Students available and allows to select one of them
     * @return
     */
    Student selectStudent();

    /**
     * Shows classes after select one student
     */
    void printClassesByStudent();

    /**
     * Create all instances needed for this exercise
     */
     void initResources();
}
