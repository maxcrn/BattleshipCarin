package Carin.Maxime;

import java.util.Random;
import java.util.Scanner;

public class Player {
	Ship carrier;
	Ship battleship;
	Ship cruiser;
	Ship submarine;
	Ship destroyer;
	Grid grille;
	Grid grilleAdv;
	int score;
	Player joueurOpp;
	String nom;
	
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Ship getCarrier() {
		return carrier;
	}

	public Grid getGrilleAdv() {
		return grilleAdv;
	}

	public void setGrilleAdv(Grid grilleAdv) {
		this.grilleAdv = grilleAdv;
	}

	public Player getJoueurOpp() {
		return joueurOpp;
	}

	public void setJoueurOpp(Player joueurOpp) {
		this.joueurOpp = joueurOpp;
	}

	public Ship getBattleship() {
		return battleship;
	}

	public Ship getCruiser() {
		return cruiser;
	}

	public Ship getSubmarine() {
		return submarine;
	}

	public Ship getDestroyer() {
		return destroyer;
	}
	
	public Grid getGrille() {
		return grille;
	}

	public void setGrille(Grid grille) {
		this.grille = grille;
	}

	public Player(){}
	
	public Player(String name) {
		grille = new Grid(10);
		grilleAdv = new Grid(10);
		Scanner sc = new Scanner(System.in);
		int statut = -1;
		nom = name;
		score = 0;
		String start;
		String end;
		/*
		 * 0 : bateau placé 
		 * 1 : bateau non placé car déjà un bateau à cet emplacement 
		 * 2 : bateau non placé car coordonnées hors grille
		 * 3 : bateau non placé car coordonnées ne sont pas sur la même ligne ou colonne
		 * 4 : bateau non placé car coordonnées pas assez espacées pour la taille du bateau
		 */
		
		if(name.equals("IA0") || name.equals("IA1") || name.equals("IA2")) { // Placement des bateaux de l'IA 
			
			while(statut != 0) {
			// Destroyer
			destroyer = new Ship(2);
			statut = Grid.placeShip(destroyer, grille);
			}
			statut = -1;
			
			while(statut != 0) {
			// Submarine
			submarine = new Ship(3);
			statut = Grid.placeShip(submarine, grille);
			}
			statut = -1;
			
			while(statut != 0) {
			// Cruiser
			cruiser = new Ship(3);
			statut = Grid.placeShip(cruiser, grille);
			}
			statut = -1;
			
			while(statut != 0) {
			// Battleship
			battleship = new Ship(4);
			statut = Grid.placeShip(battleship, grille);
			}
			statut = -1;
			
			while(statut != 0) {
			//Carrier
			carrier = new Ship(5);
			statut = Grid.placeShip(carrier,  grille);
			}
			statut = -1;
		}
		
		else {
			
			System.out.println("C'est au tour de " + name + " de placer ses bateaux");
			
			while(statut != 0) {
				// Saisie des coordonnées du destroyer
				System.out.println("Saisir la coordonnée de départ du Destroyer (taille 2) (entre A1 et J10) :");
				start = sc.nextLine();
				System.out.println("Saisir la coordonnée de fin du Destroyer à 2 cases d'écart de la coordonnée de début :");
				end = sc.nextLine();
				// Création du destroyer
				destroyer = new Ship(start, end, 2);
				// Placement du destroyer
				statut = Grid.placeShip(destroyer, grille);
				if(statut == 0) {
					System.out.println("Bateau placé !");
				}else if(statut == 1) {
					System.out.println("Bateau non placé, car il y a déjà un bateau à cet emplacement !");
				}else if(statut == 2) {
					System.out.println("Bateau non placé, car les coordonnées ne se situent pas dans la grille !");
				}else if(statut == 3) {
					System.out.println("Bateau non placé, car les coordonnées de début / fin ne se situent pas sur la même ligne ni sur la même colonne !");
				}else if(statut == 4) {
					System.out.println("Bateau non placé, car les coordonnées entrées ne correspondent pas à la taille du bateau !");
				}else if(statut == 5){
					System.out.println("Bateau non placé, car les coordonnées sont invalides !");
				}
			}
			statut = -1;
			
			while(statut != 0) {
				// Saisie des coordonnées du submarine
				System.out.println("Saisir la coordonnée de départ du Submarine (taille 3) (entre A1 et J10) :");
				start = sc.nextLine();
				System.out.println("Saisir la coordonnée de fin du Submarine à 3 cases d'écart de la coordonnée de début :");
				end = sc.nextLine();
				// Création du submarine
				submarine = new Ship(start, end, 3);
				// Placement du submarine
				statut = Grid.placeShip(submarine, grille);
				if(statut == 0) {
					System.out.println("Bateau placé !");
				}else if(statut == 1) {
					System.out.println("Bateau non placé, car il y a déjà un bateau à cet emplacement !");
				}else if(statut == 2) {
					System.out.println("Bateau non placé, car les coordonnées ne se situent pas dans la grille !");
				}else if(statut == 3) {
					System.out.println("Bateau non placé, car les coordonnées de début / fin ne se situent pas sur la même ligne ni sur la même colonne !");
				}else if(statut == 4) {
					System.out.println("Bateau non placé, car les coordonnées entrées ne correspondent pas à la taille du bateau !");
				}else if(statut == 5){
					System.out.println("Bateau non placé, car les coordonnées sont invalides !");
				}
			}
			statut = -1;
			
			while(statut != 0) {
				// Saisie des coordonnées du cruiser
				System.out.println("Saisir la coordonnée de départ du Cruiser (taille 3) (entre A1 et J10) :");
				start = sc.nextLine();
				System.out.println("Saisir la coordonnée de fin du Cruiser à 3 cases d'écart de la coordonnée de début :");
				end = sc.nextLine();
				// Création du cruiser
				cruiser = new Ship(start, end, 3);
				// Placement du cruiser
				statut = Grid.placeShip(cruiser, grille);
				if(statut == 0) {
					System.out.println("Bateau placé !");
				}else if(statut == 1) {
					System.out.println("Bateau non placé, car il y a déjà un bateau à cet emplacement !");
				}else if(statut == 2) {
					System.out.println("Bateau non placé, car les coordonnées ne se situent pas dans la grille !");
				}else if(statut == 3) {
					System.out.println("Bateau non placé, car les coordonnées de début / fin ne se situent pas sur la même ligne ni sur la même colonne !");
				}else if(statut == 4) {
					System.out.println("Bateau non placé, car les coordonnées entrées ne correspondent pas à la taille du bateau !");
				}else if(statut == 5){
					System.out.println("Bateau non placé, car les coordonnées sont invalides !");
				}
			}
			statut = -1;
			
			while(statut != 0) {
				// Saisie des coordonnées du battleship
				System.out.println("Saisir la coordonnée de départ du Battleship (taille 4) (entre A1 et J10) :");
				start = sc.nextLine();
				System.out.println("Saisir la coordonnée de fin du Battleship à 4 cases d'écart de la coordonnée de début :");
				end = sc.nextLine();
				// Création du battleship
				battleship = new Ship(start, end, 4);
				// Placement du battleship
				statut = Grid.placeShip(battleship, grille);
				if(statut == 0) {
					System.out.println("Bateau placé !");
				}else if(statut == 1) {
					System.out.println("Bateau non placé, car il y a déjà un bateau à cet emplacement !");
				}else if(statut == 2) {
					System.out.println("Bateau non placé, car les coordonnées ne se situent pas dans la grille !");
				}else if(statut == 3) {
					System.out.println("Bateau non placé, car les coordonnées de début / fin ne se situent pas sur la même ligne ni sur la même colonne !");
				}else if(statut == 4) {
					System.out.println("Bateau non placé, car les coordonnées entrées ne correspondent pas à la taille du bateau !");
				}else if(statut == 5){
					System.out.println("Bateau non placé, car les coordonnées sont invalides !");
				}
			}
			statut = -1;
			
			while(statut != 0) {
				// Saisie des coordonnées du carrier
				System.out.println("Saisir la coordonnée de départ du Carrier (taille 5) (entre A1 et J10) : ");
				start = sc.nextLine();
				System.out.println("Saisir la coordonnée de fin du Carrier à 5 cases d'écart de la coordonnée de début : ");
				end = sc.nextLine();
				// Création du carrier
				carrier = new Ship(start, end, 5);
				// Placement du carrier
				statut = Grid.placeShip(carrier, grille);
				if(statut == 0) {
					System.out.println("Bateau placé !");
				}else if(statut == 1) {
					System.out.println("Bateau non placé, car il y a déjà un bateau à cet emplacement !");
				}else if(statut == 2) {
					System.out.println("Bateau non placé, car les coordonnées ne se situent pas dans la grille !");
				}else if(statut == 3) {
					System.out.println("Bateau non placé, car les coordonnées de début / fin ne se situent pas sur la même ligne ni sur la même colonne !");
				}else if(statut == 4) {
					System.out.println("Bateau non placé, car les coordonnées entrées ne correspondent pas à la taille du bateau !");
				}else if(statut == 5){
					System.out.println("Bateau non placé, car les coordonnées sont invalides !");
				}
			}
			statut = -1;
		}
	}
	
	public static String isHit(Player joueur, String shot) { // Joueur opposé en paramètre
		int[][] grille = joueur.getGrille().getGrid();
		
		int xMissile = Grid.convertCoord(shot.substring(0, 1));
		int yMissile = Integer.parseInt(shot.substring(1)) - 1;
		
		// Cas du Destroyer
		
		// Attribution des coordonnées du bateau
		int xStart = Grid.convertCoord(joueur.getDestroyer().getStartCoord().substring(0, 1));
		int yStart = Integer.parseInt(joueur.getDestroyer().getStartCoord().substring(1)) -1;
		int xEnd = Grid.convertCoord(joueur.getDestroyer().getEndCoord().substring(0, 1));
		int yEnd = Integer.parseInt(joueur.getDestroyer().getEndCoord().substring(1)) - 1;
		int orientation = joueur.getDestroyer().getOrientation();
		
		// Vérification de la grille en fonction de l'orientation du bateau
		if(orientation == 1) {
			for (int i = yStart; i <= yEnd; i++) { // Vérification de l'état du bateau
				if(xStart == xMissile && yMissile == i) {
					joueur.getDestroyer().setNbTouche(joueur.getDestroyer().getNbTouche() + 1);
					if (Ship.isDestroyed(joueur.getDestroyer())){
						joueur.getDestroyer().sunk = true;
						joueur.getJoueurOpp().setScore(joueur.getJoueurOpp().getScore()+1);
						return "Destroyer détruit";
					}
					return "Destroyer touché";
				}
			}
		}else if(orientation == 2) {
			for (int i = yEnd; i <= yStart; i++) { // Vérification de l'état du bateau
				if(xStart == xMissile && yMissile == i) {
					joueur.getDestroyer().setNbTouche(joueur.getDestroyer().getNbTouche() + 1);
					if (Ship.isDestroyed(joueur.getDestroyer())){
						joueur.getDestroyer().sunk = true;
						joueur.getJoueurOpp().setScore(joueur.getJoueurOpp().getScore()+1);
						return "Destroyer détruit";
					}
					return "Destroyer touché";
				}
			}
		}else if(orientation == 3) {
			for (int i = xEnd; i <= xStart; i++) { // Vérification de l'état du bateau
				if(yStart == yMissile && xMissile == i) {
					joueur.getDestroyer().setNbTouche(joueur.getDestroyer().getNbTouche() + 1);
					if (Ship.isDestroyed(joueur.getDestroyer())){
						joueur.getDestroyer().sunk = true;
						joueur.getJoueurOpp().setScore(joueur.getJoueurOpp().getScore()+1);
						return "Destroyer détruit";
					}
					return "Destroyer touché";
				}
			}
		}else if(orientation == 4) {
			for (int i = xStart; i <= xEnd; i++) { // Vérification de l'état du bateau
				if(yStart == yMissile && xMissile == i) {
					joueur.getDestroyer().setNbTouche(joueur.getDestroyer().getNbTouche() + 1);
					if (Ship.isDestroyed(joueur.getDestroyer())){
						joueur.getDestroyer().sunk = true;
						joueur.getJoueurOpp().setScore(joueur.getJoueurOpp().getScore()+1);
						return "Destroyer détruit";
					}
					return "Destroyer touché";
				}
			}
		}
		

// Cas du Submarine

		// Attribution des coordonées du bateau
		xStart = Grid.convertCoord(joueur.getSubmarine().getStartCoord().substring(0, 1));
		yStart = Integer.parseInt(joueur.getSubmarine().getStartCoord().substring(1)) -1;
		xEnd = Grid.convertCoord(joueur.getSubmarine().getEndCoord().substring(0, 1));
		yEnd = Integer.parseInt(joueur.getSubmarine().getEndCoord().substring(1)) - 1;
		orientation = joueur.getSubmarine().getOrientation();
		
		// Vérification de la grille en fonction de l'orientation du bateau
		if(orientation == 1) {
			for (int i = yStart; i <= yEnd; i++) { // Vérification de l'état du bateau
				if(xStart == xMissile && yMissile == i) {
					joueur.getSubmarine().setNbTouche(joueur.getSubmarine().getNbTouche() + 1);
					if (Ship.isDestroyed(joueur.getSubmarine())){
						joueur.getSubmarine().sunk = true;
						joueur.getJoueurOpp().setScore(joueur.getJoueurOpp().getScore()+1);
						return "Submarine détruit";
					}
					return "Submarine touché";
				}
			}
		}else if(orientation == 2) {
			for (int i = yEnd; i <= yStart; i++) { // Vérification de l'état du bateau
				if(xStart == xMissile && yMissile == i) {
					joueur.getSubmarine().setNbTouche(joueur.getSubmarine().getNbTouche() + 1);
					if (Ship.isDestroyed(joueur.getSubmarine())){
						joueur.getSubmarine().sunk = true;
						joueur.getJoueurOpp().setScore(joueur.getJoueurOpp().getScore()+1);
						return "Submarine détruit";
					}
					return "Submarine touché";
				}
			}
		}else if(orientation == 3) {
			for (int i = xEnd; i <= xStart; i++) { // Vérification de l'état du bateau
				if(yStart == yMissile && xMissile == i) {
					joueur.getSubmarine().setNbTouche(joueur.getSubmarine().getNbTouche() + 1);
					if (Ship.isDestroyed(joueur.getSubmarine())){
						joueur.getSubmarine().sunk = true;
						joueur.getJoueurOpp().setScore(joueur.getJoueurOpp().getScore()+1);
						return "Submarine détruit";
					}
					return "Submarine touché";
				}
			}
		}else if(orientation == 4) {
			for (int i = xStart; i <= xEnd; i++) { // Vérification de l'état du bateau
				if(yStart == yMissile && xMissile == i) {
					joueur.getSubmarine().setNbTouche(joueur.getSubmarine().getNbTouche() + 1);
					if (Ship.isDestroyed(joueur.getSubmarine())){
						joueur.getSubmarine().sunk = true;
						joueur.getJoueurOpp().setScore(joueur.getJoueurOpp().getScore()+1);
						return "Submarine détruit";
					}
					return "Submarine touché";
				}
			}
		}

// Cas du Cruiser

		// Attribution des coordonées du bateau
		xStart = Grid.convertCoord(joueur.getCruiser().getStartCoord().substring(0, 1));
		yStart = Integer.parseInt(joueur.getCruiser().getStartCoord().substring(1)) -1;
		xEnd = Grid.convertCoord(joueur.getCruiser().getEndCoord().substring(0, 1));
		yEnd = Integer.parseInt(joueur.getCruiser().getEndCoord().substring(1)) - 1;
		orientation = joueur.getCruiser().getOrientation();
		
		// Vérification de la grille en fonction de l'orientation du bateau
		if(orientation == 1) {
			for (int i = yStart; i <= yEnd; i++) { // Vérification de l'état du bateau
				if(xStart == xMissile && yMissile == i) {
					joueur.getCruiser().setNbTouche(joueur.getCruiser().getNbTouche() + 1);
					if (Ship.isDestroyed(joueur.getCruiser())){
						joueur.getCruiser().sunk = true;
						joueur.getJoueurOpp().setScore(joueur.getJoueurOpp().getScore()+1);
						return "Cruiser détruit";
					}
					return "Cruiser touché";
				}
			}
		}else if(orientation == 2) {
			for (int i = yEnd; i <= yStart; i++) { // Vérification de l'état du bateau
				if(xStart == xMissile && yMissile == i) {
					joueur.getCruiser().setNbTouche(joueur.getCruiser().getNbTouche() + 1);
					if (Ship.isDestroyed(joueur.getCruiser())){
						joueur.getCruiser().sunk = true;
						joueur.getJoueurOpp().setScore(joueur.getJoueurOpp().getScore()+1);
						return "Cruiser détruit";
					}
					return "Cruiser touché";
				}
			}
		}else if(orientation == 3) {
			for (int i = xEnd; i <= xStart; i++) { // Vérification de l'état du bateau
				if(yStart == yMissile && xMissile == i) {
					joueur.getCruiser().setNbTouche(joueur.getCruiser().getNbTouche() + 1);
					if (Ship.isDestroyed(joueur.getCruiser())){
						joueur.getCruiser().sunk = true;
						joueur.getJoueurOpp().setScore(joueur.getJoueurOpp().getScore()+1);
						return "Cruiser détruit";
					}
					return "Cruiser touché";
				}
			}
		}else if(orientation == 4) {
			for (int i = xStart; i <= xEnd; i++) { // Vérification de l'état du bateau
				if(yStart == yMissile && xMissile == i) {
					joueur.getCruiser().setNbTouche(joueur.getCruiser().getNbTouche() + 1);
					if (Ship.isDestroyed(joueur.getCruiser())){
						joueur.getCruiser().sunk = true;
						joueur.getJoueurOpp().setScore(joueur.getJoueurOpp().getScore()+1);
						return "Cruiser détruit";
					}
					return "Cruiser touché";
				}
			}
		}


		// Cas du Battleship
		
		// Attribution des coordonées du bateau
		xStart = Grid.convertCoord(joueur.getBattleship().getStartCoord().substring(0, 1));
		yStart = Integer.parseInt(joueur.getBattleship().getStartCoord().substring(1)) -1;
		xEnd = Grid.convertCoord(joueur.getBattleship().getEndCoord().substring(0, 1));
		yEnd = Integer.parseInt(joueur.getBattleship().getEndCoord().substring(1)) - 1;
		orientation = joueur.getBattleship().getOrientation();
		
		// Vérification de la grille en fonction de l'orientation du bateau
		if(orientation == 1) {
			for (int i = yStart; i <= yEnd; i++) { // Vérification de l'état du bateau
				if(xStart == xMissile && yMissile == i) {
					joueur.getBattleship().setNbTouche(joueur.getBattleship().getNbTouche() + 1);
					if (Ship.isDestroyed(joueur.getBattleship())){
						joueur.getBattleship().sunk = true;
						joueur.getJoueurOpp().setScore(joueur.getJoueurOpp().getScore()+1);
						return "Battleship détruit";
					}
					return "Battleship touché";
				}
			}
		}else if(orientation == 2) {
			for (int i = yEnd; i <= yStart; i++) { // Vérification de l'état du bateau
				if(xStart == xMissile && yMissile == i) {
					joueur.getBattleship().setNbTouche(joueur.getBattleship().getNbTouche() + 1);
					if (Ship.isDestroyed(joueur.getBattleship())){
						joueur.getBattleship().sunk = true;
						joueur.getJoueurOpp().setScore(joueur.getJoueurOpp().getScore()+1);
						return "Battleship détruit";
					}
					return "Battleship touché";
				}
			}
		}else if(orientation == 3) {
			for (int i = xEnd; i <= xStart; i++) { // Vérification de l'état du bateau
				if(yStart == yMissile && xMissile == i) {
					joueur.getBattleship().setNbTouche(joueur.getBattleship().getNbTouche() + 1);
					if (Ship.isDestroyed(joueur.getBattleship())){
						joueur.getBattleship().sunk = true;
						joueur.getJoueurOpp().setScore(joueur.getJoueurOpp().getScore()+1);
						return "Battleship détruit";
					}
					return "Battleship touché";
				}
			}
		}else if(orientation == 4) {
			for (int i = xStart; i <= xEnd; i++) { // Vérification de l'état du bateau
				if(yStart == yMissile && xMissile == i) {
					joueur.getBattleship().setNbTouche(joueur.getBattleship().getNbTouche() + 1);
					if (Ship.isDestroyed(joueur.getBattleship())){
						joueur.getBattleship().sunk = true;
						joueur.getJoueurOpp().setScore(joueur.getJoueurOpp().getScore()+1);
						return "Battleship détruit";
					}
					return "Battleship touché";
				}
			}
		}		
		
		
		// Cas du carrier
		
		// Attribution des coordonées du bateau
		xStart = Grid.convertCoord(joueur.getCarrier().getStartCoord().substring(0, 1));
		yStart = Integer.parseInt(joueur.getCarrier().getStartCoord().substring(1)) -1;
		xEnd = Grid.convertCoord(joueur.getCarrier().getEndCoord().substring(0, 1));
		yEnd = Integer.parseInt(joueur.getCarrier().getEndCoord().substring(1)) - 1;
		orientation = joueur.getCarrier().getOrientation();
		
		// Vérification de la grille en fonction de l'orientation du bateau
		if(orientation == 1) {
			for (int i = yStart; i <= yEnd; i++) { // Vérification de l'état du bateau
				if(xStart == xMissile && yMissile == i) {
					joueur.getCarrier().setNbTouche(joueur.getCarrier().getNbTouche() + 1);
					if (Ship.isDestroyed(joueur.getCarrier())){
						joueur.getCarrier().sunk = true;
						joueur.getJoueurOpp().setScore(joueur.getJoueurOpp().getScore()+1);
						return "Carrier détruit";
					}
					return "Carrier touché";
				}
			}
		}else if(orientation == 2) {
			for (int i = yEnd; i <= yStart; i++) { // Vérification de l'état du bateau
				if(xStart == xMissile && yMissile == i) {
					joueur.getCarrier().setNbTouche(joueur.getCarrier().getNbTouche() + 1);
					if (Ship.isDestroyed(joueur.getCarrier())){
						joueur.getCarrier().sunk = true;
						joueur.getJoueurOpp().setScore(joueur.getJoueurOpp().getScore()+1);
						return "Carrier détruit";
					}
					return "Carrier touché";
				}
			}
		}else if(orientation == 3) {
			for (int i = xEnd; i <= xStart; i++) { // Vérification de l'état du bateau
				if(yStart == yMissile && xMissile == i) {
					joueur.getCarrier().setNbTouche(joueur.getCarrier().getNbTouche() + 1);
					if (Ship.isDestroyed(joueur.getCarrier())){
						joueur.getCarrier().sunk = true;
						joueur.getJoueurOpp().setScore(joueur.getJoueurOpp().getScore()+1);
						return "Carrier détruit";
					}
					return "Carrier touché";
				}
			}
		}else if(orientation == 4) {
			for (int i = xStart; i <= xEnd; i++) { // Vérification de l'état du bateau
				if(yStart == yMissile && xMissile == i) {
					joueur.getCarrier().setNbTouche(joueur.getCarrier().getNbTouche() + 1);
					if (Ship.isDestroyed(joueur.getCarrier())){
						joueur.getCarrier().sunk = true;
						joueur.getJoueurOpp().setScore(joueur.getJoueurOpp().getScore()+1);
						return "Carrier détruit";
					}
					return "Carrier touché";
				}
			}
		}			
		return "";
	}
	
	public static String nextShot(String shot, int nbShot) {
		int xTemp;
		char yTemp;
		if(nbShot == 1){ // Tir en dessous
			xTemp = Integer.parseInt(shot.substring(1)) + 1;
			return (shot.substring(0,1) + Integer.toString(xTemp));
		}
		else if(nbShot == 2) { // Tir à droite
			yTemp = shot.charAt(0);
			yTemp = (char)(yTemp + 1);
			return Character.toString(yTemp) + shot.substring(1);
		}
		else if(nbShot == 3){ // Tir au dessus
			xTemp = Integer.parseInt(shot.substring(1)) - 1;
			return (shot.substring(0,1) + Integer.toString(xTemp));
		}
		else if(nbShot == 4) { // Tir à gauche
			yTemp = shot.charAt(0);
			yTemp = (char)(yTemp - 1);
			return Character.toString(yTemp) + shot.substring(1);
		}
		return "";
	}
	
	public static String randomCoordShot() {
		Random rand = new Random();
		char c = (char)(rand.nextInt(10) + 97);
		String result = Integer.toString(rand.nextInt(10)+1);
		result = Character.toString(c) + result;
		return result;
	}
	
	public static int shot(Player joueur, String tir) {
		
		int xMissile;
		int yMissile;
		// Vérification que les coordonnées sont valides
		
		try{
			xMissile = Grid.convertCoord(tir.substring(0, 1));
			yMissile = Integer.parseInt(tir.substring(1)) - 1;
		}catch(NumberFormatException e){
			return 3;
		}catch(IndexOutOfBoundsException e){
			return 3;
		}
		
		
		xMissile = Grid.convertCoord(tir.substring(0, 1));
		yMissile = Integer.parseInt(tir.substring(1)) - 1;
		
		if(xMissile < 0 || yMissile < 0) {
			return 3;
		}
		
		String xStartChar=tir.substring(0, 1).toUpperCase();
		String xEndChar=tir.substring(0, 1).toUpperCase();
		
		if(!xStartChar.equals("A") && !xStartChar.equals("B") && !xStartChar.equals("C") && !xStartChar.equals("D") && !xStartChar.equals("E") && !xStartChar.equals("F") && !xStartChar.equals("G") && !xStartChar.equals("H") && !xStartChar.equals("I") && !xStartChar.equals("J")){
			return 3;
		}
		if(!xEndChar.equals("A") && !xEndChar.equals("B") && !xEndChar.equals("C") && !xEndChar.equals("D") && !xEndChar.equals("E") && !xEndChar.equals("F") && !xEndChar.equals("G") && !xEndChar.equals("H") && !xEndChar.equals("I") && !xEndChar.equals("J")){
			return 3;
		}
		
		// Vérification que les coordonnées sont bien situées dans la grille
		if (xMissile >= joueur.getGrille().getTaille() || yMissile >= joueur.getGrille().getTaille()) {
			return 3; // Coordonnées hors grille
		}
		
		if (joueur.getJoueurOpp().getGrille().getGrid()[xMissile][yMissile] == 1) {
			joueur.getJoueurOpp().getGrille().getGrid()[xMissile][yMissile] = 2; // MAJ de la grille de l'adversaire
			joueur.getGrilleAdv().getGrid()[xMissile][yMissile] = 2; // MAJ de la grille de frappe du joueur actuel
			return 1; // Retourne 1 si un bateau est touché.
			
		} else if (joueur.getJoueurOpp().getGrille().getGrid()[xMissile][yMissile] == 0) {
			joueur.getGrilleAdv().getGrid()[xMissile][yMissile] = 3; // MAJ de la grille de frappe du joueur actuel
			return 0; // Retourne 0 si aucun bateau n'est touché.
			
		} else if (joueur.getJoueurOpp().getGrille().getGrid()[xMissile][yMissile] == 2 || joueur.getJoueurOpp().getGrille().getGrid()[xMissile][yMissile] == 3) {
			return 2; // Retourne 2 si un tir a déjà été effectué sur cette case.
		}
		return 0;
		
	}
}