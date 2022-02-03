package university.crew;

public interface ITeacher {

    /**
     * Calculate the salary depending on the kind of the teacher
     * @return salary of the teacher
     */
    public float calculateSalary();

    @Override
    public String toString();
}
