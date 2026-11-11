package format;

import mediatheque.Document;

import java.io.IOException;
import java.util.List;

public class RapportService {
    private final FormatRapport format;
    private final SortieRapport sortie;
    private final Notificateur notificateur;
    public RapportService(FormatRapport f, SortieRapport s, Notificateur n) {
        this.format = f;
        this.sortie = s;
        this.notificateur = n;
    }
    public void generer(List<Document> docs) throws IOException {
        String contenu = format.formater(docs);
        sortie.ecrire(contenu); // si ça échoue : exception, PAS de mail
        notificateur.notifier("Rapport généré"); // seulement après succès
    }
}
