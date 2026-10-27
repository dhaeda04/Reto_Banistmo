package pa.com.reto.automation.cert.userinterface;

import net.serenitybdd.screenplay.targets.Target;

public class BanistmoHomePage {

    public static final Target BTN_CERRAR_AVISO = Target.the("Botón para cerrar el aviso de Banistmo")
            .locatedBy("//button[@data-testid='ml-bandstrip-flexible-close-btn']");

    public static final Target BTN_CERRAR_MODAL_PROMOCIONAL = Target.the("Botón para cerrar el modal promocional")
            .locatedBy("//button[@data-testid='or-promotional-modal__close']");

    public static final Target LNK_APRENDER_ES_FACIL = Target.the("Link de aprender es facil")
            .locatedBy("//a[contains(@class,'cbc-footer') and @href='/personas/aprender-es-facil']");

    public static final Target BTN_LEGALES = Target.the("Botón para descubrir la sección de legales")
            .locatedBy("//h3[normalize-space()='Legales']/ancestor::div[contains(@class,'cbc-card__size-data')]//button[@data-testid='ml-card-actions-button']");
}
