package designpattern.strategy.example2.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 项目名称：zzh<br/>
 * *********************************<br/>
 * <p>类名称：MiniDuckSimulate</p>
 * *********************************<br/>
 * <p>类描述：</p>
 * 创建人：zhouzhihui<br/>
 * 创建时间：2017/4/6 13:53<br/>
 * 修改人：周志辉<br/>
 * 修改时间：2017/4/6 13:53<br/>
 * 修改备注：<br/>
 *
 * @version 1.0<br/>
 */
public class MiniDuckSimulate {

    public static void main(String[] args) {
//        Duck duck = new MallardDuck();
//        duck.performQuake();
//        duck.performFly();

        String filePath = "d:\\des.txt";
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while((line = br.readLine()) != null) {
                System.out.print(line.length() + "\t\t");
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
