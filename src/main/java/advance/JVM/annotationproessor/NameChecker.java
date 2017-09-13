package advance.JVM.annotationproessor;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.ElementScanner8;
import javax.xml.bind.Element;

/**
 * 描述： <br>
 * 创建时间: 2017/9/1314:23 <br>
 *
 * @author 周志辉
 */
public class NameChecker {

    private final Messager messager;

    NameCheckScanner nameCheckScanner = new NameCheckScanner();


    public NameChecker(ProcessingEnvironment processingEnv) {
        this.messager = processingEnv.getMessager();
    }

    public void checkName(Element element) {
        nameCheckScanner.scan(element);
    }
    
    private class NameCheckScanner extends ElementScanner8 {

        @Override
        public Object visitType(TypeElement e, Object o) {
            return super.visitType(e, o);
        }


        @Override
        public Object visitExecutable(ExecutableElement e, Object o) {
            return super.visitExecutable(e, o);
        }


        @Override
        public Object visitVariable(VariableElement e, Object o) {
            return super.visitVariable(e, o);
        }


        public void scan(Element element) {

        }
    }
}
