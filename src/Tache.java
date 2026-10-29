import java.time.LocalDate;

public class Tache {

    private int id;
    private String titre;
    private String priorite;     // "Basse", "Normale" ou "Élevée"
    private LocalDate echeance;  // null si la tâche n'a pas d'échéance
    private String statut;       // "À faire" ou "Terminée"

    public Tache(int id, String titre, String priorite, LocalDate echeance) {
        this.id = id;
        this.titre = titre;
        this.priorite = priorite;
        this.echeance = echeance;
        this.statut = "À faire";
    }

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getPriorite() {
        return priorite;
    }

    public void setPriorite(String priorite) {
        this.priorite = priorite;
    }

    public LocalDate getEcheance() {
        return echeance;
    }

    public void setEcheance(LocalDate echeance) {
        this.echeance = echeance;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    //afficher une tache et ses propriétés
    public void printTache(){
        System.out.println("Tâche " +id+ " : "
            + "\nTitre : " + titre
            +  "\nPriorite : " + priorite
            + "\nDate d'échéance : " + (echeance == null ? "Aucune" : echeance)
            + "\nStatut : " + statut
            );
    }

    public void removeEcheance(){
        this.echeance = null;
    }

}
