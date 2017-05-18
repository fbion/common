package test.smallapplication;
import java.util.Scanner;
public class Arab2English {
    static int k;
    static String x[]={"zero","one","two", "three","four", "five","six","seven","eight","nine"};  
    static String y[]={"ten","eleven","twelve","thirteen","fourteen","fifteen","sixteen","seventeen","eighteen","nineteen"};
    static String z[]={"twenty","thirty","fourty","fifty","sixty","seventy","eighty","ninety"};
    public static void num_translate(int a){
        if(a>=20)
        {
          int c=a%10;
          int b=a/10;
          System.out.print(z[c-2]+" ");
          if(c!=0){
              System.out.print(x[c]);
          }
        }
        if(a>=10&&a<20){
            int c=a%10;
            System.out.print(y[c]);
        }
        if(a<10){
            System.out.print(x[a]);
        }
    }
    public static int str_translate(String s[]){
    
            if(s.length==2){
                for(int i=0;i<10;i++){
                    //System.out.println(x[i]);
                    if(z[i].equals(s[0])){
                        k=i+2;
                    }
                    if(x[i].equals(s[1])){
                        return k*10+i;
                    }
                }
            }
            else{
                for(int i=0;i<12;i++){
                        System.out.println(i);
                    if(x[i].equals(s[0])){
                        return i;
                    }
                    else if(y[i].equals(s[0])){
                        return i+10;
                    }
                    else if(z[i].equals(s[0])){
                        return (i+2)*10;
                    }
                }
            }
        return 0;
        
    }
    @SuppressWarnings("resource")
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str=scanner.nextLine();
        if(str.matches("\\d+")) {
            try{
                int a=Integer.parseInt(str);
                Arab2English.num_translate(a);
            }catch(Exception e){
                e.printStackTrace();
                System.out.print("输入格式有误");
            }
        } else {
            try{
                String[] s=str.split(" ");
                System.out.print(Arab2English.str_translate(s));
            }catch(Exception e){
              //System.out.print("输入格式有误"); 
            }
        }
    }
}