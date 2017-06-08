package designpattern.proxy.protect;

import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * 描述： <br>
 * 创建时间: 2017/6/114:16 <br>
 *
 * @author 周志辉
 */
public class MatchMakingTestDrive {

    public MatchMakingTestDrive() {
        initializeDatabase();
    }


    private void initializeDatabase() {
    }


    public static void main(String[] args) {
        MatchMakingTestDrive test = new MatchMakingTestDrive();
        test.drive();
    }


    private void drive() {
        PersonBean joe = getPersonFromDatabase("Joe Javabean");
        PersonBean ownerProxy = getOwnerProxy(joe);
        System.out.println("Name is " + ownerProxy.getName());
        ownerProxy.setIntrests("bowling, Go");
        System.out.println("Intrests set from owner proxy");
        try {
            ownerProxy.setHotOrNotRating(10);
        } catch (Exception e) {
            System.out.println("Cann't set rating from owner proxy");
        }
        System.out.println("Rating is " + ownerProxy.getHotOrNotRating());

        PersonBean nonOwnProxy = getNonOwnPxory(joe);
        System.out.println("Name is " + nonOwnProxy.getName());

        try {
            nonOwnProxy.setIntrests("bowling, Go");
        } catch (Exception e) {
            System.out.println("Cann't set intrests from non owner proxy");
        }
        nonOwnProxy.setHotOrNotRating(3);
        System.out.println("Rating set from non owner proxy");
        System.out.println("Rating is " + nonOwnProxy.getHotOrNotRating());

        System.out.println(Arrays.deepToString(ownerProxy.getClass().getInterfaces()));
        System.out.println(Arrays.deepToString(nonOwnProxy.getClass().getInterfaces()));
    }


    private PersonBean getNonOwnPxory(PersonBean person) {
        return  (PersonBean) Proxy.newProxyInstance(person.getClass().getClassLoader(), person.getClass().getInterfaces(),
                new NonOwnerInvocationHandler(person));
    }


    private PersonBean getOwnerProxy(PersonBean person) {
        return  (PersonBean) Proxy.newProxyInstance(person.getClass().getClassLoader(), person.getClass().getInterfaces(),
                new OwnerInvocationHandler(person));
    }


    private PersonBean getPersonFromDatabase(String name) {
        PersonBean result = new PersonBeanImpl();
        result.setName(name);
        return result;
    }


}
