

## Scénario d'utilisation pour un client : Souscription à un abonnement

### Acteur principal : 
Client

### Prérequis :
Le client doit être inscrit et authentifié.

### Flux principal :

1. Le client se connecte à son compte via l'URL: `https://localost/api/login`.
2. Après authentification, il est redirigé vers son tableau de bord personnel.
3. Le client clique sur le menu "Souscrire à un abonnement".
4. Il choisit entre l'offre "basic" et l'offre "plus".
5. Une fois le choix fait, il clique sur le bouton "Souscrire".
6. Une requête API est envoyée à l'URL : `https://etron-charging-service.com/api/users/{userId}/subscribe` avec les détails de l'abonnement choisi.
7. Le client est redirigé vers une page de paiement pour finaliser la souscription.
8. Après avoir renseigné les détails de paiement et confirmé, une requête de paiement est traitée via l'API : `https://etron-charging-service.com/api/users/{userId}/payment`.
9. Une fois le paiement validé, le client est redirigé vers une page de confirmation avec un récapitulatif de son abonnement.
10. Le client reçoit également un e-mail de confirmation avec les détails de son abonnement.

### Fonctionnalités supplémentaires :

1. Consultation de l'abonnement et de la consommation : Le client peut consulter son tableau de bord pour voir les détails de son abonnement, sa date d'expiration, sa consommation, et le montant payé.
2. Consulter les factures : Le client peut accéder à ses factures payées, les consulter en détail et télécharger un reçu.
3. Gestion des cartes : Le client peut consulter, ajouter ou supprimer des cartes de paiement associées à son compte.
4. Prolongation d'abonnement : Si l'abonnement est sur le point d'expirer ou si le client souhaite le renouveler à l'avance, il a la possibilité de le prolonger via son tableau de bord.
5. Paiements mensuels : Le client est notifié chaque mois pour effectuer le paiement de son abonnement. Il peut le faire directement via son tableau de bord.

_Note : Ce scénario est basé sur un cas réel d'utilisation du système e-tron d'Audi._

