package fr.battleship;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import Carin.Maxime.Player;

public class TestIA {

	public static void main(String[] args) {
		
		int pgIA01 = 0;
		int pgIA10 = 0;
		int pgIA02 = 0;
		int pgIA20 = 0;
		int pgIA12 = 0;
		int pgIA21 = 0;
		
		for(int pj = 0; pj <= 299; pj++){
			
			int tirEffectueP1 = 0;
			int tirEffectueP2 = 0;
			int tir = -1;
			int procheP1 = 0;
			int procheP2 = 0;
			String nextShotp1 = "";
			String nextShotp2 = "";
			String coordFirstShotp1 = "";
			String coordFirstShotp2 = "";
			String shot;

			
			Player p1 = new Player();
			Player p2 = new Player();
			
			if(pj < 100){ // Simulation des parties IA facile VS IA moyen
				p1 = new Player("IA0");
				p2 = new Player("IA1");
			}
			else if(pj < 200){ // Simulation des parties IA facile VS IA fort
				p1 = new Player("IA0");
				p2 = new Player("IA2");
			}
			else if(pj < 300){ // Simulation des parties IA moyen VS IA fort
				p1 = new Player("IA1");
				p2 = new Player("IA2");
			}
			
			
			
			
			// Définition des joueurs adverses
			p1.setJoueurOpp(p2);
			p2.setJoueurOpp(p1);
			
			while(p1.getScore()<5 && p2.getScore()<5){
				
				tirEffectueP1 = 0;
				tirEffectueP2 = 0;
				
				if(p1.getNom().equals("IA0")) { // L'IA de niveau 0 tire aléatoirement.
					String coordShot = Player.randomCoordShot();
					tir = Player.shot(p1, coordShot);
					if(tir == 1) {
						Player.isHit(p1.getJoueurOpp(),coordShot);
					}
				}
				
				else if(p1.getNom().equals("IA1")) { // L'IA de niveau 1 tire aléatoirement mais jamais sur la même case.
					while(tirEffectueP1 == 0) {
						String coordShot = Player.randomCoordShot();
						tir = Player.shot(p1, coordShot);
						if(tir == 1) {
							Player.isHit(p1.getJoueurOpp(), coordShot);
							tirEffectueP1 = 1;
						} else if(tir == 0) {
							tirEffectueP1 = 1;
						}
					}
				}
				
				else if(p1.getNom().equals("IA2")) { 
					
					while(tirEffectueP1 == 0) {
						if(procheP1 == 0) {
							coordFirstShotp1 = Player.randomCoordShot();
							tir = Player.shot(p1, coordFirstShotp1);
							if(tir == 1) {
								Player.isHit(p1.getJoueurOpp(), coordFirstShotp1);
								tirEffectueP1 = 1;
								procheP1 = 1;
							} else if(tir == 0) {
								tirEffectueP1 = 1;
							}
						}
						
						else if(procheP1 == 1) { // Tir en dessous de la coordonnée touchée si possible
							if(!coordFirstShotp1.substring(1).equals("10")) { 
								nextShotp1 = Player.nextShot(coordFirstShotp1, 1);
								tir = Player.shot(p1, nextShotp1);
								if(tir == 1) {
									Player.isHit(p1.getJoueurOpp(), nextShotp1);
									tirEffectueP1 = 1;
									coordFirstShotp1 = nextShotp1;
									procheP1 = 1;
								} else if(tir == 0) {
									tirEffectueP1 = 1;
									procheP1 = 2;
								} else if(tir == 2) {
									procheP1 = 2;
								}
							}
							else if(coordFirstShotp1.substring(1).equals("10") && !coordFirstShotp1.equals("j10")) { // Tir à droite si dessous impossible
								nextShotp1 = Player.nextShot(coordFirstShotp1, 2);
								tir = Player.shot(p1, nextShotp1);
								if(tir == 1) {
									Player.isHit(p1.getJoueurOpp(), nextShotp1);
									tirEffectueP1 = 1;
									coordFirstShotp1 = nextShotp1;
									procheP1 = 1;
								} else if(tir == 0) {
									tirEffectueP1 = 1;
									procheP1 = 3;
								}else if(tir == 2) {
									procheP1 = 3;
								}
							}
							else if(coordFirstShotp1.equals("j10")){ // Tir au dessus si dessous et droite impossibles
								nextShotp1 = Player.nextShot(coordFirstShotp1, 3);
								tir = Player.shot(p1, nextShotp1);
								if(tir == 1) {
									Player.isHit(p1.getJoueurOpp(), nextShotp1);
									tirEffectueP1 = 1;
									coordFirstShotp1 = nextShotp1;
									procheP1 = 1;
								} else if(tir == 0) {
									tirEffectueP1 = 1;
									procheP1 = 4;
								}else if(tir == 2) {
									procheP1 = 4;
								}
							}
						}
						
						else if(procheP1 == 2) { // Tir à droite de la coordonnée touchée si possible
							if(!coordFirstShotp1.substring(0,1).equals("j")) { 
								nextShotp1 = Player.nextShot(coordFirstShotp1, 2);
								tir = Player.shot(p1, nextShotp1);
								if(tir == 1) {
									Player.isHit(p1.getJoueurOpp(), nextShotp1);
									tirEffectueP1 = 1;
									coordFirstShotp1 = nextShotp1;
									procheP1 = 1;
								} else if(tir == 0) {
									tirEffectueP1 = 1;
									procheP1 = 3;
								}else if(tir == 2) {
									procheP1 = 3;
								}
							}
							else if(coordFirstShotp1.substring(0,1).equals("j") && !coordFirstShotp1.equals("j1")) { // Tir au dessus si droite impossible
								nextShotp1 = Player.nextShot(coordFirstShotp1, 3);
								tir = Player.shot(p1, nextShotp1);
								if(tir == 1) {
									Player.isHit(p1.getJoueurOpp(), nextShotp1);
									tirEffectueP1 = 1;
									coordFirstShotp1 = nextShotp1;
									procheP1 = 1;
								} else if(tir == 0) {
									tirEffectueP1 = 1;
									procheP1 = 4;
								}else if(tir == 2) {
									procheP1 = 4;
								}
							}
							else if(coordFirstShotp1.equals("j1")) { // Tir à gauche si droite et dessus impossible
								nextShotp1 = Player.nextShot(coordFirstShotp1, 4);
								tir = Player.shot(p1, nextShotp1);
								if(tir == 1) {
									Player.isHit(p1.getJoueurOpp(), nextShotp1);
									tirEffectueP1 = 1;
									coordFirstShotp1 = nextShotp1;
									procheP1 = 1;
								} else if(tir == 0) {
									tirEffectueP1 = 1;
									procheP1 = 5;
								}else if(tir == 2) {
									procheP1 = 5;
								}
							}
						}
						else if(procheP1 == 3) { // Tir au dessus si possible
							
							if(!coordFirstShotp1.substring(1).equals("1")) { 
								nextShotp1 = Player.nextShot(coordFirstShotp1, 3);
								tir = Player.shot(p1, nextShotp1);
								if(tir == 1) {
									Player.isHit(p1.getJoueurOpp(), nextShotp1);
									tirEffectueP1 = 1;
									coordFirstShotp1 = nextShotp1;
									procheP1 = 1;
								} else if(tir == 0) {
									tirEffectueP1 = 1;
									procheP1 = 4;
								}else if(tir == 2) {
									procheP1 = 4;
								}
							}
							else if(coordFirstShotp1.substring(1).equals("1") && !coordFirstShotp1.equals("a1")) { // Tir à gauche si dessus impossible
								nextShotp1 = Player.nextShot(coordFirstShotp1, 4);
								tir = Player.shot(p1, nextShotp1);
								if(tir == 1) {
									Player.isHit(p1.getJoueurOpp(), nextShotp1);
									tirEffectueP1 = 1;
									coordFirstShotp1 = nextShotp1;
									procheP1 = 1;
								} else if(tir == 0) {
									tirEffectueP1 = 1;
									procheP1 = 5;
								}else if(tir == 2) {
									procheP1 = 5;
								}
							}
							else if(coordFirstShotp1.equals("a1")) {
								nextShotp1 = Player.randomCoordShot();
								tir = Player.shot(p1, nextShotp1);
								if(tir == 1) {
									Player.isHit(p1.getJoueurOpp(), nextShotp1);
									tirEffectueP1 = 1;
									coordFirstShotp1 = nextShotp1;
									procheP1 = 1;
								} else if(tir == 0) {
									tirEffectueP1 = 1;
									procheP1 = 0;
								}else if(tir == 2) {
									procheP1 = 0;
								}
							}
						}
						else if(procheP1 == 4) {
							if(!coordFirstShotp1.substring(0,1).equals("a")) { 
								nextShotp1 = Player.nextShot(coordFirstShotp1, 4);
								tir = Player.shot(p1, nextShotp1);
								if(tir == 1) {
									Player.isHit(p1.getJoueurOpp(), nextShotp1);
									tirEffectueP1 = 1;
									coordFirstShotp1 = nextShotp1;
									procheP1 = 1;
								} else if(tir == 0) {
									tirEffectueP1 = 1;
									procheP1 = 5;
								}else if(tir == 2) {
									procheP1 = 5;
								}
							}
							else if(coordFirstShotp1.substring(0,1).equals("a")) { 
								nextShotp1 = Player.randomCoordShot();
								tir = Player.shot(p1, nextShotp1);
								if(tir == 1) {
									Player.isHit(p1.getJoueurOpp(), nextShotp1);
									tirEffectueP1 = 1;
									coordFirstShotp1 = nextShotp1;
									procheP1 = 1;
								} else if(tir == 0) {
									tirEffectueP1 = 1;
									procheP1 = 0;
								}else if(tir == 2) {
									procheP1 = 0;
								}
							}
						}
						else if(procheP1 == 5) {
							nextShotp1 = Player.randomCoordShot();
							tir = Player.shot(p1, nextShotp1);
							if(tir == 1) {
								Player.isHit(p1.getJoueurOpp(), nextShotp1);
								tirEffectueP1 = 1;
								coordFirstShotp1 = nextShotp1;
								procheP1 = 1;
							} else if(tir == 0) {
								tirEffectueP1 = 1;
								procheP1 = 0;
							}else if(tir == 2) {
								procheP1 = 0;
							}
						}
					}
					
				}
				
				
				
				if(p2.getNom().equals("IA0")) { // L'IA de niveau 0 tire aléatoirement.
					String coordShot = Player.randomCoordShot();
					int tir2 = Player.shot(p2, coordShot);
					if(tir2 == 1){
						Player.isHit(p2.getJoueurOpp(), coordShot);
					}
				}
				
				
				else if(p2.getNom().equals("IA1")) { // L'IA de niveau 1 tire aléatoirement mais jamais sur la même case.
					while(tirEffectueP2 == 0) {
						String coordShot = Player.randomCoordShot();
						tir = Player.shot(p2, coordShot);
						if(tir == 1) {
							Player.isHit(p2.getJoueurOpp(), coordShot);
							tirEffectueP2 = 1;
						} else if(tir == 0) {
							tirEffectueP2 = 1;
						}
					}
				}
				
				else if(p2.getNom().equals("IA2")) { 
					
					while(tirEffectueP2 == 0) {
						if(procheP2 == 0) {
							coordFirstShotp2 = Player.randomCoordShot();
							tir = Player.shot(p2, coordFirstShotp2);
							if(tir == 1) {
								Player.isHit(p2.getJoueurOpp(), coordFirstShotp2);
								tirEffectueP2 = 1;
								procheP2 = 1;
							} else if(tir == 0) {
								tirEffectueP2 = 1;
							}
						}
						
						else if(procheP2 == 1) { // Tir en dessous de la coordonnée touchée si possible
							if(!coordFirstShotp2.substring(1).equals("10")) { 
								nextShotp2 = Player.nextShot(coordFirstShotp2, 1);
								tir = Player.shot(p2, nextShotp2);
								if(tir == 1) {
									Player.isHit(p2.getJoueurOpp(), nextShotp2);
									tirEffectueP2 = 1;
									coordFirstShotp2 = nextShotp2;
									procheP2 = 1;
								} else if(tir == 0) {
									tirEffectueP2 = 1;
									procheP2 = 2;
								} else if(tir == 2) {
									procheP2 = 2;
								}
							}
							else if(coordFirstShotp2.substring(1).equals("10") && !coordFirstShotp2.equals("j10")) { // Tir à droite si dessous impossible
								nextShotp2 = Player.nextShot(coordFirstShotp2, 2);
								tir = Player.shot(p2, nextShotp2);
								if(tir == 1) {
									Player.isHit(p2.getJoueurOpp(), nextShotp2);
									tirEffectueP2 = 1;
									coordFirstShotp2 = nextShotp2;
									procheP2 = 1;
								} else if(tir == 0) {
									tirEffectueP2 = 1;
									procheP2 = 3;
								}else if(tir == 2) {
									procheP2 = 3;
								}
							}
							else if(coordFirstShotp2.equals("j10")){ // Tir au dessus si dessous et droite impossibles
								nextShotp2 = Player.nextShot(coordFirstShotp2, 3);
								tir = Player.shot(p2, nextShotp2);
								if(tir == 1) {
									Player.isHit(p2.getJoueurOpp(), nextShotp2);
									tirEffectueP2 = 1;
									coordFirstShotp2 = nextShotp2;
									procheP2 = 1;
								} else if(tir == 0) {
									tirEffectueP2 = 1;
									procheP2 = 4;
								}else if(tir == 2) {
									procheP2 = 4;
								}
							}
						}
						
						else if(procheP2 == 2) { // Tir à droite de la coordonnée touchée si possible
							if(!coordFirstShotp2.substring(0,1).equals("j")) { 
								nextShotp2 = Player.nextShot(coordFirstShotp2, 2);
								tir = Player.shot(p2, nextShotp2);
								if(tir == 1) {
									Player.isHit(p2.getJoueurOpp(), nextShotp2);
									tirEffectueP2 = 1;
									coordFirstShotp2 = nextShotp2;
									procheP2 = 1;
								} else if(tir == 0) {
									tirEffectueP2 = 1;
									procheP2 = 3;
								}else if(tir == 2) {
									procheP2 = 3;
								}
							}
							else if(coordFirstShotp2.substring(0,1).equals("j") && !coordFirstShotp2.equals("j1")) { // Tir au dessus si droite impossible
								nextShotp2 = Player.nextShot(coordFirstShotp2, 3);
								tir = Player.shot(p2, nextShotp2);
								if(tir == 1) {
									Player.isHit(p2.getJoueurOpp(), nextShotp2);
									tirEffectueP2 = 1;
									coordFirstShotp2 = nextShotp2;
									procheP2 = 1;
								} else if(tir == 0) {
									tirEffectueP2 = 1;
									procheP2 = 4;
								}else if(tir == 2) {
									procheP2 = 4;
								}
							}
							else if(coordFirstShotp2.equals("j1")) { // Tir à gauche si droite et dessus impossible
								nextShotp2 = Player.nextShot(coordFirstShotp2, 4);
								tir = Player.shot(p2, nextShotp2);
								if(tir == 1) {
									Player.isHit(p2.getJoueurOpp(), nextShotp2);
									tirEffectueP2 = 1;
									coordFirstShotp2 = nextShotp2;
									procheP2 = 1;
								} else if(tir == 0) {
									tirEffectueP2 = 1;
									procheP2 = 5;
								}else if(tir == 2) {
									procheP2 = 5;
								}
							}
						}
						else if(procheP2 == 3) { // Tir au dessus si possible
							
							if(!coordFirstShotp2.substring(1).equals("1")) { 
								nextShotp2 = Player.nextShot(coordFirstShotp2, 3);
								tir = Player.shot(p2, nextShotp2);
								if(tir == 1) {
									Player.isHit(p2.getJoueurOpp(), nextShotp2);
									tirEffectueP2 = 1;
									coordFirstShotp2 = nextShotp2;
									procheP2 = 1;
								} else if(tir == 0) {
									tirEffectueP2 = 1;
									procheP2 = 4;
								}else if(tir == 2) {
									procheP2 = 4;
								}
							}
							else if(coordFirstShotp2.substring(1).equals("1") && !coordFirstShotp2.equals("a1")) { // Tir à gauche si dessus impossible
								nextShotp2 = Player.nextShot(coordFirstShotp2, 4);
								tir = Player.shot(p2, nextShotp2);
								if(tir == 1) {
									Player.isHit(p2.getJoueurOpp(), nextShotp2);
									tirEffectueP2 = 1;
									coordFirstShotp2 = nextShotp2;
									procheP2 = 1;
								} else if(tir == 0) {
									tirEffectueP2 = 1;
									procheP2 = 5;
								}else if(tir == 2) {
									procheP2 = 5;
								}
							}
							else if(coordFirstShotp2.equals("a1")) {
								nextShotp2 = Player.randomCoordShot();
								tir = Player.shot(p2, nextShotp2);
								if(tir == 1) {
									Player.isHit(p2.getJoueurOpp(), nextShotp2);
									tirEffectueP2 = 1;
									coordFirstShotp2 = nextShotp2;
									procheP2 = 1;
								} else if(tir == 0) {
									tirEffectueP2 = 1;
									procheP2 = 0;
								}else if(tir == 2) {
									procheP2 = 0;
								}
							}
						}
						else if(procheP2 == 4) {
							if(!coordFirstShotp2.substring(0,1).equals("a")) { 
								nextShotp2 = Player.nextShot(coordFirstShotp2, 4);
								tir = Player.shot(p2, nextShotp2);
								if(tir == 1) {
									Player.isHit(p2.getJoueurOpp(), nextShotp2);
									tirEffectueP2 = 1;
									coordFirstShotp2 = nextShotp2;
									procheP2 = 1;
								} else if(tir == 0) {
									tirEffectueP2 = 1;
									procheP2 = 5;
								}else if(tir == 2) {
									procheP2 = 5;
								}
							}
							else if(coordFirstShotp2.substring(0,1).equals("a")) { 
								nextShotp2 = Player.randomCoordShot();
								tir = Player.shot(p2, nextShotp2);
								if(tir == 1) {
									Player.isHit(p2.getJoueurOpp(), nextShotp2);
									tirEffectueP2 = 1;
									coordFirstShotp2 = nextShotp2;
									procheP2 = 1;
								} else if(tir == 0) {
									tirEffectueP2 = 1;
									procheP2 = 0;
								}else if(tir == 2) {
									procheP2 = 0;
								}
							}
						}
						else if(procheP2 == 5) {
							nextShotp2 = Player.randomCoordShot();
							tir = Player.shot(p2, nextShotp2);
							if(tir == 1) {
								Player.isHit(p2.getJoueurOpp(), nextShotp2);
								tirEffectueP2 = 1;
								coordFirstShotp2 = nextShotp2;
								procheP2 = 1;
							} else if(tir == 0) {
								tirEffectueP2 = 1;
								procheP2 = 0;
							}else if(tir == 2) {
								procheP2 = 0;
							}
						}
					}
					
				}
				
			}
			
			if(pj < 100){ // Simulation des parties IA facile VS IA moyen
				if(p1.getScore()==5){				
					pgIA01 = pgIA01 + 1;
				}else if(p2.getScore()==5){
					pgIA10 = pgIA10 + 1;
				}
			}
			else if(pj < 200){ // Simulation des parties IA facile VS IA fort
				if(p1.getScore()==5){				
					pgIA02 = pgIA02 + 1;
				}else if(p2.getScore()==5){
					pgIA20 = pgIA20 + 1;
				}
			}
			else{ // Simulation des parties IA moyen VS IA fort
				if(p1.getScore()==5){				
					pgIA12 = pgIA12 + 1;
				}else if(p2.getScore()==5){
					pgIA21 = pgIA21 + 1;
				}
			}

		}
		System.out.println("--- IA faible vs IA moyenne ---");
		System.out.println("Parties gagnées par l'IA faible : " + pgIA01);
		System.out.println("Parties gagnées par l'IA moyenne : " + pgIA10);
		
		System.out.println("--- IA faible vs IA forte ---");
		System.out.println("Parties gagnées par l'IA faible : " + pgIA02);
		System.out.println("Parties gagnées par l'IA forte : " + pgIA20);
		
		System.out.println("--- IA moyenne vs IA forte ---");
		System.out.println("Parties gagnées par l'IA moyenne : " + pgIA12);
		System.out.println("Parties gagnées par l'IA forte : " + pgIA21);
		System.out.println("Ces résultats sont disponibles sous forme de fichier CSV à la racine des packages.");
		
		File csvFile = new File("ai_proof.csv");
		try {
			FileWriter fwResultats = new FileWriter(csvFile, true);
			fwResultats.append("AI Name; score; AI Name2; score2\nAI Level Beginner;"+pgIA01+";Level Medium;"+pgIA10+"\nAI Level Beginner;"+pgIA02+";Level Hard;"+pgIA20+"\nAI Level Medium;"+pgIA12+";Level Hard;"+pgIA21+"\n");
			fwResultats.flush();
			fwResultats.close();
		} catch (IOException e) {
			System.out.println("Bonjour");
			e.printStackTrace();
		}
	}

}


