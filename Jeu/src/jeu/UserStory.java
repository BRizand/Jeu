/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jeu;

/**
 *
 * @author brizand
 */
public class UserStory {
    private Bateau bateau;
    private Poisson poisson;
    private Dechet dechet;
    private Case cases;
    private Map map;
    
    
    public void ChangementScore(){
        if(this.cases.isPresencePoisson()){
            this.bateau.setScore(this.bateau.getScore()+10);
            this.cases.setPresencePoisson(false);
        }
        if(this.cases.isPresenceDechet()){
            this.bateau.setScore(this.bateau.getScore()-10);
            this.cases.setPresenceDechet(false);
        }
    }
    
    
    
        
    }

