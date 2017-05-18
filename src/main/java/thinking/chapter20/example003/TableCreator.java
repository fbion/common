package thinking.chapter20.example003;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：zzh<br/>
 * *********************************<br/>
 * <p>类名称：TableCreator</p>
 * *********************************<br/>
 * <p>类描述：</p>
 * 创建人：zhouzhihui<br/>
 * 创建时间：2017/3/13 16:25<br/>
 * 修改人：周志辉<br/>
 * 修改时间：2017/3/13 16:25<br/>
 * 修改备注：<br/>
 *
 * @version 1.0<br/>
 */
public class TableCreator {

    public static void main(String[] args) throws ClassNotFoundException {
        if(args.length < 1) {
//            System.out.println("arguments:annotated classes");
//            System.exit(1);
            args  = new String[]{"thinking.chapter20.example003.Member"};
        }
        for (String className : args) {
            Class<?> cl = Class.forName(className);
            DBTable dbTable = cl.getAnnotation(DBTable.class);
            if(dbTable == null) {
                System.out.println("No DBTable annotations in class " + cl);
                continue;
            }
            String tableName = dbTable.name();
            if(tableName.length() < 1) {
                tableName = cl.getName().toUpperCase();
            }
            List<String> columnDefs = new ArrayList<>();
            for (Field field : cl.getDeclaredFields()) {
                String columnName = null;
                Annotation[] anns = field.getDeclaredAnnotations();
                if(anns.length < 1) {
                    continue;
                }
                if(anns[0] instanceof SQLInteger) {
                    SQLInteger sInt = (SQLInteger)anns[0];
                    if(sInt.name().length() < 1) {
                        columnName = field.getName().toUpperCase();
                    } else {
                        columnName = sInt.name();
                    }
                    columnDefs.add(columnName + " INT" + getConstraints(sInt.constraints()));
                }
                if(anns[0] instanceof SQLString) {
                    SQLString sString = (SQLString)anns[0];
                    if(sString.name().length() < 1) {
                        columnName = field.getName().toUpperCase();
                    } else {
                        columnName = sString.name();
                    }
                    columnDefs.add(columnName + " VARCHAR(" + sString.value() + ")" + getConstraints(sString.constraints()));
                }
                StringBuilder createCommand = new StringBuilder("CREATE TABLE " + tableName + "(");
                for (String columnDef : columnDefs) {
                    createCommand.append("\n  " + columnDef + ",");
                }
                String tableCreate = createCommand.toString().substring(0, createCommand.length() - 1) + ")";
                System.out.println("Table Creation SQL for " + className + " is  :\n" + tableCreate);
            }
        }

        System.out.println("4b3Ak36ObSkK5OS5HqRRx17pDnGXlOc9JWK0m1rtqfY=".length());
    }

    private static String getConstraints(Constraints con) {
        String constraints = "";
        if(!con.allowNull()) {
            constraints += " NOT NULL";
        }
        if(con.primaryKey()) {
            constraints += " PRIMARY KEY";
        }
        if(con.unique()) {
            constraints += " UNIQUE";
        }
        return constraints;
    }
}
