import com.google.testing.compile.CompilationRule
import javax.lang.model.util.*
import org.junit.*
import tupi.processor.yaml.YamlSerializer
import controllers.*
import kotlin.test.assertEquals

class YamlSerializerTest {

    @get:Rule
    var rule = CompilationRule()
    private var elements: Elements? = null
    private var types: Types? = null

    @Before
    fun setup() {
        elements = rule.elements
        types = rule.types
    }

    @Test
    fun `should serialize a simple controller with just only one operation GET that returns a primitive type`() {
        val element = elements!!.getTypeElement(PrimitiveController::class.qualifiedName)

        val ymlBuilder = YamlSerializer()
        ymlBuilder.addRoute(element)
        val result = ymlBuilder.write()

        val expected = """openapi: 3.0.0
info:
  version: 1.0.0
  title: CARD
paths:
  /api/primitive:
    get:
      summary: 'Operation that return key of primitive type'
      operationId: get_getkey
      produces:
      - 'application/json'
      responses:
        200:
          description: 'Success'
          content:
            application/json:
              schema:
                type: integer
                format: int32

definitions:
schemes:
- http
host: localhost:8080"""

        assertEquals(expected, result)
    }


    @Test
    fun `should serialize a simple controller with just only one operation GET that returns a simple class`() {
        val element = elements!!.getTypeElement(SimpleController::class.qualifiedName)

        val ymlBuilder = YamlSerializer()
        ymlBuilder.addRoute(element)
        val result = ymlBuilder.write()

        val expected = """openapi: 3.0.0
info:
  version: 1.0.0
  title: CARD
paths:
  /api/simple:
    get:
      summary: 'Operation that return key of simple class'
      operationId: get_getdata
      produces:
      - 'application/json'
      responses:
        200:
          description: 'Success'
          content:
            application/json:
              schema:
                ${'$'}ref: '#/definitions/simpleClass'

definitions:
  simpleClass:
    type: object
    required:
    - id
    - name
    properties:
      id:
        type: string
      name:
        type: string
schemes:
- http
host: localhost:8080"""

        assertEquals(expected, result)
    }

    @Test
    fun `should serialize a simple controller with just only one operation GET that returns a complex class`() {
        val element = elements!!.getTypeElement(SimpleWithComplexResponseController::class.qualifiedName)

        val ymlBuilder = YamlSerializer()
        ymlBuilder.addRoute(element)
        val result = ymlBuilder.write()

        val expected = """openapi: 3.0.0
info:
  version: 1.0.0
  title: CARD
paths:
  /api/simple:
    get:
      summary: 'Operation that return key of complex class'
      operationId: get_getdata
      produces:
      - 'application/json'
      responses:
        200:
          description: 'Success'
          content:
            application/json:
              schema:
                ${'$'}ref: '#/definitions/complexClass'

definitions:
  item:
    type: object
    required:
    - id
    - description
    - price
    properties:
      id:
        type: integer
        format: int32
      description:
        type: string
      price:
        type: number
        format: double
  complexClass:
    type: object
    required:
    - id
    - items
    properties:
      id:
        type: integer
        format: int32
      description:
        type: string
      items:
        type: array
        items:
          ${'$'}ref: '#/definitions/item'
schemes:
- http
host: localhost:8080""".trimIndent()

        assertEquals(expected, result)
    }

    @Test
    fun `should serialize a simple controller with just only one operation GET with two responses`() {
        val element = elements!!.getTypeElement(SimpleWithTwoResponsesController::class.qualifiedName)

        val ymlBuilder = YamlSerializer()
        ymlBuilder.addRoute(element)
        val result = ymlBuilder.write()

        val expected = """openapi: 3.0.0
info:
  version: 1.0.0
  title: CARD
paths:
  /api/simple:
    get:
      summary: 'Operation that return key of simple class'
      operationId: get_getdata
      produces:
      - 'application/json'
      responses:
        404:
          description: 'Not Found'
        200:
          description: 'Success'
          content:
            application/json:
              schema:
                ${'$'}ref: '#/definitions/simpleClass'

definitions:
  simpleClass:
    type: object
    required:
    - id
    - name
    properties:
      id:
        type: string
      name:
        type: string
schemes:
- http
host: localhost:8080""".trimIndent()

        assertEquals(expected, result)
    }

    @Test
    fun `should serialize a simple controller with operation GET and parameter in PATH and it returns a simple class`() {
        val element = elements!!.getTypeElement(SimpleWithPathParameterController::class.qualifiedName)

        val ymlBuilder = YamlSerializer()
        ymlBuilder.addRoute(element)
        val result = ymlBuilder.write()

        val expected = """openapi: 3.0.0
info:
  version: 1.0.0
  title: CARD
paths:
  /api/simple/{id}:
    get:
      summary: 'Operation that return key of simple class'
      operationId: get_getdata
      produces:
      - 'application/json'
      parameters:
        - name: id
          in: path
          description: identifier
          required: true
          schema:
            type: string
      responses:
        200:
          description: 'Success'
          content:
            application/json:
              schema:
                ${'$'}ref: '#/definitions/simpleClass'

definitions:
  simpleClass:
    type: object
    required:
    - id
    - name
    properties:
      id:
        type: string
      name:
        type: string
schemes:
- http
host: localhost:8080""".trimIndent()

        assertEquals(expected, result)
    }

    @Test
    fun `should serialize a simple controller with operation GET and parameter in PATH, QUERY, HEADER and it returns a simple class`() {
        val element = elements!!.getTypeElement(SimpleWithAllKindOfParameterController::class.qualifiedName)

        val ymlBuilder = YamlSerializer()
        ymlBuilder.addRoute(element)
        val result = ymlBuilder.write()

        val expected = """openapi: 3.0.0
info:
  version: 1.0.0
  title: CARD
paths:
  /api/simple/{id}:
    get:
      summary: 'Operation that return key of simple class'
      operationId: get_getdata
      produces:
      - 'application/json'
      parameters:
        - name: name
          in: query
          description: name
          required: true
          schema:
            type: string
        - name: id
          in: path
          description: identifier
          required: true
          schema:
            type: string
        - name: version
          in: header
          description: version of api
          required: true
          schema:
            type: string
      responses:
        200:
          description: 'Success'
          content:
            application/json:
              schema:
                ${'$'}ref: '#/definitions/simpleClass'

definitions:
  simpleClass:
    type: object
    required:
    - id
    - name
    properties:
      id:
        type: string
      name:
        type: string
schemes:
- http
host: localhost:8080""".trimIndent()

        assertEquals(expected, result)
    }

    @Test
    fun `should serialize a simple controller with operation POST and parameter in BODY and it returns a simple class`() {
        val element = elements!!.getTypeElement(SimpleWithBodyParameterController::class.qualifiedName)

        val ymlBuilder = YamlSerializer()
        ymlBuilder.addRoute(element)
        val result = ymlBuilder.write()

        val expected = """openapi: 3.0.0
info:
  version: 1.0.0
  title: CARD
paths:
  /api/simple:
    post:
      summary: 'Operation that return key of simple class'
      operationId: post_getdata
      produces:
      - 'application/json'
      parameters:
        - name: id
          in: body
          description: identifier
          required: true
          schema:
            ${'$'}ref: '#/definitions/simpleClass'
      responses:
        200:
          description: 'Success'
          content:
            application/json:
              schema:
                ${'$'}ref: '#/definitions/simpleClass'

definitions:
  simpleClass:
    type: object
    required:
    - id
    - name
    properties:
      id:
        type: string
      name:
        type: string
schemes:
- http
host: localhost:8080""".trimIndent()

        assertEquals(expected, result)
    }

    @Test
    fun `should serialize a simple controller without operations`() {
        val element = elements!!.getTypeElement(SimpleWithoutOperationsController::class.qualifiedName)

        val ymlBuilder = YamlSerializer()
        ymlBuilder.addRoute(element)
        val result = ymlBuilder.write()

        val expected = """openapi: 3.0.0
info:
  version: 1.0.0
  title: CARD
paths:
definitions:
schemes:
- http
host: localhost:8080""".trimIndent()

        assertEquals(expected, result)
    }

    @Test
    fun `should serialize a simple controller without annotation in controller`() {
        val element = elements!!.getTypeElement(SimpleWithoutRouteAnnotationController::class.qualifiedName)

        val ymlBuilder = YamlSerializer()
        ymlBuilder.addRoute(element)
        val result = ymlBuilder.write()

        val expected = """openapi: 3.0.0
info:
  version: 1.0.0
  title: CARD
paths:
definitions:
schemes:
- http
host: localhost:8080""".trimIndent()

        assertEquals(expected, result)
    }
}