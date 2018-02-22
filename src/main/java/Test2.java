import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import java.io.File;

/**
 * description： <br>
 * createTime: 2018/2/211:37 <br>
 *
 * @author zzh
 */
public class Test2 {

    private static final Logger LOGGER = LoggerFactory.getLogger(Test2.class);

    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
//        tomcat.setBaseDir("E:\\IDEA\\SINOPEC-CTS\\CTS\\cts\\target\\cts.war");
        String projectPath=new File("").getAbsolutePath();
        tomcat.setBaseDir(projectPath); //Embeded tomcat存放路径
        tomcat.setPort(8082);
        try {
//            tomcat.addWebapp("cts", "E:\\IDEA\\SINOPEC-CTS\\CTS\\cts\\target\\webapp");//应用存放路径
            tomcat.start();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }

//        tomcat.addServlet("cts", "", new HttpServlet() {
//            @Override
//            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//                PrintWriter out = resp.getWriter();
//                out.write("hello world");
//                out.flush();
//                out.close();
//            }
//        });
    }
}
