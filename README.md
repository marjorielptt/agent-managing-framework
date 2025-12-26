# Framework multi-agents

Framework d’acteurs distribués, où chaque **acteur** représente une entité capable de **recevoir et traiter des messages**. Les acteurs peuvent également communiquer entre eux, qu'ils soient situés dans le même **microservice** ou non.
Notre framework a les propriétés suivantes :
- **Résilience** : Il gère les pannes d'acteurs sans avoir à redémarrer l'application,
- **Élasticité** : Il s'adapte dynamiquement à charge en créant ou supprimant des instances d'acteurs.

Ce projet contient également un exemple d'application du framework : le SAMU. 

Ce projet est inspiré du framework [Akka](https://github.com/akka/akka-core).

## Tutoriel d'installation

- Télécharger le code source du projet et l'ouvrir dans l'IDE de votre choix.

- Exécuter le fichier DiscoveryApplication.java
