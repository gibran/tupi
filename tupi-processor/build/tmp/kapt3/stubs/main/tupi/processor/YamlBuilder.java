package tupi.processor;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0006\u0010\u0014\u001a\u00020\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Ltupi/processor/YamlBuilder;", "", "context", "Ljavax/annotation/processing/ProcessingEnvironment;", "(Ljavax/annotation/processing/ProcessingEnvironment;)V", "definitions", "Ljava/util/HashMap;", "", "Ljavax/lang/model/element/TypeElement;", "operations", "Ljava/util/ArrayList;", "Ltupi/processor/yaml/YamlOperation;", "addRoute", "", "controllerElement", "Ljavax/lang/model/element/Element;", "getReturnedType", "Ljavax/lang/model/type/TypeMirror;", "responseAnnotation", "Ltupi/annotations/SwaggerResponse;", "write", "tupi-processor"})
public final class YamlBuilder {
    private final java.util.ArrayList<tupi.processor.yaml.YamlOperation> operations = null;
    private final java.util.HashMap<java.lang.String, javax.lang.model.element.TypeElement> definitions = null;
    private final javax.annotation.processing.ProcessingEnvironment context = null;
    
    public final void addRoute(@org.jetbrains.annotations.NotNull()
    javax.lang.model.element.Element controllerElement) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String write() {
        return null;
    }
    
    private final javax.lang.model.type.TypeMirror getReturnedType(tupi.annotations.SwaggerResponse responseAnnotation) {
        return null;
    }
    
    public YamlBuilder(@org.jetbrains.annotations.NotNull()
    javax.annotation.processing.ProcessingEnvironment context) {
        super();
    }
}