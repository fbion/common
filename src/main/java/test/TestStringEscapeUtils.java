package test;

import org.apache.commons.lang.StringEscapeUtils;

/**
 * description： <br>
 * createTime: 2017/10/1110:44 <br>
 *
 * @author zzh
 */
public class TestStringEscapeUtils {

    public static void main(String[] args) {
        System.out.println(StringEscapeUtils.escapeHtml("< >"));
        System.out.println(StringEscapeUtils.unescapeHtml("&lt;&nbsp;&gt;"));
    }
}
