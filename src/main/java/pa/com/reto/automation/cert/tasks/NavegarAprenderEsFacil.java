package pa.com.reto.automation.cert.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import pa.com.reto.automation.cert.userinterface.BanistmoHomePage;

public class NavegarAprenderEsFacil implements Task {
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Scroll.to(BanistmoHomePage.LNK_APRENDER_ES_FACIL),
                Click.on(BanistmoHomePage.LNK_APRENDER_ES_FACIL),
                WaitUntil.the(BanistmoHomePage.BTN_LEGALES, WebElementStateMatchers.isVisible())
                        .forNoMoreThan(15).seconds(),
                CerrarAvisos.siEstan()
        );

    }

    public static NavegarAprenderEsFacil ir() {
        return Tasks.instrumented(NavegarAprenderEsFacil.class);
    }

}