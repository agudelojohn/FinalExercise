package university.crew;

public class Student extends Person{

    public static int count = 0;
    private int id;
    private int age;

    public Student(String name, int id, int age) {
        super(name);
        this.id = id;
        this.age = age;
    }

    static public int addAndGetCount(){
        count+=1;
        return count;
    }

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Student "+this.getName()+", ID = " +id +" , age = " + age;
    }
}
