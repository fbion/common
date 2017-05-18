package Algorithms.recursion;

public class Z
{
    private Z z;
    
    public Z(){
        z = new Z();
    }
    
    public static void main(String[] args)
    {
        new Z();
    }
}
