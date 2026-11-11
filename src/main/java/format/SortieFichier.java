package format;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SortieFichier implements SortieRapport {
    private final Path chemin;
    public SortieFichier(Path chemin) { this.chemin = chemin; }
    @Override
    public void ecrire(String contenu) throws IOException {
        Files.writeString(chemin, contenu);
    }
}
