package demo.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.Set;

/**
 * 描述： <br>
 * 创建时间: 2017/9/129:03 <br>
 *
 * @author 周志辉
 */
public class Demo {

    public static void main(String[] args) {
        WebDriver driver = new HtmlUnitDriver(true);//支持js
        driver.get("https://uic.aliyun.sinopec.com/sso/login?back_url=http%3A%2F%2Fuatcts.cloud.sinopec.com%2Findex");
        Set<Cookie> cookies = driver.manage().getCookies();
        for (Cookie cookie : cookies) {
            System.out.println(cookie.getName() + " : " + cookie.getValue());
        }
        WebElement userNameElement = driver.findElement(By.id("userName"));
        userNameElement.sendKeys("zhihui.zhou");
        WebElement passwordElement = driver.findElement(By.id("password"));
        passwordElement.sendKeys("shyk789");
        WebElement inputCodeElement = driver.findElement(By.id("inputCode"));
        String code = driver.findElement(By.id("checkCode")).getAttribute("value");
        inputCodeElement.sendKeys(code);
        WebElement subkitButton = driver.findElement(By.className("login_btn"));
        subkitButton.submit();
        cookies = driver.manage().getCookies();
        for (Cookie cookie : cookies) {
            System.out.println(cookie.getName() + " : " + cookie.getValue());
        }
        driver.close();
        driver.quit();
    }
}
