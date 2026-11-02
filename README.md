# INF1763-Mini-projet-1
INF1763 — Techniques et outils professionnels de développement logiciel Projet

# TaskFlow

Application Java en console pour gérer une liste de tâches (INF1763, mini-projet 1).

## Équipe
- Abdramane Moussa Doumbia
- Florian Yann Ollo Hien
- Elafissou Noham Gnanvo
- Ibrahima Kalil Soumaoro
- Dalaké Armel Landry Yoda

## Prérequis
- JDK : openjdk version "25" `java -version`

## Compiler et exécuter
Depuis la racine du projet :

    javac -d out src/*.java
    java -cp out Main

Lancez toujours l'application depuis la racine du projet : le fichier de données `taches.csv` est créé et lu dans le dossier courant.

## Fonctionnalités
Menu principal :

1. Ajouter une tâche (titre obligatoire, priorité et échéance facultatives, « annuler » pour revenir au menu)
2. Afficher les tâches
3. Terminer une tâche
4. Modifier la priorité
5. Attribuer une échéance
6. Modifier une échéance
7. Retirer une échéance
8. Sauvegarder
9. Quitter

Chaque tâche a : un identifiant (attribué automatiquement), un titre, une priorité (Basse, Normale ou Élevée), une échéance facultative (AAAA-MM-JJ) et un statut (À faire ou Terminée).

## Exemple d'utilisation
1. Démarrer l'application : « Aucun fichier de données trouvé… », liste vide.
2. Choix `1` : titre « Vérifier la sauvegarde du serveur », priorité `3`, échéance `2026-12-01` → identifiant 1.
3. Choix `1` : titre « Mettre à jour le guide utilisateur », Entrée, Entrée → identifiant 2.
4. Choix `2` : les deux tâches s'affichent.
5. Choix `4`, identifiant `2`, priorité `Basse` ; choix `3`, identifiant `1`.
6. Choix `8` : « Sauvegarde réussie : 2 tâche(s) enregistrée(s). »
7. Choix `9`, puis relancer : « Chargement réussi : 2 tâche(s) chargée(s)… ». Les tâches ont les mêmes identifiants.
8. Ajouter une tâche : elle reçoit l'identifiant 3.

## Fichier de données
- **Emplacement :** `taches.csv`, dans le dossier d'où l'application est lancée (la racine du projet). Il est ignoré par Git (`.gitignore`).
- **Écrasement :** chaque sauvegarde remplace le contenu précédent du fichier.
- **Format :** texte UTF-8, une tâche par ligne, 5 colonnes séparées par `;`, sans en-tête : `identifiant;titre;priorité;échéance;statut`
- **Tâche sans échéance :** la colonne de l'échéance est vide.
- **Exemple :** `1;Vérifier la sauvegarde;Élevée;2026-12-01;Terminée`

## Sauvegarde et rechargement
- La sauvegarde est explicite : choix `8`. Rien n'est écrit tant que vous ne la lancez pas.
- Si l'écriture échoue (fichier en lecture seule, par exemple), l'application affiche un message d'échec et n'annonce pas de réussite.
- Au démarrage, si `taches.csv` existe, les tâches sont rechargées automatiquement avec leurs identifiants. Une nouvelle tâche reçoit un identifiant supérieur au plus grand identifiant chargé.
- Si le fichier n'existe pas, l'application démarre avec une liste vide. Le fichier n'est créé qu'à la première sauvegarde.
- Si le fichier est illisible ou mal formé, l'application affiche la cause (avec le numéro de ligne) et s'arrête sans modifier le fichier.

## Documentation
- Organisation de l'équipe : `docs/organisation.md`
- Scénarios de vérification : `docs/verification.md`

Les données utilisées dans les exemples sont fictives.