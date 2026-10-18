import java.time.LocalDate;
import java.util.ArrayList;
import java.text.Normalizer;

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
    // US-04 : modifie la priorité d'une tâche existante.
    public void modifierPriorite(int id, String saisie) {
        Tache tache = trouverParId(id);
        if (tache == null) {
            throw new IllegalArgumentException("Aucune tâche avec l'identifiant " + id + ".");
        }
        String priorite = normaliserPriorite(saisie);
        if (priorite == null) {
            throw new IllegalArgumentException("Priorité invalide. Choisissez Basse, Normale ou Élevée.");
        }
        tache.setPriorite(priorite);
    }

    // Accepte la saisie sans tenir compte de la casse ni des accents.
    private String normaliserPriorite(String saisie) {
        if (saisie == null) {
            return null;
        }
        String texte = Normalizer.normalize(saisie.trim(), Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .toLowerCase();
        switch (texte) {
            case "basse":   return "Basse";
            case "normale": return "Normale";
            case "elevee":  return "Élevée";
            default:        return null;
        }
    }
}
