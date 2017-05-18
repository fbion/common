package test.d;//package test;
//
//import org.apache.commons.beanutils.BeanUtils;
//import org.springframework.util.Assert;
//
//import java.beans.PropertyDescriptor;
//import java.lang.reflect.Method;
//import java.lang.reflect.Modifier;
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.List;
//
///**
// * Created by Administrator on 2016/7/25.
// */
//public class Test111 {
//    public static void copyProperties(Object source, Object target,
//                                      Class<?> editable, String[] ignoreProperties) throws Exception {
//
//        Assert.notNull(source, "Source must not be null");
//        Assert.notNull(target, "Target must not be null");
//
//        Class<?> actualEditable = target.getClass();
//        if (editable != null) {
//            if (!editable.isInstance(target)) {
//                throw new IllegalArgumentException("Target class ["
//                        + target.getClass().getName()
//                        + "] not assignable to Editable class ["
//                        + editable.getName() + "]");
//            }
//            actualEditable = editable;
//        }
//        PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(actualEditable);
//        List<String> ignoreList = (ignoreProperties != null) ? Arrays
//                .asList(ignoreProperties) : null;
//
//        for (PropertyDescriptor targetPd : targetPds) {
//            if (targetPd.getWriteMethod() != null
//                    && (ignoreProperties == null || (!ignoreList
//                    .contains(targetPd.getName())))) {
//                PropertyDescriptor sourcePd = BeanUtils.getPropertyDescriptor(
//                        source.getClass(), targetPd.getName());
//                if (sourcePd != null && sourcePd.getReadMethod() != null) {
//                    try {
//                        Method readMethod = sourcePd.getReadMethod();
//                        if (!Modifier.isPublic(readMethod.getDeclaringClass()
//                                .getModifiers())) {
//                            readMethod.setAccessible(true);
//                        }
//                        Object value = readMethod.invoke(source);
//                        Method writeMethod = targetPd.getWriteMethod();
//                        if (!Modifier.isPublic(writeMethod.getDeclaringClass()
//                                .getModifiers())) {
//                            writeMethod.setAccessible(true);
//                        }
//
//                        if(value instanceof Collection){
//                            Method targetReadMethod = targetPd.getReadMethod();
//                            Object targetValue = targetReadMethod.invoke(target);
//                            if(null!=targetValue){
//                                copyCollection((Collection)value, (Collection)targetValue, false);
//                                continue;
//                            }
//                        }
//
//                        writeMethod.invoke(target, value);
//                    } catch (Throwable ex) {
//                        throw new Exception();
//                    }
//                }
//            }
//        }
//    }
//
//    private static void copyCollection() {
//    }
//}