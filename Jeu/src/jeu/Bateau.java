/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jeu;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import java.util.*;


/**
 *
 * @author brizand
 */
public class Bateau {

    private BufferedImage sprite;
    private String nom;
    private double CoordX;
    private double CoordY;
    private int PointDeVie;
    private int Score;
    private boolean gauche, droite, haut, bas;
    private Carte carte;
    private Connection connexion;
    private ArrayList<Integer> EAU;
    
    public Bateau(String nom, double CoordX, double CoordY, int PointDeVie, int Score) {
        this();
        this.nom = nom;
        this.CoordX = CoordX;
        this.CoordY = CoordY;
        this.PointDeVie = PointDeVie;
        this.Score = Score;
        try {
            this.sprite = ImageIO.read(getClass().getResource("bateau.png "));
        } catch (IOException ex) {
            Logger.getLogger(Bateau.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void setConnexion(Connection connexion) {
        this.connexion = connexion;
    }

    public void setCarte(Carte carte) {
        this.carte = carte;
    }

    public Bateau() {
        this.EAU = new ArrayList();
        this.EAU.add(82);
        this.EAU.add(322);
         this.EAU.add(360);
          this.EAU.add(361);
           this.EAU.add(362);
            this.EAU.add(365);
        this.nom = "NomParDefaut";
        this.CoordX = 0;
        this.CoordY = 0;
        this.PointDeVie = 100;
        this.Score = 0;
        try {
            this.sprite = ImageIO.read(getClass().getResource("bateau.png "));
        } catch (IOException ex) {
            Logger.getLogger(Bateau.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setGauche(boolean gauche) {
        this.gauche = gauche;
    }

    public void setDroite(boolean droite) {
        this.droite = droite;
    }

    public void setHaut(boolean haut) {
        this.haut = haut;
    }

    public void setBas(boolean bas) {
        this.bas = bas;
    }

    public boolean isGauche() {
        return gauche;
    }

    public boolean isDroite() {
        return droite;
    }

    public boolean isHaut() {
        return haut;
    }

    public boolean isBas() {
        return bas;
    }

    

    public void miseAJour() {
        if (this.PointDeVie > 0) {
            if (this.gauche) {
                CoordX -= 5;
            }
            if (this.droite) {
                CoordX += 5;
            }
            if (this.bas) {
                CoordY += 5;
            }
            if (this.haut) {
                CoordY -= 5;
            }
            if (CoordX > this.carte.getLargeur() * 32 - sprite.getWidth()) { // collision avec le bord droit de la scene
                CoordX = this.carte.getLargeur() * 32 - sprite.getWidth();
                this.droite = false;
            }
            if (CoordX < 0) { // collision avec le bord gauche de la scene
                CoordX = 0;
                this.gauche = false;
            }
            if (CoordY > this.carte.getHauteur() * 32 - sprite.getWidth()) {
                CoordY = this.carte.getHauteur() * 32 - sprite.getWidth();
                this.bas = false;
            }
            if (CoordY < 0) {
                CoordY = 0;
                this.haut = false;
            }
            if (EAU.contains(this.carte.getDecor((int) Math.round((CoordY / this.carte.getTailleTuileLargeur())), (int) Math.round((CoordX / this.carte.getTailleTuileHauteur())))) && (this.droite == true || this.gauche == true || this.haut == true || this.bas == true)) {
                this.haut = false;
                this.bas = false;
                this.gauche = false;
                this.droite = false;
                this.PointDeVie -= 10;
                System.out.println("a");
            }
        } else {
            return;
        }
        try {
            
            PreparedStatement requete = connexion.prepareStatement("UPDATE Bateau SET CoordX=?,CoordY=?,PointDeVie=? WHERE Pseudo=?");
            requete.setDouble(1, this.CoordX);
            requete.setDouble(2, this.CoordY);
            requete.setInt(3, this.PointDeVie);
            requete.setString(4, this.nom);
            System.out.println(requete);
            int nbModif = requete.executeUpdate();
            System.out.println(nbModif + " mdif enregistr??s");

            requete.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return;
    }

    public void rendu(Graphics2D contexteBuffer) {
        contexteBuffer.drawImage(this.sprite, (int) CoordX, (int) CoordY, null);
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

    public int getPointDeVie() {
        return PointDeVie;
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
