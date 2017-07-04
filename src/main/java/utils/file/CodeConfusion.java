package utils.file;

import utils.file.interfaces.impl.InsertInnerClass;
import utils.file.interfaces.impl.StringSubstitution;

import java.io.File;

/**
 * 描述： <br>
 * 创建时间: 2017/6/2210:48 <br>
 *
 * @author 周志辉
 */
public class CodeConfusion {
    private static String classNames = "Inner Valid Validator Entity";
    public static void addInnerClass(String filePath) {
        DirectoryRecurseOperatorUtil.recurseDirectory(filePath, new
                InsertInnerClass(classNames.split(" ")));
    }


    public static void editInnerClass(String filePath) {
        String desDirectory = filePath.replaceAll("\\.jar", "");
//        FileUtils.createDir(desDirectory);
        ZipUtils.unZip(filePath, desDirectory);
        System.out.println("delete result : " + new File(filePath).delete());
        DirectoryRecurseOperatorUtil.recurseDirectory(desDirectory, new
                StringSubstitution(classNames.split(" ")));
        ZipUtils.jar(desDirectory, filePath);
    }

    public static void main(String[] args) {
//        addInnerClass("D:\\test\\Info.java");
//        editInnerClass("D:\\test\\jar\\Info.jar");

//        addInnerClass("D:\\test\\SmartRegulation");
        editInnerClass("D:\\test\\projectjar\\smartregulation-datasource-0.0.1-SNAPSHOT.jar");
        editInnerClass("D:\\test\\projectjar\\smartregulation-plan-0.0.1-SNAPSHOT.jar");
        editInnerClass("D:\\test\\projectjar\\smartregulation-registry-0.0.1-SNAPSHOT.jar");
        editInnerClass("D:\\test\\projectjar\\smartregulation-web-0.0.1-SNAPSHOT.jar");
    }
}
