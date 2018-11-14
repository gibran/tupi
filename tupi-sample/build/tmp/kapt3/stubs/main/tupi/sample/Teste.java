package tupi.sample;

import java.lang.System;

@tupi.annotations.SwaggerRoute(routePath = "/accounts")
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004\u00a8\u0006\u0007"}, d2 = {"Ltupi/sample/Teste;", "", "()V", "Listar", "", "Obter", "id", "tupi-sample"})
public final class Teste {
    
    @org.jetbrains.annotations.NotNull()
    @tupi.annotations.SwaggerResponses(values = {@tupi.annotations.SwaggerResponse(status = (short)200, type = tupi.sample.Order.class, description = "Successful operation")})
    @tupi.annotations.SwaggerOperation(method = tupi.annotations.enumerators.OperationType.GET, operationPath = "/{id}", summary = "Show data for a given account.")
    public final java.lang.String Obter(@org.jetbrains.annotations.NotNull()
    @tupi.annotations.SwaggerParameter(type = tupi.annotations.enumerators.ParameterType.PATH)
    java.lang.String id) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @tupi.annotations.SwaggerResponses(values = {@tupi.annotations.SwaggerResponse(status = (short)400, type = java.lang.String.class, description = "ERROS")})
    @tupi.annotations.SwaggerOperation(method = tupi.annotations.enumerators.OperationType.GET, operationPath = "", summary = "O objetivo desta rota \u00e9 retornar uma lista de ordens")
    public final java.lang.String Listar() {
        return null;
    }
    
    public Teste() {
        super();
    }
}