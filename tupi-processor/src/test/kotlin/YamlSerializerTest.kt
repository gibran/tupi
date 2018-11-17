import com.google.testing.compile.CompilationRule
import javax.lang.model.util.*
import org.junit.*
import tupi.processor.yaml.YamlBuilder
import controllers.HelloController

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

    /**
     * 1 - Controller com um método GET e um response de tipo primitivo (int, string, double)
     * 2 - Controller com um método GET e um response com classe simples
     * 3 - Controller com um método GET e um response com classe complexa
     * 4 - Controller com um método GET e dois respons um com classe simples e outro sem classe
     * 5 - Controller com um método GET com passagem de parâmetro no PATH e um response com classe simples
     * 6 - Controller com um método GET com passagem de parâmetro na QUERY e um response com classe simples
     * 7 - Controller com um método GET com passagem de parâmetro no HEADER e um response com classe simples
     * 8 - Controller com um método GET com passagem de parâmetro no PATH e um parâmetro na QUERY e um response com classe simples
     * 9 - Controller com um método POST com passagem de parâmetro no BODY e um response com classe simples
     * 10 - Controller com métodos e sem nenhuma anotação nos métodos
     * 11 - Controller sem nenhuma anotação na classe
     */

    @Test
    fun `should be test serialize with controller with one operator that return a simple class`() {
        val element = elements!!.getTypeElement(HelloController::class.qualifiedName)

        val ymlBuilder = YamlBuilder(types!!)
        ymlBuilder.addRoute(element)
        val result = ymlBuilder.write()

    }

    @Test
    fun `should be test serialize with controller with one operator that return a complex class`() {
        val element = elements!!.getTypeElement(HelloController::class.qualifiedName)

        val ymlBuilder = YamlBuilder(types!!)
        ymlBuilder.addRoute(element)
        val result = ymlBuilder.write()

    }
}