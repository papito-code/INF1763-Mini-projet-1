import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class FichierTaches {

    private static final Path CHEMIN = Path.of("taches.csv");

    public boolean fichierExiste() {
        return Files.exists(CHEMIN);
    }

    public String tacheEnLigne(Tache tache) {
        return tache.getId() + ";" + tache.getTitre() + ";" + tache.getPriorite() + ";"
                + (tache.getEcheance() == null ? "" : tache.getEcheance()) + ";" + tache.getStatut();
    }

    public void sauvegarder(ArrayList<Tache> taches) {
        ArrayList<String> saves = new ArrayList<>();

        for (Tache tache : taches) {
            saves.add(tacheEnLigne(tache));
        }

        try {
            Files.write(CHEMIN, saves, StandardCharsets.UTF_8);
            System.out.println("Sauvegarde réussie : " + saves.size() + " tâche(s) enregistrée(s).");
        } catch (IOException e) {
            System.out.println("Échec de la sauvegarde : Impossible d'écrire dans taches.csv ("
                    + e.getMessage() + "). Vos données n'ont pas été sauvegardées.");
        }
    }

    // Lève IllegalArgumentException avec le numéro de ligne si une ligne est mal formée.
    public ArrayList<Tache> charger() throws IOException {
        ArrayList<Tache> taches = new ArrayList<>();
        Set<Integer> identifiantsVus = new HashSet<>();
        int numero = 0;

        for (String ligne : Files.readAllLines(CHEMIN, StandardCharsets.UTF_8)) {
            numero++;
            if (ligne.isBlank()) {
                continue;
            }
            Tache tache = ligneEnTache(ligne, numero);
            if (!identifiantsVus.add(tache.getId())) {
                throw new IllegalArgumentException(
                        "ligne " + numero + ", identifiant en double (" + tache.getId() + ")");
            }
            taches.add(tache);
        }
        return taches;
    }

    private Tache ligneEnTache(String ligne, int numero) {
        String[] champs = ligne.split(";", -1);
        if (champs.length != 5) {
            throw new IllegalArgumentException(
                    "ligne " + numero + ", 5 colonnes attendues au lieu de " + champs.length);
        }

        int id;
        try {
            id = Integer.parseInt(champs[0]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ligne " + numero + ", identifiant invalide");
        }
        if (id <= 0) {
            throw new IllegalArgumentException("ligne " + numero + ", identifiant invalide");
        }

        if (champs[1].isBlank()) {
            throw new IllegalArgumentException("ligne " + numero + ", titre vide");
        }

        String priorite = champs[2];
        if (!priorite.equals("Basse") && !priorite.equals("Normale") && !priorite.equals("Élevée")) {
            throw new IllegalArgumentException("ligne " + numero + ", priorité invalide");
        }

        LocalDate echeance = null;
        if (!champs[3].isEmpty()) {
            try {
                echeance = LocalDate.parse(champs[3]);
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("ligne " + numero + ", échéance invalide");
            }
        }

        String statut = champs[4];
        if (!statut.equals("À faire") && !statut.equals("Terminée")) {
            throw new IllegalArgumentException("ligne " + numero + ", statut invalide");
        }

        Tache tache = new Tache(id, champs[1], priorite, echeance);
        tache.setStatut(statut);
        return tache;
    }

}
