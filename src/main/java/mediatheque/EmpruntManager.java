package mediatheque;

import mediatheque.Catalogue;
import mediatheque.ConfigurationMediatheque;
import mediatheque.Document;
import mediatheque.exception.DocumentIndisponibleException;
import mediatheque.exception.DocumentIntrouvableException;
import mediatheque.exception.MediathequeException;

import java.time.LocalDate;
public class EmpruntManager {
    private final Catalogue<Document> catalogue; // COMPOSITION : le manager possède son
    private int empruntsEnCours = 0; // initialisé UNE fois à la création ;
// incrémenté à chaque emprunt réussi

    public EmpruntManager(Catalogue<Document> catalogue) {
        this.catalogue = catalogue;
// Remarque : rien n'indique ici que la classe dépend de ConfigurationMediatheque.
// C'est la "dépendance cachée" que l'étape 3 va corriger.
    }
    // Signature INCHANGÉE par rapport à votre code : void, un seul paramètre
    public void emprunter(String titre) throws MediathequeException {
// 0. On va CHERCHER la configuration globale (dépendance cachée)
        ConfigurationMediatheque config = ConfigurationMediatheque.getInstance();
// Quota atteint : on refuse avant toute chose
        if (empruntsEnCours >= config.getNbMaxEmprunts()) {
            throw new DocumentIndisponibleException(titre,
                    "quota d'emprunts atteint (" + config.getNbMaxEmprunts() + ")");
        }
// 1. Recherche (délégation au catalogue) : absent -> exception métier
        Document doc = catalogue.rechercherParTitre(titre)
                .orElseThrow(() -> new DocumentIntrouvableException(titre));
// 2. Vérification de la capacité "Empruntable" (pattern variable, Java 16+)
        if (!(doc instanceof Empruntable e)) {
            throw new DocumentIndisponibleException(titre, "consultation sur place uniquement");
        }
// 3. Emprunt : peut lever DocumentIndisponibleException (déjà emprunté).
// Si l'exception est levée, on sort ici et le compteur n'est PAS incrémenté.
        e.emprunter();
        empruntsEnCours++;
    }
    // Méthode SÉPARÉE : c'est ici que la durée d'emprunt en jours est utilisée.
// Date de retour = date de début + durée d'emprunt de la configuration.
    public LocalDate dateRetour(LocalDate debut) {
        return debut.plusDays(ConfigurationMediatheque.getInstance().getDureeEmpruntJours());
    }
}