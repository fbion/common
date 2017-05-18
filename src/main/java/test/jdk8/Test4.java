package test.jdk8;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2016/4/19.
 */
public class Test4 {
    public static void main(String[] args) {
        String[] strs = {"1", "2", "3", "4"};
        Map<String, Integer> strMap = Arrays.stream(strs).collect(Collectors.toMap((x) -> x, (x) -> Integer.parseInt(x)));
        System.out.println(strMap);

        Person[] persons = {new Person("zhangsan", 20), new Person("lisi", 21), new Person("wangwu", 22), new Person("zhuba", 23)};
        Map<String, Person> map = Arrays.stream(persons).collect(Collectors.toMap(Person::getName, (p) -> p));
        System.out.println(map);
    }
}
class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
}