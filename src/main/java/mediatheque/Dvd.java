package mediatheque;

public class Dvd extends Document {

    protected Dvd(String titre, int annee) {
        super(titre, annee);
    }

    @Override
    public String descriptionCourte() {
        return "";
    }
}
