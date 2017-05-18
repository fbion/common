package test.tool;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/6/14.
 */
public class Test1 {
    static ScriptEngine jse = new ScriptEngineManager().getEngineByName("js");
    public static void main(String[] args){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss SSS");
        String str = "1+2*(+0.5+2*(1+1.2/2)+1+2*(+0.5+2*(1+1.2/2)+1+2*(+0.5+2*(1+1.2/2)+1+2*(+0.5+2*(1+1.2/2)+1+2*(+0.5+2*(1+1.2/2)g )";
        str = "一";
        try{
            System.out.println(sdf.format(new Date()));
            System.out.println(jse.eval(str));
        } catch (ScriptException e) {
            System.out.println(e.getMessage());
            System.out.println("计算式不合法");
        }
        System.out.println(sdf.format(new Date()));
    }
}
