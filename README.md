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
## Fonctionnalités
Menu : ajouter, afficher, terminer une tâche, modifier la priorité, sauvegarder, quitter.
Les autres fonctionnalités (échéances, rechargement des données) arrivent à l'itération 2.

## Fichier de données, limites connues

- **Nom et emplacement :** `taches.csv`, créé dans le dossier d'où l'application est lancée (la racine du projet). Il est ignoré par Git (`.gitignore`).
- **Quand :** uniquement quand l'utilisateur choisit « Sauvegarder » dans le menu. Chaque sauvegarde remplace le contenu précédent du fichier.
- **Format :** texte encodé en UTF-8, une tâche par ligne, 5 colonnes séparées par un point-virgule (`;`), dans cet ordre : identifiant;titre;priorité;échéance;statut
- **Tâche sans échéance :** la colonne de l'échéance est laissée vide.
- **Exemple de ligne :** `1;test;Basse;2000-10-15;À faire`
- **Limite connue :** un titre contenant un point-virgule n'est pas géré.