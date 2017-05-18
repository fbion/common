package thinking.chapter14.utils;

import java.util.HashMap;

public class TypeCounter extends HashMap<Class<?>, Integer>
{
    private Class<?> baseType;

    public TypeCounter(Class<?> baseType)
    {
        this.baseType = baseType;
    }
    
    public void count(Object obj){
        Class<?> type = obj.getClass();
        if(!baseType.isAssignableFrom(baseType)){
            throw new RuntimeException(obj + "incorrect type:" + type
                    + ", should be type or subtype of " + baseType);
        }
        countClass(type);
    }
    
    @SuppressWarnings("rawtypes")
    public void countClass(Class type){
        Integer quality = get(type);
        put(type, quality == null?1:quality+1);
        Class<?> superClass = type.getSuperclass();
        if(superClass != null && baseType.isAssignableFrom(superClass))
        {
            countClass(superClass);
        }
    }
    
    @Override
    public String toString() 
    {
        StringBuffer result = new StringBuffer("{");
        for (Entry<Class<?>, Integer> pair : entrySet())
        {
            result.append(pair.getKey().getSimpleName());
            result.append("=");
            result.append(pair.getValue());
            result.append(". ");
        }
        result.delete(result.length()-2, result.length());
        result.append("}");
        return result.toString();
    }
}
