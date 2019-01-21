package com.apo.puissance4;

/**	
 * La classe Colonne permet de g�rer une colonne dans un jeu de "puissance 4"
 * � l'aide de tableau @see Case.
 * @author Thibaut Delplanque
 *
 */
public class Colonne {
	
	// ***********************
	// Attributs
	// ***********************
	/**
	 * tableau de @see Case qui repr�sente une colonne d'un jeu de puissance 4
	 * le bas de la colonne se trouve � l'indice 0, et le haut � l'indice n 
	 */
	private Case[] _colonne;
	/**
	 * la taille de la colonne
	 */
	private int _tailleColonne;
	/**
	 * l'index de la case vide la plus en bas dans la colonne
	 */
	private int _indexCaseVide;
	
	// ***********************
	// Getters Setters
	// ***********************
	/**
	 * Permet de r�cup�rer la taille de la colonne
	 * @return retourne un int pour la taille
	 */
	public int getTaille() {
		return _tailleColonne;
	}
	
	/**
	 * Permet de r�cup�rer l'index de la case vide la plus en bas dans la colonne
	 * @return retourne un int pour l'index 
	 */
	public int getIndexCaseVide() {
		return _indexCaseVide;
	}

	/**
	 * Initialise une instance de la classe @see Colonne avec une taille 
	 * @param taille la taille de l'instance  
	 */
	public Colonne(int taille) {
		_tailleColonne = taille;
		_indexCaseVide = 0;
		for (int i = 0; i < size; i++) {
			Case[i] = new Case();			
		}
	}
	
	/**
	 * Ajoute un @see Jeton dans la colonne, � la case la plus en bas 
	 * @param joueur
	 */
	public void ajouterJeton(Joueur joueur) {
		_colonne[_indexCaseVide].ajouterJeton(joueur);
		_indexCaseVide++;
	}
	
	
}
