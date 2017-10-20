package test.utils;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * description: <br>
 * createTime: 2017/10/2014:18 <br>
 *
 * @author zzh
 */
public class GetUnsafe {
     public static Unsafe getUnsafe() {
         Unsafe unsafe = null;
         try{
             Field field = Unsafe.class.getDeclaredField("theUnsafe");
             field.setAccessible(true);
             unsafe = (Unsafe) field.get(null);
         } catch (IllegalAccessException e) {
             e.printStackTrace();
         } catch (NoSuchFieldException e) {
             e.printStackTrace();
         }
         return unsafe;
     }
}
