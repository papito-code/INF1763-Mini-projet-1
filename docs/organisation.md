# Organisation du travail d'équipe – TaskFlow (Mini-projet 1)

Dépôt : https://github.com/papito-code/INF1763-Mini-projet-1
Tableau de suivi : https://github.com/users/papito-code/projects/1

---

## 1. Objectif du produit

TaskFlow est une application Java en console destinée à l'équipe de soutien d'une entreprise fictive de services informatiques. Elle remplace les notes personnelles dispersées : elle permet de conserver une liste de tâches, de leur attribuer une priorité et une échéance, de les marquer comme terminées, puis de retrouver ses données à la prochaine utilisation grâce à une sauvegarde dans un fichier local. Cette version sert de base aux parties suivantes du projet.

## 2. Équipe et responsabilités

| Membre | Compte GitHub | Rôle de coordination | Responsabilité |
| --- | --- | --- | --- |
| Elafissou Noham Gnanvo | drilcoin | Responsable technique | Structure du code, ordre de fusion des PR |
| Ibrahima Kalil Soumaoro | kalil-soum | Validation des saisies | Ajout d'une tâche, attribution d'une échéance et validation de leurs saisies |
| Abdramane Moussa Doumbia | papito-code | Coordination et suivi | GitHub Projects, Issues, ce document |
| Florian Yann Ollo Hien | yannuser | Vérifications | `docs/verification.md`, données fictives |
| Dalaké Armel Landry Yoda | Melito2k | Documentation | `README.md` (format du fichier, limites connues) |

Une responsabilité de coordination ne remplace pas la contribution au code : chaque membre développe, ouvre des PR et participe aux vérifications.

### Règles de fonctionnement

- **Prise de décision** : discussion d'équipe, accord de la majorité ; en cas d'égalité, le responsable technique tranche pour le code et le responsable de coordination pour l'organisation.
- **Blocages** : signalés dans les commentaires des pull-requests ou sur le canal d'équipe (WhatsApp).
- **Vérification et intégration** : voir la section 4 et la définition de terminé (section 6).
- **Données** : uniquement des données fictives dans le dépôt (aucune donnée réelle, mot de passe ou secret).

## 3. Backlog de produit.

### 3.1 Récits utilisateurs

Estimation en points relatifs (suite de Fibonacci). L'ordre correspond à l'ordre de réalisation.

| Ordre | ID | Titre | Itération | Pts | Rédigé par | Issue |
| --- | --- | --- | --- | --- | --- | --- |
| 1 | US-13 | Choisir une action dans le menu | 1 | 3 | Armel | #18 |
| 2 | US-14 | Quitter proprement l'application | 1 | 1 | Armel | #19 |
| 3 | US-01 | Ajouter une tâche | 1 | 5 | Noham | #5 |
| 4 | US-02 | Afficher la liste des tâches | 1 | 3 | Noham | #6 |
| 5 | US-03 | Marquer une tâche comme terminée | 1 | 2 | Noham | #7 |
| 6 | US-04 | Modifier la priorité d'une tâche | 1 | 2 | Yann | #8 |
| 7 | US-05 | Attribuer une échéance | 2 | 3 | Yann | #9 |
| 8 | US-06 | Modifier une échéance | 2 | 2 | Yann | #10 |
| 9 | US-07 | Retirer une échéance | 2 | 1 | Abdramane | #11 |
| 10 | US-08 | Sauvegarder les données | 2 | 5 | Abdramane | #12 |
| 11 | US-12 | Être averti d'un échec de sauvegarde | 2 | 2 | Kalil | #17 |
| 12 | US-09 | Recharger les données au démarrage | 2 | 5 | Abdramane | #13 |
| 13 | US-10 | Démarrer sans fichier de données | 2 | 2 | Kalil | #14 |
| 14 | US-11 | Être averti d'un fichier de données illisible | 2 | 3 | Kalil | #15 |
|  |  | **Total** |  | **39** |  |  |

**Hypothèses d'estimation**

| Récit | Hypothèse |
| --- | --- |
| US-13 | Boucle de saisie robuste (choix non numérique ou inexistant sans plantage) |
| US-14 | Simple option du menu |
| US-01 | Modèle, validation, identifiant automatique, correction ou annulation de la saisie |
| US-02 | Affichage lisible avec libellés, cas de la liste vide |
| US-03 | Introduit la recherche par identifiant, réutilisée ensuite |
| US-04 | Réutilise la validation de la priorité de US-01 et la recherche par identifiant |
| US-05 | Validation d'une date réelle au format AAAA-MM-JJ (`LocalDate`) |
| US-06 | Réutilise la validation de US-05 |
| US-07 | Cas simple une fois l'échéance gérée |
| US-08 | Choix du format, sérialisation, écriture, message avec le nombre de tâches |
| US-09 | Lecture, désérialisation, calcul de l'identifiant suivant |
| US-10 | Cas particulier de la lecture (fichier absent) |
| US-11 | Détection de plusieurs formes de fichier mal formé |
| US-12 | Gestion d'exception à l'écriture, absence de message de réussite |

### 3.2 Tâches techniques

(les tâches techniques ne figurent pas toutes dans le dépôt)

| ID | Tâche technique | Itération | Responsable |
| --- | --- | --- | --- |
| T-01 | Squelette du projet : dépôt, dossiers, `.gitignore`, classes de base (`Main`, `Tache`, `GestionnaireTaches`, `FichierTaches`) | 1 | Noham |
| T-02 | Validation des saisies à l'ajout d'une tâche (titre, priorité, échéance) | 1 | Kalil |
| T-03 | GitHub Projects (états, règles de passage), Issues, accès de l'enseignant et du correcteur | 1 | Abdramane |
| T-04 | Début de `docs/verification.md` (tableau des scénarios, données fictives) | 1 | Yann |
| T-05 | README initial (JDK, commandes de compilation et d'exécution) | 1 | Armel |
| T-06 | `docs/organisation.md`, mis à jour à chaque itération | 1–2 | Abdramane |
| T-07 | Compléter le README (format du fichier, sauvegarde et rechargement, limites connues) | 2 | Armel |
| T-08 | Compléter `docs/verification.md` (scénarios de l'itération 2) | 2 | Armel |

### 3.3 Récits et critères d'acceptation

Dans les récits ci-dessous, « l'identifiant » désigne un entier positif : 0, un nombre négatif, un champ vide ou un texte non numérique sont refusés avec un message compréhensible, sans plantage.

#### US-01 – Ajouter une tâche

« En tant que membre de l'équipe de soutien, je veux ajouter une tâche avec un titre, et au besoin une priorité et une échéance, afin de savoir ce qui reste à faire au lieu de le garder dans mes notes personnelles. »

Critères d'acceptation :

- Un titre vide ou composé uniquement d'espaces est refusé et aucune tâche n'est ajoutée.
- Sans priorité choisie, la tâche reçoit Normale. Une valeur autre que Basse, Normale ou Élevée est refusée. La saisie de la priorité ne tient pas compte de la casse.
- Sans échéance précisée, la tâche est créée sans échéance. Une date inexistante (2026-02-30) ou au mauvais format (10/08/2026) est refusée. Une date passée est acceptée.
- La tâche créée reçoit automatiquement un identifiant et le statut À faire, et un message de confirmation indique cet identifiant.
- Si une donnée est invalide, le message explique le problème et l'utilisateur peut corriger sa saisie ou annuler l'ajout, sans qu'aucune tâche invalide ou incomplète soit créée.

#### US-02 – Afficher la liste des tâches

« En tant que membre de l'équipe de soutien, je veux voir une liste des tâches avec pour chacune son identifiant, son titre, sa priorité, son échéance ou l'absence d'échéance, et son statut, afin d'avoir une idée des tâches restantes et de ce qui est terminé. »

Critères d'acceptation :

- Toutes les tâches existantes sont affichées, chacune avec son identifiant, son titre, sa priorité, son échéance et son statut.
- Chaque tâche occupe sa propre ligne, et chaque information est précédée d'un libellé ou placée sous un en-tête de colonne, de sorte qu'on peut les distinguer sans deviner.
- Une tâche sans échéance affiche une mention explicite (par exemple « Aucune ») et non un champ vide.
- Les statuts À faire et Terminée sont visibles pour chaque tâche.
- Deux tâches portant le même titre apparaissent comme deux lignes distinctes, avec des identifiants différents.
- Si la liste est vide, l'application affiche un message explicite et aucun tableau vide.
- L'affichage de la liste ne modifie aucune donnée.

#### US-03 – Marquer une tâche comme terminée

« En tant que membre de l'équipe de soutien, je veux marquer une tâche comme terminée à l'aide de son identifiant, afin de distinguer ce qui est fait de ce qu'il reste à faire. »

Critères d'acceptation :

- Avec un identifiant existant, la tâche prend le statut Terminée et un message confirme le changement.
- Le titre, la priorité, l'échéance et l'identifiant de cette tâche restent inchangés, ainsi que toutes les autres tâches.
- Avec un identifiant inexistant, un message signale le problème et aucune donnée n'est modifiée.
- Si la tâche est déjà terminée, l'application le signale, ne crée aucun doublon et n'effectue aucune modification.
- Si l'identifiant saisi n'est pas un entier positif, la saisie est refusée avec un message compréhensible, sans plantage du programme.

#### US-04 – Modifier la priorité d'une tâche

« En tant que membre de l'équipe de soutien, je veux modifier la priorité d'une tâche à l'aide de son identifiant, afin que la liste reflète ce qui est le plus urgent quand la situation change. »

Critères d'acceptation :

- Avec un identifiant existant et une priorité parmi Basse, Normale ou Élevée, la priorité de la tâche est mise à jour et un message confirme le changement. La saisie de la priorité ne tient pas compte de la casse.
- Seule la priorité de cette tâche change : le reste de la tâche et les autres tâches sont inchangés.
- Une valeur autre que Basse, Normale ou Élevée est refusée avec un message, et la priorité actuelle est conservée.
- Avec un identifiant inexistant, un message signale le problème et aucune donnée n'est modifiée.
- Si l'identifiant saisi n'est pas un entier positif, la saisie est refusée avec un message compréhensible, sans plantage.

#### US-05 – Attribuer une échéance

« En tant que membre de l'équipe de soutien, je veux attribuer une échéance à une tâche qui n'en a pas, afin de savoir à quel moment elle doit être faite. »

Critères d'acceptation :

- Avec un identifiant existant et une date valide au format AAAA-MM-JJ, l'échéance est attribuée et un message la confirme. Une date passée est acceptée.
- Un format incorrect (par exemple 10/08/2026) est refusé avec un message qui indique le format attendu, et la tâche reste sans échéance.
- Une date inexistante (par exemple 2026-02-30) est refusée avec un message, et la tâche reste sans échéance.
- Si la tâche a déjà une échéance, l'attribution est refusée avec un message qui renvoie vers la modification d'une échéance, et aucune donnée n'est modifiée.
- Avec un identifiant inexistant ou non valide, un message signale le problème et aucune donnée n'est modifiée.
- Les autres informations de la tâche et les autres tâches sont inchangées.

#### US-06 – Modifier une échéance

« En tant que membre de l'équipe de soutien, je veux modifier l'échéance d'une tâche, afin que la date reflète un délai qui a changé. »

Critères d'acceptation :

- Avec un identifiant existant et une date valide, la nouvelle échéance remplace l'ancienne et un message confirme le changement. Une date passée est acceptée.
- Un format incorrect ou une date inexistante est refusé avec un message, et l'échéance actuelle est conservée.
- Si la tâche n'a pas d'échéance, la modification est refusée avec un message qui renvoie vers l'attribution d'une échéance, et aucune donnée n'est modifiée.
- Avec un identifiant inexistant ou non valide, un message signale le problème et aucune donnée n'est modifiée.
- Les autres informations de la tâche et les autres tâches sont inchangées.

#### US-07 – Retirer une échéance

« En tant que membre de l'équipe de soutien, je veux retirer l'échéance d'une tâche, afin de ne plus afficher une date devenue sans objet. »

Critères d'acceptation :

- Avec un identifiant existant dont la tâche a une échéance, celle-ci est retirée et un message le confirme.
- Après le retrait, la liste affiche pour cette tâche la mention d'absence d'échéance.
- Si la tâche n'a pas d'échéance, l'application le signale et ne modifie rien.
- Avec un identifiant inexistant ou non valide, un message signale le problème et aucune donnée n'est modifiée.
- Les autres informations de la tâche et les autres tâches sont inchangées.

#### US-08 – Sauvegarder les données

« En tant que membre de l'équipe de soutien, je veux sauvegarder mes tâches dans un fichier local sur commande, afin de les retrouver à ma prochaine utilisation. »

Critères d'acceptation :

- La sauvegarde ne s'effectue que lorsque l'utilisateur la demande explicitement.
- Pour chaque tâche, l'identifiant, le titre, la priorité, l'échéance (ou son absence) et le statut sont écrits dans le fichier.
- Quand la sauvegarde réussit, un message l'indique avec le nombre de tâches enregistrées.
- Le format et l'emplacement du fichier sont documentés dans le README.md.

#### US-09 – Recharger les données au démarrage

« En tant que membre de l'équipe de soutien, je veux que mes tâches soient rechargées automatiquement au démarrage, afin de reprendre mon travail là où je l'ai laissé. »

Critères d'acceptation :

- Si le fichier existe, les tâches sont chargées sans action de l'utilisateur et un message indique le nombre de tâches chargées.
- Chaque tâche retrouve les mêmes informations qu'avant la fermeture : identifiant, titre, priorité, échéance (ou absence d'échéance) et statut.
- Une tâche créée après le rechargement reçoit un identifiant distinct de ceux des tâches déjà chargées.

#### US-10 – Démarrer sans fichier de données

« En tant que membre de l'équipe de soutien, je veux pouvoir utiliser l'application dès la première fois, sans fichier préparé à l'avance, afin de commencer à noter mes tâches immédiatement. »

Critères d'acceptation :

- Si le fichier n'existe pas, l'application démarre avec une liste vide et un message l'indique.
- Aucune erreur n'est affichée dans ce cas.
- Le fichier n'est pas créé tant que l'utilisateur n'a pas demandé une sauvegarde.

#### US-11 – Être averti d'un fichier de données illisible

« En tant que membre de l'équipe de soutien, je veux être averti quand mon fichier de données est illisible, afin de ne pas perdre mes données sans le savoir. »

Critères d'acceptation :

- Si le fichier existe mais est illisible ou mal formé, un message clair l'indique. Un fichier est mal formé lorsque son contenu ne respecte pas le format, qu'un champ est manquant, qu'une priorité ou un statut est invalide, qu'une date est impossible ou qu'un identifiant est invalide ou en double.
- Le démarrage de l'application est interrompu.
- Le fichier n'est pas modifié.

#### US-12 – Être averti d'un échec de sauvegarde

« En tant que membre de l'équipe de soutien, je veux savoir quand ma sauvegarde échoue, afin de ne pas croire à tort que mes tâches sont enregistrées. »

Critères d'acceptation :

- Si l'écriture dans le fichier est impossible (par exemple fichier en lecture seule ou dossier inexistant), un message d'échec clair est affiché.
- Aucun message de réussite n'est affiché dans ce cas.
- L'application reste utilisable et les tâches en mémoire sont conservées.

#### US-13 – Choisir une action dans le menu

« En tant que membre de l'équipe de soutien, je veux choisir mes actions dans un menu numéroté, afin d'utiliser l'application sans connaître de commande. »

Critères d'acceptation :

- Le menu liste les actions disponibles et l'option pour quitter, chacune avec son numéro.
- Un choix valide déclenche l'action correspondante.
- Un choix inexistant ou non numérique produit un message compréhensible, puis le menu est réaffiché sans plantage.

#### US-14 – Quitter proprement l'application

« En tant que membre de l'équipe de soutien, je veux quitter l'application par une commande prévue, afin de terminer ma session sans erreur. »

Critères d'acceptation :

- L'option Quitter du menu ferme le programme et affiche un message de fermeture.
- La fermeture ne produit aucune erreur ni trace d'exception.

## 4. Processus d'intégration

1. Sélectionner une Issue (récit ou tâche technique) et déplacer sa carte vers **En cours**.
2. Créer une branche au nom compréhensible (ex. : `feature/US-05-attribuer-echeance`).
3. Faire des commits cohérents avec des messages explicites.
4. Ouvrir une pull request qui explique le changement et référence l'Issue.
5. Déplacer la carte vers **À vérifier** ; un autre membre relit et essaie le changement.
6. Apporter les corrections demandées.
7. Fusionner dans `main`, puis vérifier que l'application compile et démarre.
8. Mettre à jour l'Issue (critères cochés) et déplacer la carte vers **Terminé**.

L'initialisation du dépôt (T-01) peut être poussée directement sur `main`. Tout travail suivant passe par une branche et une PR.

Le commentaire d'approbation indique ce qui a été relu et ce qui a été essayé dans l'application; Le relecteur exécute les scénarios de vérification liés à la PR.

### Ordre de fusion

- T-01 (squelette) avant tout autre travail.
- Itération 2 : US-08 avant US-12 ; US-09 avant US-10 et US-11.
- En cas de conflit de fusion : résolution avec les membres concernés, puis vérification du fonctionnement.

## 5. Tableau de suivi (GitHub Projects)

Les cartes représentent des récits utilisateurs (`US-xx`) et des tâches techniques (`T-xx`), distingués par une étiquette.

| État | Règle d'entrée |
| --- | --- |
| **À faire** | L'Issue existe, avec ses critères d'acceptation et son estimation. |
| **En cours** | Un membre est assigné et une branche est créée. |
| **À vérifier** | Une PR est ouverte, liée à l'Issue ; elle attend la revue. |
| **Terminé** | Tous les points de la définition de terminé sont satisfaits. |

## 6. Définition de « terminé »

Un élément est terminé lorsque :

1. **Critères d'acceptation** : chaque critère de la fiche GitHub a été essayé dans l'application, cas d'erreur et cas particuliers compris, et le résultat observé correspond au résultat attendu.
2. **Vérifications** : les scénarios pertinents ont été exécutés avec des données fictives et consignés dans `docs/verification.md` (données, résultat attendu, résultat observé, conclusion).
3. **Intégration** : le changement est fusionné dans la branche principale par pull request ; après la fusion, l'application compile et démarre avec le JDK indiqué dans le README, et les fonctionnalités déjà terminées fonctionnent toujours.
4. **Revue** : un membre autre que l'auteur a relu le changement et approuvé la PR avec un commentaire précisant ce qu'il a relu et essayé.
5. **Documentation** : le README et les fichiers de `docs/` touchés sont à jour.
6. **Suivi** : l'Issue est liée à la PR, ses critères sont cochés et la carte est dans **Terminé**.

## 7. Itérations

### Choix de priorité

- **Menu et sortie d'abord (US-13, US-14)** : le menu est le seul moyen d'atteindre les autres fonctions.
- **Ajouter puis afficher (US-01, US-02)** : les actions suivantes portent sur des tâches existantes, et l'affichage permet de vérifier chaque action et donne les identifiants.
- **Terminer avant la priorité (US-03, US-04)** : savoir ce qui est terminé est le besoin central du client ; la priorité existe dès la création et se modifie en une action.
- **Échéances (US-05 à US-07)** : facultatives et réparties en trois récits ; US-06 et US-07 dépendent de US-05.
- **Persistance (US-08 à US-12)** : le fichier doit contenir les informations complètes, donc elle vient après les actions sur les tâches. Le rechargement (US-09) passe avant ses cas d'erreur (US-10, US-11) ; l'échec de sauvegarde (US-12) suit US-08.

### Itération 1 – Application utilisable en mémoire

- **Objectif** : gérer ses tâches pendant une session.
- **Récits** : US-13, US-14, US-01, US-02, US-03, US-04 (16 pts) ; tâches T-01 à T-05.
- **Principales tâches de réalisation** : modèle de tâche, gestion de la liste en mémoire (ajout, recherche par identifiant, identifiant automatique), validation des saisies, interaction (menu, messages, affichage), initialisation du README et de `docs/verification.md`.
- **Version obtenue** : v0.1
- **Vérifications effectuées** :
- **Observations et ajustements décidés** :

### Itération 2 – Échéances et persistance

- **Objectif** : compléter la gestion des échéances et conserver les données d'une session à l'autre.
- **Récits** : US-05, US-06, US-07, US-08, US-09, US-10, US-11, US-12 (23 pts) ; tâches T-06 à T-08.
- **Principales tâches de réalisation** : échéances (attribuer, modifier, retirer), choix et documentation du format et de l'emplacement du fichier, lecture et écriture dans une partie du code distincte, chargement au démarrage et calcul de l'identifiant suivant, trois cas d'erreur du fichier (absent, mal formé, écriture impossible), README et vérifications complétés.
- **Version obtenue** : v0.2
- **Vérifications effectuées** :
- **Observations et ajustements décidés** :

## 8. Contribution de chaque membre

| Membre | Récits réalisés | Tâches techniques | PR de code | Revues effectuées |
| --- | --- | --- | --- | --- |
| **Noham** (drilcoin) | US-13 ([#18](https://github.com/papito-code/INF1763-Mini-projet-1/issues/18)), US-14 ([#19](https://github.com/papito-code/INF1763-Mini-projet-1/issues/19)), US-08 ([#12](https://github.com/papito-code/INF1763-Mini-projet-1/issues/12)) | T-01 ([#20](https://github.com/papito-code/INF1763-Mini-projet-1/issues/20)) | [#21](https://github.com/papito-code/INF1763-Mini-projet-1/pull/21), [#30](https://github.com/papito-code/INF1763-Mini-projet-1/pull/30) | [#22](https://github.com/papito-code/INF1763-Mini-projet-1/pull/22), [#32](https://github.com/papito-code/INF1763-Mini-projet-1/pull/32), [#39](https://github.com/papito-code/INF1763-Mini-projet-1/pull/39), [#40](https://github.com/papito-code/INF1763-Mini-projet-1/pull/40) |
| **Kalil** (kalil-soum) | US-01 ([#5](https://github.com/papito-code/INF1763-Mini-projet-1/issues/5)), US-05 ([#9](https://github.com/papito-code/INF1763-Mini-projet-1/issues/9)) | T-02 ([#22](https://github.com/papito-code/INF1763-Mini-projet-1/pull/22)) | [#22](https://github.com/papito-code/INF1763-Mini-projet-1/pull/22), [#37](https://github.com/papito-code/INF1763-Mini-projet-1/pull/37) | [#31](https://github.com/papito-code/INF1763-Mini-projet-1/pull/31) |
| **Abdramane** (papito-code) | US-02 ([#6](https://github.com/papito-code/INF1763-Mini-projet-1/issues/6)), US-06 ([#10](https://github.com/papito-code/INF1763-Mini-projet-1/issues/10)), US-07 ([#11](https://github.com/papito-code/INF1763-Mini-projet-1/issues/11)) | T-03 ([GitHub Projects](https://github.com/users/papito-code/projects/1)), T-06 | [#28](https://github.com/papito-code/INF1763-Mini-projet-1/pull/28), [#31](https://github.com/papito-code/INF1763-Mini-projet-1/pull/31), [#32](https://github.com/papito-code/INF1763-Mini-projet-1/pull/32) | [#21](https://github.com/papito-code/INF1763-Mini-projet-1/pull/21), [#24](https://github.com/papito-code/INF1763-Mini-projet-1/pull/24), [#25](https://github.com/papito-code/INF1763-Mini-projet-1/pull/25), [#27](https://github.com/papito-code/INF1763-Mini-projet-1/pull/27), [#29](https://github.com/papito-code/INF1763-Mini-projet-1/pull/29), [#30](https://github.com/papito-code/INF1763-Mini-projet-1/pull/30), [#36](https://github.com/papito-code/INF1763-Mini-projet-1/pull/36), [#38](https://github.com/papito-code/INF1763-Mini-projet-1/pull/38), [#39](https://github.com/papito-code/INF1763-Mini-projet-1/pull/39) |
| **Yann** (yannuser) | US-03 ([#7](https://github.com/papito-code/INF1763-Mini-projet-1/issues/7)), US-09 ([#13](https://github.com/papito-code/INF1763-Mini-projet-1/issues/13)), US-10 ([#14](https://github.com/papito-code/INF1763-Mini-projet-1/issues/14)) | T-04 ([#26](https://github.com/papito-code/INF1763-Mini-projet-1/issues/26)) | [#25](https://github.com/papito-code/INF1763-Mini-projet-1/pull/25), [#33](https://github.com/papito-code/INF1763-Mini-projet-1/pull/33), [#34](https://github.com/papito-code/INF1763-Mini-projet-1/pull/34), [#27](https://github.com/papito-code/INF1763-Mini-projet-1/pull/27), [#29](https://github.com/papito-code/INF1763-Mini-projet-1/pull/29) | [#23](https://github.com/papito-code/INF1763-Mini-projet-1/pull/23) |
| **Armel** (Melito2k) | US-04 ([#8](https://github.com/papito-code/INF1763-Mini-projet-1/issues/8)), US-11 ([#15](https://github.com/papito-code/INF1763-Mini-projet-1/issues/15)), US-12 ([#17](https://github.com/papito-code/INF1763-Mini-projet-1/issues/17)) | T-05, T-07, T-08 | [#23](https://github.com/papito-code/INF1763-Mini-projet-1/pull/23), [#24](https://github.com/papito-code/INF1763-Mini-projet-1/pull/24), [#36](https://github.com/papito-code/INF1763-Mini-projet-1/pull/36), [#38](https://github.com/papito-code/INF1763-Mini-projet-1/pull/38), [#39](https://github.com/papito-code/INF1763-Mini-projet-1/pull/39), [#40](https://github.com/papito-code/INF1763-Mini-projet-1/pull/40) | [#28](https://github.com/papito-code/INF1763-Mini-projet-1/pull/28), [#34](https://github.com/papito-code/INF1763-Mini-projet-1/pull/34), [#37](https://github.com/papito-code/INF1763-Mini-projet-1/pull/37) |

### Détail

- **Noham** : squelette du projet poussé directement sur `main` (T-01), menu et sortie du programme (PR #21), sauvegarde des tâches dans `taches.csv` (PR #30). Revues : US-01 (#22), US-06 (#32, avec corrections demandées puis approbation), US-11 (#39), ajustement de `docs/verification.md` (#40).
- **Kalil** : ajout d'une tâche avec validation du titre, de la priorité et de la date (PR #22), puis attribution d'une échéance avec validation de la date (PR #37). Revue : US-07 (#31).
- **Abdramane** : liste des tâches (PR #28), retrait d'une échéance (PR #31), modification d'une échéance (PR #32), avec l'ajout de vérifications communes aux options de modification après la revue. Revues : menu et sortie (#21), README initial (#24), US-03 (#25), `docs/verification.md` (#27 et #29), US-08 (#30), US-12 (#36), README complet (#38), US-11 (#39).
- **Yann** : marquer une tâche comme terminée (PR #25), rechargement des données (PR #34), démarrage sans fichier (PR #33), début de `docs/verification.md` et exécution des premiers scénarios (PR #27 et #29). Revue : US-04 (#23).
- **Armel** : modification de la priorité (PR #23), README initial (PR #24) puis complet (PR #38), échec de sauvegarde (PR #36), validation du fichier de données (PR #39). Une PR d'ajustement de `docs/verification.md` ([#40](https://github.com/papito-code/INF1763-Mini-projet-1/pull/40)) a été fusionnée. Revues : US-02 (#28), US-09 (#34), US-05 (#37).

Les scénarios de `docs/verification.md` sont exécutés par le responsable des vérifications et par les relecteurs lors des revues, qui indiquent ce qu'ils ont essayé.
