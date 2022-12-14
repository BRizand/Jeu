package jeu;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author eporceil
 */
public class Carte {

    private int largeur = 300;
    private int hauteur = 300;
    private int tailleTuileLargeur = 32;
    private int tailleTuileHauteur = 32;
    private int nbTuileLargeur = 21;
    private int nbTuileHauteur = 23;
    private BufferedImage[] uneTuile;
    private int[][] decor = {};
    private int[][] calque = {};

    public Carte() {
        try {

            BufferedImage tileset = ImageIO.read(getClass().getResource("tileset.png"));
            uneTuile = new BufferedImage[nbTuileLargeur * nbTuileHauteur];
            for (int i = 0; i < uneTuile.length; i++) {
                int x = (i % nbTuileLargeur) * tailleTuileLargeur;
                int y = (i / nbTuileLargeur) * tailleTuileHauteur;
                // System.out.println(x+" "+y);
                uneTuile[i] = tileset.getSubimage(x, y, tailleTuileLargeur, tailleTuileHauteur);
            }
        } catch (IOException ex) {
            Logger.getLogger(Carte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void miseAJour() {

    }

    public int getLargeur() {
        return largeur;
    }

    public int getHauteur() {
        return hauteur;
    }

    public int getDecor(int x, int y) {
        return decor[x][y];
    }

    public int getTailleTuileLargeur() {
        return tailleTuileLargeur;
    }

    public int getTailleTuileHauteur() {
        return tailleTuileHauteur;
    }

    public void ConsDecor(String carte) {
        try {
            BufferedReader fichier = new BufferedReader(new FileReader(carte));
            fichier.readLine();
            fichier.readLine();
            String info = fichier.readLine();
            String infoCarte[] = info.split(",");
            largeur = Integer.parseInt(infoCarte[0]);
            hauteur = Integer.parseInt(infoCarte[1]);
            tailleTuileLargeur = Integer.parseInt(infoCarte[2]);
            tailleTuileHauteur = Integer.parseInt(infoCarte[3]);
            decor = new int[hauteur][largeur];
            for (int i = 0; i < hauteur; i++) {
                String ligne = fichier.readLine();
                String infoCase[] = ligne.split(",");
                for (int j = 0; j < largeur; j++) {
                    decor[i][j] = Integer.parseInt(infoCase[j]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ConsCalque1(String calque1) {
        try {
            BufferedReader fichier = new BufferedReader(new FileReader(calque1));
            fichier.readLine();
            fichier.readLine();
            String info = fichier.readLine();
            String infoCarte[] = info.split(",");
            largeur = Integer.parseInt(infoCarte[0]);
            hauteur = Integer.parseInt(infoCarte[1]);
            tailleTuileLargeur = Integer.parseInt(infoCarte[2]);
            tailleTuileHauteur = Integer.parseInt(infoCarte[3]);
            calque = new int[hauteur][largeur];
            for (int i = 0; i < hauteur; i++) {
                String ligne = fichier.readLine();
                String infoCase[] = ligne.split(",");
                for (int j = 0; j < largeur; j++) {
                    calque[i][j] = Integer.parseInt(infoCase[j]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

     public void rendu(Graphics2D contexte) {
        for (int i = 0; i < largeur; i++) {
            for (int j = 0; j < hauteur; j++) {              
                contexte.drawImage(uneTuile[decor[j][i]], i * 32, j * 32, null);   
            }
        }
    }
    
    public void renduCalque(Graphics2D contexte) {
        for (int i = 0; i < largeur; i++) {
            for (int j = 0; j < hauteur; j++) {
                if (calque[j][i] >= 0) {
                    contexte.drawImage(uneTuile[calque[j][i]], i * 32, j * 32, null);
                }
            }
        }
    }

}
