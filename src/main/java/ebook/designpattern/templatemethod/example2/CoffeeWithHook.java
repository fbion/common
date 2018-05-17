package ebook.designpattern.templatemethod.example2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2016/5/23.
 */
public class CoffeeWithHook extends CaffeineBeverageWithHook {
    @Override
    public void brew() {
        System.out.println("Dripping coffee through filter");
    }

    @Override
    public void addCondiments() {
        System.out.println("Adding Sugar and Milk");
    }

    @Override
    public boolean customerWantsCondiments() {
        String answer = getUserInput();
        if(answer.toLowerCase().startsWith("y")) {
            return true;
        } else {
            return false;
        }
    }

    public String getUserInput() {
        String answer = null;
        System.out.println("Would you like milk and sugar with your coffee(y/n)?");
        try(BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            answer = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(answer == null) {
            return "no";
        }
        return answer;
    }
}
