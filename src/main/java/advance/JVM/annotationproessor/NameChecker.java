package advance.JVM.annotationproessor;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.ElementScanner8;
import javax.tools.Diagnostic;
import java.util.EnumSet;

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
    
    private class NameCheckScanner extends ElementScanner8<Void, Void> {

        /**
         * 此方法用于检查Java类
         */
        @Override
        public Void visitType(TypeElement e, Void o) {
            scan(e.getTypeParameters(), o);
            checkCamelCase(e, true);
            super.visitType(e, o);
            return null;
        }


        /**
         * 检查方法名是否合法
         */
        @Override
        public Void visitExecutable(ExecutableElement e, Void o) {
            if(e.getKind() == ElementKind.METHOD) {
                Name name = e.getSimpleName();
                if(name.contentEquals(e.getEnclosingElement().getSimpleName())) {
                    messager.printMessage(Diagnostic.Kind.WARNING, " 一个普通方法 " + name + "不应该与类名重复，避免与构造函数产生混淆", e);
                }
                checkCamelCase(e, false);
            }
            super.visitExecutable(e, o);
            return null;
        }

        /**
         * 检查变量命名是否合法
         */
        @Override
        public Void visitVariable(VariableElement e, Void o) {
            if(e.getKind() == ElementKind.ENUM_CONSTANT || e.getConstantValue() != null || heuristicallyConstant(e)) {
                checkAllCaps(e);
            } else {
                checkCamelCase(e, false);
            }
//            super.visitVariable(e, o);
            return null;
        }


        /**
         * 判断一个变量是否常量
         */
        private boolean heuristicallyConstant(VariableElement e) {
            if(e.getEnclosingElement().getKind() == ElementKind.INTERFACE) {
                return true;
            } else if(e.getKind() == ElementKind.FIELD && e.getModifiers().containsAll(EnumSet.of(
                                                                    javax.lang.model.element.Modifier.PUBLIC,
                                                                    javax.lang.model.element.Modifier.STATIC,
                                                                    javax.lang.model.element.Modifier.FINAL))) {
                return true;
            } else {
                return false;
            }
        }


        private void checkCamelCase(javax.lang.model.element.Element e, boolean initialCaps) {
            String name = e.getSimpleName().toString();
            boolean previousUpper = false;
            boolean conventional = true;
            int firstCodePoint = name.codePointAt(0);
            if(Character.isUpperCase(firstCodePoint)) {
                previousUpper = true;
                if(!initialCaps) {
                    messager.printMessage(Diagnostic.Kind.WARNING, " 名称" + name + "应该以小写字母开头", e);
                    return ;
                } else if(Character.isLowerCase(firstCodePoint)) {
                    if(initialCaps) {
                        messager.printMessage(Diagnostic.Kind.WARNING, " 名称" + name + "应该以大写字母开头", e);
                        return ;
                    } else {
                        conventional = false;
                    }

                    if(conventional) {
                        int cp = firstCodePoint;
                        for (int i = Character.charCount(cp); i < name.length(); i += Character.charCount(cp)) {
                            cp = name.codePointAt(i);
                            if(Character.isUpperCase(cp)) {
                                if(previousUpper) {
                                    conventional = false;
                                    break;
                                } else {
                                    previousUpper = false;
                                }
                            }
                        }
                    }
                }
            }

            if(!conventional) {
                messager.printMessage(Diagnostic.Kind.WARNING, "名称" + name + "应当符合驼式命名法(Camel Case Names)", e);
            }
        }


        /**
         * 大写命名检查，要求第一个字母必须是大写的英文字母，其余部分可以是下划线或大写字母
         */
        private void checkAllCaps(VariableElement e) {
            String name = e.getSimpleName().toString();
            boolean conventional = true;
            int firstCodePoint = name.codePointAt(0);
            if(!Character.isUpperCase(firstCodePoint)) {
                conventional = false;
            } else {
                boolean previousUnderScore = false;
                int cp = firstCodePoint;
                for (int i = Character.charCount(cp); i < name.length(); i += Character.charCount(cp)) {
                    cp = name.codePointAt(i);
                    if(cp == (int) '_') {
                        if(previousUnderScore) {
                            conventional = false;
                            break;
                        }
                        previousUnderScore = true;
                    } else {
                        previousUnderScore = false;
                        if(!Character.isUpperCase(cp) && !Character.isDigit(cp)) {
                            conventional = false;
                            break;
                        }
                    }
                }
            }
            if(!conventional) {
                messager.printMessage(Diagnostic.Kind.WARNING, "常量" + name +
                        "应当全部以大写字母或下划线命名，并且以字母开头", e);
            }
        }
    }
}
