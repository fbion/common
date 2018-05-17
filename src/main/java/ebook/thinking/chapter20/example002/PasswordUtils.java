package ebook.thinking.chapter20.example002;

import java.util.List;

/**
 * 项目名称：zzh<br/>
 * *********************************<br/>
 * <p>类名称：PasswordUtils</p>
 * *********************************<br/>
 * <p>类描述：</p>
 * 创建人：zhouzhihui<br/>
 * 创建时间：2017/3/9 17:56<br/>
 * 修改人：周志辉<br/>
 * 修改时间：2017/3/9 17:56<br/>
 * 修改备注：<br/>
 *
 * @version 1.0<br/>
 */
public class PasswordUtils {

    @UseCase(id=47, description = "Password must contain at least one numeric")
    public boolean validatePassword(String password) {
        return (password.matches("\\w*\\d\\w*"));
    }

    @UseCase(id=48)
    public String encryptPassword(String password) {
        return new StringBuilder(password).reverse().toString();
    }

    @UseCase(id=49, description = "new password can't equal previously used ones")
    public boolean checkForNewPassword(List<String> prevPasswords, String password) {
        return !prevPasswords.contains(password);
    }
}
