import java.time.LocalDate;
import java.util.ArrayList;

public class GestionnaireDeTaches {

    private ArrayList<Tache> taches = new ArrayList<>();
    private int prochainId = 1;

    // Crée une tâche avec le prochain identifiant et l'ajouter à la liste.
    public Tache ajouterTache(String titre, String priorite, LocalDate echeance) {
        Tache tache = new Tache(prochainId, titre, priorite, echeance);
        taches.add(tache);
        prochainId++;
        return tache;
    }

    public ArrayList<Tache> getTaches() {
        return taches;
    }

    // Renvoie la tâche qui a cet identifiant, ou null si elle n'existe pas.
    public Tache trouverParId(int id) {
        for (Tache tache : taches) {
            if (tache.getId() == id) {
                return tache;
            }
        }
        return null;
    }
}
