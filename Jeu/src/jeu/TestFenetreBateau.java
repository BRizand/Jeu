/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package jeu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author srahabi
 */
public class TestFenetreBateau extends JFrame implements KeyListener{
    
    private Bateau bateau;
    
    public TestFenetreBateau(){
        this.addKeyListener(this);
        
        this.bateau = new Bateau();
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        // NOP
    }
    @Override
    public void keyPressed(KeyEvent event){ //méthode pour récuperer les touches pour le déplacement du bateau
        if(event.getKeyCode() == event.VK_RIGHT){
            bateau.setCoordX(bateau.getCoordX()+0.01);
            System.out.println("Right");
        }
        if(event.getKeyCode() == event.VK_LEFT){
            bateau.setCoordX(bateau.getCoordX()-0.01);
            System.out.println("Left");
        }
        if(event.getKeyCode() == event.VK_UP){
            bateau.setCoordY(bateau.getCoordY()+0.01);
            System.out.println("Up");
        }
        if(event.getKeyCode() == event.VK_DOWN){
            bateau.setCoordY(bateau.getCoordY()-0.01);
            System.out.println("Down");
        }
            
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        // NOP
        }
    
    public boolean laVictoire(){ 
        boolean vic = false;
        if(bateau.getScore() >= 100){
            vic = true;
        }
        return vic;
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TestFenetreBateau fenetre = new TestFenetreBateau();
        fenetre.setVisible(true);
        // TODO code application logic here
    }
}
    

