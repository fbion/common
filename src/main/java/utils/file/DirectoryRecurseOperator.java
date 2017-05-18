package utils.file;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

/**
 * Created by Administrator on 2016/6/8.
 */
public class DirectoryRecurseOperator {
    public static boolean recurseDirectory(String basePath, String relativePath, IOperator operator, String ...otherString) throws IOException {
        String fullPath = basePath + relativePath;
//        System.out.println("In recurseDirectory : " + fullPath);
        File file = new File(fullPath);
        if(!file.exists()) {
            System.exit(1);
        }
        if(file.isDirectory()) {
//            System.out.println("In recurseDirectory : " + fullPath);
            String[] fileNames = file.list();
//            System.out.println(Arrays.toString(fileNames));
            for (int i = 0; i < fileNames.length; i++) {
                if(recurseDirectory(basePath, relativePath + "\\" + fileNames[i], operator, otherString)) {
                    return true;
                }
            }
        } else {
            if(operator.operator(basePath, relativePath, otherString)) {
                return false;
            }
        }
        return false;
    }

    public static boolean operator(String basePath, String relativePath) {
//        System.out.println("In operator : " + basePath + relativePath);
        if(!(basePath + relativePath).contains(".htm")) {
            return false;
        }
        try(RandomAccessFile rf = new RandomAccessFile(basePath + relativePath, "rw");) {
            long length = rf.length();
            String start = "<!-- " + relativePath + " :Start-->\r\n";
            String end = "\r\n<!-- " + relativePath + " :End-->";
            byte[] bs = start.getBytes();
            byte[] be = end.getBytes();
            long bslength = bs.length;
            long belength = be.length;
            long totalLength = length + bslength + belength;
            rf.setLength(totalLength);
            rf.seek(length + bslength);
            rf.write(be);
            for(long i = length + bslength - 1; i > bslength - 1; i--){
                rf.seek(i - bs.length);
                byte temp = rf.readByte();
                rf.seek(i);
                rf.writeByte(temp);
            }
            rf.seek(0);
            rf.write(bs);
        }catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    static int count = 0;
    public static void main(String[] args) throws IOException {
//        recurseDirectory("F:\\html", "\\template", new InsertString());//后面参数最前面要加\\F:\html\template
//        recurseDirectory("F:\\mytest", "");//
//        File file = new File("F:\\idea-projects\\qizhi-cloud\\conf\\routes");
//        Map<String, String> map = new HashMap<>();
//        String line;
//        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
//            while((line = br.readLine()) != null) {
//                if(!line.startsWith("#") && !line.matches("\\s*")) {
//                    String[] temp = line.split("\\s{2,}");
//                    if(temp.length > 2) {
//                        map.put(temp[1], temp[2]);
//                    } else {
//                        System.out.println("array length lower than 3.");
//                        System.out.println("-------------" + Arrays.deepToString(temp));
//                    }
//                }
//            }
////            System.out.println(map);
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//        for (Map.Entry<String, String> entry : map.entrySet()) {
//            System.out.println(entry.getKey() + " : " + entry.getValue());
//            recurseDirectory("F:\\idea-projects\\qizhi-cloud\\app\\controllers", "", new FindString(), entry.getValue());//后面参数最前面要加\\,空串不需要
//            recurseDirectory("F:\\idea-projects\\qizhi-cloud\\app\\views", "", new FindString(), entry.getKey());
//            System.out.println();
//        }

//        //获取views下面所有页面的名，然后遍历controller看文件是否包含此页面名称
//        List<String> list = new ArrayList<>();
//        recurseDirectory("F:\\idea-projects\\qizhi-cloud\\app\\views", "", (basePath, relativePath,array)->{
//            if((basePath + relativePath).endsWith(".scala.html")) {
//                list.add((basePath + relativePath).replaceAll("(.*\\\\views)(.*)","$2").replaceAll(".scala.html", "").replaceAll("\\\\", "."));
//            }
//            return false;
//        });
//        List<String> list1 = list.stream().map((s) ->  "views.html" + s).collect(Collectors.toList());
//        System.out.println(list.size());
//        System.out.println(list);
//        System.out.println(list1.size());
//        System.out.println(list1);
//        for (String s : list1) {
//            if(!recurseDirectory("F:\\idea-projects\\qizhi-cloud\\app\\controllers", "", new FindString(), s)) {
//                System.out.println(s);
//            }
//        }

//        Set<String> set = new HashSet<>();
//        int count = 0;
//        File config = new File("F:\\idea-projects\\qizhi-cloud\\conf\\messages.zh-CN");
//        try (BufferedReader br = new BufferedReader(new FileReader(config))) {
//            String line;
//            String[] temp;
//            while((line = br.readLine()) != null) {
//                if(line.length() > 3) {
//                    count++;
//                    set.add(line.replaceAll("([^\\.]*\\.[^\\.]*)(\\.)(.*)", "$1"));
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.exit(1);
//        }
//        System.out.println(set);
//        System.out.println(set.size());
//        System.out.println("total line : " + count);
//
//        recurseDirectory("F:\\idea-projects\\qizhi-cloud\\app\\views", "", (basePath, relativePath,otherString)->{
//            if(relativePath.contains("attendant\\attendants") || relativePath.contains("attendant\\editAttendant") || relativePath.contains("guest\\initUser") || relativePath.contains("maintain\\")
//                    || relativePath.contains("repo\\") || relativePath.contains("cloudApiDoc.") || relativePath.contains("developDoc.")
//                    || relativePath.contains("document.") || relativePath.contains("getSdk.") || relativePath.contains("admin\\template") || relativePath.contains("test.")) {
//                return false;
//            }
//            String[] temp =  relativePath.split("\\\\");
////            System.out.println(temp.length + " : " + Arrays.deepToString(temp));
//            String element = null;
//            if(temp.length == 2) {
//                element = "root." + temp[1].split("\\.")[0];
//            } else if(temp.length == 3){
//                element = temp[1] + "." + temp[2].split("\\.")[0];
//            } else {
//                System.out.println("------------------------");
//            }
//            if(!set.contains(element)) {
//                System.out.println("Not contains " + element);
//            }
//            return false;
//        });



        //获取views下面所有页面的名，然后遍历controller看文件是否包含此页面名称
//        recurseDirectory("F:\\idea-projects\\qizhi-cloud\\app\\views", "", new FindString(), "root.main.menu.management.index", "admin.main.menu.management.index", "toapp.toApp.linkto.index", "root.index.title");

        //获取views下面所有页面的名，然后遍历controller看文件是否包含此页面名称
//        recurseDirectory("F:\\idea-projects\\qizhi-cloud\\app\\controllers", "", (basePath, relativePath, otherString) -> {
//            if( relativePath.contains("MaintainController") || relativePath.contains("RepoController") || relativePath.contains("ResourceController")
//                    || relativePath.contains("ServiceController") || relativePath.contains("UEditorController") || relativePath.contains("AttendantController")
//                    || relativePath.contains("Authenticator") || relativePath.contains("QADataController") || relativePath.contains("QAServiceController") ) {
//                return false;
//            }
//            Pattern p = Pattern.compile(otherString[0]);
//            int count = 0;
//            TestUtils.recordInFile(basePath + relativePath, "f:\\work\\w.txt");
//            try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(basePath + relativePath))))) {
//                String line = null;
//                while((line = br.readLine()) != null) {
//                    Matcher m = p.matcher(line);
//                    while(m.find()) {
//                        TestUtils.recordInFile("println(" + m.group() + ")", "f:\\work\\v.txt");
//                        TestUtils.recordInFile("\t\t" + m.group(), "f:\\work\\w.txt");
//                        count++;
//                    }
//                }
//                TestUtils.recordInFile("\ttotal:" + count, "f:\\work\\w.txt");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return false;
//        }, "messagesApi\\(\"[^\"]*\"[^\\)]*\\)");

        //controllers.UserController.listUsers.update.user.success
//        recurseDirectory("F:\\idea-projects\\qizhi-cloud\\app\\views", "", new FindString(), "/cloud/qa/edit/");
        recurseDirectory("F:\\idea-projects\\qizhi-cloud\\app", "", new FindString(), ".copy(");

//        recurseDirectory("F:\\idea-projects\\qizhi-cloud\\app", "", new FindString(), "views.html.robot.editQA");
//        recurseDirectory("F:\\idea-projects\\qizhi-weixin\\app", "", new FindString(), "HttpUtils.getRequestBody");
//        recurseDirectory("F:\\idea-projects\\corpus-tool\\app\\views", "", new FindString(), "category.js");



//        System.out.println("                        @messages(\"robot.knowledgePoints.select.category\") </a>".contains("robot.knowledgePoints.select.category"));
    }
}

class InsertString implements IOperator{
    @Override
    public boolean operator(String basePath, String relativePath, String... otherString) {
//        System.out.println("In operator : " + basePath + relativePath);
        if(!(basePath + relativePath).contains(".htm")) {
            return false;
        }
        try(RandomAccessFile rf = new RandomAccessFile(basePath + relativePath, "rw");) {
            long length = rf.length();
            String start = "<!-- " + relativePath + " :Start-->\r\n";
            String end = "\r\n<!-- " + relativePath + " :End-->";
            byte[] bs = start.getBytes();
            byte[] be = end.getBytes();
            long bslength = bs.length;
            long belength = be.length;
            long totalLength = length + bslength + belength;
            rf.setLength(totalLength);
            rf.seek(length + bslength);
            rf.write(be);
            for(long i = length + bslength - 1; i > bslength - 1; i--){
                rf.seek(i - bs.length);
                byte temp = rf.readByte();
                rf.seek(i);
                rf.writeByte(temp);
            }
            rf.seek(0);
            rf.write(bs);
        }catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}

class FindString implements IOperator{
    @Override
    public boolean operator(String basePath, String relativePath, String... otherString) {
//        System.out.println("\t\tIn operator : " + basePath + relativePath);
        if(relativePath.contains("views\\")) {
//            System.out.println("false");
            return false;
        }
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(basePath + relativePath))))) {
            String line = null;
            while((line = br.readLine()) != null) {
                for(String s : otherString) {
                    if(!s.trim().startsWith("//") && line.contains(s)) {
                        System.out.println(String.format("%-30s",s) + basePath + relativePath);
                        return true;
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}