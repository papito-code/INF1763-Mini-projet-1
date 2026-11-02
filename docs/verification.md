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
| 9 | Sauvegarde et redémarrage | Deux tâches (une terminée, une sans échéance), sauvegarder, quitter, relancer | Mêmes identifiants, informations, statuts, et échéance absente conservée | « Tâche test A » (Élevée, échéance 2026-11-30, terminée) et « Tâche test B » (Normale, sans échéance) : « Sauvegarde réussie : 2 tâche(s) enregistrée(s). » Le fichier contient `1;Tâche test A;Élevée;2026-11-30;Terminée` et `2;Tâche test B;Normale;;À faire`. Au relancement : « Chargement réussi : 2 tâche(s) chargée(s) depuis taches.csv. » avant le menu ; l'affichage montre la tâche 1 (Élevée, 2026-11-30, Terminée) et la tâche 2 (Normale, « Date d'échéance : Aucune », À faire) avec les mêmes titres. L'empreinte sha256 du fichier est identique avant et après le chargement | Conforme |
| 10 | Ajout après rechargement | Ajouter une tâche après le redémarrage du scénario 9 | Identifiant distinct de ceux déjà chargés | « Tâche test C » ajoutée après le rechargement des tâches 1 et 2 : « Identifiant : 3 » ; l'affichage montre les tâches 1, 2 et 3. Essais complémentaires : avec un fichier écrit à la main contenant les identifiants 1 et 5, la nouvelle tâche reçoit l'identifiant 6 ; avec un fichier vide, « Chargement réussi : 0 tâche(s) chargée(s) depuis taches.csv. » et la nouvelle tâche reçoit l'identifiant 1 | Conforme |
| 11 | Fichier absent ou mal formé | Démarrer sans fichier ; démarrer avec un fichier de données mal formé | Liste vide dans le premier cas ; message et démarrage interrompu sans modifier le fichier dans le second | Sans fichier : « Aucun fichier de données trouvé (taches.csv). Démarrage avec une liste vide. » avant le menu, aucune erreur ; taches.csv n'existe toujours pas après avoir quitté, il n'est créé qu'au choix Sauvegarder ; au relancement avec le fichier présent, aucun message d'absence. Fichier mal formé : non exécuté, fonctionnalité pas encore livrée | Conforme pour le démarrage sans fichier ; À exécuter pour le fichier mal formé |
| 12 | Échec d'écriture | Sauvegarder alors que le fichier est en lecture seule | Échec signalé, aucun message de réussite | Non exécuté : fonctionnalité pas encore livrée | À exécuter |

## Scénario 1 : Liste vide

| # | Données | Résultat attendu | Résultat observé | Conclusion |
|---|---|---|---|---|
| 1.1 | Aucun `taches.csv`, démarrer, option 2 | Message de démarrage « Aucun fichier de données trouvé… », puis message explicite indiquant qu'aucune tâche n'existe | Message de démarrage affiché, puis message indiquant qu'aucune tâche n'existe | Conforme |

## Scénario 2 : Ajout valide

| # | Données | Résultat attendu | Résultat observé | Conclusion |
|---|---|---|---|---|
| 2.1 | Titre « Vérifier la sauvegarde du serveur », Entrée, Entrée | « Tâche ajoutée avec succès. », identifiant 1, priorité Normale, échéance Aucune, statut À faire | Message de succès, identifiant 1, Normale, Aucune, À faire | Conforme |
| 2.2 | Titre « Mettre à jour le guide utilisateur », priorité `3`, échéance `2026-12-01` | Identifiant 2, priorité Élevée, échéance 2026-12-01, statut À faire | Identifiant 2, Élevée, 2026-12-01, À faire | Conforme |
| 2.3 | Titre « Vérifier la sauvegarde du serveur » (titre déjà utilisé) | Tâche créée avec un identifiant distinct (3) | Tâche créée avec l'identifiant 3 | Conforme |

## Scénario 3 : Titre invalide

| # | Données | Résultat attendu | Résultat observé | Conclusion |
|---|---|---|---|---|
| 3.1 | Titre vide (Entrée) | « Erreur : le titre est obligatoire. », titre redemandé | Message d'erreur affiché, titre redemandé | Conforme |
| 3.2 | Titre composé de trois espaces | Même refus, titre redemandé | Même message d'erreur, titre redemandé | Conforme |
| 3.3 | Titre `annuler` | « Ajout annulé. », retour au menu | « Ajout annulé. », retour au menu | Conforme |
| 3.4 | Option 2 après 3.1 à 3.3 | Aucune tâche invalide ajoutée | Aucune nouvelle tâche dans la liste | Conforme |

## Scénario 4 : Liste de plusieurs tâches

| # | Données | Résultat attendu | Résultat observé | Conclusion |
|---|---|---|---|---|
| 4.1 | « Tâche A » (Élevée, 2026-11-30), « Tâche B » (Normale, sans échéance), « Tâche B » (Basse), puis option 2 | Trois tâches affichées avec identifiant, titre, priorité, échéance ou « Aucune », statut ; les deux « Tâche B » distinguées par l'identifiant | Trois tâches affichées avec tous leurs champs, identifiants 1, 2 et 3, « Aucune » pour les tâches sans échéance | Conforme |

## Scénario 5 : Tâche terminée

| # | Données | Résultat attendu | Résultat observé | Conclusion |
|---|---|---|---|---|
| 5.1 | Tâches 1 et 2 existantes, option 3, identifiant 1 | Confirmation ; l'option 2 montre la tâche 1 « Terminée », ses autres champs et la tâche 2 inchangés | Confirmation affichée ; tâche 1 Terminée, reste inchangé | Conforme |
| 5.2 | Option 3, identifiant 1 une seconde fois | « La tâche 1 est déjà terminée. Aucune modification n'est nécessaire. » | Message affiché, aucune modification | Conforme |

## Scénario 6 : Identifiant inconnu

| # | Données | Résultat attendu | Résultat observé | Conclusion |
|---|---|---|---|---|
| 6.1 | Options 3, 4, 5, 6 et 7 avec l'identifiant 99 | Message indiquant qu'aucune tâche n'a cet identifiant | Message d'erreur affiché dans chaque cas | Conforme |
| 6.2 | Option 2 après 6.1 | Toutes les données existantes conservées | Tâches inchangées | Conforme |

## Scénario 7 : Priorité

| # | Données | Résultat attendu | Résultat observé | Conclusion |
|---|---|---|---|---|
| 7.1 | Option 4, id `1`, `Basse` | Priorité modifiée, message de succès | Priorité modifiée, message de succès | Conforme |
| 7.2 | id `1`, `ÉLEVÉE` | Accepté (casse et accents ignorés), devient « Élevée » | Accepté, devient « Élevée » | Conforme |
| 7.3 | id `1`, `elevee` | Accepté, devient « Élevée » | Accepté | Conforme |
| 7.4 | id `1`, `Normale` | Accepté, devient « Normale » | Accepté | Conforme |
| 7.5 | id `1`, `urgente` | « Priorité invalide. Choisissez Basse, Normale ou Élevée. », priorité inchangée | Message affiché, priorité inchangée | Conforme |
| 7.6 | id `1`, priorité identique à l'actuelle | Message « a déjà la priorité… », aucune modification | Message affiché | Conforme |
| 7.7 | id `0`, `-3`, `abc` ou vide | « Identifiant invalide : entrez un nombre entier positif. » | Message affiché dans chaque cas | Conforme |

## Scénario 8 : Échéance

| # | Données | Résultat attendu | Résultat observé | Conclusion |
|---|---|---|---|---|
| 8.1 | Option 5, id 1 (sans échéance), `2026-12-15` | Échéance attribuée | Échéance attribuée | Conforme |
| 8.2 | Option 5, id 2 (a déjà une échéance) | Refusé, renvoi vers l'option 6 | Refusé avec renvoi vers l'option 6 | Conforme |
| 8.3 | Option 5, `10/08/2026` puis `2026-02-30` | Refusés avec « Erreur : date invalide… », date redemandée | Refusés, date redemandée | Conforme |
| 8.4 | Option 5, `2026-10-08` (date passée) | Avertissement « déjà passée », échéance quand même attribuée | Avertissement affiché, échéance attribuée | Conforme |
| 8.5 | Option 6, id 2, `2027-01-10` | Échéance modifiée | Échéance modifiée | Conforme |
| 8.6 | Option 6, id 2, `2026-02-30` | Refusée, échéance existante inchangée | Refusée, échéance inchangée | Conforme |
| 8.7 | Option 7, id 2 | Échéance retirée, affichée « Aucune » | Échéance retirée, « Aucune » à l'affichage | Conforme |
| 8.8 | Option 7 sur une tâche sans échéance | Signalé, aucune modification | Message affiché, aucune modification | Conforme |

## Scénario 9 : Sauvegarde et redémarrage

| # | Données | Résultat attendu | Résultat observé | Conclusion |
|---|---|---|---|---|
| 9.1 | « Tâche A » (Élevée, 2026-11-30, terminée) et « Tâche B » (Normale, sans échéance), option 8 | « Sauvegarde réussie : 2 tâche(s) enregistrée(s). » ; fichier `1;Tâche A;Élevée;2026-11-30;Terminée` et `2;Tâche B;Normale;;À faire` | Message de réussite ; contenu du fichier conforme | Conforme |
| 9.2 | Option 9, relancer, option 2 | « Chargement réussi : 2 tâche(s) chargée(s) depuis taches.csv. » ; mêmes identifiants, titres, priorités, échéances (absente conservée), statuts ; fichier inchangé | Chargement réussi, mêmes données, empreinte identique | Conforme |

## Scénario 10 : Ajout après rechargement

| # | Données | Résultat attendu | Résultat observé | Conclusion |
|---|---|---|---|---|
| 10.1 | Après 9.2, ajouter « Tâche C » | Identifiant 3, distinct de 1 et 2 | Identifiant 3 | Conforme |
| 10.2 | Fichier écrit à la main avec les identifiants 1 et 5, ajouter une tâche | Identifiant 6 | Identifiant 6 | Conforme |
| 10.3 | Fichier vide, ajouter une tâche | « Chargement réussi : 0 tâche(s)… », identifiant 1 | Message affiché, identifiant 1 | Conforme |

## Scénario 11 : Fichier absent ou mal formé

| # | Contenu de `taches.csv` | Résultat attendu | Résultat observé | Conclusion |
|---|---|---|---|---|
| 11.0 | Fichier absent | « Aucun fichier de données trouvé (taches.csv). Démarrage avec une liste vide. », fichier non créé avant une sauvegarde | Message affiché, fichier non créé | Conforme |
| 11.1 | Deux lignes avec l'identifiant 1 | « ligne 2, identifiant en double (1) », arrêt, fichier inchangé | « Impossible de charger taches.csv : ligne 2, identifiant en double (1). Le fichier n'a pas été modifié. Arrêt de l'application. » | Conforme |
| 11.2 | `1; ;Basse;;À faire` | « ligne 1, titre vide », arrêt, fichier inchangé | « ligne 1, titre vide. Le fichier n'a pas été modifié. Arrêt de l'application. » | Conforme |
| 11.3 | `1;Test;Basse;À faire` | « ligne 1, 5 colonnes attendues au lieu de 4 », arrêt, fichier inchangé | Message attendu affiché, arrêt, fichier inchangé | Conforme |
| 11.4 | `1;Test;urgente;;À faire` | « ligne 1, priorité invalide », arrêt, fichier inchangé | Message attendu affiché, arrêt, fichier inchangé | Conforme |
| 11.5 | `1;Test;Basse;2026-02-30;À faire` | « ligne 1, échéance invalide », arrêt, fichier inchangé | Message attendu affiché, arrêt, fichier inchangé | Conforme |
| 11.6 | `1;Test;Basse;;Fini` | « ligne 1, statut invalide », arrêt, fichier inchangé | Message attendu affiché, arrêt, fichier inchangé | Conforme |
| 11.7 | `abc;Test;Basse;;À faire` | « ligne 1, identifiant invalide », arrêt, fichier inchangé | Message attendu affiché, arrêt, fichier inchangé | Conforme |
| 11.8 | `1;Test;Basse;;À faire` | « Chargement réussi : 1 tâche(s) chargée(s) depuis taches.csv. » | Message affiché | Conforme |

## Scénario 12 : Échec d'écriture

| # | Données | Résultat attendu | Résultat observé | Conclusion |
|---|---|---|---|---|
| 12.1 | Sauvegarder une fois, quitter, `chmod 444 taches.csv`, relancer, ajouter une tâche, option 8 | Message d'échec, aucun « Sauvegarde réussie » | Message d'échec affiché, aucun message de réussite | Conforme |
| 12.2 | `chmod 644 taches.csv`, relancer, ajouter une tâche, option 8 | « Sauvegarde réussie : N tâche(s) enregistrée(s). » | Message de réussite affiché | Conforme |

## Anomalies connues
- Un titre contenant `;` casse le format du fichier de données (limite connue, documentée dans le README).
- Les modifications non sauvegardées sont perdues à la fermeture, sans avertissement.