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
public class TestPoissons {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Poisson p = new Poisson("Nemo",0,0,50);
        System.out.println(p.getNom()+"//"+p.getCoordX()+"//" + p.getCoordY()+"//" + p.getScore());
        p.setCoordX(50);
        p.setCoordY(40);
        p.setScore(25);
        p.setNom("NemoBis");
        System.out.println(p.getNom()+"//"+p.getCoordX()+"//" + p.getCoordY()+"//" + p.getScore());
    }
    
}
