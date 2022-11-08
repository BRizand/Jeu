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
public class Poisson {
    
    private String nom;
    private int CoordX;
    private int CoordY;
    private int Score;
    
    public Poisson(String nom, int CoordX, int CoordY, int Score) {
        this.nom = nom;
        this.CoordX = CoordX;
        this.CoordY = CoordY;
        this.Score = Score;
    }
    
    public Poisson(){
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
    
    public static int rand(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }
    
    public void unDeplacementAleatoire(){
        int x = rand(-1,1);
        int y = rand(-1,1);
        setCoordX(this.getCoordX()+x);
        setCoordY(this.getCoordY()+y);
    }
        
        
    }
    
    
    

