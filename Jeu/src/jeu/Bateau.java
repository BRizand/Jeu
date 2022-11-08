/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jeu;

/**
 *
 * @author brizand
 */
public class Bateau {
    private String nom;
    private int CoordX;
    private int CoordY;
    private int PointDeVie;
    private int Score;

    public Bateau(String nom, int CoordX, int CoordY, int PointDeVie, int Score) {
        this.nom = nom;
        this.CoordX = CoordX;
        this.CoordY = CoordY;
        this.PointDeVie = PointDeVie;
        this.Score = Score;
    }
    
    public Bateau(){
        this.nom = "NomParDefaut";
        this.CoordX =0;
        this.CoordY=0;
        this.PointDeVie=100;
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

    public int getPointDeVie() {
        return PointDeVie;
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

    public void setPointDeVie(int PointDeVie) {
        this.PointDeVie = PointDeVie;
    }

    public void setScore(int Score) {
        this.Score = Score;
    }

    
    public String toString() {
        return "Bateau{" + "nom=" + nom + ", CoordX=" + CoordX + ", CoordY=" + CoordY + ", PointDeVie=" + PointDeVie + ", Score=" + Score + '}';
    }
    
   
}
