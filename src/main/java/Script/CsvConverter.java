package Script;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * 描述： <br>
 * 创建时间: 2017/8/319:29 <br>
 *
 * @author 周志辉
 */
public class CsvConverter {

    public static String getFixedLengthString(int val, int length, String s) {
        if(val > Math.pow(10, length)) {
            System.out.println(val + " is longer than length");
            return null;
        }
        StringBuilder sb = new StringBuilder();
        String valString = val + "";
        int count = length - valString.length();
        while(count-- > 0) {
            sb.append(s);
        }
        return sb.toString() + valString;
    }

    public static void deal() {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream
                ("D:\\work\\项目\\CTS\\数据库\\new\\接口登记 - final.csv"), "GBK"));
            BufferedWriter menuBw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream
                    ("D:\\work\\项目\\CTS\\数据库\\new\\interface.csv"), "GBK"));
            BufferedWriter roleMenuBw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream
                    ("D:\\work\\项目\\CTS\\数据库\\new\\roleMenu.csv"), "GBK"))
        ){
            menuBw.write("menuId,url,isMenu,description\n");
            roleMenuBw.write("roleMenuId,roleId,menuId\n");
            String line = br.readLine();
            String[] array = null;
            int menuIndex = 1;
            int interfaceIndex = 1;
            String interfacePrefix = "interface";
            String menuPrefix = "menu";
            String menuId = null;
            String url = null;
            String description = null;
            int roleMenuIndex = 1;
            String roleMenuPrefix = "roleMenu";
            String roleMenuId = null;
            String roleId = null;
            int length = line.split(",").length;
            System.out.println("length : " + length);
            int[] counts = new int[8];
            while((line = br.readLine()) != null) {
                array = (line + ",1").split(",");
                if(array.length < length + 1) {
                    System.out.println("数据不全");
                    continue;
                }
                url = array[3];
                description = array[5];
                menuId = "".equals(array[1]) ?
                        (interfacePrefix + getFixedLengthString(interfaceIndex++, 4, "0")) :
                        (menuPrefix + getFixedLengthString(menuIndex++, 4, "0"));
                        //添加menu表记录
                menuBw.write( menuId + "," + url + "," +
                        1 + "," + description + "\n");

                //添加系统管理员接口权限
                if("1".equals(array[6])) {
                    roleMenuId = roleMenuPrefix + getFixedLengthString(roleMenuIndex++, 4, "0");
                    roleMenuBw.write(roleMenuId + ",0000," + menuId +"\n");
                    counts[0]++;
                } else {
                    System.out.println(line);
                }

                //添加管理员接口权限
                if("1".equals(array[7])) {
                    roleMenuId = roleMenuPrefix + getFixedLengthString(roleMenuIndex++, 4, "0");
                    roleMenuBw.write(roleMenuId + ",0001," + menuId +"\n");
                    counts[1]++;
                }

                //添加项目经理接口权限
                if("1".equals(array[8])) {
                    roleMenuId = roleMenuPrefix + getFixedLengthString(roleMenuIndex++, 4, "0");
                    roleMenuBw.write(roleMenuId + ",0002," + menuId +"\n");
                    counts[2]++;
                }

                //添加配置人员接口权限
                if("1".equals(array[9])) {
                    roleMenuId = roleMenuPrefix + getFixedLengthString(roleMenuIndex++, 4, "0");
                    roleMenuBw.write(roleMenuId + ",0003," + menuId +"\n");
                    counts[3]++;
                }

                //添加测试人员接口权限
                if("1".equals(array[10])) {
                    roleMenuId = roleMenuPrefix + getFixedLengthString(roleMenuIndex++, 4, "0");
                    roleMenuBw.write(roleMenuId + ",0004," + menuId +"\n");
                    counts[4]++;
                }

                //添加业务人员接口权限
                if("1".equals(array[11])) {
                    roleMenuId = roleMenuPrefix + getFixedLengthString(roleMenuIndex++, 4, "0");
                    roleMenuBw.write(roleMenuId + ",0005," + menuId +"\n");
                    counts[5]++;
                }

                //添加加开发人员接口权限
                if("1".equals(array[12])) {
                    roleMenuId = roleMenuPrefix + getFixedLengthString(roleMenuIndex++, 4, "0");
                    roleMenuBw.write(roleMenuId + ",0006," + menuId +"\n");
                    counts[6]++;
                }

                //添加浏览者接口权限
                if("1".equals(array[13])) {
                    roleMenuId = roleMenuPrefix + getFixedLengthString(roleMenuIndex++, 4, "0");
                    roleMenuBw.write(roleMenuId + ",0007," + menuId +"\n");
                    counts[7]++;
                }
            }
            System.out.println(Arrays.toString(counts));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        deal();
    }
}
