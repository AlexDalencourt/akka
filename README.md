# akka

### Processus 

La classe Process.java contient le main de l'application.

Une fois lancée l'application demande de sélectionner le nombre d'acteurs compteur à utiliser.
Après la création des acteurs l'application demande la saisie du text à analyser.
Enfin il faut alors saisir le pattern à chercher dans le text. Les regexs fonctionnent.

### Fonctionnement général

Après avoir créé les processus de lecture le processus créé des noeuds de regroupement permettant de réaliser
un arbre binaire.
Enfin un noeud root contiendra le premier noeud de l'arbre permettant de dispatcher à tous les sous noeuds
et feuilles.
Lorsqu'un text doit être analysé le message se retrouve divisé en deux au centre de la chaine dans les deux
sous noeuds ou feuilles. Puis lorsque les deux messages de retour contenant le résultat sont reçu le nouveau
résultat est renvoyé au niveau supérieur.