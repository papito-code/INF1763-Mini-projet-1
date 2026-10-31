# Vérification de TaskFlow

Ce document liste les scénarios de vérification de TaskFlow et le résultat de leur exécution sur la branche `main`. Les essais ont été faits le 2026-10-23, au commit `d581d3c`, avec le JDK suivant :

    $ java -version
    openjdk version "25.0.4-ea" 2026-07-21
    OpenJDK Runtime Environment (build 25.0.4-ea+4-1-Debian)
    OpenJDK 64-Bit Server VM (build 25.0.4-ea+4-1-Debian, mixed mode, sharing)

Compilation et lancement, depuis la racine du projet :

    javac -d out src/*.java
    java -cp out Main

Les entrées ont été envoyées au programme par redirection de l'entrée standard, dans un dossier temporaire situé hors du dépôt. Toutes les données de test sont fictives.

Après chaque scénario exécuté, l'état des tâches a été contrôlé avec l'option 2 (Afficher les tâches).

## Scénarios

| N° | Scénario | Données utilisées | Résultat attendu | Résultat observé | Conclusion |
|---|---|---|---|---|---|
| 1 | Liste vide | Aucune tâche, choix Afficher | Message explicite indiquant qu'aucune tâche n'est présente | « Aucune tâche crée pour l'instant » | Conforme |
| 2 | Ajout valide | Titre « Vérifier la sauvegarde du serveur », priorité et échéance omises (Entrée) | Identifiant attribué, priorité Normale, sans échéance, statut À faire, message de confirmation avec l'identifiant | « Tâche ajoutée avec succès. » puis Identifiant 1, Priorité Normale, Échéance Aucune, Statut À faire | Conforme |
| 3 | Titre invalide | Titre vide, puis titre composé de trois espaces, puis « annuler » | Refusé avec un message, aucune tâche ajoutée | « Erreur : le titre est obligatoire. » pour chacune des deux saisies, le titre est redemandé ; « annuler » ramène au menu. L'affichage répond ensuite « Aucune tâche crée pour l'instant » | Conforme |
| 4 | Liste de plusieurs tâches | « Tâche test A » (Élevée, échéance 2026-11-30) et « Tâche test B » (Normale, sans échéance), choix Afficher | Toutes affichées avec identifiant, titre, priorité, échéance ou absence d'échéance, statut | Les deux tâches sont affichées : « Tâche 1 », titre, « Priorite : Élevée », « Date d'échéance : 2026-11-30 », « Statut : À faire », puis « Tâche 2 » avec « Date d'échéance : Aucune » | Conforme |
| 5 | Tâche terminée | Tâches 1 et 2 (« Tâche test A », « Tâche test B »), terminer la tâche 1 deux fois | Seul le statut de cette tâche change ; la seconde tentative est signalée sans modification | 1re tentative : « La tâche 1 (« Tâche test A ») est maintenant terminée. Statut : À faire -> Terminée. » 2e tentative : « La tâche 1 est déjà terminée. Aucune modification n'est nécessaire. » L'affichage montre la tâche 1 en Terminée avec titre, priorité, échéance et identifiant inchangés, et la tâche 2 inchangée | Conforme |
| 6 | Identifiant inconnu | Identifiant 99 pour terminer, modifier la priorité et gérer l'échéance | Message clair, données existantes conservées | Terminer et modifier la priorité : « Aucune tâche avec l'identifiant 99. » ; l'affichage montre la tâche 1 inchangée. Pour la priorité, le message arrive après la saisie de la nouvelle priorité. Gestion de l'échéance : non exécuté, fonctionnalité pas encore livrée | Conforme pour terminer et priorité ; À exécuter pour l'échéance |
| 7 | Priorité | Tâche 1 en Normale, puis Basse, Normale, Élevée, puis « urgente » (option 4) | Valeurs autorisées acceptées ; valeur invalide refusée sans modifier la priorité actuelle | Basse, Normale et Élevée sont acceptées, mais sans aucun message de confirmation : le menu revient directement. « urgente » : « Priorité invalide. Choisissez Basse, Normale ou Élevée. » L'affichage montre ensuite la priorité Élevée | Conforme |
| 8 | Échéance | Attribuer 2026-10-08, la modifier, la retirer ; essayer « 10/08/2026 » et « 2026-02-30 » | Ajout, modification et retrait fonctionnent ; format incorrect et date impossible refusés, échéance existante inchangée | À la création de la tâche seulement : « 10/08/2026 » et « 2026-02-30 » refusées avec « Erreur : date invalide. Utilisez le format AAAA-MM-JJ avec une date qui existe. » ; 2026-10-08 acceptée avec l'avertissement « cette date d'échéance est déjà passée » et la tâche est créée avec cette échéance. Modification et retrait de l'échéance sur une tâche existante : non exécuté, fonctionnalité pas encore livrée | À exécuter |
| 9 | Sauvegarde et redémarrage | Deux tâches (une terminée, une sans échéance), sauvegarder, quitter, relancer | Mêmes identifiants, informations, statuts, et échéance absente conservée | Non exécuté : fonctionnalité pas encore livrée | À exécuter |
| 10 | Ajout après rechargement | Ajouter une tâche après le redémarrage du scénario 9 | Identifiant distinct de ceux déjà chargés | Non exécuté : fonctionnalité pas encore livrée | À exécuter |
| 11 | Fichier absent ou mal formé | Démarrer sans fichier ; démarrer avec un fichier de données mal formé | Liste vide dans le premier cas ; message et démarrage interrompu sans modifier le fichier dans le second | Non exécuté : fonctionnalité pas encore livrée | À exécuter |
| 12 | Échec d'écriture | Sauvegarder alors que le fichier est en lecture seule | Échec signalé, aucun message de réussite | Non exécuté : fonctionnalité pas encore livrée | À exécuter |

## Scénario 7 : Modifier la priorité (US-04)

| # | Données saisies (option 4) | Résultat attendu | Résultat observé | Conclusion |
|---|---|---|---|---|
| 7.1 | id `1`, priorité `Basse` | Priorité modifiée, message de succès | Priorité modifiée, message de succès | Réussi |
| 7.2 | id `1`, priorité `ÉLEVÉE` | Accepté (casse et accents ignorés), devient « Élevée » | Accepté, devient « Élevée » | Réussi |
| 7.3 | id `1`, priorité `elevee` | Accepté, devient « Élevée » | Accepté | Réussi |
| 7.4 | id `1`, priorité `urgente` | Refusé, priorité actuelle inchangée | Message « Priorité invalide… », priorité inchangée | Réussi |
| 7.5 | id `999` | Message « Aucune tâche avec l'identifiant 999 », données conservées | Message affiché, données conservées | Réussi |
| 7.6 | id `0` | Refusé : « Identifiant invalide » | Message affiché | Réussi |
| 7.7 | id `-3` | Refusé : « Identifiant invalide » | Message affiché | Réussi |
| 7.8 | id `abc` | Refusé : « Identifiant invalide » | Message affiché | Réussi |
| 7.9 | id `1`, priorité identique à l'actuelle | Message « a déjà la priorité… », aucune modification | Message affiché | Réussi |


## Scénario 12 : Échec d'écriture lors de la sauvegarde (US-12)

| # | Données / action | Résultat attendu | Résultat observé | Conclusion |
|---|---|---|---|---|
| 12.1 | Relancer, ajouter une tâche, option 8 avec `taches.csv` en lecture seule | Message d'échec clair avec la raison, **aucun** message « Sauvegarde réussie » | Message d'erreur constaté | Réussi |
| 12.2 | `chmod 644 taches.csv`, relancer, ajouter une tâche, option 8 | « Sauvegarde réussie : N tâche(s) enregistrée(s). » | Sauvergarde réussi | Réussi |
---