package utils;

import com.alibaba.fastjson.JSON;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * 描述： <br>
 * 创建时间: 2017/5/1916:25 <br>
 *
 * @author 周志辉
 */
public class EncodeTest {

    static Charset[] charsets = new Charset[5];
    static String[] charsetNames = {"UTF-8", "GBK", "ISO-8859-1", "GB2312", "UTF-16"};
    static {
        for (int i = 0; i < charsetNames.length; i++) {
            charsets[i] = Charset.forName(charsetNames[i]);
        }
    }
    public static void main(String[] args) throws UnsupportedEncodingException {
//        test1();
        Object obj = JSON.parse("{\"total\":12,\"totalSize\":16384,\"freeSize\":6144,\"usedSize\":10240,\"rows\":[{\"sysStorageId\":\"c688bb4d3c0d471ab1030241d16cf39d\",\"sysId\":\"sysId\",\"volName\":\"eewew\",\"state\":0,\"storageAddr\":null,\"storagePath\":null,\"size\":512,\"instanceId\":\"\",\"remark\":null,\"deleteFlag\":0,\"createUserId\":\"cds009\",\"createUserAccount\":\"jing.lv\",\"createUserName\":\"吕晶\",\"createTime\":1526352351000,\"updateUserId\":\"cds009\",\"updateUserAccount\":\"jing.lv\",\"updateUserName\":\"吕晶\",\"updateTime\":1526355647000,\"appName\":null,\"appId\":null,\"instanceType\":null,\"type\":1,\"dockerClusterId\":\"f93c4a03cb7a42c2a63f163bb4ab6e23\",\"dockerClusterName\":null,\"regionCode\":null,\"dockerClusterType\":null},{\"sysStorageId\":\"b41cabbcd4ca4f3e9c19890390913e80\",\"sysId\":\"sysId\",\"volName\":\"xsss\",\"state\":0,\"storageAddr\":null,\"storagePath\":null,\"size\":4608,\"instanceId\":null,\"remark\":null,\"deleteFlag\":0,\"createUserId\":\"cds009\",\"createUserAccount\":\"jing.lv\",\"createUserName\":\"吕晶\",\"createTime\":1524187760000,\"updateUserId\":\"cds009\",\"updateUserAccount\":\"jing.lv\",\"updateUserName\":\"吕晶\",\"updateTime\":1524542426000,\"appName\":null,\"appId\":null,\"instanceType\":null,\"type\":1,\"dockerClusterId\":\"fadb567a463b40d5b53c66bc95a3e694\",\"dockerClusterName\":\"cluster-test\",\"regionCode\":null,\"dockerClusterType\":null},{\"sysStorageId\":\"51f55e768e684689a8cff2e8f5f0059a\",\"sysId\":\"sysId\",\"volName\":\"0408nfs\",\"state\":0,\"storageAddr\":null,\"storagePath\":null,\"size\":512,\"instanceId\":null,\"remark\":null,\"deleteFlag\":0,\"createUserId\":\"cds666\",\"createUserAccount\":\"haocheng.jiang\",\"createUserName\":\"姜浩诚\",\"createTime\":1523330508000,\"updateUserId\":null,\"updateUserAccount\":null,\"updateUserName\":null,\"updateTime\":null,\"appName\":null,\"appId\":null,\"instanceType\":null,\"type\":1,\"dockerClusterId\":\"a383affc095c47f8a64117b6ef89558c\",\"dockerClusterName\":null,\"regionCode\":null,\"dockerClusterType\":null},{\"sysStorageId\":\"6a0f2f4bbcb0483b94f4c13c5301aed5\",\"sysId\":\"sysId\",\"volName\":\"yj2\",\"state\":0,\"storageAddr\":null,\"storagePath\":null,\"size\":512,\"instanceId\":null,\"remark\":null,\"deleteFlag\":0,\"createUserId\":\"cds777\",\"createUserAccount\":\"yujun\",\"createUserName\":\"於军\",\"createTime\":1519293055000,\"updateUserId\":null,\"updateUserAccount\":null,\"updateUserName\":null,\"updateTime\":null,\"appName\":null,\"appId\":null,\"instanceType\":null,\"type\":1,\"dockerClusterId\":\"ca24e1b33a894792884039f842c29180\",\"dockerClusterName\":null,\"regionCode\":null,\"dockerClusterType\":null},{\"sysStorageId\":\"94d55dfd96ab4384b2824f8b799ebe51\",\"sysId\":\"sysId\",\"volName\":\"yj\",\"state\":1,\"storageAddr\":null,\"storagePath\":null,\"size\":512,\"instanceId\":\"94dbdbdd97ff4efb95a1b49200574ce0\",\"remark\":null,\"deleteFlag\":0,\"createUserId\":\"cds777\",\"createUserAccount\":\"yujun\",\"createUserName\":\"於军\",\"createTime\":1519289608000,\"updateUserId\":\"91a369befb7f472fa6053715e7ac47dc\",\"updateUserAccount\":\"pengpeng.zhu\",\"updateUserName\":\"朱鹏鹏\",\"updateTime\":1519292293000,\"appName\":null,\"appId\":null,\"instanceType\":null,\"type\":1,\"dockerClusterId\":\"c8fb9389df864961b796f7826687be9d\",\"dockerClusterName\":null,\"regionCode\":null,\"dockerClusterType\":null},{\"sysStorageId\":\"312d248f2d254102ac15c978f1f98088\",\"sysId\":\"sysId\",\"volName\":\"lvjingjing\",\"state\":0,\"storageAddr\":\"10.248.27.50:24007&&10.248.27.51:24007\",\"storagePath\":\"PTY/lvjingjing\",\"size\":512,\"instanceId\":\"\",\"remark\":null,\"deleteFlag\":0,\"createUserId\":\"cds009\",\"createUserAccount\":\"jing.lv\",\"createUserName\":\"吕晶\",\"createTime\":1515655676000,\"updateUserId\":\"cds009\",\"updateUserAccount\":\"jing.lv\",\"updateUserName\":\"吕晶\",\"updateTime\":1515663554000,\"appName\":null,\"appId\":null,\"instanceType\":null,\"type\":0,\"dockerClusterId\":\"fadb567a463b40d5b53c66bc95a3e694\",\"dockerClusterName\":\"cluster-test\",\"regionCode\":null,\"dockerClusterType\":null},{\"sysStorageId\":\"62b1aaf3ff7e42a6a4f4b441e88c895b\",\"sysId\":\"sysId\",\"volName\":\"jingjingsuccess\",\"state\":0,\"storageAddr\":null,\"storagePath\":null,\"size\":512,\"instanceId\":\"\",\"remark\":null,\"deleteFlag\":0,\"createUserId\":\"8e8444253a394d109411f6a91dd8b0a9\",\"createUserAccount\":\"tianhao\",\"createUserName\":\"田浩\",\"createTime\":1515554581000,\"updateUserId\":\"cds009\",\"updateUserAccount\":\"jing.lv\",\"updateUserName\":\"吕晶\",\"updateTime\":1515577806000,\"appName\":null,\"appId\":null,\"instanceType\":null,\"type\":1,\"dockerClusterId\":\"fadb567a463b40d5b53c66bc95a3e694\",\"dockerClusterName\":\"cluster-test\",\"regionCode\":null,\"dockerClusterType\":null},{\"sysStorageId\":\"4154fa6994204076a099c7b765a0d357\",\"sysId\":\"sysId\",\"volName\":\"sss\",\"state\":0,\"storageAddr\":null,\"storagePath\":null,\"size\":512,\"instanceId\":\"\",\"remark\":null,\"deleteFlag\":0,\"createUserId\":\"cds011\",\"createUserAccount\":\"pengpeng.zhu\",\"createUserName\":\"朱鹏鹏\",\"createTime\":1514363197000,\"updateUserId\":\"cds011\",\"updateUserAccount\":\"pengpeng.zhu\",\"updateUserName\":\"朱鹏鹏\",\"updateTime\":1514364410000,\"appName\":null,\"appId\":null,\"instanceType\":null,\"type\":1,\"dockerClusterId\":\"3e1714710b2f41c795f82018866cdcdf\",\"dockerClusterName\":null,\"regionCode\":null,\"dockerClusterType\":null},{\"sysStorageId\":\"ae0c4924998745e9b2d151170461318a\",\"sysId\":\"sysId\",\"volName\":\"tttt\",\"state\":0,\"storageAddr\":null,\"storagePath\":null,\"size\":512,\"instanceId\":\"\",\"remark\":null,\"deleteFlag\":0,\"createUserId\":\"cds011\",\"createUserAccount\":\"pengpeng.zhu\",\"createUserName\":\"朱鹏鹏\",\"createTime\":1514345503000,\"updateUserId\":\"cds011\",\"updateUserAccount\":\"pengpeng.zhu\",\"updateUserName\":\"朱鹏鹏\",\"updateTime\":1514364062000,\"appName\":null,\"appId\":null,\"instanceType\":null,\"type\":1,\"dockerClusterId\":\"d8d8059e2dc44cb78b1d6dbeb3898c21\",\"dockerClusterName\":null,\"regionCode\":null,\"dockerClusterType\":null},{\"sysStorageId\":\"a845aa79588848c7a86916cfe2f3a3c3\",\"sysId\":\"sysId\",\"volName\":\"jingwang\",\"state\":0,\"storageAddr\":null,\"storagePath\":null,\"size\":512,\"instanceId\":\"\",\"remark\":null,\"deleteFlag\":0,\"createUserId\":\"8e8444253a394d109411f6a91dd8b0a9\",\"createUserAccount\":\"tianhao\",\"createUserName\":\"田浩\",\"createTime\":1514274054000,\"updateUserId\":\"cds005\",\"updateUserAccount\":\"xuan.bi\",\"updateUserName\":\"毕轩\",\"updateTime\":1515380783000,\"appName\":null,\"appId\":null,\"instanceType\":null,\"type\":1,\"dockerClusterId\":\"3e1714710b2f41c795f82018866cdcdf\",\"dockerClusterName\":null,\"regionCode\":null,\"dockerClusterType\":null}]}");
        System.out.println(obj);
    }
    public static void test1() throws UnsupportedEncodingException {
        String str  = "一二三四五六七";
        byte[] bytes = str.getBytes("GBK");
        String temp;
        for (String charsetName : charsetNames) {
            for (String name : charsetNames) {
                System.out.print(String.format("%15s", charsetName) + String.format("%15s", name) + "\t\t");
                System.out.println(new String(str.getBytes(charsetName), name));
            }
        }

    }

    public static void test2() throws UnsupportedEncodingException {
        //遍历看哪些编码间乱码可以还原
        String str  = "一二三四五六七";
        for (String charsetName1 : charsetNames) {
            for (String charsetName2 : charsetNames) {
                //用charsetName1把String转byte[],再用charsetName2用byte[]构造String，再用charsetName2把String转成byte[]，
                // 这里用charsetName2构造了String再转回byte[]，感觉像是还原成原来的byte[]，但某些编码之间通过这样的步骤是还原不了的
                //最后再把byte[]用charsetName1转成String
                if(!charsetName1.equals(charsetName2)) {
                    String temp = new String(str.getBytes(charsetName1), charsetName2);
                    String back = new String(temp.getBytes(charsetName2), charsetName1);
                    if(str.equals(back)) {
                        System.out.print(String.format("%15s", charsetName1) + String.format("%15s", charsetName2) + "\t\t");
                        System.out.print(temp + "\t\t\t");
                        System.out.println(back);
                    }
                }
            }
        }
    }
}
