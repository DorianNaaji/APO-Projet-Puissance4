package com.apo.puissance4.console;

import java.util.Scanner;
import com.apo.puissance4.ManagerJeu;
import com.apo.puissance4.exception.FullColumnException;
import com.apo.puissance4.exception.KeyDoNotExistException;

public class AffichageConsole {

	private static Scanner sc = new Scanner(System.in);
	
	private static ManagerJeu jeu;
	
	public static void main(String[] args) {
		menuPrincipal();
	}
	
	private static void menuPrincipal() {
		boolean quitter = false;
		
		do {
			System.out.println("Lancer une partie local ou distante ?");
			quitter = false;
			System.out.println("1    : local");
			System.out.println("2    : distante");
			System.out.println("exit : quitter");
			String saisie = sc.nextLine();
			
			switch (saisie) {
			case "1":
				partieLocal();
				break;
			case "2":
				partieDistante();
				break;
			case "exit":
				quitter = true;
				break;
			default:
				System.out.println("saisie incorrecte");
				break;
			}
		}while(!quitter);
		quitter();
	}

	private static void quitter() {
		System.out.println("Bisous");
		System.exit(0);
	}

	private static void partieDistante() {
		// TODO Auto-generated method stub
		
	}

	private static void partieLocal() {
		jeu = new ManagerJeu();
		boolean choisie;
		do {
			System.out.println("Partie rapide ou personnalisé ?");
			choisie = true;
			System.out.println("1    : rapide (2j)");
			System.out.println("2    : personnalisé");
			System.out.println("back : retour");
			String saisie = sc.nextLine();
			
			switch (saisie) {
			case "1":
				saisieNomJoueur();
				jouer();
				break;
			case "2":
				partiePersonnalise();
				break;
			case "back":
				return;
			default:
				choisie = false;
				System.out.println("saisie incorrecte");
				break;
			}
		}while(!choisie);		
	}
	
	private static void partiePersonnalise() {
		saisieInfoPartie();
		saisieInfoGrille();
		saisieInfoJoueur();
		jouer();
	}
	

	
	private static void jouer() {
		System.out.println();
		System.out.println("----  START  ----");
		System.out.println();
		boolean tourFinie;
		//boucle pour les différentes parties à jouer
		do {
			// boucle pour les différents joueurs
			do {
				// boucle pour le joueur qui joue (tant qu'il joue pas correctement)
				do {
					tourFinie = true;
					System.out.print("Partie n°" + jeu.getNumPartie() + "/" + jeu.getNbPartie() + " - ");
					System.out.println("Tour de " + jeu.getNomJoueurCourrant() + " (" + jeu.getSymboleJoueurCourrant() + ")");
					System.out.println();
					System.out.println(jeu.affichage());
					System.out.print("Entrée le  nom de la case: ");
					String saisie = sc.nextLine();
					
					try {
						jeu.jouer(saisie);
					} catch (FullColumnException e) {
						// TODO Auto-generated catch block
						System.out.println("la colonne est pleinne ! Gros Beta !! ");
						tourFinie = false;
					} catch (KeyDoNotExistException e) {
						// TODO Auto-generated catch block
						System.out.println("appuie sur une touche qui existe. Patate! -_- ");
						tourFinie = false;
					}catch(IllegalArgumentException e) {
						System.out.println("UNE ERREUR !!, Mais qu'est-ce que vous avez encore fait !! >:( ");
						quitter();
					}
				}while(!tourFinie);
				
			}while(!jeu.partieFinie());
			affichageVictoire();
			jeu.nouvellePartie();
			
		}while(jeu.getNbPartieRestante() > 0);
		
		System.out.println();
		System.out.println("----  Partie terminée  ----");
		System.out.println();
		scoreFinal();
	}
	
	private static void affichageVictoire() {
		System.out.println();
		System.out.println("----  Manche terminée  ----");
		System.out.println();
		System.out.println("Le Joueur " + jeu.getNomJoueurCourrant() + " à gagné ");
		System.out.println();
	}
	
	private static void scoreFinal() {
		System.out.println();
		System.out.println("----  Score Final  ----");
		System.out.println();
		System.out.println(jeu.infoScore());
		System.out.println();
		System.out.println();
	}
	
	private static void saisieInfoJoueur() {
		saisieNombreJoueur();
		for (int i = 0; i < jeu.getNbJoueur(); i++) {
			System.out.print("Entrée le nom du joueur n° "+ (i + 1) + ": ");
			String nom = sc.nextLine();
			jeu.getJoueur(i).setNom(nom);
			
			System.out.print("Entrée le symbole pour "+ nom + " : ");
			String saisie = sc.nextLine();
			jeu.getJoueur(i).setSymbole(saisie.charAt(0));
			
			System.out.println("Paramètrage des racourcis claviers de " + nom + " : ");
			String[] touches = new String[jeu.getGrille().getNbColonnes()];
			for (int j = 0; j < jeu.getGrille().getNbColonnes(); j++) {
				System.out.print("Entrée le symbole pour "+ nom + ", colonne " + (j + 1) + " : " );
				saisie = sc.nextLine();
				touches[j] = saisie;
			}
			jeu.getJoueur(i).setTouche(touches);
		}
	}
	
	private static void saisieNombreJoueur() {
		boolean condition;
		do {
			condition = false;
			System.out.print("Entrée le nombre de joueur : ");
			String saisie = sc.nextLine();
			try {
				jeu.setNbJoueur(Integer.parseInt(saisie));
			}catch(NumberFormatException e) {
				System.out.println("j'ai dit un nombre !");
				condition = true;
			}
		} while (condition);
	}
	
	private static void saisieNomJoueur() {
		for (int i = 0; i < jeu.getNbJoueur(); i++) {
			System.out.print("Entrée le nom du joueur n° "+ i + ": ");
			String saisie = sc.nextLine();
			jeu.getJoueur(i).setNom(saisie);
		}
		
	}
	
	private static void saisieInfoPartie() {
		boolean condition;
		do {
			condition = false;
			System.out.print("Entrée le nombre de partie : ");
			String saisie = sc.nextLine();
			try {
				jeu.setNbPartie(Integer.parseInt(saisie));
			}catch(NumberFormatException e) {
				System.out.println("j'ai dit un nombre !");
				condition = true;
			}
		} while (condition);
		
		do {
			condition = false;
			System.out.print("Entrée le nombre de pion à aligner pour gagner : ");
			String saisie = sc.nextLine();
			try {
				jeu.setConditionVictoire(Integer.parseInt(saisie));
			}catch(NumberFormatException e) {
				System.out.println("j'ai dit un nombre !");
				condition = true;
			}
		} while (condition);
	}
	
	private static void saisieInfoGrille() {
		boolean condition;
		int nbLignes = 6;
		int nbColonnes = 7;
		
		do {
			condition = false;
			System.out.print("Entrée le nombre de lignes : ");
			String saisie = sc.nextLine();
			try {
				nbLignes = Integer.parseInt(saisie);
			}catch(NumberFormatException e) {
				System.out.println("j'ai dit un nombre !");
				condition = true;
			}
		} while (condition);
		
		do {
			condition = false;
			System.out.print("Entrée le nombre de colonnes : ");
			String saisie = sc.nextLine();
			try {
				nbColonnes = Integer.parseInt(saisie);
			}catch(NumberFormatException e) {
				System.out.println("j'ai dit un nombre !");
				condition = true;
			}
		} while (condition);
		
		jeu.setGrille(nbLignes, nbColonnes);
	}
	
}

