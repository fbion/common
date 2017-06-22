//package work.qizhi.tools.script;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import utils.TestUtils;
//import utils.file.DirectoryRecurseOperatorUtil;
//import utils.file.FileOperator;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
///**
// * Created by Administrator on 2016/8/8.
// */
//public class Internationalization {
//    static final Logger logger = LoggerFactory.getLogger(Internationalization.class);
//    public static void views() throws IOException {
//        logger.info("start");
//        Map<String, Map<String, List<List<String>>>> map = new HashMap<>();
//        int count = 0;
//        File config = new File("F:\\idea-projects\\qizhi-cloud\\conf\\messages.zh-CN");
//        try (BufferedReader br = new BufferedReader(new FileReader(config))) {
//            String line;
//            String[] temp;
//            while((line = br.readLine()) != null) {
//                if(line.length() > 3) {
//                    count++;
//                    temp = line.split("=");
//                    String[] temp2 = temp[0].split("\\.");
//                    System.out.println("temp2 : " + Arrays.toString(temp2));
//                    Map<String, List<List<String>>> data = map.get(temp2[0]);
//                    if(data == null) {
//                        data = new HashMap<>();
//                        map.put(temp2[0], data);
//                    }
//                    List<List<String>> list = data.get(temp2[1]);
//                    if(list == null) {
//                        list = new ArrayList<>();
//                        data.put(temp2[1], list);
//                    }
//                    List<String> l = new ArrayList<>();
//                    list.add(l);
//
//                    Collections.addAll(l, temp);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.exit(1);
//        }
//        System.out.println("total line : " + count);
//
//        List<List<String>> globalList = map.get("root").get("global");
//        TestUtils.recordInFile("globalList : " + globalList, "f:\\work\\test.txt");
//        DirectoryRecurseOperatorUtil.recurseDirectory("F:\\idea-projects\\qizhi-cloud\\app\\views", "", (basePath, relativePath, array) -> {
//            TestUtils.recordInFile(basePath + relativePath, "f:\\work\\test.txt");
//            String[] key;
//            String tempKey;
//            if(relativePath.contains("attendant\\attendants") || relativePath.contains("attendant\\editAttendant") || relativePath.contains("guest\\initUser") || relativePath.contains("maintain\\")
//                    || relativePath.contains("repo\\") || relativePath.contains("cloudApiDoc.") || relativePath.contains("developDoc.")
//                    || relativePath.contains("document.") || relativePath.contains("getSdk.") || relativePath.contains("admin\\template")
//                    || relativePath.contains("test.") || relativePath.contains("analyze\\kefuSessionRecords.")
//                    || relativePath.contains("analyze\\sessionRecords.") || relativePath.contains("robot\\robotUI.")) {
//                return false;
//            }
//            if(!relativePath.replaceAll("^\\\\", "").contains("\\")) {
////                System.out.println("relativePath : " + relativePath);
////                System.out.println("relativePath : " + relativePath.replaceAll("^\\\\", ""));
////                System.out.println("relativePath : " + Arrays.deepToString(relativePath.replaceAll("^\\\\", "").split("\\.")));
//                tempKey = "root." + relativePath.replaceAll("^\\\\", "").split("\\.")[0];
//            } else {
////                System.out.println("relativePath : " + relativePath);
//                String[] temp = relativePath.replaceAll("^\\\\", "").split("\\\\");
////                System.out.println("temp : " + Arrays.deepToString(temp));
//                tempKey =temp[0] + "." + temp[1].split("\\.")[0];
//            }
//            key = tempKey.split("\\.");
//            System.out.println("key :" + Arrays.deepToString(key));
//
//            FileOperator.operateFile(basePath + relativePath, (str) -> {
//                if(str.trim().startsWith("//") || str.trim().startsWith("<!--")) {
//                    TestUtils.recordInFile("str : " + str, "f:\\work\\coment.txt");
//                    return str + "\r\n";
//                }
//                TestUtils.recordInFile("src : " + str, "f:\\work\\test.txt");
//                Pattern p1 = Pattern.compile("([\">\\s:'])([^\">\\s:']*[\u4e00-\u9fa5][^\"<\\s']*)([\"<\\s:'])");
//                Pattern p2 = Pattern.compile("([\">\\s:'])([^\">\\s:']*[\u4e00-\u9fa5][^\"<\\s']*)$");
//                Matcher m = null;
//                if(p1.matcher(str).find()) {
//                    m = p1.matcher(str);
//                } else if(p2.matcher(str).find()) {
//                    m = p2.matcher(str);
//                }
//
//                if(m == null) {
//                    return str + "\r\n";
//                }
//
//                List<List<String>> src = new ArrayList<>();
//                src.addAll(globalList);
//                src.addAll(map.get("root").get("main"));
//                Map<String, List<List<String>>> t = map.get(key[0]);
//                if(t.containsKey("common")) {
//                    src.addAll(t.get("common"));
//                }
//
//                if(t.get(key[1]) == null) {
//                    return str + "\r\n";
//                }
//                src.addAll(t.get(key[1]));
//                TestUtils.recordInFile("List : ", "f:\\work\\test.txt");
//                for (List<String> stringList : src) {
//                    TestUtils.recordInFile("\t\t" + stringList.toString(), "f:\\work\\test.txt");
//                }
//                outer: while(m.find()) {
//                    String temp = m.group(2).trim();
//                    TestUtils.recordInFile("\t\t" + temp, "f:\\work\\test.txt");
//                    boolean flag = true;
//                    for (int i = 0; i < 2; i++) {
//                        for (List<String> l : src) {
//                            TestUtils.recordInFile(l.toString(), "f:\\work\\test.txt");
//                            if(flag) {
//                                if(l.get(1).equals(temp)) {
//                                    TestUtils.recordInFile("[" + temp + "] equals : [" + l.get(1) + "]", "f:\\work\\test.txt");
//                                    TestUtils.recordInFile("[" + temp + "] equals : [" + l.get(1) + "]", "f:\\work\\equals.txt");
//                                    if(l.size() < 3) {
//                                        l.add(1 + "");
//                                    } else {
//                                        l.set(2, (Integer.parseInt(l.get(2)) + 1) + "");
//                                    }
//                                    l.add(basePath + relativePath);
//                                    str = str.replaceAll(Pattern.quote(temp), "@messages(\"" + l.get(0) + "\")").replaceAll("(@main\\()\"@(messages\\(\"[^\"]*\"\\))\"", "$1$2");
//                                    continue outer;
//                                }
//                            } else {
//                                if (temp.contains(l.get(1))) {
//                                    logger.info("[" + temp + "] contains [" + l.get(1) + "]");
//                                    TestUtils.recordInFile("[" + temp + "] contains [" + l.get(1) + "]", "f:\\work\\contains.txt");
//                                    continue;
//                                }
//                            }
//                        }
//                        flag = false;
//                    }
//                    TestUtils.recordInFile(basePath + relativePath + "\t\t" + str + "\t\t[" + temp + "]", "f:\\work\\test.txt");
//                    TestUtils.recordInFile(basePath + relativePath + "\t\t" + str + "\t\t[" + temp + "]", "f:\\work\\no.txt");
//                }
//
//                return str + "\r\n";
//            });
//            return false;
//        });
//
//        for (Map.Entry<String, Map<String, List<List<String>>>> stringMapEntry : map.entrySet()) {
//            for (Map.Entry<String, List<List<String>>> stringListEntry : stringMapEntry.getValue().entrySet()) {
//                TestUtils.recordInFile(stringMapEntry.getKey() + "." + stringListEntry.getKey(), "f:\\work\\record.txt");
//                for (List<String> stringList : stringListEntry.getValue()) {
//                    TestUtils.recordInFile("\t\t" + stringList.toString(), "f:\\work\\record.txt");
//                }
//            }
//        }
//    }
//
//    public static void controllers() throws IOException {
//        logger.info("start");
//        Map<String, List<List<String>>> map = new HashMap<>();
//        int count = 0;
//        File config = new File("F:\\idea-projects\\qizhi-cloud\\conf\\messages.zh-CN");
//        Pattern p = Pattern.compile("([^=]*)=(.*)");
//        try (BufferedReader br = new BufferedReader(new FileReader(config))) {
//            String line;
//            String[] temp = new String[2];
//            while((line = br.readLine()) != null) {
//                if(line.startsWith("controllers.")) {
//                    count++;
//                    Matcher m = p.matcher(line);
//                    if(m.find()) {
//                        temp[0] = m.group(1);
//                        temp[1] = m.group(2);
////                        temp = line.split("=");
//                        String[] temp2 = temp[0].split("\\.");
////                    System.out.println("temp2 : " + Arrays.toString(temp2));
//                        List<List<String>> list = map.get(temp2[1]);
//                        if(list == null) {
//                            list = new ArrayList<>();
//                            map.put(temp2[1], list);
//                        }
//                        List<String> l = new ArrayList<>();
//                        list.add(l);
//
//                        Collections.addAll(l, temp);
//                    }
//
//
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.exit(1);
//        }
//        System.out.println("total line : " + count);
//
//        DirectoryRecurseOperatorUtil.recurseDirectory("F:\\idea-projects\\qizhi-cloud\\app\\controllers", "", (basePath, relativePath, array) -> {
//            TestUtils.recordInFile(basePath + relativePath, "f:\\work\\test.txt");
//            String tempKey;
//            if( relativePath.contains("MaintainController") || relativePath.contains("RepoController") || relativePath.contains("ResourceController")
//                    || relativePath.contains("ServiceController") || relativePath.contains("UEditorController") || relativePath.contains("AttendantController")
//                    || relativePath.contains("Authenticator") || relativePath.contains("QADataController") || relativePath.contains("QAServiceController") ) {
//                return false;
//            }
//            String key = relativePath.replaceAll("^\\\\", "").replaceAll("\\.scala", "");
//            System.out.println("key :" +key);
////            List<List<String>> src = new ArrayList<>();
////            src.addAll(map.get("common"));
////
////            if(map.get(key) == null) {
////                return false;
////            }
////            src.addAll(map.get(key));
////            System.out.println("-----------------------------------------------");
////            System.out.println(src);
//
//            FileOperator.operateFile(basePath + relativePath, (str) -> {
//                if(str.trim().startsWith("//") || str.trim().startsWith("<!--")) {
//                    TestUtils.recordInFile("str : " + str, "f:\\work\\coment.txt");
//                    return str + "\r\n";
//                }
//                TestUtils.recordInFile("src : " + str, "f:\\work\\test.txt");
//                Pattern p1 = Pattern.compile("s?\"([^\"]*[\u4e00-\u9fa5]+[^\"]*)\"");
//                Matcher m = p1.matcher(str);
//
//                if(m == null) {
//                    return str + "\r\n";
//                }
//
//                List<List<String>> src = new ArrayList<>();
//                src.addAll(map.get("common"));
//
//                if(map.get(key) == null) {
//                    return str + "\r\n";
//                }
//                src.addAll(map.get(key));
//                TestUtils.recordInFile("List : ", "f:\\work\\test.txt");
//                for (List<String> stringList : src) {
//                    TestUtils.recordInFile("\t\t" + stringList.toString(), "f:\\work\\test.txt");
//                }
//                outer: while(m.find()) {
//                    String replaced = m.group();
//                    String temp = m.group(1);
//                    System.out.println("line : " + str);
//                    System.out.println("replaced : " + replaced);
//                    System.out.println("temp : " + temp);
//                    TestUtils.recordInFile("\t\t" + temp, "f:\\work\\test.txt");
//                    boolean flag = true;
//                    for (int i = 0; i < 2; i++) {
//                        for (List<String> list : src) {
//                            TestUtils.recordInFile(list.toString(), "f:\\work\\test.txt");
//                            if(flag) {
//                                if(list.get(1).equals(temp)) {
//                                    TestUtils.recordInFile("[" + temp + "] equals : [" + list.get(1) + "]", "f:\\work\\test.txt");
//                                    TestUtils.recordInFile("[" + temp + "] equals : [" + list.get(1) + "]", "f:\\work\\equals.txt");
//                                    if(list.size() < 3) {
//                                        list.add(1 + "");
//                                    } else {
//                                        list.set(2, (Integer.parseInt(list.get(2)) + 1) + "");
//                                    }
//                                    list.add(basePath + relativePath);
//                                    str = str.replaceAll(Pattern.quote(replaced), "messagesApi(\"" + list.get(0) + "\")");
//                                    continue outer;
//                                }
//                            } else {
//                                if (temp.contains(list.get(1))) {
//                                    logger.info("[" + temp + "] contains [" + list.get(1) + "]");
//                                    TestUtils.recordInFile("[" + temp + "] contains [" + list.get(1) + "]", "f:\\work\\contains.txt");
//                                    continue;
//                                }
//                            }
//                        }
//                        flag = false;
//                    }
//                    TestUtils.recordInFile(basePath + relativePath + "\t\t" + str + "\t\t[" + temp + "]", "f:\\work\\test.txt");
//                    TestUtils.recordInFile(basePath + relativePath + "\t\t" + str + "\t\t[" + temp + "]", "f:\\work\\no.txt");
//                }
//
//                return str + "\r\n";
//            });
//            return false;
//        });
//
//        System.out.println("-------------------------------------------");
//        for (Map.Entry<String, List<List<String>>> stringMapEntry : map.entrySet()) {
//            TestUtils.recordInFile("controllers." + stringMapEntry.getKey(), "f:\\work\\record.txt");
//            for (List<String> stringList : stringMapEntry.getValue()) {
//                if(stringList.size() < 3) {
//                    TestUtils.recordInFile("\t\t" + stringList, "f:\\work\\noFound.txt");
//                } else {
//                    TestUtils.recordInFile("size:" + stringList.size() + "\t\t" + stringList, "f:\\work\\record.txt");
//                }
//            }
//        }
//    }
//
//    public static void main(String[] args) throws IOException {
//        controllers();
//    }
//}
