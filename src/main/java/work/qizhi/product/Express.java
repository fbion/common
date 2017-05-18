package work.qizhi.product;

/**
 * Created by Administrator on 2016/10/19.
 */
enum Express {
    SHUNFENG("http://www.sf-express.com", "95338", "shunfeng", "顺丰速运"),                                         //976186294981
    SHENTONG("http://www.sto.cn", "95543", "shentong", "申通快递"),                                                  //229855869255
    YUANTONG("http://www.ytoexpress.com/", "95554", "yuantong", "圆通快递"),                                       //881443775034378914
    ZHONGTONG("http://www.zto.cn", "95311", "zhongtong", "中通快递"),                                               //768404530475
    HUITONGKUAIDI("http://www.800bestex.com/", "4009 565656", "huitongkuaidi", "百世快递"),                      //280490671426
    YUNDA("http://www.yundaex.com", "95546", "yunda", "韵达快递"),                                                  //1201875063322
    TIANTIAN("http://www.ttkdex.com", "400-188-8888", "tiantian", "天天快递"),                                    //580239318951
    YOUZHENGGUONEI("http://yjcx.chinapost.com.cn", "11185", "youzhengguonei", "邮政快递"),                      //9610005849243
    EMS("http://www.ems.com.cn/", "11183", "ems", "EMS快递"),                                                      //5116741128399
    ZHAIJISONG("http://www.zjs.com.cn", "400-6789-000", "zhaijisong", "宅急送"),                                //5977596855
    DEBANGWULIU("http://www.deppon.com", "95353", "debangwuliu", "德邦"),                                        //230624798
    QUANFENGKUAIDI("http://www.qfkd.com.cn", "400-100-0001", "quanfengkuaidi", "全峰全球专递");              //720193084187

    private String url;
    private String phone;
    private String simpleName;
    private String companyName;

    public String getUrl() {
        return url;
    }

    Express(String url, String phone, String simpleName, String companyName) {
        this.url = url;
        this.phone = phone;
        this.simpleName = simpleName;
        this.companyName = companyName;
    }

    public String getPhone() {
        return phone;
    }

    public String getSimpleName() {
        return simpleName;
    }

    public String getCompanyName() {
        return companyName;
    }
}