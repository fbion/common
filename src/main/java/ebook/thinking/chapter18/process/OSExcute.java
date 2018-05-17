package ebook.thinking.chapter18.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2016/3/22.
 */
public class OSExcute {
    public static void command(String command)
    {
        boolean err = false;
        try{
            Process process = new ProcessBuilder(command.split(" ")).start();
            BufferedReader results = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String s;
            while((s = results.readLine()) != null)
            {
                System.out.println(s);
            }
            BufferedReader errors = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            while((s = errors.readLine()) != null)
            {
                System.out.println(s);
                err = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
            if(!command.startsWith("CMD /C"))
            {
                command("CMD /C" + command);
            }
            else
            {
                throw new RuntimeException(e);
            }
        }
        if(err)
        {
            throw new OSExcuteException("Errors excuting " + command);
        }
    }
}
