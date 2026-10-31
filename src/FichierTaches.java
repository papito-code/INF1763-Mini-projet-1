
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
public class FichierTaches {

    public String tacheEnLigne(Tache tache) {
        return tache.getId() + ";" + tache.getTitre() + ";" + tache.getPriorite() + ";" + (tache.getEcheance() == null ? "" : tache.getEcheance()) + ";" + tache.getStatut();
    }

    public void sauvegarder(ArrayList<Tache> taches) {
        ArrayList<String> saves = new ArrayList<>();

        for (Tache tache : taches) {
            saves.add(tacheEnLigne(tache));
        }
        try {
            Files.write(Path.of("taches.csv"), saves, StandardCharsets.UTF_8);
            System.out.println("Sauvegarde réussie : " + saves.size() + " tâche(s) enregistrée(s).");
        } catch (IOException e) {
            System.out.println("Échec de la sauvegarde : Impossible d'écrire dans taches.csv ("
                    + e.getMessage() + "). Vos données n'ont pas été sauvegardées.");
        }
    }

}