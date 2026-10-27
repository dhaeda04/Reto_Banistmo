package pa.com.reto.automation.cert.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Scroll;
import pa.com.reto.automation.cert.userinterface.BanistmoLegalesPage;

import java.net.URI;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DescargarDocumentoPdf implements Task {

    private static final String EXPECTED_FILE_NAME =
            "PJ-Autocertificacion-unificado-Fatca-y-CRS.pdf";
    private static final Duration DOWNLOAD_TIMEOUT = Duration.ofSeconds(30);

    @Override
    public <T extends Actor> void performAs(T actor) {
        Path downloadDirectory = Paths.get(System.getProperty("user.dir"), "build", "downloads")
                .toAbsolutePath();
        Path downloadedFile = downloadDirectory.resolve(EXPECTED_FILE_NAME);

        try {
            Files.createDirectories(downloadDirectory);
            Files.deleteIfExists(downloadedFile);
            Set<Path> previousFiles;
            try (Stream<Path> files = Files.list(downloadDirectory)) {
                previousFiles = files.collect(Collectors.toCollection(HashSet::new));
            }

            URI documentUri = URI.create(BanistmoLegalesPage.LNK_AUTOCERTIFICACION_UNIFICADA
                    .resolveFor(actor).getAttribute("href"));
            if (!"https".equalsIgnoreCase(documentUri.getScheme())
                    || !"assets.ctfassets.net".equalsIgnoreCase(documentUri.getHost())
                    || !documentUri.getPath().endsWith(EXPECTED_FILE_NAME)) {
                throw new IllegalStateException("El enlace de descarga no corresponde al PDF unificado FATCA y CRS esperado.");
            }

            actor.attemptsTo(
                    Scroll.to(BanistmoLegalesPage.LNK_AUTOCERTIFICACION_UNIFICADA),
                    Click.on(BanistmoLegalesPage.LNK_AUTOCERTIFICACION_UNIFICADA)
            );
            esperarDescarga(downloadDirectory, downloadedFile, previousFiles);
        } catch (IOException e) {
            throw new IllegalStateException("No se pudo guardar la descarga del PDF de autocertificación FATCA y CRS.", e);
        }
        actor.remember("documentoDescargado", downloadedFile);
    }

    private void esperarDescarga(Path downloadDirectory, Path downloadedFile, Set<Path> previousFiles)
            throws IOException {
        long deadline = System.nanoTime() + DOWNLOAD_TIMEOUT.toNanos();
        while (System.nanoTime() < deadline) {
            try (Stream<Path> files = Files.list(downloadDirectory)) {
                Path pdfDownload = files
                        .filter(Files::isRegularFile)
                        .filter(file -> !previousFiles.contains(file))
                        .filter(file -> !file.equals(downloadedFile))
                        .filter(file -> !file.getFileName().toString().endsWith(".crdownload"))
                        .filter(this::hasPdfSignature)
                        .findFirst()
                        .orElse(null);
                if (pdfDownload != null) {
                    Files.move(pdfDownload, downloadedFile, StandardCopyOption.REPLACE_EXISTING);
                    return;
                }
            }
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new IllegalStateException("Se interrumpió la espera de descarga del PDF.", e);
            }
        }
        throw new IllegalStateException("El PDF esperado no se descargó en " + downloadDirectory
                + " dentro de " + DOWNLOAD_TIMEOUT.toSeconds() + " segundos.");
    }

    private boolean hasPdfSignature(Path file) {
        try (var input = Files.newInputStream(file)) {
            byte[] header = new byte[5];
            return input.read(header) == header.length
                    && header[0] == '%'
                    && header[1] == 'P'
                    && header[2] == 'D'
                    && header[3] == 'F'
                    && header[4] == '-';
        } catch (IOException e) {
            throw new UncheckedIOException("No se pudo leer el archivo de descarga.", e);
        }
    }

    public static DescargarDocumentoPdf desdeLaPagina() {
        return Tasks.instrumented(DescargarDocumentoPdf.class);
    }
}
