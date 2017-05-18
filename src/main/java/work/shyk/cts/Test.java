package work.shyk.cts;

/**
 * 项目名称：zzh<br/>
 * *********************************<br/>
 * <p>类名称：Test1</p>
 * *********************************<br/>
 * <p>类描述：</p>
 * 创建人：zhouzhihui<br/>
 * 创建时间：2017/4/18 14:50<br/>
 * 修改人：周志辉<br/>
 * 修改时间：2017/4/18 14:50<br/>
 * 修改备注：<br/>
 *
 * @version 1.0<br/>
 */

import java.io.IOException;
import java.net.URL;
import java.util.zip.ZipInputStream;

public class Test {
    public static void main(String[] args) {
        String path = "http://localhost:9090/dir/file.zip";path = path.replaceAll("////", "/");// 如果端口号后面的路径中有/就需要转换了，在这里是废话一句
         ZipInputStream inputStream = null;
         try {
             URL url = new URL(path);
             inputStream = new ZipInputStream(url.openStream());// 通过URL拿到输入流，并转换成Zip输入流。装饰就是好啊。。。
             inputStream.getNextEntry();// 这一句很重要。本例中，压缩包中只有一个文件。如果有多个，要多次执行这句话的。
             byte[] b = new byte[1];
             int count = inputStream.read(b);
             while (count != -1) {// 在这里做想做的事情
                  System.out.println(b);count = inputStream.read(b);
             }
         } catch (Exception e) {
             e.printStackTrace();
         } finally {
             try {
                 if (inputStream != null) {
                 inputStream.close();
                 }
             } catch (IOException e) {
                 e.printStackTrace();
             }
         }
    }
}