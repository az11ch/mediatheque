package mediatheque;

public class Livre extends Document implements Empruntable {
    private final String auteur;
    private final int nbPages;
    private boolean disponible = true;

    public Livre(String titre, int annee, String auteur, int nbPages) {
        super(titre, annee);
        this.auteur = auteur;
        this.nbPages = nbPages;
    }

    public String getAuteur() {
        return auteur;
    }

    public int getNbPages() {
        return nbPages;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String descriptionCourte() {
        return "[Livre]" + getTitre() + "(" + annee + ")," + auteur + "," + nbPages + "p";
    }

    @Override
    public void emprunter() {
        if (!disponible) {
            throw new IllegalStateException("Deja emprunter :" + getTitre());
        }
        disponible = false;
    }

    @Override
    public void rendre() {
        disponible = true;
    }

    @Override
    public boolean estDisponible() {
        return disponible;
    }

}
