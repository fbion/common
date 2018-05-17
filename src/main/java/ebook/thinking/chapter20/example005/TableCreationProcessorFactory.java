package ebook.thinking.chapter20.example005;

import com.sun.mirror.apt.AnnotationProcessor;
import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.apt.AnnotationProcessorFactory;
import com.sun.mirror.declaration.AnnotationTypeDeclaration;
import com.sun.mirror.declaration.ClassDeclaration;
import com.sun.mirror.declaration.FieldDeclaration;
import com.sun.mirror.declaration.TypeDeclaration;
import com.sun.mirror.util.SimpleDeclarationVisitor;
import ebook.thinking.chapter20.example003.Constraints;
import ebook.thinking.chapter20.example003.DBTable;
import ebook.thinking.chapter20.example003.SQLInteger;
import ebook.thinking.chapter20.example003.SQLString;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

/**
 * 项目名称：zzh<br/>
 * *********************************<br/>
 * <p>类名称：TableCreationProcessorFactory</p>
 * *********************************<br/>
 * <p>类描述：</p>
 * 创建人：zhouzhihui<br/>
 * 创建时间：2017/3/15 17:50<br/>
 * 修改人：周志辉<br/>
 * 修改时间：2017/3/15 17:50<br/>
 * 修改备注：<br/>
 *
 * @version 1.0<br/>
 */
public class TableCreationProcessorFactory implements AnnotationProcessorFactory {

    @Override
    public Collection<String> supportedOptions() {
        return Collections.emptySet();
    }


    @Override
    public Collection<String> supportedAnnotationTypes() {
        return Arrays.asList("thinking.chapter20.example003.DBTable", "thinking.chapter20.example003.Constraints",
                "thinking.chapter20.example003.SQLInteger", "thinking.chapter20.example003.SQLString");
    }


    @Override
    public AnnotationProcessor getProcessorFor(Set<AnnotationTypeDeclaration> set, AnnotationProcessorEnvironment annotationProcessorEnvironment) {
        return new TableCreationProcessor(annotationProcessorEnvironment);
    }

    private static class TableCreationProcessor implements AnnotationProcessor {
        private final  AnnotationProcessorEnvironment env;

        private String sql = "";


        public TableCreationProcessor(AnnotationProcessorEnvironment env) {
            this.env = env;
        }


        @Override
        public void process() {
            for (TypeDeclaration typeDeclaration : env.getSpecifiedTypeDeclarations()) {
                typeDeclaration.accept(new TableCreationVisitor());     //原书还传入了一个NO_OP参数
                sql = sql.substring(0, sql.length() - 1) + ");";
                System.out.println("create SQL is : " + sql);
                sql = "";
            }
        }

        private class TableCreationVisitor extends SimpleDeclarationVisitor {

            @Override
            public void visitClassDeclaration(ClassDeclaration classDeclaration) {
                DBTable dbTable = classDeclaration.getAnnotation(DBTable.class);
                if(dbTable != null) {
                    sql += "CREATE TABLE";
                    sql += (dbTable.name().length() < 1)? classDeclaration.getSimpleName().toUpperCase() : dbTable.name();
                    sql += " (";
                }
            }


            @Override
            public void visitFieldDeclaration(FieldDeclaration fieldDeclaration) {
                String columnName = "";
                if(fieldDeclaration.getAnnotation(SQLInteger.class) != null) {
                    SQLInteger sInt = fieldDeclaration.getAnnotation(SQLInteger.class);
                    if(sInt.name().length() < 1) {
                        columnName = fieldDeclaration.getSimpleName().toUpperCase();
                    } else {
                        columnName = sInt.name();
                    }
                    sql += "\n " + columnName + " INT" + getConstraints(sInt.constraints()) + ".";
                }
                if(fieldDeclaration.getAnnotation(SQLString.class) != null) {
                    SQLString sString = fieldDeclaration.getAnnotation(SQLString.class);
                    if(sString.name().length() < 1) {
                        columnName = fieldDeclaration.getSimpleName().toUpperCase();
                    } else {
                        columnName = sString.name();
                    }
                    sql += "\n " + columnName + " VARCHAR(" + sString.value() + ")" + getConstraints(sString.constraints()) + ".";
                }
            }

            private String getConstraints(Constraints con) {
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
    }
}
