package Carin.Maxime;
import java.util.Random;
import java.util.Scanner;

public class Grid {
	
	int taille;
	int[][] grid;

	// 0 : Case vide, 1 : Case avec un bateau, 2 : Case avec un bateau touché, 3 : Case où un tir sans succès a déjà été effectué.

	public Grid(int size) {
		taille = size;
		grid = new int[size][size];
		// Initialise toutes les cases de la grille à 0.
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				grid[x][y] = 0;
			}
		}
	}

	public int[][] getGrid() {
		return grid;
	}

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}

	public static void showGrille(Player joueur){
		
		System.out.println("Votre grille : ");
		System.out.println(" ");
		int a = 1;
		System.out.println("    A B C D E F G H I J");
		System.out.println("    ___________________");
		for (int y = 0; y < 10; y++) {
			System.out.print(a + " | ");
			for (int x = 0; x < 10; x++) {
				System.out.print(joueur.getGrille().getGrid()[x][y] + " ");
			}
			System.out.println(" ");
			a++;
		}
	}
	
	public static void showGrilleAdv(Player joueur){
		System.out.println(" ");
		System.out.println("Votre grille de frappe : ");
		System.out.println(" ");
		int a = 1;
		System.out.println("    A B C D E F G H I J");
		System.out.println("    ___________________");
		for (int y = 0; y < 10; y++) {
			System.out.print(a + " | ");
			for (int x = 0; x < 10; x++) {
				System.out.print(joueur.getGrilleAdv().getGrid()[x][y] + " ");
			}
			System.out.println(" ");
			a++;
		}
	}
	
	public static int placeShip(Ship ship, Grid grid) {

		/*
		 * 0 : bateau placé 
		 * 1 : bateau non placé car déjà un bateau à cet emplacement 
		 * 2 : bateau non placé car coordonnées hors grille
		 * 3 : bateau non placé car coordonnées ne sont pas sur la même ligne ou colonne
		 * 4 : bateau non placé car coordonnées pas assez espacées pour la taille du bateau
		 * 5 : coordonnées erronées
		 */

		// Récupération des coordonnées du bateau
		
		int xStart;
		int yStart;
		int xEnd;
		int yEnd;

		// Vérification que les coordonnées sont valides
		try{
			xStart = convertCoord(ship.getStartCoord().substring(0, 1));
			yStart = Integer.parseInt(ship.getStartCoord().substring(1)) -1;
			xEnd = convertCoord(ship.getEndCoord().substring(0, 1));
			yEnd = Integer.parseInt(ship.getEndCoord().substring(1)) - 1;
		}catch(NumberFormatException e){
			return 5;
		}catch(StringIndexOutOfBoundsException e){
			return 5;
		}
		
		String xStartChar=ship.getStartCoord().substring(0, 1).toUpperCase();
		String xEndChar=ship.getEndCoord().substring(0, 1).toUpperCase();
		
		if(!xStartChar.equals("A") && !xStartChar.equals("B") && !xStartChar.equals("C") && !xStartChar.equals("D") && !xStartChar.equals("E") && !xStartChar.equals("F") && !xStartChar.equals("G") && !xStartChar.equals("H") && !xStartChar.equals("I") && !xStartChar.equals("J")){
			return 5;
		}
		if(!xEndChar.equals("A") && !xEndChar.equals("B") && !xEndChar.equals("C") && !xEndChar.equals("D") && !xEndChar.equals("E") && !xEndChar.equals("F") && !xEndChar.equals("G") && !xEndChar.equals("H") && !xEndChar.equals("I") && !xEndChar.equals("J")){
			return 5;
		}
		
		// Vérification que les coordonnées sont bien situées dans la grille
		if (xStart >= grid.getTaille() || yStart >= grid.getTaille() || xEnd >= grid.getTaille() || yEnd >= grid.getTaille()) {
			return 2; // Coordonnées hors grille
		}else if(xStart < 0 || yStart < 0 || xEnd < 0 || yEnd < 0) {
			return 2;
		}

		// Placement du bateau

		if (xStart == xEnd && yStart < yEnd) { // Cas 1 : bateau vertical avec la coordonnée de début inférieure à celle de fin
			for (int i = yStart; i <= yEnd; i++) { // Vérification du cas où deux bateaux se chevaucheraient
				if (grid.getGrid()[xStart][i] != 0) {
					return 1;
				}
			}
			if((yEnd - yStart) < (ship.getSize()-1) || (yEnd - yStart) > (ship.getSize()-1)) { // Vérification du cas où les coordonnées ne correspondent pas à la taille du bateau
				return 4; 
			}
			for (int i = yStart; i <= yEnd; i++) { // Placement du bateau
				grid.getGrid()[xStart][i] = 1;
			}
			ship.setOrientation(1);

		} else if (xStart == xEnd && yStart > yEnd) { // Cas 2 : bateau vertical avec la coordonnée de début supérieure à celle de fin
			for (int i = yEnd; i <= yStart; i++) { // Vérification du cas où deux bateaux se chevaucheraient
				if (grid.getGrid()[xStart][i] != 0) {
					return 1;
				}
			}
			if((yStart - yEnd) < (ship.getSize()-1) || (yStart - yEnd) > (ship.getSize()-1)) { // Vérification du cas où les coordonnées ne correspondent pas à la taille du bateau
				return 4; 
			}
			for (int i = yEnd; i <= yStart; i++) { // Placement du bateau
				grid.getGrid()[xStart][i] = 1;
			}
			ship.setOrientation(2);

		} else if (xStart > xEnd && yStart == yEnd) { // Cas 3 : bateau horizontal avec la coordonnée de début supérieure à celle de fin
			for (int i = xEnd; i <= xStart; i++) { // Vérification du cas où deux bateaux se chevaucheraient
				if (grid.getGrid()[i][yStart] != 0) {
					return 1;
				}
			}
			if((xStart - xEnd) < (ship.getSize()-1) || (xStart - xEnd) > (ship.getSize()-1)) { // Vérification du cas où les coordonnées ne correspondent pas à la taille du bateau
				return 4; 
			}
			for (int i = xEnd; i <= xStart; i++) { // Placement du bateau
				grid.getGrid()[i][yStart] = 1;
			}
			ship.setOrientation(3);

		} else if (xStart < xEnd && yStart == yEnd) { // Cas 4 : bateau horizontal avec la coordonnée de début inférieure à celle de fin
			for (int i = xStart; i <= xEnd; i++) { // Vérification du cas où deux bateaux se chevaucheraient
				if (grid.getGrid()[i][yStart] != 0) {
					return 1;
				}
			}
			if((xEnd - xStart) < (ship.getSize()-1) || (xEnd - xStart) > (ship.getSize()-1)) { // Vérification du cas où les coordonnées ne correspondent pas à la taille du bateau
				return 4; 
			}
			for (int i = xStart; i <= xEnd; i++) { // Placement du bateau
				grid.getGrid()[i][yStart] = 1;
			}
			ship.setOrientation(4);
			
		} else {
			return 3;
		}
		return 0;

	}

	public static int convertCoord(String coord){
		String coordMaj = coord.toUpperCase();
		int j = 0;
		char xCoord = coordMaj.charAt(0);
		for(char i = 'A'; i <= 'J'; i++){
			if(xCoord==i){
				return j;
			}
			j++;
		}
		return j;
	}
	
	
}