package test.demo;

import redis.clients.jedis.Jedis;

/**
 * 描述： <br>
 * 创建时间: 2017/6/210:44 <br>
 *
 * @author 周志辉
 */
public class JedisDemo {

    public static void main(String[] args) {
        //创建jedis对象
        Jedis jedis = new Jedis("10.238.225.79");
//        Jedis jedis = new Jedis("192.168.153.128", 6379);
//        jedis.auth("123456");
        System.out.println("Connection to server sucessfully");
        //查看服务是否运行
        System.out.println("Server is running: " + jedis.ping());

        //调用jedis对象的方法，方法名称和redis 的命令一致
        jedis.set("key2", "jedis test2");
        String string = jedis.get("key2");
        System.out.println(string);
    }
}
