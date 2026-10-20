import java.time.LocalDate;
import java.util.ArrayList;
import java.text.Normalizer;

public class GestionnaireDeTaches {

    private ArrayList<Tache> taches = new ArrayList<>();
    private int prochainId = 1;

    // US-01 : Crée une tâche avec le prochain identifiant et l'ajouter à la liste.
    public Tache ajouterTache(String titre, String priorite, LocalDate echeance) {
        Tache tache = new Tache(prochainId, titre, priorite, echeance);
        taches.add(tache);
        prochainId++;
        return tache;
    }

    public ArrayList<Tache> getTaches() {
        return taches;
    }

    // US-02 : Afficher la liste des taches
    public void affichertaches(){
        if (taches.size() == 0){
            System.out.println("Aucune tâche crée pour l'instant");
        }
        else{
            for (Tache tache : taches){
                tache.printTache();
                System.out.println();
            }
        }
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

    // US-03 : marque une tâche existante comme terminée.
    public Tache terminerTache(int id) {
        Tache tache = trouverParId(id);
        if (tache == null) {
            throw new IllegalArgumentException("Aucune tâche avec l'identifiant " + id + ".");
        }
        if (tache.getStatut().equals("Terminée")) {
            throw new IllegalStateException(
                    "La tâche " + id + " est déjà terminée. Aucune modification n'est nécessaire.");
        }
        tache.setStatut("Terminée");
        return tache;
    }

    // US-04 : modifie la priorité d'une tâche existante.
    // Renvoie false si la tâche avait déjà cette priorité.
    public boolean modifierPriorite(int id, String saisie) {
        Tache tache = trouverParId(id);
        if (tache == null) {
            throw new IllegalArgumentException("Aucune tâche avec l'identifiant " + id + ".");
        }
        String priorite = normaliserPriorite(saisie);
        if (priorite == null) {
            throw new IllegalArgumentException("Priorité invalide. Choisissez Basse, Normale ou Élevée.");
        }
        if (tache.getPriorite().equals(priorite)) {
            return false;
        }
        tache.setPriorite(priorite);
        return true;
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

    //US-06 : Modifier une échéance
    public Tache modifierEcheance(int id, LocalDate echeance){

        Tache tache = trouverParId(id);

        if (tache == null) {
            throw new IllegalArgumentException("Aucune tâche avec l'identifiant " + id + ".");
        }

        tache.setEcheance(echeance);
        return tache;

    }

    // US-07 : retirer une echéance
    public Tache retirerEcheance(int id) {
        Tache tache = trouverParId(id);

        if (tache == null) {
            throw new IllegalArgumentException("Aucune tâche avec l'identifiant " + id + ".");
        }

        if (tache.getEcheance() == null) {
            throw new IllegalStateException(
                    "La tâche " + id + " n'a déjà pas d'échéance.");
        }

        tache.removeEcheance();

        return tache;
        
    }

}


