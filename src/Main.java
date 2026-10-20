import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.nio.charset.Charset;
import java.util.Scanner;

import javax.swing.plaf.TreeUI;

public class Main {

    public static void main(String[] args) {
        // On lit le clavier avec l'encodage de la console (sous Windows, ce n'est pas
        // UTF-8).
        // Sans cela, les accents tapés (ex. « révision », « Élevée ») arrivent
        // déformés.
        Charset encodage = Charset.defaultCharset();
        if (System.console() != null) {
            encodage = System.console().charset();
        }
        Scanner scanner = new Scanner(System.in, encodage);
        GestionnaireDeTaches gestionnaire = new GestionnaireDeTaches();

        while (true) {
            System.out.print(
                    "\n1. Ajouter une tâche \n2. Afficher les tâches\n3. Terminer une tâche \n4. Modifier la priorité \n5. Quitter\nChoix : ");
            String texte = scanner.nextLine();
            int choix;
            try {
                choix = Integer.parseInt(texte);

            } catch (NumberFormatException e) {
                System.out.println("Veuillez choisir un numéro de la liste merci.");

                continue;
            }

            //ajoute une tache
            if (choix == 1) {

                System.out.println(); //saut a ligne


                boolean annulation = false; //verifie si l'utilisateur a annulé la création de tâche

                // 1. Le titre : obligatoire
                String titre = "";
                while (titre.isEmpty()) {
                    System.out.print("Titre : ");
                    titre = scanner.nextLine().trim();
                    if (titre.equalsIgnoreCase("annuler")) {
                        System.out.println("Ajout annulé.");
                        annulation = true;
                        break;

                    }
                    if (titre.isEmpty()) {
                        System.out.println("Erreur : le titre est obligatoire.");
                    }
                }

                // 2. La priorité : Normale si l'utilisateur appuie sur Entrée
                if (!annulation){
                    String priorite = null;
                    while (priorite == null) {
                        // On accepte le numéro ou le nom : le numéro évite les problèmes
                        // d'accents dans la console Windows.
                        System.out.print("Priorité (1 = Basse, 2 = Normale, 3 = Élevée) [Entrée = Normale] : ");
                        String saisie = scanner.nextLine().trim();
                        if (saisie.equalsIgnoreCase("annuler")) {
                            System.out.println("Ajout annulé.");
                            annulation = true;
                            break;
                        }
                        if (saisie.isEmpty() || saisie.equals("2") || saisie.equalsIgnoreCase("normale")) {
                            priorite = "Normale";
                        } else if (saisie.equals("1") || saisie.equalsIgnoreCase("basse")) {
                            priorite = "Basse";
                        } else if (saisie.equals("3") || saisie.equalsIgnoreCase("élevée") || saisie.equalsIgnoreCase("elevee")) {
                            priorite = "Élevée";
                        } else {
                            System.out.println("Erreur : choisissez 1 (Basse), 2 (Normale) ou 3 (Élevée).");
                        }
                    }

                    // 3. L'échéance : facultative, au format AAAA-MM-JJ
                    if (!annulation){
                        LocalDate echeance = null;
                        boolean echeanceValide = false;
                        while (!echeanceValide) {
                            System.out.print("Échéance (AAAA-MM-JJ) [Entrée = aucune] : ");
                            String saisie = scanner.nextLine().trim();
                            if (saisie.equalsIgnoreCase("annuler")) {
                                System.out.println("Ajout annulé.");
                                annulation = true;
                                break; 
                            }
                            if (saisie.isEmpty()) {
                                echeanceValide = true;
                            } else 
                                try {
                                    echeance = LocalDate.parse(saisie);
                                    echeanceValide = true;
                                    // Une date passée est acceptée (la tâche est en retard), mais on prévient
                                    // l'utilisateur.
                                    if (echeance.isBefore(LocalDate.now())) {
                                        System.out.println(
                                                "Attention : cette date d'échéance est déjà passée. La tâche sera quand même créée.");
                                    }
                                } catch (DateTimeParseException e) {
                                    System.out.println("Erreur : date invalide. Utilisez le format AAAA-MM-JJ avec une date qui existe.");
                                }
                            }

                    
                        // 4. Création de la tâche et confirmation
                        if (!annulation){

                            System.out.println();

                            Tache tache = gestionnaire.ajouterTache(titre, priorite, echeance);

                            System.out.println("Tâche ajoutée avec succès.");
                            System.out.println("Identifiant : " + tache.getId());
                            System.out.println("Titre : " + tache.getTitre());
                            System.out.println("Priorité : " + tache.getPriorite());
                            if (tache.getEcheance() == null) {
                                System.out.println("Échéance : Aucune");
                            } else {
                                System.out.println("Échéance : " + tache.getEcheance());
                            }
                            System.out.println("Statut : " + tache.getStatut());
                            System.out.println();
                        }
                    }
                }
                   
                          

            } else if (choix == 2) {
                System.out.println();
                gestionnaire.affichertaches();

            } else if (choix == 3) {
                System.out.println();
                System.out.print("Identifiant de la tâche : ");
                int id;
                try {
                    id = Integer.parseInt(scanner.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("Identifiant invalide : entrez un nombre entier positif.");
                    continue;
                }
                if (id <= 0) {
                    System.out.println("Identifiant invalide : entrez un nombre entier positif.");
                    continue;
                }
                try {
                    Tache tache = gestionnaire.terminerTache(id);
                    System.out.println("La tâche " + id + " (« " + tache.getTitre()
                            + " ») est maintenant terminée. Statut : À faire -> Terminée.");
                } catch (IllegalArgumentException | IllegalStateException e) {
                    System.out.println(e.getMessage());
                }

            } else if (choix == 4) {
                System.out.println();
                System.out.print("Identifiant de la tâche : ");
                int id;
                try {
                    id = Integer.parseInt(scanner.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("Identifiant invalide : entrez un nombre entier positif.");
                    continue;
                }
                if (id <= 0) {
                    System.out.println("Identifiant invalide : entrez un nombre entier positif.");
                    continue;
                }
                System.out.print("Nouvelle priorité (Basse, Normale ou Élevée) : ");
                String priorite = scanner.nextLine();
                try {
                    if (gestionnaire.modifierPriorite(id, priorite)) {
                     
                    } else {
                        System.out.println("La tâche " + id + " a déjà la priorité "
                                + gestionnaire.trouverParId(id).getPriorite()
                                + ". Aucune modification n'a été faite.");
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }

            } else if (choix == 5) {
                System.out.println();
                System.out.println("Au revoir !");
                break;

            } else {
                System.out.println("Veuillez choisir un numéro de la liste merci.");
            }
        }

        scanner.close();
    }

}

