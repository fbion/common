package Script;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 描述： <br>
 * 创建时间: 2017/9/78:46 <br>
 *
 * @author 周志辉
 */
public class RoleMenuCSV2Sql {
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

    public static void deal(String csvFileName, String sqlFileName) {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream
                (new File(csvFileName)), "GBK"));
            BufferedWriter sqlBw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream
                    (new File(sqlFileName)), "GBK"))
        ){
//            menuBw.write("menuId,url,isMenu,description\n");
//            roleMenuBw.write("roleMenuId,roleId,menuId\n");
            StringBuilder menuSql = new StringBuilder("TRUNCATE cts_menu;\nINSERT INTO cts_menu(menuId, url, isMenu, description) VALUES");
            StringBuilder roleMenuSql = new StringBuilder("TRUNCATE cts_role_menu;\nINSERT INTO cts_role_menu" +
                    "(roleMenuId, roleId, menuId) VALUES");
            String line = br.readLine();
            String[] array = null;
            int menuIndex = 1;
            int interfaceIndex = 1;
            String interfacePrefix = "interface";
            String menuPrefix = "menu";
            String menuId = null;
            String url = null;
            String description = null;
            int isMenu = 0;
            int roleMenuIndex = 1;
            String roleMenuPrefix = "roleMenu";
            String roleMenuId = null;
            String roleId = null;
            int length = line.split(",").length;
            System.out.println("length : " + length);
            Integer[] counts = {0,0,0,0,0,0,0,0,0};
            while((line = br.readLine()) != null) {
                array = (line + ",1").split(",");
                if(array.length < length + 1) {
                    System.out.println("数据不全");
                    continue;
                }
                url = array[3];
                description = array[5];
                if("".equals(array[1])) {
                    menuId = interfacePrefix + getFixedLengthString(interfaceIndex++, 4, "0");
                    isMenu = 1;
                } else {
                    menuId = menuPrefix + getFixedLengthString(menuIndex++, 4, "0");
                    isMenu = 1;
                }
                //添加menu表记录
                menuSql.append("('" + menuId + "','" + url + "','" +
                        isMenu + "'," +  ("".equals(description) ?  null : "'" + description) + "'),");


                //添加系统管理员接口权限
                if("1".equals(array[6])) {
                    roleMenuId = roleMenuPrefix + getFixedLengthString(roleMenuIndex++, 4, "0");
                    roleId = "0000";
                    counts[0]++;
                } else {
                    System.out.println(line);
                }

                //添加管理员接口权限
                if("1".equals(array[7])) {
                    roleMenuId = roleMenuPrefix + getFixedLengthString(roleMenuIndex++, 4, "0");
                    roleId = "0001";
                    counts[1]++;
                }

                //添加项目经理接口权限
                if("1".equals(array[8])) {
                    roleMenuId = roleMenuPrefix + getFixedLengthString(roleMenuIndex++, 4, "0");
                    roleId = "0002";
                    counts[2]++;
                }

                //添加配置人员接口权限
                if("1".equals(array[9])) {
                    roleMenuId = roleMenuPrefix + getFixedLengthString(roleMenuIndex++, 4, "0");
                    roleId = "0003";
                    counts[3]++;
                }

                //添加测试人员接口权限
                if("1".equals(array[10])) {
                    roleMenuId = roleMenuPrefix + getFixedLengthString(roleMenuIndex++, 4, "0");
                    roleId = "0004";
                    counts[4]++;
                }

                //添加业务人员接口权限
                if("1".equals(array[11])) {
                    roleMenuId = roleMenuPrefix + getFixedLengthString(roleMenuIndex++, 4, "0");
                    roleId = "0005";
                    counts[5]++;
                }

                //添加加开发人员接口权限
                if("1".equals(array[12])) {
                    roleMenuId = roleMenuPrefix + getFixedLengthString(roleMenuIndex++, 4, "0");
                    roleId = "0006";
                    counts[6]++;
                }

                //添加浏览者接口权限
                if("1".equals(array[13])) {
                    roleMenuId = roleMenuPrefix + getFixedLengthString(roleMenuIndex++, 4, "0");
                    roleId = "0007";
                    counts[7]++;
                }

                //添加无角色接口权限
                if("1".equals(array[14])) {
                    roleMenuId = roleMenuPrefix + getFixedLengthString(roleMenuIndex++, 4, "0");
                    roleId = "";
                    counts[8]++;
                }

                roleMenuSql.append("('" + roleMenuId + "','" + roleId + "','" + menuId  + "'),");
            }
            System.out.println(Arrays.toString(counts));
            List<Integer> list = new ArrayList<>();
            list.addAll(Arrays.asList(counts));
            if(list.stream().mapToInt(i -> i).sum() > 0) {
                menuSql.deleteCharAt(menuSql.length());
                roleMenuSql.deleteCharAt(roleMenuSql.length());
                sqlBw.write(menuSql.toString() + "\n");
                sqlBw.write(roleMenuSql.toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if(args.length < 2) {
            System.err.println("CsvFileName or SqlFileName missing");
            System.exit(1);
        }
        deal(args[0], args[1]);
    }
}
