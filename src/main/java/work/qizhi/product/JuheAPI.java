package work.qizhi.product;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.ning.http.client.*;
import org.jsoup.Jsoup;
import play.libs.Json;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;

/**
 * Created by Administrator on 2016/5/25.
 */
public class JuheAPI {
    public static AsyncHttpClient client = new AsyncHttpClient();

//    public static CloseableHttpClient httpClient = HttpClients.createDefault();
//
//    {
//        httpClient = new DefaultHttpClient();
//    }

    /**
     * 调用天气API的key
     */
    static String weatherKey = "8fa034337310fa6287f003f1640a96bc";

    /**
     * 调用新闻API的key
     */
    static String newsKey = "e3d314858b3a94c3c6eb9d5fe1cf6760";

    /**
     * 调用万年历API的key
     */
    static String calendarKey = "f4d35ec7b3d309645d2b016f78b53e69";

    /**
     * 调用笑话API的key
     */
    static String jokeKey = "143f912394283004a90a0da1402be872";

    /**
     * 调用查询北京时间API的key
     */
    static String timeKey = "be86c23da47b4afe8ae44c72a7a7176c";

    /**
     * 调用查询手机号API的key
     */
    static String phoneKey = "82d05698709c5054e5c3bdfcaf62e4a0";

    /**
     * 根据指定城市查询当天天气
     * @param city
     * @return
     */
    public static ListenableFuture<String> weather(String city) {

        String weatherAPI = "http://op.juhe.cn/onebox/weather/query?cityname=" + URLEncoder.encode(city) + "&dtype=json&key=" + weatherKey;//"http://api.k780.com:88/?app=life.time&appkey=19531&sign=d58d278159298a3b3f4422455528b158&format=json";
        return client.prepareGet(weatherAPI).execute(new AsyncCompletionHandler<String> (){
            @Override
            public String onCompleted(Response resp) throws Exception {
                String responseBody = resp.getResponseBody();
                String result = "暂不支持该城市";
                JsonNode jsonNode = Json.parse(responseBody);
                StringBuffer weather = new StringBuffer(city);
                if(jsonNode.get("error_code").asInt() == 0) {
                    if(responseBody.contains("result") && responseBody.contains("data") && jsonNode.get("result").get("data") != null) {

                        for (JsonNode node : ((ArrayNode) jsonNode.get("result").get("data").withArray("weather"))) {
                            weather.append("\t\t" + node.get("date").asText() + " 星期" + node.get("week").asText() + " 农历：" + node.get("nongli").asText() + "\n\t\t");
                            node = node.get("info");
                            ArrayNode dayNode = (ArrayNode)node.withArray("day");
                            String dayWeather = dayNode.get(1).asText();
                            ArrayNode nightNode = (ArrayNode)node.withArray("night");
                            String nightWeather = dayNode.get(1).asText();

                            if(dayWeather != null && dayWeather.equals(nightWeather)) {
                                weather.append(dayWeather);
                            } else {
                                weather.append(dayWeather + " 转 " + nightWeather);
                            }
                            weather.append(" 温度").append(nightNode.get(2).asText()).append("-").append(dayNode.get(2).asText()).append("℃ ").append(dayNode.get(3).asText()).append(" ").append(dayNode.get(4).asText()).append("\n");
                        }
                        result = weather.toString();
                        result = result.replaceAll(city + "\t", city);
//                        System.out.println(result);
                    }
                }
                return result;
            }
        });
    }

    /**
     * 根据指定关键字查询相关新闻
     * @return
     */
    public static ListenableFuture<String> news(String keyWord) {
        String newsAPI = "http://op.juhe.cn/onebox/news/query?key=" + newsKey + "&dtype=json&q="  + URLEncoder.encode(keyWord);
        return client.prepareGet(newsAPI).execute(new AsyncCompletionHandler<String> (){
            @Override
            public String onCompleted(Response resp) throws Exception {
                String responseBody = resp.getResponseBody();
                String result = "查询不到相关的新闻";
                JsonNode jsonNode = Json.parse(responseBody);
                if(jsonNode.get("error_code").asInt() == 0) {
                    result = "";
                    for (JsonNode node : jsonNode.get("result")) {
                        result += node.get("title").asText() + "\n" + Jsoup.parse(node.get("content").asText()).text() + "\n\n";
                    }
                    System.out.println(result);
                }
                return result;
            }
        });
    }

    /**
     * 根据指定日期查询万年历
     * @param dateString
     * @return
     */
    public static ListenableFuture<String> calendar(String dateString) {
        String calendarAPI = "http://japi.juhe.cn/calendar/day?date=" + dateString + "&dtype=json&key=" + calendarKey;
        return client.prepareGet(calendarAPI).execute(new AsyncCompletionHandler<String> (){
            @Override
            public String onCompleted(Response resp) throws Exception {
                String responseBody = resp.getResponseBody();
                String result = "参数格式错误,正确格式为：YYYY-MM-DD";
                JsonNode temp = Json.parse(responseBody);
                if(responseBody.contains("error_code") && temp.get("error_code").asInt() == 0) {
                    JsonNode jsonNode = temp.get("result").get("data");
                    result = jsonNode.get("date").asText() + " " + jsonNode.get("weekday").asText() + (responseBody.contains("holiday") ? " 节日： " + jsonNode.get("holiday").asText() : "") + (responseBody.contains("desc")? " 放假：" + jsonNode.get("desc").asText() + " " : "") + "\n农历:" + jsonNode.get("lunar").asText();
                }
                return result;
            }
        });
    }

    /**
     * 查询今天日期
     * @return
     */
    public static ListenableFuture<String> today() {
        return calendar(new SimpleDateFormat("YYYY-M-d").format(new Date()));
    }

    /**
     * 按给定手机号前7-9位查询手机归属地
     * @param phoneNo
     * @return
     */
    public static ListenableFuture<String> phone(String phoneNo) {
        String phoneAPI = "http://apis.juhe.cn/mobile/get?key=" + phoneKey + "&dtype=json&phone=" + phoneNo;
        return client.prepareGet(phoneAPI).execute(new AsyncCompletionHandler<String> (){
            @Override
            public String onCompleted(Response resp) throws Exception {
                String responseBody = resp.getResponseBody();
                String result = "手机号码格式有误";
                JsonNode temp = Json.parse(responseBody);
                if(temp.get("error_code").asInt() == 0) {
                    JsonNode jsonNode= temp.get("result");
                    result = jsonNode.get("company").asText() + " " + jsonNode.get("card").asText() + " " + jsonNode.get("province").asText() + "省 " + jsonNode.get("city").asText() + "市 区号：" + jsonNode.get("areacode").asText() + " 邮编：" + jsonNode.get("zip").asText();
                }
                return result;
            }
        });
    }

    /**
     * 随机返回一个笑话
     * @return
     */
    public static ListenableFuture<String> joke() {
        String pageNow = "" + ((int)(Math.random() * 100000));
        String jokeAPI = "http://japi.juhe.cn/joke/content/list.from?key=" + jokeKey + "&page=" + pageNow + "&pagesize=1&sort=desc&time=" + (System.currentTimeMillis()/1000);
        return client.prepareGet(jokeAPI).execute(new AsyncCompletionHandler<String> (){
            @Override
            public String onCompleted(Response resp) throws Exception {
                String responseBody = resp.getResponseBody();
                String result = "未找到";
                JsonNode temp = Json.parse(responseBody);
                if(temp.get("error_code").asInt() == 0) {
                    if(!"null".equals(temp.get("result").get("data").asText())) {
                        Iterator iterator= temp.get("result").get("data").elements();
                        if(iterator.hasNext()) {
                            result = Json.parse(iterator.next().toString()).get("content").asText();
                        }
                    }
                }
                return result;
            }
        });
    }

    /**
     * 查询当前北京时间
     * @return
     */
    public static ListenableFuture<String> pktime() {
        String timeAPI = "http://api.avatardata.cn/BeijingTime/LookUp?key=" + timeKey + "&dtype=JSON";
        return client.prepareGet(timeAPI).execute(new AsyncCompletionHandler<String> (){
            @Override
            public String onCompleted(Response resp) throws Exception {
                String responseBody = resp.getResponseBody();
                String result = "查询失败";
                JsonNode temp = Json.parse(responseBody);
                if(temp.get("error_code").asInt() == 0) {
                    JsonNode jsonNode= temp.get("result");
                    result = jsonNode.get("stime").asText();
                    System.out.println(new Date(Long.parseLong(result) * 1000));
                }
                return "北京时间：" + new SimpleDateFormat("yyyy MM-dd HH:mm:ss").format(new Date(Long.parseLong(result) * 1000));
            }
        });
    }

    //聚合数据
    public static void testJuhe() throws ExecutionException, InterruptedException, IOException {
        String url = "http://m.weather.com.cn/data/101010100.html";
        String timeAPI = "http://api.k780.com:88/?app=weather.future&weaid=399&&appkey=19531&sign=d58d278159298a3b3f4422455528b158&format=json";

        String trainKey = "30297aa6004228dd8490affdb7c61478";
        String trainNumber = "D3010";
        String trainAPI = "http://op.juhe.cn/onebox/train/query?key=" + trainKey + "&dtype=json&train="  + URLEncoder.encode(trainNumber);
//        System.out.println(client.prepareGet(trainAPI).execute().get().getResponseBody());

        String idiomKey = "0cacac737aeb84b43bcfd47188909c76";
        String idiomWord = "十全十美";
        String idiomAPI = "http://v.juhe.cn/chengyu/query?key=" + idiomKey + "&dtype=json&word=" + URLEncoder.encode(idiomWord);
//        String response = client.prepareGet(idiomAPI).execute().get().getResponseBody();
//        System.out.println(response);
//        JsonNode temp = Json.parse(response);
//        if(temp.get("error_code").asInt() == 0) {
//            System.out.println(temp);
//        }

        String identifyCardKey = "f85dd383dcb72772154b5ed98952b784";
        String identifyCardNo = "230281198407260233";
        String identifyCardAPI = "http://apis.juhe.cn/idcard/index?key=" + identifyCardKey + "&dtype=json&cardno=" + identifyCardNo;
//        String response = client.prepareGet(identifyCardAPI).execute().get().getResponseBody();
//        System.out.println(response);
//        JsonNode temp = Json.parse(response);
//        if(temp.get("error_code").asInt() == 0) {
//            JsonNode jsonNode= temp.get("result");
//            String valid = jsonNode.get("verify").asText();
////            if(!"该身份证号校验位不正确".equals(valid)) {
////            }
//            String result = jsonNode.get("area").asText() + " , " + jsonNode.get("sex").asText() +  " , " + jsonNode.get("birthday").asText();
//            System.out.println(result);
//        }

//        String startStation = "南京";
//        String endStation = "北京";
//        String date = "2016-06-05";
//        String trainURL1 = "http://touch.qunar.com/h5/train/trainList";
//        String trainURL = "http://touch.qunar.com/h5/train/trainList?startStation=" + URLEncoder.encode(startStation, "UTF-8") + "&endStation=" + URLEncoder.encode(endStation, "UTF-8") + "&searchType=stasta&date=" + URLEncoder.encode(date, "UTF-8") + "&sort=7&filterTrainType=1&filterTrainType=2&filterTrainType=3&filterTrainType=4&filterTrainType=5&filterTrainType=6&filterTrainType=7&filterDeptTimeRange=1&filterDeptTimeRange=2&filterDeptTimeRange=3&filterDeptTimeRange=4";
//        System.out.println(trainURL);
//        String response1 = client.prepareGet(trainURL1).execute().get().getResponseBody();
//        System.out.println(response1);
//        String response = client.prepareGet(trainURL).execute().get().getResponseBody();
//        System.out.println(response);
//        System.out.println("Over");
    }

    /**
     * 根据快递名和单号查询快递信息
     * @param express
     * @param postId
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     * @throws IOException
     */
    public static ListenableFuture<String> queryExpress(Express express, String postId) throws ExecutionException, InterruptedException, IOException {

        String id = "1";
        String valicode = "";
        String temp = Math.random() + "";
        String pageNow = "" + ((int)(Math.random() * 100000));
        String kdAPI = "http://www.kuaidi100.com/query?type=" + express.getSimpleName() + "&postid=" + postId + "&id=" + id + "&valicode=" + valicode + "&temp=" + temp;
        return client.prepareGet(kdAPI).execute(new AsyncCompletionHandler<String> (){
            @Override
            public String onCompleted(Response resp) throws Exception {
                String responseBody = resp.getResponseBody();
                String result = null;
                JsonNode tmp = Json.parse(responseBody);
                if(tmp.get("status").asInt() == 200) {
                    StringBuffer sb = new StringBuffer(express.getCompanyName() + "\n快递单号：" + postId + "\n");
                    ArrayNode array = (ArrayNode)tmp.withArray("data");
                    for (int i = 0; i < array.size(); i++) {
                        JsonNode node = array.get(i);
                        sb.append(i + ".  更新时间：" + node.get("ftime").asText() + (node.toString().contains("location") ? node.get("location") : "")).append("信息：" + node.get("context").asText() + "\n");
                    }
                    result = sb.toString();
                } else {
                    result = tmp.get("message").asText();
                }
                return result;
            }
        });
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException, URISyntaxException {
        //查询订单
//        System.out.println(queryExpress(Express.YUANTONG, "881443775034378914"));
//        System.out.println(queryExpress(Express.valueOf("yuantong".toUpperCase()), "881443775034378914").get());
    }
}
