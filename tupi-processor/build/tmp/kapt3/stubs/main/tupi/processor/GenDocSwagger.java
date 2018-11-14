package tupi.processor;

import java.lang.System;

@com.google.auto.service.AutoService(value = {javax.annotation.processing.Processor.class})
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0016J\b\u0010\u0006\u001a\u00020\u0007H\u0016J \u0010\b\u001a\u00020\t2\u000e\u0010\n\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000b0\u00042\u0006\u0010\f\u001a\u00020\rH\u0016\u00a8\u0006\u000f"}, d2 = {"Ltupi/processor/GenDocSwagger;", "Ljavax/annotation/processing/AbstractProcessor;", "()V", "getSupportedAnnotationTypes", "", "", "getSupportedSourceVersion", "Ljavax/lang/model/SourceVersion;", "process", "", "annotations", "Ljavax/lang/model/element/TypeElement;", "roundEnv", "Ljavax/annotation/processing/RoundEnvironment;", "Companion", "tupi-processor"})
@javax.annotation.processing.SupportedOptions(value = {"kapt.kotlin.generated"})
@javax.annotation.processing.SupportedSourceVersion(value = javax.lang.model.SourceVersion.RELEASE_8)
public final class GenDocSwagger extends javax.annotation.processing.AbstractProcessor {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KAPT_KOTLIN_GENERATED_OPTION_NAME = "kapt.kotlin.generated";
    public static final tupi.processor.GenDocSwagger.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.util.Set<java.lang.String> getSupportedAnnotationTypes() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public javax.lang.model.SourceVersion getSupportedSourceVersion() {
        return null;
    }
    
    @java.lang.Override()
    public boolean process(@org.jetbrains.annotations.NotNull()
    java.util.Set<? extends javax.lang.model.element.TypeElement> annotations, @org.jetbrains.annotations.NotNull()
    javax.annotation.processing.RoundEnvironment roundEnv) {
        return false;
    }
    
    public GenDocSwagger() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Ltupi/processor/GenDocSwagger$Companion;", "", "()V", "KAPT_KOTLIN_GENERATED_OPTION_NAME", "", "tupi-processor"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}