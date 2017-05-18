package test.tool;

/**
 * Created by Administrator on 2016/3/21.
 */
public class TestPattern {
    public static void main(String[] args) {
//        String str = "123ww1231111111111";
//        Pattern p = Pattern.compile("^([a-zA-Z0-9]){9}(\\1{2}(\\1{3}(\\1{4})?)?)?$");
//        Matcher m = p.matcher(str);
//        if(m.find()) {
//            System.out.println(m.group());
//        }

//        String str1 = "<script type=\"text/javascript\" src=\"http://www.xss001.com/discuz/api/uc.php?time=1463386932&code=745b1lYyeWf0Yd36LToqUDrTYPxTWQ0NXgIWz3l2TEyWmqQV4Sh12dDQcplMat6J7vHJPfwG13JATNf%2F6q7got61uThz%2BGmYYnTzLzjiF9r4prghdLgNVXSmmG7DBOQb7um3k90JZQ85wj%2FsOiiUeN6FPTCN9KrbeZGaj0Sv\" reload=\"1\"></script>";
//        Pattern p1 = Pattern.compile("(?<=src=\")(http://www.xss001.com.*?)(?=\")");
//        Matcher m1 = p1.matcher(str1);
//        if(m1.find()) {
//            System.out.println(m1.group());
//        }

//        String str1 = "11";
//        Pattern p1 = Pattern.compile("^(10)||(\\d?)$");
//        Matcher m1 = p1.matcher(str1);
//        System.out.println(m1.matches());


//        System.out.println("123jasldfuwoie123235+_".replaceAll("(\\d*)", "$1$1"));
//        System.out.println("!@#&(*$&as+-df123jasldfuwoie123235+_1234ewer13".replaceAll("(\\D*\\d+\\D*)(\\d*)(\\D*)(\\.*)", "$1$2$2$3$4"));

//        String str1 = "abaaababbabbbaaba";
//        Pattern p1 = Pattern.compile("([ab])\\1{2}");
//        Matcher m1 = p1.matcher(str1);
//        while(m1.find()) {
//            System.out.println(m1.group());
//        }


//        Pattern p1 = Pattern.compile("(https?:)(//)(\\w+\\.)+(\\w+)(/index\\.jsp)");
//        String[] ss = new String[]{"asdf http://www.baidu.com/index.jsp asdfasdf", "asdf https://www.jasdlkfjlasdu.com/index.jsp asdfasdf"};
//        for (String s : ss) {
//            Matcher m1 = p1.matcher(s);
//            if(m1.find()) {
//                System.out.print(m1.group() + "\t\t");
//                System.out.print(m1.group(1) + "\t\t");
//                System.out.print(m1.group(2) + "\t\t");
//                System.out.print(m1.group(3) + "\t\t");
//                System.out.print(m1.group(4) + "\t\t");
//                System.out.println(m1.group(5) + "\t\t");
//            }
//        }


//        System.out.println("hello hello hello hellohello".matches("(\\w*)(\\s*\\1+)*"));
//        System.out.println("hello hello hello hellohelle".matches("(\\w*)(\\s*\\1+)*"));
//        System.out.println("helle helle helle hellehelle".matches("(\\w*)(\\s*\\1+)*"));
//        System.out.println("world world world world".matches("(\\w*)(\\s*\\1+)*"));

//        System.out.println("http://www.baidu.com".matches("https?://\\w+(\\.\\w+){2,}(/.*)*"));
//        System.out.println("https://api.smartnlp.cn/cloud/robot/55d28d61d3a93df500131c24/answer?q=hi".matches("https?://\\w+(\\.\\w+){2,}(/.*)*"));
//        System.out.println("https://clfoud.smartnlp.cn/favicon.ico".matches("https?://\\w+(\\.\\w+){2,}(/.*)*"));
//        System.out.println("http://127.0.0.1:9000/manager/ka8098o4u90987cv9b".startsWith("http://127.0.0.1:9000/manager/ka8098o4u90987cv9b"));


        String str = "搬家时间\t\t百度糯米\t\t公司\t\t优惠\t\t机票\t\t退款\t\t支付账户\t\t今天\t\t今天晚上\t\t今天可以\t\t过生日\t\t小来的红包\t\t而且那天晚上\t\t我现在的位置\t\t我的具体位置\t\t帮我看看\t\t怎么使用\t\t订一杯咖啡\t\t任何时候都\t\t好像有酒店优惠券\t\t时间限制\t\t害羞\t\t网购\t\t设置\t\t怎样";
        System.out.println(str.split("\\s+").length);
        //charset unicode   UTF-16LE    utf-8
//        try(OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(new File("f:\\test111.txt"),true),"gbk")){
//            out.write(str);
//        }catch (IOException e) {
//
//        }
    }
}
