package pa.com.reto.automation.cert.runners;



import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/retobanistmo.feature",
        glue = "pa.com.reto.automation.cert.stepdefinitions",
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        tags = "@descargarDocumentoInformativo"
)
public class BanistmoTestRunner {
}
