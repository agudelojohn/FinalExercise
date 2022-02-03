package university.crew;

public class FullTimeTeacher extends Teacher{

    private int experienceYears;

    public FullTimeTeacher(String name, float baseSalary, int experienceYears) {
        super(name, baseSalary);
        this.experienceYears = experienceYears;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public void addOneExperienceYear(){
        this.experienceYears+=1;
    }

    @Override
    public float calculateSalary(){
        double salary = this.getBaseSalary() * (1.10 * experienceYears);
        return (float) salary;
    }

    @Override
    public String toString() {
        return "Teacher  "+this.getName()+" has a full time work. " +
                "\nExperience years = " + experienceYears +
                "\nSalary = "+this.calculateSalary();
    }
}
