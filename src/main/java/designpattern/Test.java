package designpattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zzh on 2015-12-07.
 */
public class Test {
    public static void test(Interface inf) {
        System.out.println("In test");
    }

    public static void main(String[] args) {
//        Impl inf = new Impl();
//        test(inf);
        String str = "status 500 reading RegulationOrderClient#addRegulationOrder(RegulationOrder); content:\n" +
                "{\"timestamp\":1495163837174,\"status\":500,\"error\":\"Internal Server Error\",\"exception\":\"com.pcitc.sof.smartregulation.plan.common.exception.BusinessException\",\"message\":\"主要参数重复\",\"path\":\"/smartregulation-plan/regulationOrder\"}";
        Pattern p = Pattern.compile("\\\"message\\\":\\\"([^\\\"]*?)\\\"");
        Matcher m = p.matcher(str);
        if(m.find()) {
            System.out.println(m.group(1));
        }
    }
}

interface Interface{}

class Impl implements Interface {}