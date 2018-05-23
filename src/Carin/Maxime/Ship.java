package Carin.Maxime;

import java.util.Random;

public class Ship {
	String startCoord;
	String endCoord;
	int size;
	int nbTouche;
	int orientation; // 1 : Vertical, 2 : Vertical inversé, 3 : Horizontal inversé, 4 : Horizontal
	boolean sunk = false; // True : coulé. False : à flot.

	public boolean isSunk() {
		return sunk;
	}

	public void setSunk(boolean etat) {
		this.sunk = etat;
	}

	public String getStartCoord() {
		return startCoord;
	}

	public int getOrientation() {
		return orientation;
	}

	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}

	public void setStartCoord(String startCoord) {
		this.startCoord = startCoord;
	}

	public int getNbTouche() {
		return nbTouche;
	}

	public void setNbTouche(int nbTouche) {
		this.nbTouche = nbTouche;
	}

	public String getEndCoord() {
		return endCoord;
	}

	public void setEndCoord(String endCoord) {
		this.endCoord = endCoord;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Ship(String sCoord, String eCoord, int shipSize) {
		startCoord = sCoord;
		endCoord = eCoord;
		size = shipSize;
		nbTouche = 0;
		orientation = 0;
	}
	
	public Ship(int shipSize) {
		size = shipSize;
		nbTouche = 0;
		orientation = 0;
		Random rand = new Random();
		int randOr = rand.nextInt(3);
		
		char c = (char)(rand.nextInt(10) + 97);
		String result = Integer.toString(rand.nextInt(8)+1);
		startCoord = Character.toString(c) + result;
		
		if(randOr == 0) { // Vertical
			endCoord = startCoord.substring(0, 1) + Integer.toString(Integer.parseInt(startCoord.substring(1))-1+size);
		}else if(randOr == 1) { // Vertical inversé
			endCoord = startCoord.substring(0, 1) + Integer.toString(Integer.parseInt(startCoord.substring(1))+1-size);
		}else if(randOr == 2) { // Horizontal inversé
			char a = startCoord.charAt(0);
			a= (char)(a - size + 1);
			endCoord = Character.toString(a) + startCoord.substring(1);
		}else if(randOr == 3) { // Horizontal 
			char a = startCoord.charAt(0);
			a= (char)(a + size - 1);
			endCoord = Character.toString(a) + startCoord.substring(1);
		}
		
		
	}

	public static boolean isDestroyed(Ship ship) {
		if (ship.getNbTouche() == ship.getSize()) {
			return true;
		} else {
			return false;
		}
	}
}