package mediatheque;

import mediatheque.exception.CatalogueCorrompuException;
import mediatheque.exception.DocumentIndisponibleException;
import mediatheque.exception.MediathequeException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class CauseEtRessourcesTest {
    @Test void cause_chainee() {
        Path absent = Path.of("listDocument.txt");
        CatalogueCorrompuException ex = assertThrows(
                CatalogueCorrompuException.class,
                () -> ChargeurCatalogue.chargerTitres(absent));
        assertTrue(ex.getMessage().contains("listDocument.txt"));  // message
        assertInstanceOf(IOException.class, ex.getCause());   // cause conservee
    }

}
