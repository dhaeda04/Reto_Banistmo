package pa.com.reto.automation.cert.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;
import pa.com.reto.automation.cert.userinterface.BanistmoHomePage;

public class CerrarAvisos implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        cerrarSiVisible(actor, BanistmoHomePage.BTN_CERRAR_AVISO);
        cerrarSiVisible(actor, BanistmoHomePage.BTN_CERRAR_MODAL_PROMOCIONAL);
    }

    private <T extends Actor> void cerrarSiVisible(T actor, Target controlDeCierre) {
        var controles = controlDeCierre.resolveAllFor(actor);
        if (!controles.isEmpty() && controles.get(0).isDisplayed()) {
            actor.attemptsTo(Click.on(controlDeCierre));
        }
    }

    public static CerrarAvisos siEstan() {
        return Tasks.instrumented(CerrarAvisos.class);
    }
}
