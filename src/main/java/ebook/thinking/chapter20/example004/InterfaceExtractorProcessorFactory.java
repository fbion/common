package ebook.thinking.chapter20.example004;

import com.sun.mirror.apt.AnnotationProcessor;
import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.apt.AnnotationProcessorFactory;
import com.sun.mirror.declaration.AnnotationTypeDeclaration;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

/**
 * 项目名称：zzh<br/>
 * *********************************<br/>
 * <p>类名称：InterfaceExtractorProcessorFactory</p>
 * *********************************<br/>
 * <p>类描述：</p>
 * 创建人：zhouzhihui<br/>
 * 创建时间：2017/3/14 17:41<br/>
 * 修改人：周志辉<br/>
 * 修改时间：2017/3/14 17:41<br/>
 * 修改备注：<br/>
 *
 * @version 1.0<br/>
 */
@SuppressWarnings("deprecation")
public class InterfaceExtractorProcessorFactory implements AnnotationProcessorFactory {


    @Override
    public Collection<String> supportedOptions() {
        return Collections.emptySet();
    }


    @Override
    public Collection<String> supportedAnnotationTypes() {
        return Collections.singleton("thinking.chapter20.example004.ExtractInterface");
    }


    @Override
    public AnnotationProcessor getProcessorFor(Set<AnnotationTypeDeclaration> set, AnnotationProcessorEnvironment annotationProcessorEnvironment) {
        return new InterfaceExtractorProcessor(annotationProcessorEnvironment);
    }
}
