package pa.com.reto.automation.cert.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;
import pa.com.reto.automation.cert.userinterface.BanistmoHomePage;
import pa.com.reto.automation.cert.userinterface.BanistmoLegalesPage;

public class NavegarFactaPdf implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Scroll.to(BanistmoHomePage.BTN_LEGALES),
                Click.on(BanistmoHomePage.BTN_LEGALES),
                WaitUntil.the(BanistmoLegalesPage.BTN_AUTOCERTIFICACION_FACTA_CRS,
                        WebElementStateMatchers.isVisible()).forNoMoreThan(15).seconds(),
                CerrarAvisos.siEstan(),
                Scroll.to(BanistmoLegalesPage.BTN_AUTOCERTIFICACION_FACTA_CRS),
                Click.on(BanistmoLegalesPage.BTN_AUTOCERTIFICACION_FACTA_CRS),
                WaitUntil.the(BanistmoLegalesPage.BTN_ENTENDIDO,
                        WebElementStateMatchers.isVisible()).forNoMoreThan(15).seconds(),
                Click.on(BanistmoLegalesPage.BTN_ENTENDIDO),
                WaitUntil.the(BanistmoLegalesPage.LNK_AUTOCERTIFICACION_UNIFICADA,
                        WebElementStateMatchers.isVisible()).forNoMoreThan(15).seconds()
        );

    }
    public static NavegarFactaPdf ir() {
        return Tasks.instrumented(NavegarFactaPdf.class);
    }
}
