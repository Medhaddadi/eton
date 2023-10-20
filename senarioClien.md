

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

