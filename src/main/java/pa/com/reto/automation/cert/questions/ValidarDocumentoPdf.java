package pa.com.reto.automation.cert.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ValidarDocumentoPdf implements Question<Boolean> {

    @Override
    public Boolean answeredBy(Actor actor) {
        Path downloadedFile = actor.recall("documentoDescargado");
        if (downloadedFile == null
                || !Files.isRegularFile(downloadedFile)
                || !downloadedFile.getFileName().toString()
                        .equals("PJ-Autocertificacion-unificado-Fatca-y-CRS.pdf")) {
            return false;
        }

        try (InputStream input = Files.newInputStream(downloadedFile)) {
            byte[] header = new byte[5];
            return input.read(header) == header.length
                    && header[0] == '%'
                    && header[1] == 'P'
                    && header[2] == 'D'
                    && header[3] == 'F'
                    && header[4] == '-';
        } catch (IOException e) {
            throw new UncheckedIOException("No se pudo validar el archivo PDF descargado.", e);
        }
    }

    public static ValidarDocumentoPdf esCorrecto() {
        return new ValidarDocumentoPdf();
    }
}
