/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jeu;

/**
 *
 * @author shaya
 */
public class Case {
    private boolean presencePoisson;
    private boolean presenceDechet;
    private boolean presenceTerre;
    private boolean presencePort;
    public Case(){
        this.presencePoisson = false;
        this.presenceDechet = false;
        this.presenceTerre = false;
        this.presencePort = false;
            
        }

    public boolean isPresencePoisson() {
        return presencePoisson;
    }

    public boolean isPresenceDechet() {
        return presenceDechet;
    }

    public boolean isPresenceTerre() {
        return presenceTerre;
    }

    public boolean isPresencePort() {
        return presencePort;
    }

    public void setPresencePoisson(boolean presencePoisson) {
        this.presencePoisson = presencePoisson;
    }

    public void setPresenceDechet(boolean presenceDechet) {
        this.presenceDechet = presenceDechet;
    }

    public void setPresenceTerre(boolean presenceTerre) {
        this.presenceTerre = presenceTerre;
    }

    public void setPresencePort(boolean presencePort) {
        this.presencePort = presencePort;
    }
    
    }

