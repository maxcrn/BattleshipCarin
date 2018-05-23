package Carin.Maxime;

import java.util.Scanner;

public class Battleship {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// ----- Partie simple -----
		
		boolean termine = false;
		String replay = "0";
		Player p1 = new Player();
		Player p2 = new Player();
		
		while(!termine){
			int tirEffectue = 0;
			int tir = -1;
			int procheP1 = 0;
			int procheP2 = 0;
			String nextShotp1 = "";
			String nextShotp2 = "";
			String coordFirstShotp1 = "";
			String coordFirstShotp2 = "";
			String shot;
			
			
			if(replay.equals("0")){
				// Nom du premier joueur
				System.out.println("Saisir le nom du premier joueur : (IA0 pour une IA de niveau facile, IA1 pour une IA de niveau moyen et IA2 pour une IA de niveau difficile)");
				String nom = sc.nextLine();
				p1 = new Player(nom);
				
				// Nom du second joueur
				System.out.println("Saisir le nom du second joueur : (IA0 pour une IA de niveau facile, IA1 pour une IA de niveau moyen et IA2 pour une IA de niveau difficile)");
				nom = sc.nextLine();
				p2 = new Player(nom);
			}
			else if (replay.equals("1")){
				String secondJoueur = p1.getNom();
				p1 = new Player(p2.getNom());
				p2 = new Player(secondJoueur);
			}
			
			
			// Définition des joueurs adverses
			p1.setJoueurOpp(p2);
			p2.setJoueurOpp(p1);
			
			while(p1.getScore()<5 && p2.getScore()<5){
				
				tirEffectue = 0;
				
				if (!p1.getNom().equals("IA0") && !p1.getNom().equals("IA1") && !p1.getNom().equals("IA2")){
					System.out.print("Tour de " );
					System.out.println(p1.getNom() );
					Grid.showGrille(p1);
					Grid.showGrilleAdv(p1);
					
					while(tirEffectue == 0){
						Scanner sc1 = new Scanner(System.in);
						System.out.println("Saisir les coordonnées du tir : ");
						shot = sc1.nextLine();
						
						tir = Player.shot(p1,shot);
						if(tir == 1){
							System.out.println(Player.isHit(p1.getJoueurOpp(),shot));
							tirEffectue = 1;
						}else if(tir == 0){
							System.out.println("Raté !");
							tirEffectue = 1;
						}
						else if(tir == 2){
							System.out.println("Vous avez déjà tiré ici.");
						}else if(tir == 3){
							System.out.println("Coordonnées erronées.");
						}
					}
				}
				
				else if(p1.getNom().equals("IA0")) { // L'IA de niveau 0 tire aléatoirement.
					String coordShot = Player.randomCoordShot();
					tir = Player.shot(p1, coordShot);
					if(tir == 1) {
						Player.isHit(p1.getJoueurOpp(),coordShot);
					}
				}
				
				else if(p1.getNom().equals("IA1")) { // L'IA de niveau 1 tire aléatoirement mais jamais sur la même case.
					while(tirEffectue == 0) {
						String coordShot = Player.randomCoordShot();
						tir = Player.shot(p1, coordShot);
						if(tir == 1) {
							Player.isHit(p1.getJoueurOpp(), coordShot);
							tirEffectue = 1;
						} else if(tir == 0) {
							tirEffectue = 1;
						}
					}
				}
				
				else if(p1.getNom().equals("IA2")) { // L'IA de niveau 2 tire aléatoirement jusqu'à trouver un bateau puis tire autour de la position touchée.
					
					while(tirEffectue == 0) { // Si aucune coordonnée n'est touchée, les tirs sont aléatoires
						if(procheP1 == 0) {
							coordFirstShotp1 = Player.randomCoordShot();
							tir = Player.shot(p1, coordFirstShotp1);
							if(tir == 1) {
								Player.isHit(p1.getJoueurOpp(), coordFirstShotp1);
								tirEffectue = 1;
								procheP1 = 1;
							} else if(tir == 0) {
								tirEffectue = 1;
							}
						}
						
						else if(procheP1 == 1) { // Tire en dessous de la coordonnée touchée si possible
							if(!coordFirstShotp1.substring(1).equals("10")) { 
								nextShotp1 = Player.nextShot(coordFirstShotp1, 1);
								tir = Player.shot(p1, nextShotp1);
								if(tir == 1) {
									Player.isHit(p1.getJoueurOpp(), nextShotp1);
									tirEffectue = 1;
									coordFirstShotp1 = nextShotp1;
									procheP1 = 1;
								} else if(tir == 0) {
									tirEffectue = 1;
									procheP1 = 2;
								} else if(tir == 2) {
									procheP1 = 2;
								}
							}
							else if(coordFirstShotp1.substring(1).equals("10") && !coordFirstShotp1.equals("j10")) { // Tire à droite si dessous impossible
								nextShotp1 = Player.nextShot(coordFirstShotp1, 2);
								tir = Player.shot(p1, nextShotp1);
								if(tir == 1) {
									Player.isHit(p1.getJoueurOpp(), nextShotp1);
									tirEffectue = 1;
									coordFirstShotp1 = nextShotp1;
									procheP1 = 1;
								} else if(tir == 0) {
									tirEffectue = 1;
									procheP1 = 3;
								}else if(tir == 2) {
									procheP1 = 3;
								}
							}
							else if(coordFirstShotp1.equals("j10")){ // Tire au dessus si dessous et droite impossibles
								nextShotp1 = Player.nextShot(coordFirstShotp1, 3);
								tir = Player.shot(p1, nextShotp1);
								if(tir == 1) {
									Player.isHit(p1.getJoueurOpp(), nextShotp1);
									tirEffectue = 1;
									coordFirstShotp1 = nextShotp1;
									procheP1 = 1;
								} else if(tir == 0) {
									tirEffectue = 1;
									procheP1 = 4;
								}else if(tir == 2) {
									procheP1 = 4;
								}
							}
						}
						
						else if(procheP1 == 2) { // Tire à droite de la coordonnée touchée si possible
							if(!coordFirstShotp1.substring(0,1).equals("j")) { 
								nextShotp1 = Player.nextShot(coordFirstShotp1, 2);
								tir = Player.shot(p1, nextShotp1);
								if(tir == 1) {
									Player.isHit(p1.getJoueurOpp(), nextShotp1);
									tirEffectue = 1;
									coordFirstShotp1 = nextShotp1;
									procheP1 = 1;
								} else if(tir == 0) {
									tirEffectue = 1;
									procheP1 = 3;
								}else if(tir == 2) {
									procheP1 = 3;
								}
							}
							else if(coordFirstShotp1.substring(0,1).equals("j") && !coordFirstShotp1.equals("j1")) { // Tire au dessus si droite impossible
								nextShotp1 = Player.nextShot(coordFirstShotp1, 3);
								tir = Player.shot(p1, nextShotp1);
								if(tir == 1) {
									Player.isHit(p1.getJoueurOpp(), nextShotp1);
									tirEffectue = 1;
									coordFirstShotp1 = nextShotp1;
									procheP1 = 1;
								} else if(tir == 0) {
									tirEffectue = 1;
									procheP1 = 4;
								}else if(tir == 2) {
									procheP1 = 4;
								}
							}
							else if(coordFirstShotp1.equals("j1")) { // Tire à gauche si droite et dessus impossible
								nextShotp1 = Player.nextShot(coordFirstShotp1, 4);
								tir = Player.shot(p1, nextShotp1);
								if(tir == 1) {
									Player.isHit(p1.getJoueurOpp(), nextShotp1);
									tirEffectue = 1;
									coordFirstShotp1 = nextShotp1;
									procheP1 = 1;
								} else if(tir == 0) {
									tirEffectue = 1;
									procheP1 = 5;
								}else if(tir == 2) {
									procheP1 = 5;
								}
							}
						}
						else if(procheP1 == 3) { // Tire au dessus si possible
							
							if(!coordFirstShotp1.substring(1).equals("1")) { 
								nextShotp1 = Player.nextShot(coordFirstShotp1, 3);
								tir = Player.shot(p1, nextShotp1);
								if(tir == 1) {
									Player.isHit(p1.getJoueurOpp(), nextShotp1);
									tirEffectue = 1;
									coordFirstShotp1 = nextShotp1;
									procheP1 = 1;
								} else if(tir == 0) {
									tirEffectue = 1;
									procheP1 = 4;
								}else if(tir == 2) {
									procheP1 = 4;
								}
							}
							else if(coordFirstShotp1.substring(1).equals("1") && !coordFirstShotp1.equals("a1")) { // Tire à gauche si dessus impossible
								nextShotp1 = Player.nextShot(coordFirstShotp1, 4);
								tir = Player.shot(p1, nextShotp1);
								if(tir == 1) {
									Player.isHit(p1.getJoueurOpp(), nextShotp1);
									tirEffectue = 1;
									coordFirstShotp1 = nextShotp1;
									procheP1 = 1;
								} else if(tir == 0) {
									tirEffectue = 1;
									procheP1 = 5;
								}else if(tir == 2) {
									procheP1 = 5;
								}
							}
							else if(coordFirstShotp1.equals("a1")) { // Tire sur une nouvelle coordonnée si gauche et dessus impossible
								nextShotp1 = Player.randomCoordShot();
								tir = Player.shot(p1, nextShotp1);
								if(tir == 1) {
									Player.isHit(p1.getJoueurOpp(), nextShotp1);
									tirEffectue = 1;
									coordFirstShotp1 = nextShotp1;
									procheP1 = 1;
								} else if(tir == 0) {
									tirEffectue = 1;
									procheP1 = 0;
								}else if(tir == 2) {
									procheP1 = 0;
								}
							}
						}
						else if(procheP1 == 4) { // Tire à gauche si possible
							if(!coordFirstShotp1.substring(0,1).equals("a")) { 
								nextShotp1 = Player.nextShot(coordFirstShotp1, 4);
								tir = Player.shot(p1, nextShotp1);
								if(tir == 1) {
									Player.isHit(p1.getJoueurOpp(), nextShotp1);
									tirEffectue = 1;
									coordFirstShotp1 = nextShotp1;
									procheP1 = 1;
								} else if(tir == 0) {
									tirEffectue = 1;
									procheP1 = 5;
								}else if(tir == 2) {
									procheP1 = 5;
								}
							}
							else if(coordFirstShotp1.substring(0,1).equals("a")) { // Tire sur une nouvelle position aléatoire si gauche impossible
								nextShotp1 = Player.randomCoordShot();
								tir = Player.shot(p1, nextShotp1);
								if(tir == 1) {
									Player.isHit(p1.getJoueurOpp(), nextShotp1);
									tirEffectue = 1;
									coordFirstShotp1 = nextShotp1;
									procheP1 = 1;
								} else if(tir == 0) {
									tirEffectue = 1;
									procheP1 = 0;
								}else if(tir == 2) {
									procheP1 = 0;
								}
							}
						}
						else if(procheP1 == 5) { // Tire sur une nouvelle position
							nextShotp1 = Player.randomCoordShot();
							tir = Player.shot(p1, nextShotp1);
							if(tir == 1) {
								Player.isHit(p1.getJoueurOpp(), nextShotp1);
								tirEffectue = 1;
								coordFirstShotp1 = nextShotp1;
								procheP1 = 1;
							} else if(tir == 0) {
								tirEffectue = 1;
								procheP1 = 0;
							}else if(tir == 2) {
								procheP1 = 0;
							}
						}
					}
					
				}
				
				
				
				tirEffectue = 0;
				
				if (!p2.getNom().equals("IA0") && !p2.getNom().equals("IA1") && !p2.getNom().equals("IA2")){
					System.out.print("Tour de " );
					System.out.println(p2.getNom() );
					Grid.showGrille(p2);
					Grid.showGrilleAdv(p2);
					
					while(tirEffectue == 0){
						Scanner sc1 = new Scanner(System.in);
						System.out.println("Saisir les coordonnées du tir : ");
						shot = sc1.nextLine();
						
						tirEffectue = 0;
						tir = Player.shot(p2, shot);
						if(tir == 1){
							System.out.println(Player.isHit(p2.getJoueurOpp(), shot));
							tirEffectue = 1;
						}else if(tir == 0){
							System.out.println("Raté !");
							tirEffectue = 1;
						}else if(tir == 2){
							System.out.println("Vous avez déjà tiré ici.");
						}else if(tir == 3){
							System.out.println("Coordonnées erronées.");
						}
					}
				}else if(p2.getNom().equals("IA0")) { // L'IA de niveau 0 tire aléatoirement.
					String coordShot = Player.randomCoordShot();
					int tir2 = Player.shot(p2, coordShot);
					if(tir2 == 1){
						Player.isHit(p2.getJoueurOpp(), coordShot);
					}
				}
				
				
				else if(p2.getNom().equals("IA1")) { // L'IA de niveau 1 tire aléatoirement mais jamais sur la même case.
					while(tirEffectue == 0) {
						String coordShot = Player.randomCoordShot();
						tir = Player.shot(p2, coordShot);
						if(tir == 1) {
							Player.isHit(p2.getJoueurOpp(), coordShot);
							tirEffectue = 1;
						} else if(tir == 0) {
							tirEffectue = 1;
						}
					}
				}
				
				else if(p2.getNom().equals("IA2")) { // L'IA de niveau 2 tire aléatoirement jusqu'à trouver un bateau puis tire autour de la position touchée.
					
					while(tirEffectue == 0) { // Si aucune coordonnée n'est touchée, les tirs sont aléatoires
						if(procheP2 == 0) {
							coordFirstShotp2 = Player.randomCoordShot();
							tir = Player.shot(p2, coordFirstShotp2);
							if(tir == 1) {
								Player.isHit(p2.getJoueurOpp(), coordFirstShotp2);
								tirEffectue = 1;
								procheP2 = 1;
							} else if(tir == 0) {
								tirEffectue = 1;
							}
						}
						
						else if(procheP2 == 1) { // Tire en dessous de la coordonnée touchée si possible
							if(!coordFirstShotp2.substring(1).equals("10")) { 
								nextShotp2 = Player.nextShot(coordFirstShotp2, 1);
								tir = Player.shot(p2, nextShotp2);
								if(tir == 1) {
									Player.isHit(p2.getJoueurOpp(), nextShotp2);
									tirEffectue = 1;
									coordFirstShotp2 = nextShotp2;
									procheP2 = 1;
								} else if(tir == 0) {
									tirEffectue = 1;
									procheP2 = 2;
								} else if(tir == 2) {
									procheP2 = 2;
								}
							}
							else if(coordFirstShotp2.substring(1).equals("10") && !coordFirstShotp2.equals("j10")) { // Tire à droite si dessous impossible
								nextShotp2 = Player.nextShot(coordFirstShotp2, 2);
								tir = Player.shot(p2, nextShotp2);
								if(tir == 1) {
									Player.isHit(p2.getJoueurOpp(), nextShotp2);
									tirEffectue = 1;
									coordFirstShotp2 = nextShotp2;
									procheP2 = 1;
								} else if(tir == 0) {
									tirEffectue = 1;
									procheP2 = 3;
								}else if(tir == 2) {
									procheP2 = 3;
								}
							}
							else if(coordFirstShotp2.equals("j10")){ // Tire au dessus si dessous et droite impossibles
								nextShotp2 = Player.nextShot(coordFirstShotp2, 3);
								tir = Player.shot(p2, nextShotp2);
								if(tir == 1) {
									Player.isHit(p2.getJoueurOpp(), nextShotp2);
									tirEffectue = 1;
									coordFirstShotp2 = nextShotp2;
									procheP2 = 1;
								} else if(tir == 0) {
									tirEffectue = 1;
									procheP2 = 4;
								}else if(tir == 2) {
									procheP2 = 4;
								}
							}
						}
						
						else if(procheP2 == 2) { // Tire à droite de la coordonnée touchée si possible
							if(!coordFirstShotp2.substring(0,1).equals("j")) { 
								nextShotp2 = Player.nextShot(coordFirstShotp2, 2);
								tir = Player.shot(p2, nextShotp2);
								if(tir == 1) {
									Player.isHit(p2.getJoueurOpp(), nextShotp2);
									tirEffectue = 1;
									coordFirstShotp2 = nextShotp2;
									procheP2 = 1;
								} else if(tir == 0) {
									tirEffectue = 1;
									procheP2 = 3;
								}else if(tir == 2) {
									procheP2 = 3;
								}
							}
							else if(coordFirstShotp2.substring(0,1).equals("j") && !coordFirstShotp2.equals("j1")) { // Tire au dessus si droite impossible
								nextShotp2 = Player.nextShot(coordFirstShotp2, 3);
								tir = Player.shot(p2, nextShotp2);
								if(tir == 1) {
									Player.isHit(p2.getJoueurOpp(), nextShotp2);
									tirEffectue = 1;
									coordFirstShotp2 = nextShotp2;
									procheP2 = 1;
								} else if(tir == 0) {
									tirEffectue = 1;
									procheP2 = 4;
								}else if(tir == 2) {
									procheP2 = 4;
								}
							}
							else if(coordFirstShotp2.equals("j1")) { // Tire à gauche si droite et dessus impossible
								nextShotp2 = Player.nextShot(coordFirstShotp2, 4);
								tir = Player.shot(p2, nextShotp2);
								if(tir == 1) {
									Player.isHit(p2.getJoueurOpp(), nextShotp2);
									tirEffectue = 1;
									coordFirstShotp2 = nextShotp2;
									procheP2 = 1;
								} else if(tir == 0) {
									tirEffectue = 1;
									procheP2 = 5;
								}else if(tir == 2) {
									procheP2 = 5;
								}
							}
						}
						else if(procheP2 == 3) { // Tire au dessus si possible
							
							if(!coordFirstShotp2.substring(1).equals("1")) { 
								nextShotp2 = Player.nextShot(coordFirstShotp2, 3);
								tir = Player.shot(p2, nextShotp2);
								if(tir == 1) {
									Player.isHit(p2.getJoueurOpp(), nextShotp2);
									tirEffectue = 1;
									coordFirstShotp2 = nextShotp2;
									procheP2 = 1;
								} else if(tir == 0) {
									tirEffectue = 1;
									procheP2 = 4;
								}else if(tir == 2) {
									procheP2 = 4;
								}
							}
							else if(coordFirstShotp2.substring(1).equals("1") && !coordFirstShotp2.equals("a1")) { // Tire à gauche si dessus impossible
								nextShotp2 = Player.nextShot(coordFirstShotp2, 4);
								tir = Player.shot(p2, nextShotp2);
								if(tir == 1) {
									Player.isHit(p2.getJoueurOpp(), nextShotp2);
									tirEffectue = 1;
									coordFirstShotp2 = nextShotp2;
									procheP2 = 1;
								} else if(tir == 0) {
									tirEffectue = 1;
									procheP2 = 5;
								}else if(tir == 2) {
									procheP2 = 5;
								}
							}
							else if(coordFirstShotp2.equals("a1")) { // Tire sur une nouvelle coordonnée si gauche et dessus impossible
								nextShotp2 = Player.randomCoordShot();
								tir = Player.shot(p2, nextShotp2);
								if(tir == 1) {
									Player.isHit(p2.getJoueurOpp(), nextShotp2);
									tirEffectue = 1;
									coordFirstShotp2 = nextShotp2;
									procheP2 = 1;
								} else if(tir == 0) {
									tirEffectue = 1;
									procheP2 = 0;
								}else if(tir == 2) {
									procheP2 = 0;
								}
							}
						}
						else if(procheP2 == 4) { // Tire à gauche si possible
							if(!coordFirstShotp2.substring(0,1).equals("a")) { 
								nextShotp2 = Player.nextShot(coordFirstShotp2, 4);
								tir = Player.shot(p2, nextShotp2);
								if(tir == 1) {
									Player.isHit(p2.getJoueurOpp(), nextShotp2);
									tirEffectue = 1;
									coordFirstShotp2 = nextShotp2;
									procheP2 = 1;
								} else if(tir == 0) {
									tirEffectue = 1;
									procheP2 = 5;
								}else if(tir == 2) {
									procheP2 = 5;
								}
							}
							else if(coordFirstShotp2.substring(0,1).equals("a")) { // Tire sur une nouvelle position aléatoire si gauche impossible
								nextShotp2 = Player.randomCoordShot();
								tir = Player.shot(p2, nextShotp2);
								if(tir == 1) {
									Player.isHit(p2.getJoueurOpp(), nextShotp2);
									tirEffectue = 1;
									coordFirstShotp2 = nextShotp2;
									procheP2 = 1;
								} else if(tir == 0) {
									tirEffectue = 1;
									procheP2 = 0;
								}else if(tir == 2) {
									procheP2 = 0;
								}
							}
						}
						else if(procheP2 == 5) { // Tire sur une nouvelle position
							nextShotp2 = Player.randomCoordShot();
							tir = Player.shot(p2, nextShotp2);
							if(tir == 1) {
								Player.isHit(p2.getJoueurOpp(), nextShotp2);
								tirEffectue = 1;
								coordFirstShotp2 = nextShotp2;
								procheP2 = 1;
							} else if(tir == 0) {
								tirEffectue = 1;
								procheP2 = 0;
							}else if(tir == 2) {
								procheP2 = 0;
							}
						}
					}
					
				}
				
				if(p1.getScore()==5){
					System.out.print("Victoire de " );
					System.out.println(p1.getNom() );
				}
				else if(p2.getScore()==5){
					System.out.print("Victoire de ");
					System.out.println(p2.getNom() );
				}
			}
			System.out.println("Souhaitez-vous rejouer ? (1 : Oui, 0 : Non)");
			replay = sc.nextLine();
			
			// Application de la fin de la partie
			if(replay.equals("0")){
				termine = true;
			}
		}
	}
}

