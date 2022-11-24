/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package jeu;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 * Exemple de fenetre de jeu en utilisant uniquement des commandes
 *
 * @author X
 */
public class FenetreDeJeu extends JFrame implements KeyListener, ActionListener {

    private BufferedImage buffer;
    private Graphics2D contexteBuffer;
    private Jeu jeu;
    private Timer timer;
    private Timer timerMenu;
    private JLabel jLabel1;
    private Bateau bateau;
    private JTextField pseudo;
    private JButton ValiderPseudo;
    private int nbJoueurs;
    private JPanel menu;
    private String Pseudonyme;
    private boolean PartieEnCours = false;
    private JButton jouer;
    private Connection connexion;
    private String Pseudos;
    private JLabel afficherPseudo;
    private int scoreExtrait;
    private JTextField score;
    private int pdvExtrait;
    private JTextField vie;

    public FenetreDeJeu() {
        // initialisation de la fenetre
        nbJoueurs = 0;
        this.setSize(800, 429);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                if (Pseudonyme != null) {//test si on est déjà dans la partie
                    try {
                        PreparedStatement requete = connexion.prepareStatement("SELECT COUNT(*) FROM Bateau");
                        System.out.println(requete);
                        ResultSet resultat = requete.executeQuery();
                        while (resultat.next()) {
                            nbJoueurs = resultat.getInt("COUNT(*)");
                        }
                        requete.close();
                        if (nbJoueurs > 1) {
                            PreparedStatement requete2 = connexion.prepareStatement("DELETE FROM Bateau WHERE Pseudo=?");
                            requete2.setString(1, Pseudonyme);
                            requete2.executeQuery();
                            requete2.close();
                        } else {
                            PreparedStatement requete3 = connexion.prepareStatement("DELETE FROM Bateau WHERE Pseudo=?");
                            requete3.setString(1, Pseudonyme);
                            requete3.executeQuery();
                            requete3.close();
                            PreparedStatement requete4 = connexion.prepareStatement("DELETE FROM MAP WHERE 1");
                            requete4.executeQuery();
                            requete4.close();
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                System.exit(0);
            }
        });
        menu = new JPanel();
        menu.setBounds(0, 0, 800, 429);
        menu.setBackground(Color.lightGray);
        pseudo = new JTextField("Pseudo");
        pseudo.setBounds(20, 20, 100, 20);
        this.add(pseudo);
        ValiderPseudo = new JButton("Commencer");
        ValiderPseudo.setBounds(20, 60, 500, 20);
        ValiderPseudo.addActionListener(this);
        this.add(ValiderPseudo);
        this.add(menu);
        this.setLayout(null);
        this.setVisible(true);

    }

    public void DemarerPartie() {
        timerMenu.stop();
        this.remove(menu);
        this.jLabel1 = new JLabel();
        this.jLabel1.setPreferredSize(new java.awt.Dimension(1000, 1000));
        this.setContentPane(this.jLabel1);
        
        //Extraction du score depuis la base de données
        try {
            Connection connexion3 = DriverManager.getConnection("jdbc:mariadb://nemrod.ens2m.fr:3306/2022-2023_s1_vs2_tp2_BateauNettoyeur", "user_bateau", "QGb4dhxBW@x.mT.I");
            
            PreparedStatement requete3 = connexion3.prepareStatement("SELECT Score FROM Bateau");
            System.out.println(requete3);
            ResultSet scoreExtrait = requete3.executeQuery();
            requete3.close();
            connexion3.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(scoreExtrait);
        //Ajout du score sur le HUD
        this.score = new JTextField(""+scoreExtrait+"");
        score.setBounds(0,0, 150, 30);
        score.setEditable(false);
        score.setOpaque(false);
        this.add(score);
        try {
            Connection connexion4 = DriverManager.getConnection("jdbc:mariadb://nemrod.ens2m.fr:3306/2022-2023_s1_vs2_tp2_BateauNettoyeur", "user_bateau", "QGb4dhxBW@x.mT.I");
            
            PreparedStatement requete4 = connexion4.prepareStatement("SELECT PointDeVie FROM Bateau");
            System.out.println(requete4);
            ResultSet pdvExtrait = requete4.executeQuery();
            requete4.close();
            connexion4.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(pdvExtrait);
        
        //Ajout de la vie sur le HUD
        this.vie = new JTextField(""+pdvExtrait+"");
        vie.setBounds(0,30, 150, 30);
        vie.setEditable(false);
        vie.setOpaque(false);
        this.add(vie);
        
        this.pack();

        // Creation du buffer pour l'affichage du jeu et recuperation du contexte graphique
        this.buffer = new BufferedImage(this.jLabel1.getWidth(), this.jLabel1.getHeight(), BufferedImage.TYPE_INT_ARGB);
        this.jLabel1.setIcon(new ImageIcon(buffer));
        this.contexteBuffer = this.buffer.createGraphics();

        // Creation du jeu
        this.jeu = new Jeu();
        this.jLabel1.setFocusable(true);
        this.requestFocus();
        this.addKeyListener(this);

        this.bateau = new Bateau();
        this.bateau.setNom(Pseudonyme);
        this.bateau.setConnexion(connexion);
        this.bateau.setCarte(this.jeu.getCarte());
        // Creation du Timer qui appelle this.actionPerformed() tous les 40 ms
        this.timer = new Timer(40, this);
        this.timer.start();
    }

    public void Partie() {
        try {
            connexion = DriverManager.getConnection("jdbc:mariadb://nemrod.ens2m.fr:3306/2022-2023_s1_vs2_tp2_BateauNettoyeur", "user_bateau", "QGb4dhxBW@x.mT.I");

        } catch (SQLException ex) {
            Logger.getLogger(FenetreDeJeu.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.remove(pseudo);
        this.remove(ValiderPseudo);
        try {

            PreparedStatement requete = connexion.prepareStatement("INSERT INTO Bateau VALUES (?,?,?,?,?)");
            requete.setString(1, Pseudonyme);
            requete.setDouble(2, 0);
            requete.setDouble(3, 0);
            requete.setInt(4, 100);
            requete.setInt(5, 0);
            System.out.println(requete);
            requete.executeUpdate();

            requete.close();

            PreparedStatement requete2 = connexion.prepareStatement("SELECT COUNT(*) FROM Bateau");
            System.out.println(requete2);
            ResultSet resultat2 = requete2.executeQuery();
            while (resultat2.next()) {
                nbJoueurs = resultat2.getInt("COUNT(*)");
            }
            requete2.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if (nbJoueurs <= 1) {
            JButton map1 = new JButton("map1");
            map1.setBounds(20, 20, 100, 20);
            JButton map2 = new JButton("map2");
            map2.setBounds(20, 60, 100, 20);
            jouer = new JButton("Démarer la partie");
            jouer.setBounds(20, 100, 100, 20);
            jouer.addActionListener(this);
            menu.add(map1);
            menu.add(map2);
            menu.add(jouer);
            this.setLayout(null);
            this.setVisible(true);
        } else {
            JTextArea attente = new JTextArea("En attente de l'hôte");
            attente.setBounds(20, 90, 280, 30);
            attente.setEditable(false);
            menu.add(attente);
            this.setLayout(null);
            this.setVisible(true);
        }
        afficherPseudo = new JLabel();
        afficherPseudo.setBounds(200,200,1000,1000);
        afficherPseudo.setLayout(null);
        menu.add(afficherPseudo);
        this.timerMenu = new Timer(40, this);
        this.timerMenu.start();
    }

    // Methode appelee par le timer et qui contient la boucle de jeu
    public void windowClosing(WindowEvent e) {

        System.out.println("exiting1...");
        this.windowClosing(e);
        System.out.println("exiting1.1...");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (PartieEnCours && (timer != null)) {
            this.jeu.miseAJour();
            this.jeu.rendu(contexteBuffer);
            this.bateau.miseAJour();
            this.bateau.rendu(contexteBuffer);
            this.pack();
            this.jLabel1.repaint();
        }
        if (timerMenu != null) {
            try {
                PreparedStatement requete = connexion.prepareStatement("SELECT Pseudo FROM Bateau");
                ResultSet resultat = requete.executeQuery();
                Pseudos="";
                while (resultat.next()){
                    Pseudos=Pseudos+System.getProperty("line.separator")+resultat.getString("Pseudo");
                }
                afficherPseudo.setText(Pseudos);
                requete.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        Pseudonyme = pseudo.getText();
        if (e.getSource() == ValiderPseudo) {
            Partie();
        }
        if (e.getSource() == jouer) {
            PartieEnCours = true;
            DemarerPartie();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // NOP
    }

    @Override
    public void keyPressed(KeyEvent event) {
        if (event.getKeyCode() == event.VK_RIGHT) {
            if (bateau.isGauche()) {
                bateau.setGauche(false);
            } else {
                bateau.setDroite(true);
            }
            System.out.println("Right");
        }
        if (event.getKeyCode() == event.VK_LEFT) {
            if (bateau.isDroite()) {
                bateau.setDroite(false);
            } else {
                bateau.setGauche(true);
            }
            System.out.println("Left");
        }
        if (event.getKeyCode() == event.VK_UP) {
            if (bateau.isBas()) {
                bateau.setBas(false);
            } else {
                bateau.setHaut(true);
            }
            System.out.println("Up");
        }
        if (event.getKeyCode() == event.VK_DOWN) {
            if (bateau.isHaut()) {
                bateau.setHaut(false);
            } else {
                bateau.setBas(true);
            }
            System.out.println("Down");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // NOP
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        FenetreDeJeu fenetre = new FenetreDeJeu();
        fenetre.setVisible(true);
    }

}
