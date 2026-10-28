import java.nio.charset.Charset;
import java.sql.Array;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;



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
        FichierTaches fichier = new FichierTaches();

        while (true) {
            
            // liste des taches
            ArrayList<Tache> taches = gestionnaire.getTaches();

            System.out.print(
                    "\n1. Ajouter une tâche \n2. Afficher les tâches \n3. Terminer une tâche \n4. Modifier la priorité \n5. Attribuer une échéance \n6. Modifier une échéance \n7. Retirer une échéance \n8.Sauvergarder \n9. Quitter \n\nChoix : "
                );
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
                   
                          
            //choix 2 : Afficher les taches
            } else if (choix == 2) {
                System.out.println();
                gestionnaire.afficherTaches();

            //choix == 3 terminer une tache
            } else if (choix == 3) {

                // verifie si il y a des taches qui existe avant quoi que ce soit
                if (taches.size() < 1) {
                    System.out.println("\nAucune tâche crée pour l'instant");
                    continue;
                }

                int id = askId(taches, scanner);

                try {
                    Tache tache = gestionnaire.terminerTache(id);
                    System.out.println("La tâche " + id + " (« " + tache.getTitre()
                            + " ») est maintenant terminée. Statut : À faire -> Terminée.");
                } catch (IllegalArgumentException | IllegalStateException e) {
                    System.out.println(e.getMessage());
                }
            
            // choix == 4 modifier une priorite
            } else if (choix == 4) {

                // verifie si il y a des taches qui existe avant quoi que ce soit
                if (taches.size() < 1) {
                    System.out.println("\nAucune tâche crée pour l'instant");
                    continue;
                }

                // demande et verifie la validite de l'id
                int id = askId(taches, scanner);

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
            
            //attribuer une echeance
            } else if (choix == 5) {
                
            
            //choix 6 : modifier une échéance
            } else if (choix == 6) {
                // verifie si il y a des taches qui existe avant quoi que ce soit
                if (taches.size() < 1) {
                    System.out.println("\nAucune tâche crée pour l'instant");
                    continue;
                }

                System.out.println();

                // demande et verifie la validite de l'id
                int id = askId(taches, scanner);

                LocalDate echeance = null;
                boolean echeanceValide = false;
                while (!echeanceValide) {
                            System.out.print("Nouvelle Échéance: (AAAA-MM-JJ) [Entrée = aucune] : ");
                            String saisie = scanner.nextLine().trim();
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
                                                "\nAttention : cette date d'échéance est déjà passée. L'échéance sera quand même modifier.");
                                    }
                                } catch (DateTimeParseException e) {
                                    System.out.println("Erreur : date invalide. Utilisez le format AAAA-MM-JJ avec une date qui existe.");
                                }
                }

                System.out.println();

                try {
                    Tache tache = gestionnaire.modifierEcheance(id, echeance);
                    System.out.println("La tâche " + id + " (« " + tache.getTitre()
                            + " ») a maintenant pour nouvelle echeance : " + (tache.getEcheance() == null ? "aucune écheance": tache.getEcheance()));
                } catch (IllegalArgumentException | IllegalStateException e) {
                    System.out.println(e.getMessage());
                }

                
            
            //choix 7 : retirer une échéance
            } else if (choix == 7) {

                // verifie si il y a des taches qui existe avant quoi que ce soit
                if (taches.size() < 1) {
                    System.out.println("\nAucune tâche crée pour l'instant");
                    continue;
                }

                // demande et verifie la validite de l'id
                int id = askId(taches, scanner);
                System.out.println();

                try {
                    Tache tache = gestionnaire.retirerEcheance(id);
                    System.out.println("La tâche " + id + " (« " + tache.getTitre()
                            + " ») est maintenant sans échéance. Échéance : Aucune.");
                } catch (IllegalArgumentException | IllegalStateException e) {
                    System.out.println(e.getMessage());
                }

            //choix 8 : sauvegarder
            }else if (choix == 8) {
                  System.out.println();
                  fichier.sauvegarder( gestionnaire.getTaches() );

            } else if (choix == 9) {
                System.out.println();
                System.out.println("Au revoir !");
                break;

            } else {
                System.out.println("Veuillez choisir un numéro de la liste merci.");
            }
        }

        scanner.close();
    }

    public static int askId( ArrayList<Tache> taches, Scanner scanner) {





        boolean idValide = false;
        int id = 0;

        while (!idValide){
            System.out.print("Identifiant de la tâche : ");
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

            // verifier la si la tache existe
            try {
                if (taches.get(id-1).getId() != id){
                continue;
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("\nAcune tâche avec l'identifiant " + id + " Réesayez :");
                System.out.println();
                continue;
            }
            idValide = true;
            
        }

        

        return id;

    }

}

