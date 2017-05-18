package test.base;

public class TestStr
{

    public static void main(String[] args)
    {
        String str1 = "Hello";
        String str2 = "Hello";
        System.out.println(str1 == str2);
        System.out.println("str1 == str2 : " + (str1 == str2));
        String str = "str1 == str2 : " + (str1 == str2);
        System.out.println(str);
    }
}
