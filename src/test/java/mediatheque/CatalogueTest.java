package mediatheque;

import org.junit.jupiter.api.Test;

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

    @Test void empruntNominal() {
        Livre l = new Livre("Clean Code", 2008, "R. Martin", 464);
        l.emprunter();
        assertFalse(l.estDisponible());
        l.rendre();
        assertTrue(l.estDisponible());
    }

    @Test void doubleEmpruntLeveException() {
        Livre l = new Livre("Refactoring", 1999, "M. Fowler", 448);
        l.emprunter();
        assertThrows(IllegalStateException.class, l::emprunter);
    }

    @Test void rechercheInfructueuse() {
        Catalogue<Document> c = new Catalogue<>();
        c.ajouter(new Revue("Science", 2026, 412));
        assertTrue(c.rechercherParTitre("Nature").isEmpty());
        assertEquals(c.rechercherParTitre("Nature"), Optional.empty());
    }
}