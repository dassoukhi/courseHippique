# Course hippique
## Auteur
DASSOUKHI saleh
## Consigne

- Outil de gestion de courses hippiques


- Hippodrome (un seul, nom), Course (nom, date, 6 chevaux), cheval(nom, âge, nombre de victoire). Indépendant les uns des autres.

- Menu en console.

- CRUD sur course et cheval.

- R sur Hippodrome (resultats) -> lister les 10 dernières courses et le cheval qui a gagné.

- Outil qui génère les résultats de course.

- Enregistrement des données (bdd, fichier txt)

- Le code sur github, et le lien sera rendu sur Moodle.

- Le taux de couverture doit être de 75% minimum.

- Chaque test doit passer.

- Un fichier readme expliquera succinctement la stratégie de tests.

## Stratégie de tests
Pour effectuer ce projet, on s'est basé sur la méthodologie DTT pour mettre en place les différentes fonctionnalités. 

On a d'abord mis en place les modèles( Cheval, Course, Hippodrome, et Data). Puis nous avons développer respectivement les classes DAO, ainsi que les controllers. Toute fonctionnalité a été d'abord pensé et implémenté en test avant de le créée dans notre application. Au final, nous avons implémenté la partie Menu qui permettra à un User quelconque de lancer l'appli.

Pour sauvegarder les données, On est parti sur un fichier JSON où on contrôle la lecture/écriture et le changement d'état de la BDD(json) , puis on a testé toutes les fonctionnalité lieu à notre BDD.

L'avantage de tester le code à chaque fois qu'on avance nous permis de nous questionner sur différentes fonctions qu'on a implémenté, de revoir notre logique du code, et de vérifier le bon fonctionnement du code s'il respecte des conditions prédéfinies.

## Coverage
![image description](https://github.com/dassoukhi/courseHippique/blob/master/coverage.PNG)
## Installation
Cloner le répertoire dans votre bureau par exemple.

```bash
git clone https://github.com/dassoukhi/courseHippique.git
```
Positionner à la racine du dossier.

```bash
cd ./courseHippique OU ouvrez le à partir de votre IDE préféré.
```
Pour exécuter les test, lancez tous les fichiers(controlerTest, daoTest, utilsTest) qui se trouvent dans le dossier 'src/tests' Ou à partir du terminal.

```bash
Exemple :
javac HyppodromeControlerTest.java
java HyppodromeControlerTest
```

Pour lancer l'application, exécutez le fichier Main.java qui se trouve dans src/controler Ou à partir du terminal avec.

```bash
javac Main.java
java Main
```
