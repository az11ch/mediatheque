package mediatheque;

public class ConfigurationMediatheque {
    // Valeurs par défaut de la médiathèque
    private int dureeEmpruntJours = 21;
    private int nbMaxEmprunts = 5;
    // Constructeur PRIVÉ : personne en dehors de cette classe ne peut faire "new"
    public ConfigurationMediatheque() { }
    // Idiome du HOLDER : la classe interne n'est chargée par la JVM qu'au premier
// appel de getInstance(). L'instance est donc créée à ce moment (paresse),
// une seule fois (thread-safe), sans synchronized.
    private static class Holder {
        static final ConfigurationMediatheque INSTANCE = new ConfigurationMediatheque();
    }
    // Point d'accès GLOBAL (c'est lui que le cours critique)
    public static ConfigurationMediatheque getInstance() {
        return Holder.INSTANCE;
    }
    public int getDureeEmpruntJours() { return dureeEmpruntJours; }
    public int getNbMaxEmprunts() { return nbMaxEmprunts; }
    // Setters : nécessaires pour fabriquer le "flagrant délit" de l'étape 2
    public void setDureeEmprunt(int jours) { this.dureeEmpruntJours = jours; }
    public void setNbMaxEmprunts(int n) { this.nbMaxEmprunts = n; }
}

