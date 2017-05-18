package work.qizhi.tools.script;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/9.
 */
public class Test {
    public static List test() {
        List list = new ArrayList();
        list.add(13);
        return list;
    }
    public static void main(String[] args) {
//        List<Long> list = test();
//        System.out.println(list.get(0));
//        System.out.println(list.get(0).intValue());

//        String str = "cloudApiDoc.scala.html";
//        System.out.println(Arrays.toString(str.split("\\.")));

//        System.out.println(">1".matches(">[^\">]"));
//        String str = "                    <span class=\"caption-subject font-green-sharp bold uppercase\">今日访问走势</span>";
//        Pattern p1 = Pattern.compile("([\">\\s:'])([^\">\\s]*?[\u4e00-\u9fa5][^\"<\\s]*)([\"<\\s'])");
//        Pattern p2 = Pattern.compile("([\">\\s:'])([^\">\\s]*[\u4e00-\u9fa5][^\"<\\s]*)$");
//        Matcher m = p1.matcher(str);
//        if(m.find()) {
//            System.out.println(m.group(2));
//        }
//
//        System.out.println("p1");
//        m = p2.matcher(str);
//        if(m.find()) {
//            System.out.println(m.group(2));
//        }
//        System.out.println("p2");

//        try (BufferedReader br = new BufferedReader(new FileReader(new File("f:\\work\\messages.zh-CN")))) {    //record.txt
//            String line;
//            int count = 0;
//            while((line = br.readLine()) != null) {
//                count++;
//                if(line.length() < 1) {
//                    continue;
//                }
//                if(!line.contains("=")) {
//                    System.out.print(line);
//                    System.out.println("\t\t" + count);
//                }
//            }
////            int count1 = 0;
////            int count2 = 0;
////            Pattern p = Pattern.compile(".*,.*,.*");
////            while((line = br.readLine()) != null) {
////                if(line.matches(".*,.*,.*")) {
////                    count1 ++;
////                    System.out.println(line);
////                } else if(line.matches(".*,.*")) {
////                    count2 ++;
////                }
////            }
////            System.out.println("count1 : " + count1);
////            System.out.println("count2 : " + count2);
//        }catch (Exception e) {
//            e.printStackTrace();
//        }




//        try (BufferedReader br = new BufferedReader(new FileReader(new File("f:\\work\\messages.zh-CN")))) {    //record.txt
//            String line;
//            int count = 0;
//            while((line = br.readLine()) != null) {
//                count++;
//                if(!line.matches(".*=.*[\u4e00-\u9fa5].*")) {
//                    System.out.println(count + " : " + line);
//                }
//            }
//        }catch (Exception e) {
//            e.printStackTrace();
//        }

//        System.out.println("@main(\"@messages(\"".matches("@main\\(\"@messages\\(\""));
//
//        String str = "@main(\"@messages(\"root.internalError.title\")\", false, \"index\", \"\", request, messages){";
//         Pattern p = Pattern.compile("(@main\\()\"@(messages\\(\"[^\"]*\"\\))\"");
//                Matcher m = p.matcher(str);
//                if(m.find()) {
//                    System.out.println(m.group());
//                } else {
//                    System.out.println("false");
//                }
//        System.out.println(str.replaceAll("(@main\\()\"@(messages\\(\"[^\"]*\"\\))\"", "$1$2"));


    }
}
