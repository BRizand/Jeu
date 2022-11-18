/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu;

/**
 *
 * @author benja
 */
public class Dechet {
    
    private String nom;
    private int CoordX;
    private int CoordY;
    private int Score;

    public Dechet(String nom, int CoordX, int CoordY, int Score) {
        this.nom = nom;
        this.CoordX = CoordX;
        this.CoordY = CoordY;
        this.Score = Score;
    }
    
    public Dechet(){
        this.nom = "NomParDefaut";
        this.CoordX =0;
        this.CoordY=0;
        this.Score=0;
    }

    public String getNom() {
        return nom;
    }

    public int getCoordX() {
        return CoordX;
    }

    public int getCoordY() {
        return CoordY;
    }

    public int getScore() {
        return Score;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setCoordX(int CoordX) {
        this.CoordX = CoordX;
    }

    public void setCoordY(int CoordY) {
        this.CoordY = CoordY;
    }

    public void setScore(int Score) {
        this.Score = Score;
    }
    
    
}
