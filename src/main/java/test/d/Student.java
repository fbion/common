package test.d;

import java.util.Arrays;

/**
 * Created by Administrator on 2016/6/15.
 */
public class Student implements Comparable {
    public static void main(String[] args) {
        Student stu1=new Student("lilei", true, 12, "北京大学");
        Student stu2=new Student("Tom", true, 19, "南京大学");
        Student stu3=new Student("WangJuan", false, 12, "复旦大学");
        Student stu4=new Student("Hellen", false, 17, "四川大学");
        Student[] stu={stu1,stu2,stu3,stu4,};
        Arrays.sort(stu);
        System.out.println(Arrays.deepToString(stu));
        for (Student student : stu) {
            System.out.println(student);
        }
    }

    private String name;
    private boolean sex;
    private int age;
    private String school;
    public Student() {}

    public Student(String name, boolean sex, int age, String school) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.school = school;
    }
    public String getName() {
        return name;
    }
    public boolean isSex() {
        return sex;
    }
    public int getAge() {
        return age;
    }
    public String getSchool() {
        return school;
    }

    @Override
    public String toString() {
        return "Student [name=" + name + ", sex=" + (sex?"m":"f") + ", age=" + age + ", school=" + school + "]";
    }

    @Override
    public int compareTo(Object obj) {
        Student stu=(Student)obj;
        if(this.age-stu.age>0){
            return 1;
        }else if(this.age-stu.age<0){
            return -1;
        }else{
            return 0;
        }
    }
}