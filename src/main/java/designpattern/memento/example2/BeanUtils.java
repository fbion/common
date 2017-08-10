package designpattern.memento.example2;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述： <br>
 * 创建时间: 2017/8/109:57 <br>
 *
 * @author 周志辉
 */
public class BeanUtils {
    public static Map<String, Object> backupProp(Object bean){
        Map<String, Object> result = new HashMap<String, Object>();
        try{
            BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
            PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
            for(PropertyDescriptor des: descriptors){
                String fieldName = des.getName();
                Method getter = des.getReadMethod();
                Object fieldValue = getter.invoke(bean, new Object[]{});
                if(!fieldName.equalsIgnoreCase("class")){
                    result.put(fieldName, fieldValue);
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public static void restoreProp(Object bean, Map<String, Object> propMap){
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
            PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
            for(PropertyDescriptor des: descriptors){
                String fieldName = des.getName();
                if(propMap.containsKey(fieldName)){
                    Method setter = des.getWriteMethod();
                    setter.invoke(bean, new Object[]{propMap.get(fieldName)});
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}