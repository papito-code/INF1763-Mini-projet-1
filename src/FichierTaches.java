
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
public class FichierTaches {
    
    private static final Path CHEMIN = Path.of("taches.csv");

    public boolean fichierExiste() {
        return Files.exists(CHEMIN);
    }

    public String tacheEnLigne(Tache tache) {
        return tache.getId() + ";" + tache.getTitre() + ";" + tache.getPriorite() +";" + (tache.getEcheance()==null ? "":tache.getEcheance()) +";" + tache.getStatut();
    }
    public void sauvegarder(ArrayList<Tache> taches){
        ArrayList<String> saves = new ArrayList<>();

        for (Tache tache :taches){
            saves.add(tacheEnLigne(tache)) ;
        }
        
        try {
            Files.write(CHEMIN, saves  , StandardCharsets.UTF_8);
            System.out.println("Sauvegarde réussie : " + saves.size() + " tâche(s) enregistrée(s).");
        } catch (IOException e) {
            System.out.println( "Impossible d'effectuer la sauvegarde.");
        }

    }
    
}
