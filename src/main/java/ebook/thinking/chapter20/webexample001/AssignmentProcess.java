package ebook.thinking.chapter20.webexample001;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 项目名称：zzh<br/>
 * *********************************<br/>
 * <p>类名称：AssignmentProcess</p>
 * *********************************<br/>
 * <p>类描述：</p>
 * 创建人：zhouzhihui<br/>
 * 创建时间：2017/3/14 18:11<br/>
 * 修改人：周志辉<br/>
 * 修改时间：2017/3/14 18:11<br/>
 * 修改备注：<br/>
 *
 * @version 1.0<br/>
 */
@SupportedSourceVersion(SourceVersion.RELEASE_6)
@SupportedAnnotationTypes("annotation.Assignment")
public class AssignmentProcess extends AbstractProcessor {
    private TypeElement assignmentElement;
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        Elements elementUtils = processingEnv.getElementUtils();
        assignmentElement = elementUtils.getTypeElement("annotation.Assignment");
    }
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(assignmentElement);
        for (Element element : elements) {
            processAssignment(element);
        }
        //少返回语句？？？
        return true;
    }
    private void processAssignment(Element element) {
        List<? extends AnnotationMirror> annotations = element.getAnnotationMirrors();
        for (AnnotationMirror mirror : annotations) {
            if (mirror.getAnnotationType().asElement().equals(assignmentElement)) {
                Map<? extends ExecutableElement, ? extends AnnotationValue> values = mirror.getElementValues();
                //报错
//                String assignee = (String) getAnnotationValue(values, "assignee"); //获取注解的值
            }
        }
    }
}
