package university.crew;

public class PartTimeTeacher extends Teacher{
    public int activeHoursPerWeek;

    public PartTimeTeacher(String name, float baseSalary, int activeHoursPerWeek) {
        super(name, baseSalary);
        this.activeHoursPerWeek = activeHoursPerWeek;
    }

    public int getActiveHoursPerWeek() {
        return activeHoursPerWeek;
    }

    public void setActiveHoursPerWeek(int activeHoursPerWeek) {
        this.activeHoursPerWeek = activeHoursPerWeek;
    }

    @Override
    public float calculateSalary(){
        return (float) this.getBaseSalary()*this.activeHoursPerWeek;
    }

    @Override
    public String toString() {
        return "Teacher  "+this.getName()+" has a part time work, " +
                "\nActive Hours Per Week = " + activeHoursPerWeek +
                "\nSalary = "+this.calculateSalary();
    }
}
