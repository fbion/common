package Script;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
        try(BufferedReader br = new BufferedReader(new FileReader(fileName));
            BufferedWriter bw = new BufferedWriter(new FileWriter(desFileName))) {
            String line = null;
            //状态码，0表示未读到表名，1表示已读到表名，2
            StringBuffer sb = new StringBuffer();
            int status = 0;
            Pattern p = Pattern.compile("([^\\[]?)\\[(.*)\\]");
            String tableName = null;
            Matcher m = null;
            while((line = br.readLine()) != null) {
                if("".equals(line)) {
                    if(status != 0) {
                        status = 0;
                        bw.write(sb.toString());
                        bw.flush();
                        sb.deleteCharAt(sb.capacity());
                        sb.append(";");
                        sb.delete(0, sb.capacity());
                    }
                    continue;
                }
                if(status == 0) {
                    m = p.matcher(line);
                    if(m.find()) {
                        tableName = m.group(2);
                        sb.append("INSERT INTO `" + tableName + "` ");
                    }
                    status = 1;
                } else if (status  == 1) {
                    sb.append("(" + line + ") values");
                    status = 2;
                } else if (status == 2) {
                    String[] values = line.split(",");
                    sb.append("(");
                    for (String value : values) {
                        sb.append("'" + value + "'");
                    }
                    sb.append("),");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Pattern p = Pattern.compile("([^\\[]?)\\[(.*)\\]");
        String[] strs = {"账户信息表[T_AccountInformation]", "云资源种类表[T_CloudResourceType]", "云资源信息表[T_CloudResourceInformation]"};
        String tableName = null;
        Matcher m = null;
        for (String str : strs) {
            m = p.matcher(str);
            if(m.find()) {
                tableName = m.group(2);
                System.out.println(tableName);
            }
        }
    }
}
