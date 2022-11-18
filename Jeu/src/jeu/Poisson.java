/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu;

import java.util.Random;
/**
 *
 * @author benja
 */
public class Poisson {
    
    private String nom;
    private double CoordX;
    private double CoordY;
    private int Score;
    
    public Poisson(String nom, double CoordX, double CoordY, int Score) {
        this.nom = nom;
        this.CoordX = CoordX;
        this.CoordY = CoordY;
        this.Score = Score;
    }
    
    public Poisson(){
        this.nom = "NomParDefaut";
        this.CoordX = (new Random().nextDouble() * (100 - 0)) + 0;
        this.CoordY = (new Random().nextDouble() * (100 - 0)) + 0;
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
    
    public static int rand(int min, int max) { //méthode de randomize
        return min + (int) (Math.random() * ((max - min) + 1));
    }
    
    public void unDeplacementAleatoire(){ //methode pour le déplacement aléatoire des poissons
        int x = rand(-1,1);
        int y = rand(-1,1);
        setCoordX(this.getCoordX()+x);
        setCoordY(this.getCoordY()+y);
    }
        
        
    }
    
    
    

