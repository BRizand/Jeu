/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jeu;

import java.awt.Graphics2D;

/**
 *
 * @author eporceil
 */
public class Jeu {
    private final Carte carte;
     
    public Jeu() {        
        this.carte = new Carte();
        this.carte.ConsDecor("mer.csv");
    }

    public Carte getCarte() {
        return carte;
    }

    
    public void miseAJour() {
        this.carte.miseAJour();
    }

    public void rendu(Graphics2D contexte) {
        this.carte.rendu(contexte);
    }

}
