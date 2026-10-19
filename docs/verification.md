# Vérification de TaskFlow

Ce document liste les scénarios de vérification de TaskFlow et le résultat de leur exécution sur la branche `main`. Les essais ont été faits le 2026-10-19, au commit `b22271f`, avec le JDK suivant :

    $ java -version
    openjdk version "25.0.4-ea" 2026-07-21
    OpenJDK Runtime Environment (build 25.0.4-ea+4-1-Debian)
    OpenJDK 64-Bit Server VM (build 25.0.4-ea+4-1-Debian, mixed mode, sharing)

Compilation et lancement, depuis la racine du projet :

    javac -d out src/*.java
    java -cp out Main

Les entrées ont été envoyées au programme par redirection de l'entrée standard, dans un dossier temporaire situé hors du dépôt. Toutes les données de test sont fictives.

Le menu ne propose pas encore l'affichage de la liste (l'option 2 répond « en cours de conception »). L'état des tâches après les scénarios 5, 6 et 7 a donc été contrôlé avec un petit programme hors du dépôt, qui appelle directement `GestionnaireDeTaches` avec les mêmes données.

## Scénarios

| N° | Scénario | Données utilisées | Résultat attendu | Résultat observé | Conclusion |
|---|---|---|---|---|---|
| 1 | Liste vide | Aucune tâche, choix Afficher | Message explicite indiquant qu'aucune tâche n'est présente | Non exécuté : fonctionnalité pas encore livrée | À exécuter |
| 2 | Ajout valide | Titre « Vérifier la sauvegarde du serveur », priorité et échéance omises (Entrée) | Identifiant attribué, priorité Normale, sans échéance, statut À faire, message de confirmation avec l'identifiant | « Tâche ajoutée avec succès. » puis Identifiant 1, Priorité Normale, Échéance Aucune, Statut À faire | Conforme |
| 3 | Titre invalide | Titre vide, puis titre composé de trois espaces, puis « annuler » | Refusé avec un message, aucune tâche ajoutée | « Erreur : le titre est obligatoire. » pour chacune des deux saisies, le titre est redemandé ; « annuler » ramène au menu. La tâche ajoutée ensuite reçoit l'identifiant 1, donc aucune tâche n'avait été créée | Conforme |
| 4 | Liste de plusieurs tâches | Deux tâches, dont une sans échéance, choix Afficher | Toutes affichées avec identifiant, titre, priorité, échéance ou absence d'échéance, statut | Non exécuté : fonctionnalité pas encore livrée | À exécuter |
| 5 | Tâche terminée | Tâches 1 et 2 (« Tâche test A », « Tâche test B »), terminer la tâche 1 deux fois | Seul le statut de cette tâche change ; la seconde tentative est signalée sans modification | 1re tentative : « La tâche 1 (« Tâche test A ») est maintenant terminée. Statut : À faire -> Terminée. » 2e tentative : « La tâche 1 est déjà terminée. Aucune modification n'est nécessaire. » Contrôle de l'état : seule la tâche 1 passe à Terminée, titre, priorité, échéance et identifiant inchangés, tâche 2 inchangée, toujours deux tâches | Conforme |
| 6 | Identifiant inconnu | Identifiant 99 pour terminer, modifier la priorité et gérer l'échéance | Message clair, données existantes conservées | Terminer et modifier la priorité : « Aucune tâche avec l'identifiant 99. » ; données inchangées au contrôle. Pour la priorité, le message arrive après la saisie de la nouvelle priorité. Gestion de l'échéance : non exécuté, fonctionnalité pas encore livrée | Conforme pour terminer et priorité ; À exécuter pour l'échéance |
| 7 | Priorité | Tâche 1 en Normale, puis Basse, Normale, Élevée, puis « urgente » (option 4) | Valeurs autorisées acceptées ; valeur invalide refusée sans modifier la priorité actuelle | Basse, Normale et Élevée : « Priorité de la tâche 1 modifiée avec succès. » ; « urgente » : « Priorité invalide. Choisissez Basse, Normale ou Élevée. » La priorité reste Élevée au contrôle | Conforme |
| 8 | Échéance | Attribuer 2026-10-08, la modifier, la retirer ; essayer « 10/08/2026 » et « 2026-02-30 » | Ajout, modification et retrait fonctionnent ; format incorrect et date impossible refusés, échéance existante inchangée | À la création de la tâche seulement : « 10/08/2026 » et « 2026-02-30 » refusées avec « Erreur : date invalide. Utilisez le format AAAA-MM-JJ avec une date qui existe. » ; 2026-10-08 acceptée avec l'avertissement « cette date d'échéance est déjà passée » et la tâche est créée avec cette échéance. Modification et retrait de l'échéance sur une tâche existante : non exécuté, fonctionnalité pas encore livrée | À exécuter |
| 9 | Sauvegarde et redémarrage | Deux tâches (une terminée, une sans échéance), sauvegarder, quitter, relancer | Mêmes identifiants, informations, statuts, et échéance absente conservée | Non exécuté : fonctionnalité pas encore livrée | À exécuter |
| 10 | Ajout après rechargement | Ajouter une tâche après le redémarrage du scénario 9 | Identifiant distinct de ceux déjà chargés | Non exécuté : fonctionnalité pas encore livrée | À exécuter |
| 11 | Fichier absent ou mal formé | Démarrer sans fichier ; démarrer avec un fichier de données mal formé | Liste vide dans le premier cas ; message et démarrage interrompu sans modifier le fichier dans le second | Non exécuté : fonctionnalité pas encore livrée | À exécuter |
| 12 | Échec d'écriture | Sauvegarder alors que le fichier est en lecture seule | Échec signalé, aucun message de réussite | Non exécuté : fonctionnalité pas encore livrée | À exécuter |
