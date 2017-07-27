package utils;

import com.alibaba.fastjson.JSON;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 描述： <br>
 * 创建时间: 2017/7/279:59 <br>
 *
 * @author 周志辉
 */
public class Xml2Json {

    /**
     * 方法描述: 把指定文件路径的xml文件解析转为Map对象<br>
     * 
     * @author 周志辉
     * @param filePath xml文件路径
     * @param map       以节点路径为key,值为数组的属性值的set集合
     * @return  转换结果
     */
    public static Map<String, Object> xml2Map(String filePath, Map<String, Set<String>> map) throws
            JDOMException,
            IOException {
        SAXBuilder builder=new SAXBuilder();
        Document doc = builder.build(new File(filePath));
        List<Element> elements = new ArrayList<>();
        elements.add(doc.getRootElement());
        return toMap(elements, map, "");
    }

    /**
     * 方法描述: 把Element的lis转为map对象<br>
     *
     * @author 周志辉
     * @param elements  Element的list
     * @param map       以节点路径为key,值为数组的属性值的set集合
     * @param path      elements所属的xml路径
     * @return  转换结果
     */
    public static Map<String, Object> toMap(List<Element> elements, Map<String, Set<String>> map, String path) {
        Map<String, Object> result = new HashMap<>();

        //以当前xml路径下值为数组类型的所有属性名构造一个map,值为list,用于保存所有该属性名的Element对象
        Map<String, List<Element>> listHashMap = new HashMap<>();
        if(map.containsKey(path)) {
            Set<String> set = map.get(path);
            for (String s : set) {
                listHashMap.put(s, new ArrayList<>());
            }
        }

        //遍历结点
        for (Element element : elements) {
            String name = element.getName();
            if(listHashMap.containsKey(name)) {             //数组类型的结点保存到listHashMap里的对应list里
                listHashMap.get(name).add(element);
            } else if(element.getChildren().size() == 0){   //非数组类型的，如果是叶结点，直接存result的map里
                result.put(element.getName(), element.getValue());
            } else {                                        //非数组类型的，如果是非叶结点，递归调用toMap
                result.put(element.getName(), toMap(element.getChildren(), map, path + "/" + element.getName()));
            }
        }
        //处理所有数组类型的结点
        for (Map.Entry<String, List<Element>> entry : listHashMap.entrySet()) {
            result.put(entry.getKey(), toList(entry.getValue(), map, path + "/" + entry.getKey()));
        }

        return result;
    }

    /**
     * 方法描述: 把一组相同name的element转为list对象<br>
     *
     * @author 周志辉
     * @param elements  Element的list
     * @param map       以节点路径为key,值为数组的属性值的set集合
     * @param path      elements所属的xml路径
     * @return  转换结果
     */
    public static List<Object> toList(List<Element> elements, Map<String, Set<String>> map, String path) {
        List<Object> result = new ArrayList<>();
        for (Element element : elements) {
            result.add(toMap(element.getChildren(), map, path));
        }
        return result;
    }

    public static void main(String[] args) throws JDOMException, IOException {
        Map<String, Set<String>> map = new HashMap<>();
        Set<String> set1 = new HashSet<>();
        set1.add("menu");
        Set<String> set2 = new HashSet<>();
        set2.add("menu");
        map.put("/mspplus-root", set1);
        map.put("/mspplus-root/menu/children", set2);
        Map<String, Object> result = xml2Map("D:\\test.xml", map);
        System.out.println(JSON.toJSONString(result));
        result = xml2Map("D:\\test1.xml", map);
        System.out.println(JSON.toJSONString(result));
    }
}
