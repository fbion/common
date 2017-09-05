package Script;

import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 描述： <br>
 * 创建时间: 2017/6/1617:34 <br>
 *
 * @author 周志辉
 */
public class DataBaseSrcipts {

    /**
     * 方法描述: 读取csv数据文件生成插入语句的sql文件<br>
     *
     * @author 周志辉
     * @param   fileName    csv文件名全路径
     * @param   desFileName 生成的sql文件名全路径
     */
    public static void csv2Sql(String fileName, String desFileName) {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "GBK"));
            BufferedWriter bw = new BufferedWriter(new FileWriter(desFileName))) {
            String line = null;
            List<String> temp = new ArrayList<>();
            //状态码，0表示未读到表名，1表示已读到表名，2表示读完表名下一行，
            StringBuffer sb = new StringBuffer();
            int status = 0;
            Pattern p = Pattern.compile("([^\\[]?)\\[(.*)\\]");
            String tableName = null;
            Matcher m = null;
            while((line = br.readLine()) != null) {
                if(",".equals(line.replaceAll(",+", ","))) {
                    if(status != 0) {
                        status = 0;
                        createSql(sb, List2Array(temp));
                        temp.clear();
                        if(sb.indexOf("VALUES(") >= 0){
                            bw.write(sb.toString());
                            bw.flush();
                        }
                        sb.delete(0, sb.length());
                    }
                    continue;
                }
                //状态0表示在匹配表名
                if(status == 0) {
                    m = p.matcher(line);
                    if(m.find()) {
                        tableName = m.group(2);
                        sb.append("INSERT INTO `" + tableName + "`");
                    }
                    status = 1;
                } else if (status  == 1) {
                    status = 2;
                    continue;
                } else if (status  == 2) {
                    temp.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String[][] List2Array(List<String> list) {
        String[][] data = new String[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            data[i] = mySplit(list.get((i)), ",");
        }
        return data;
    }

    private static void createSql(StringBuffer sb, String[][] data){
        if(data.length > 0 && data[0].length > 3) {
            sb.append("(");
            for (int i = 0; i < data.length; i++) {
                sb.append(data[i][0] + ",");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append(") VALUES");
            for (int j = 3; j < data[0].length; j++) {
                boolean flag = true;
                for (int i = 0; i < data.length; i++) {
                    if(!StringUtils.isBlank(data[i][j])) {
                        flag = false;
                    }
                }
                if(flag) {
                    break;
                }
                sb.append("(");
                for (int i = 0; i < data.length; i++) {
                    sb.append(("".equals(data[i][j]) ?  null : "'" + data[i][j] + "'") + ",");
                }
                sb.deleteCharAt(sb.length() - 1);
                sb.append("),");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append(";\n\n\n");
        }
    }

    private static String[] mySplit(String str, String separate) {
        List<String> list = new ArrayList<>();
        String temp = str;
        int index = temp.indexOf(separate);
        while(index >= 0) {
            list.add(temp.substring(0, index));
            temp = temp.substring(index + 1);
            index = temp.indexOf(separate);
        }
        list.add(temp);
        String[] result = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }
    public static void dealWithDumpedSqlFile(String fileName) {
        dealWithDumpedSqlFile(fileName, "UTF-8");
    }

    public static void dealWithDumpedSqlFile(String filePath, String charset) {
        String desFilePath = filePath + System.currentTimeMillis();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), charset));
            BufferedWriter bw = new BufferedWriter(new FileWriter(desFilePath))) {
            String line = null;
            int flag = 0;
            StringBuffer sb = new StringBuffer();
            String temp = null;
            while((line = br.readLine()) != null) {
                temp = line.trim().toUpperCase();
                if(flag == 0) {
                    if(temp.startsWith("DROP")) {
                        flag = 1;
                    } else {
                        sb.append(line + "\n");
                    }
                } else if(temp.endsWith(";")) {
                    flag = (flag + 1) % 2;
                }
            }
            bw.write(sb.toString());
            bw.flush();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        File srcFile = new File(filePath);
        srcFile.delete();
        File desFile = new File(desFilePath);
        desFile.renameTo(srcFile);
    }

    public static void main(String[] args) {
        csv2Sql("D:\\work\\项目\\CTS\\数据库\\new\\interface.csv", "D:\\work\\项目\\CTS\\数据库\\new\\interface.sql");
        csv2Sql("D:\\work\\项目\\CTS\\数据库\\new\\roleMenu.csv",
                "D:\\work\\项目\\CTS\\数据库\\new\\roleMenu.sql");
//        dealWithDumpedSqlFile("D:\\work\\项目\\others\\众创平台\\ZCPDev.sql");
    }
}
