package mediatheque;

import mediatheque.exception.DocumentIndisponibleException;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CatalogueTest {

    @Test
    void testAjouterLivre() {
        Catalogue<Livre> catalogue = new Catalogue<>();
        Livre l1 = new Livre("blanche neige", 2001, "aziz", 120);
        catalogue.ajouter(l1);

        // Assert : vérifier le résultat
        assertEquals(1, catalogue.getElements().size());
        assertTrue(catalogue.getElements().contains(l1));
    }

    @Test
    void rechercherParTitreTrue() {
        Catalogue<Document> c = new Catalogue<>();
        Livre l1 = new Livre("blanche neige", 2001, "aziz", 120);
        c.ajouter(new Revue("Science", 2026, 412));
        c.ajouter(l1);

        assertTrue(c.rechercherParTitre("Science").isPresent());
        assertEquals(c.rechercherParTitre("blanche neige"), Optional.of(l1));
    }

    @Test
    void empruntNominal() throws DocumentIndisponibleException {
        Livre l = new Livre("Clean Code", 2008, "R. Martin", 464);
        l.emprunter();
        assertFalse(l.estDisponible());
        l.rendre();
        assertTrue(l.estDisponible());
    }

    @Test
    void doubleEmpruntLeveException() throws DocumentIndisponibleException {
        Livre l = new Livre("Refactoring", 1999, "M. Fowler", 448);
        l.emprunter();
        DocumentIndisponibleException ex =
                assertThrows(DocumentIndisponibleException.class, l::emprunter);
        assertTrue(ex.getMessage().contains("Refactoring"));
    }

    @Test
    void rechercheInfructueuse() {
        Catalogue<Document> c = new Catalogue<>();
        c.ajouter(new Revue("Science", 2026, 412));
        assertTrue(c.rechercherParTitre("Nature").isEmpty());
        assertEquals(c.rechercherParTitre("Nature"), Optional.empty());
    }

    @Test
    void deux_parcours_sont_independants() {
        Catalogue<Document> catalogue = new Catalogue<>();
        catalogue.ajouter(new Livre("Clean Code", 2008, "R. Martin", 464));
        catalogue.ajouter(new Revue("Pour la Science", 2024, 12));

        Iterator<Document> a = catalogue.iterator();
        Iterator<Document> b = catalogue.iterator();
        a.next();
        a.next();

        assertEquals("Clean Code", b.next().getTitre());
    }

    @Test
    void remove_est_interdict() {
        Catalogue<Document> catalogue = new Catalogue<>();
        catalogue.ajouter(new Livre("Clean Code", 2008, "R. Martin", 464));

        Iterator<Document> it = catalogue.iterator();
        it.next();

        assertThrows(UnsupportedOperationException.class, it::remove);
    }

    @Test
    void catalogue_vide_n_a_pas_de_suivant() {
        assertFalse(new Catalogue<Livre>().iterator().hasNext());
    }
    @Test void recherche_ignore_la_casse() {
        Catalogue<Document> c = new Catalogue<>();
        c.ajouter(new Dvd("Inception", 2010, 148));
        assertTrue(c.rechercherParTitre("INCEPTION").isPresent());
    }




}
