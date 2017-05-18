package thinking.chapter20.example004;

import com.sun.mirror.apt.AnnotationProcessor;
import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.declaration.MethodDeclaration;
import com.sun.mirror.declaration.Modifier;
import com.sun.mirror.declaration.ParameterDeclaration;
import com.sun.mirror.declaration.TypeDeclaration;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * 项目名称：zzh<br/>
 * *********************************<br/>
 * <p>类名称：InterfaceExtractorProcessor</p>
 * *********************************<br/>
 * <p>类描述：</p>
 * 创建人：zhouzhihui<br/>
 * 创建时间：2017/3/14 17:18<br/>
 * 修改人：周志辉<br/>
 * 修改时间：2017/3/14 17:18<br/>
 * 修改备注：<br/>
 *
 * @version 1.0<br/>
 */
@SuppressWarnings("deprecation")
public class InterfaceExtractorProcessor implements AnnotationProcessor {

    private final AnnotationProcessorEnvironment env;

    private ArrayList<MethodDeclaration> interfaceMethods = new ArrayList<>();


    public InterfaceExtractorProcessor(AnnotationProcessorEnvironment env) {
        this.env = env;
    }



    @Override
    public void process() {
        for (TypeDeclaration typeDeclaration : env.getSpecifiedTypeDeclarations()) {
            ExtractInterface annot = typeDeclaration.getAnnotation(ExtractInterface.class);
            if(annot == null) {
                break;
            }
            for (MethodDeclaration methodDeclaration : typeDeclaration.getMethods()) {
                if(methodDeclaration.getModifiers().contains(Modifier.PUBLIC) && (!methodDeclaration.getModifiers().contains(java.lang.reflect.Modifier.STATIC))) {
                    interfaceMethods.add(methodDeclaration);
                }
            }
            if(interfaceMethods.size() > 0) {
                try {
                    PrintWriter writer = env.getFiler().createSourceFile(annot.value());
                    writer.println("package " + typeDeclaration.getPackage().getQualifiedName() + ";");
                    writer.println("public interface " + annot.value() + "{");
                    for (MethodDeclaration interfaceMethod : interfaceMethods) {
                        writer.print("    public ");
                        writer.print(interfaceMethod.getReturnType());
                        writer.print(" " + interfaceMethod.getSimpleName() + " (");
                        int i = 0;
                        for (ParameterDeclaration parameterDeclaration : interfaceMethod.getParameters()) {
                            writer.print(parameterDeclaration.getType() + " " + parameterDeclaration.getSimpleName());
                            if(++i < interfaceMethod.getParameters().size()) {
                                writer.print(", ");
                            }
                        }
                        writer.println(");");
                    }
                    writer.println("}");
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
