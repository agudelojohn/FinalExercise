package university.crew;

public class Teacher extends Person implements ITeacher{
    private float baseSalary;

    public Teacher(String name, float baseSalary) {
        super(name);
        this.baseSalary = baseSalary;
    }

    public float getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(float baseSalary) {
        this.baseSalary = baseSalary;
    }

    @Override
    public float calculateSalary(){
        return 0F;
    }


}
