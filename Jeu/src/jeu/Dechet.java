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
    private double CoordX;
    private double CoordY;
    private int Score;

    public Dechet(String nom, double CoordX, double CoordY, int Score) {
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

    public double getCoordX() {
        return CoordX;
    }

    public double getCoordY() {
        return CoordY;
    }

    public int getScore() {
        return Score;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setCoordX(double CoordX) {
        this.CoordX = CoordX;
    }

    public void setCoordY(double CoordY) {
        this.CoordY = CoordY;
    }

    public void setScore(int Score) {
        this.Score = Score;
    }
    
    
}
