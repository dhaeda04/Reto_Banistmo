package pa.com.reto.automation.cert.stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;
import pa.com.reto.automation.cert.questions.ValidarDocumentoPdf;
import pa.com.reto.automation.cert.tasks.DescargarDocumentoPdf;
import pa.com.reto.automation.cert.tasks.NavegarAprenderEsFacil;
import pa.com.reto.automation.cert.tasks.NavegarFactaPdf;
import pa.com.reto.automation.cert.userinterface.BanistmoHomePage;

public class BanistmoStepDefinitions {

    @Before
    public void setTheStage() {
        WebDriverManager.chromedriver().setup();
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled("Usuario");
    }

    @Dado("que el usuario navega a la pagina principal de Banistmo Personas")
    public void queElUsuarioNavegaALaPaginaPrincipalDeBanistmoPersonas() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                Open.url("https://www.banistmo.com/personas"),
                WaitUntil.the(BanistmoHomePage.BTN_CERRAR_AVISO,
                        WebElementStateMatchers.isVisible()).forNoMoreThan(15).seconds(),
                Click.on(BanistmoHomePage.BTN_CERRAR_AVISO),
                WaitUntil.the(BanistmoHomePage.BTN_CERRAR_MODAL_PROMOCIONAL,
                        WebElementStateMatchers.isVisible()).forNoMoreThan(15).seconds(),
                Click.on(BanistmoHomePage.BTN_CERRAR_MODAL_PROMOCIONAL)
        );
    }

    @Cuando("navega a la seccion de aprender es facil desde el pie de pagina")
    public void navegaALaSeccionDeAprenderEsFacilDesdeElPieDePagina() {
        OnStage.theActorInTheSpotlight().attemptsTo(NavegarAprenderEsFacil.ir());
    }

    @Cuando("selecciona la opcion de legales y accede a FACTA & CRS")
    public void seleccionaLaOpcionDeLegalesYAccedeAFACTACRS() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                NavegarFactaPdf.ir()
        );
    }

    @Cuando("descarga el documento  PDF de Autocertificacion unificada FACTA y CRS")
    public void descargaElDocumentoPDFDeAutocertificacionUnificadaFACTAYCRS() {
        OnStage.theActorInTheSpotlight().attemptsTo(DescargarDocumentoPdf.desdeLaPagina());
    }

    @Entonces("el usuario valida que el documento generado sea el correcto")
    public void elUsuarioValidaQueElDocumentoGeneradoSeaElCorrecto() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                Ensure.that(ValidarDocumentoPdf.esCorrecto()).isTrue()
        );
    }
}
