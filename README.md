# Nom du Projet : ETRONCAR


## Table des matières

- [Fonctionnalités](#fonctionnalités)
- [Installation](#installation)
- [Utilisation](#utilisation)
- [Contribution](#contribution)
- [Database Schema](#Database Schema)
- [Technologie](#technologie)


## Fonctionnalités
![diagramDeSequence.png](diagramDeSequence.png)
### Scénario client
- Créer un compte
- Se connecter
- Voir le profil
- Voir les offres disponibles
- Rechercher une offre par nom
- Créer un abonnement
- Obtenir le texte du contrat
- Voir mes factures
- Payer une facture
- Consulter mon abonnement

### Scénario administrateur
- S'inscrire
- Se connecter
- Voir toutes les offres
- Voir les voitures
- Voir les vim
- Créer un vim
- Supprimer un abonnement
- Créer une offre

## Installation

Décrivez ici comment installer et configurer votre projet.

```bash
# Clonez le dépôt
git clone https://github.com/Medhaddadi/eton.git
# Naviguez vers le dossier du projet
cd eton
```

```mysql
INSERT INTO VIN (vin_number, description)
VALUES ('1HGCM82633A123456', 'Exemple de VIN 1'),
('2HGCM82633B234567', 'Exemple de VIN 2'),
('3HGCM82633C345678', 'Exemple de VIN 3'),
('4HGCM82633D456789', 'Exemple de VIN 4'),
('5HGCM82633E567890', 'Exemple de VIN 5');

```
## Database Schema
![etrondb.png](etrondb.png)

## Technologie
- Spring Boot 
  - Spring Data JPA
    - Hibernate : ORM 
      - definition : Hibernate est un framework open source gérant la persistance des objets en base de données relationnelle. Il propose une solution performante pour le mapping objet-relationnel (ORM) permettant de faciliter le développement d'applications d'accès aux données.
  - Spring Security
    - JWT
      - definition : JSON Web Token (JWT) est un standard ouvert (RFC 7519) qui définit un moyen compact et autonome pour sécuriser les échanges d'informations entre plusieurs parties sous forme d'objet JSON. Cette information peut être vérifiée et validée facilement car elle est signée numériquement.
  - Spring Web
    - Spring REST
      - definition : REST (REpresentational State Transfer) est un style d'architecture permettant de construire des applications légères, évolutives et performantes, notamment pour les applications web. REST est un style d'architecture, et non un protocole. Il utilise les verbes HTTP (GET, POST, PUT, DELETE) pour manipuler les ressources.
  - Spring Validation
    - definition : Spring Validation est un framework de validation de données. Il permet de valider les données d'un formulaire avant de les enregistrer dans la base de données.
  - Spring DevTools
    - definition : Spring DevTools est un module qui permet de redémarrer automatiquement l'application lorsqu'un changement est détecté dans le code source.
  - Java Faker
    - definition : Java Faker est une bibliothèque Java qui permet de générer des données aléatoires.
  - Lombok
    - definition : Lombok est une bibliothèque Java qui permet de réduire le code source en générant automatiquement les getters et les setters.
- MySQL