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
public class TestDechet {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Dechet d = new Dechet("canette",0,0,50);
        System.out.println(d.getNom()+"//"+d.getCoordX()+"//" + d.getCoordY()+"//" + d.getScore());
        d.setCoordX(50);
        d.setCoordY(40);
        d.setScore(25);
        d.setNom("CanetteBis");
        System.out.println(d.getNom()+"//"+d.getCoordX()+"//" + d.getCoordY()+"//" + d.getScore());
    }
    
}
