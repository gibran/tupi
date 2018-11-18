# TUPI

#### Kotlin

Project responsible to write swagger specification in YAML in Kotlin. This specification was based in [OAS 3.0](https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.0.md).

##### How it works

We created some annotations to decorate the controller that will be generated the swagger Specification.
After you decorate your classes with this annotation and build your project, then the `swagger_api.yml` file will be generated.


##### You will need

- Add two [.jar](https://github.com/gibran/tupi/blob/master/libs/) in your project (tupi-processor.jar and tupi-annotation.jar)
- Add [kapt](https://kotlinlang.org/docs/reference/kapt.html) plugin in your build.gradle `apply plugin: 'kotlin-kapt'`
- Add dependencies to tupi-processor in you build.gradle
```
dependencies {
    compile files('libs/tupi-annotation-{VERSION}.jar')
    compile files('libs/tupi-processor-{VERSION}.jar')
    kapt files('libs/tupi-processor-{VERSION}.jar')
     ...
``` 
- Decorate your controller like this
```
@SwaggerRoute("/api/simple", "Simple controller with one operation and it returns a simple class")
class SimpleWithBodyParameterController {
    @SwaggerOperation(OperationType.POST, "", "Operation that return key of simple class")
    @SwaggerResponses([
        SwaggerResponse(200, "Success", simpleClass::class),
        SwaggerResponse(400, "Bad Request")
    ])
    @SwaggerParameters([
        SwaggerParameter(ParameterType.BODY, "id", simpleClass::class, true, "identifier")
    ])
    fun getData(data: simpleClass) { }
}
```

**SwaggerRoute** -> Define the main path the controller will be respond \
**SwaggerOperation** -> Define the specific path for the operation \
**SwaggerResponses** -> Define a array of response for the operation \
**SwaggerParameters** -> Define a array of parameters for the operation 