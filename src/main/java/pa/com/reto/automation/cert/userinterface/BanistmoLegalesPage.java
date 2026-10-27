package pa.com.reto.automation.cert.userinterface;

import net.serenitybdd.screenplay.targets.Target;

public class BanistmoLegalesPage {

    public static final Target BTN_AUTOCERTIFICACION_FACTA_CRS = Target.the("Botón de autocertificación fiscal FATCA y CRS")
            .locatedBy("//h3[normalize-space()='Autocertificación fiscal FATCA & CRS']/ancestor::div[contains(@class,'cbc-card__size-data')]//button[@data-testid='ml-card-actions-button']");

    public static final Target BTN_ENTENDIDO = Target.the("Botón Entendido de autocertificación fiscal")
            .locatedBy("//button[@aria-label='Entendido']");

    public static final Target LNK_AUTOCERTIFICACION_UNIFICADA = Target.the("PDF de autocertificación unificada FATCA y CRS")
            .locatedBy("//a[contains(@href,'PJ-Autocertificacion-unificado-Fatca-y-CRS.pdf')]");
}
