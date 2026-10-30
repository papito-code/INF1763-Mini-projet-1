# INF1763-Mini-projet-1
INF1763 — Techniques et outils professionnels de développement logiciel Projet

# TaskFlow

Application Java en console pour gérer une liste de tâches (INF1763, mini-projet 1).

## Équipe
- Abdramane Moussa Doumbia
- Florian Yann Ollo Hien
- Elafissou Noham Gnanvo
- Kalil Ibrahima Soumaoro
- Dalaké Armel Landry Yoda

## Prérequis
- JDK : openjdk version "25.0.2" `java -version`

## Compiler et exécuter
Depuis la racine du projet :

    javac -d out src/*.java
    java -cp out Main

## Fonctionnalités
Menu : ajouter, afficher, terminer une tâche, modifier la priorité, attribuer une échéance(pas encore fait), modifier une échéance, retirer une echeance sauvegarder, quitter.
Au démarrage, les tâches sauvegardées sont rechargées automatiquement. L'attribution d'une échéance à une tâche existante et la vérification complète du fichier de données au chargement (identifiants en double, par exemple) arrivent à l'itération 2.

## Fichier de données, limites connues

- **Nom et emplacement :** `taches.csv`, créé dans le dossier d'où l'application est lancée (la racine du projet). Il est ignoré par Git (`.gitignore`).
- **Quand :** uniquement quand l'utilisateur choisit « Sauvegarder » dans le menu. Chaque sauvegarde remplace le contenu précédent du fichier.
- **Format :** texte encodé en UTF-8, une tâche par ligne, 5 colonnes séparées par un point-virgule (`;`), dans cet ordre : identifiant;titre;priorité;échéance;statut
- **Tâche sans échéance :** la colonne de l'échéance est laissée vide.
- **Exemple de ligne :** `1;test;Basse;2000-10-15;À faire`
- **Limite connue :** un titre contenant un point-virgule n'est pas géré.
- **Fichier absent :** si `taches.csv` n'existe pas au démarrage, l'application affiche « Aucun fichier de données trouvé (taches.csv). Démarrage avec une liste vide. » et démarre avec une liste vide. Le fichier n'est créé qu'à la première sauvegarde.
- **Rechargement au démarrage :** si `taches.csv` existe, les tâches sont rechargées automatiquement avant l'affichage du menu et l'application affiche le nombre de tâches chargées (« Chargement réussi : N tâche(s) chargée(s) depuis taches.csv. »). Si le fichier est illisible ou contient une ligne mal formée, l'application affiche la cause (avec le numéro de la ligne) puis s'arrête sans modifier le fichier.