/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rti_2.checkinap;

import rti_2.checkcarp.Serveur_Card;
import rti_2.database.facility.MyInstruction;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import rti_2.checkinap.requetereponse.ConsoleServeur;
import rti_2.checkinap.requetereponse.Requete;

/**
 *
 * @author fredm
 */
public class RequeteCHECKINAP implements Requete, Serializable{
    public static int LOGIN = 1;
    public static int BOOKING = 2;
    public static int BUY = 3;
    public static int CLOSE = 4;
    public static int REQUEST_E_MAIL = 10;
    public static int REQUEST_TEMPORARY_KEY = 20;
    public static int IS_CARTE_VALIDE = 5;
    public static Hashtable tableLogin = new Hashtable();
    static
    {
        tableLogin.put("heyea", "azerty159");
        tableLogin.put("marcotty", "admin");
        tableLogin.put("a", "b"); //debug
    }
   
    public int type;
    private String chargeUtile;
    private Socket socketClient;
    private Socket socketServeurCard;
    
    public RequeteCHECKINAP(int t, String chu)
    {
        type = t; setChargeUtile(chu);
    }
    public RequeteCHECKINAP(int t, String chu, Socket s)
    {
        type = t; setChargeUtile(chu); socketClient =s;
    }/*
    public Runnable createRunnable (final Socket s, final ConsoleServeur cs)
    {
        return new Runnable()
        {
            public void run()
            {
                traiteRequeteLogin(s, cs);
                while(true)
                {
                    TraiterRequete(s, cs);
                }
            }
        };
    }
    private void TraiterRequete(Socket s, ConsoleServeur cs) {
        RequeteCHECKINAP req = null;
        req = RecevoirRequete(s);
        if(req.type == BOOKING)
        {
            traiteRequeteBooking(s, cs);
        }
        else if(req.type == BUY)
        {
            traiteRequeteBuy(s, cs);
        }
        else if(req.type == CLOSE)
        {
            traiteRequeteClose(s, cs);
        }
    }*/
    
    public Runnable createRunnable (final Socket s, final ConsoleServeur cs)
    {
        //attente de la requete suivante
        if (type==LOGIN)
            return new Runnable()
            {
                public void run()
                {
                    traiteRequeteLogin(s, cs);
                }
            };
        else if (type==BOOKING)
            return new Runnable()
            {
                public void run()
                {
                    traiteRequeteBooking(s, cs);
                }
            };
        else if (type==BUY)
            return new Runnable()
            {
                public void run()
                {
                    traiteRequeteBuy(s, cs);
                }
            };
        else if (type==CLOSE)
            return new Runnable()
            {
                public void run()
                {
                    traiteRequeteClose(s, cs);
                }
            };
        else return null;
        
    }
    
    
    private synchronized void traiteRequeteBooking(Socket s, ConsoleServeur cs)
    {
        System.out.println("Charge utile recue : " + chargeUtile);
        cs.TraceEvenements(s.getRemoteSocketAddress().toString() + "#BOOKING" + "#Thread");
        Vector infos = new Vector();
        String code, passagers;
        StringTokenizer parser = new StringTokenizer(chargeUtile, "#");
        while(parser.hasMoreTokens())
            infos.add(parser.nextToken());
        code = (String) infos.get(0);
        //passagers = (String) infos.get(1);
        
        ReponseCHECKINAP rep;
        if(BookingExiste(code))
        {
            System.out.println("Booking ok !");
            int places = RecupPlaces(code);
            //if(places != "-1")
            chargeUtile = Integer.toString(places);
            rep = new ReponseCHECKINAP(ReponseCHECKINAP.BOOKING_OK, getChargeUtile());
        }
        else
        {
            System.out.println("booking n'existe pas !");
            rep = new ReponseCHECKINAP(ReponseCHECKINAP.BOOKING_NOK, getChargeUtile());
        }
        ObjectOutputStream oos;
        try
        {
        oos = new ObjectOutputStream(s.getOutputStream());
        oos.writeObject(rep); oos.flush();
        oos.close();
        }
        catch (IOException e)
        {
        System.err.println("Erreur réseau ? [" + e.getMessage() + "]");
        }
    }
    private synchronized void traiteRequeteBuy(Socket s, ConsoleServeur cs)
    {
        System.out.println("Charge utile recue : " + chargeUtile);
        cs.TraceEvenements(s.getRemoteSocketAddress().toString() + "#BUY" + "#Thread");
        Vector infos = new Vector();
        String nom, immatriculation, passagers, carte;
        StringTokenizer parser = new StringTokenizer(chargeUtile, "#");
        while(parser.hasMoreTokens())
            infos.add(parser.nextToken());
        nom = (String) infos.get(0);
        immatriculation = (String) infos.get(1);
        passagers = (String) infos.get(2);
        carte = (String) infos.get(3);
        
        ReponseCHECKINAP rep = null; 
        //Verif serveur de cartes
        System.out.println("Carte : " + carte);
        if(socketServeurCard == null)
        {
            System.out.println("socket cartes null -- connexion");
            ConnexionServeurCarte();
        }
        if(CarteValide(carte, socketServeurCard))
        {
            System.out.println("carte_remote ok");
            rep = new ReponseCHECKINAP(ReponseCHECKINAP.BUY_OK, getChargeUtile());
        }
        else
        {
            System.out.println("carte_remote invalide");
            rep = new ReponseCHECKINAP(ReponseCHECKINAP.BUY_NOK, getChargeUtile());
        }
        
        /*
        
        
        
        
        if(card_Socket != null)
        {
            if(CarteValide(carte, CSocket))
            {
                System.out.println("carte_remote ok");
                rep = new ReponseCHECKINAP(ReponseCHECKINAP.BUY_OK, getChargeUtile());
            }
            else
            {
                System.out.println("carte_remote invalide");
                rep = new ReponseCHECKINAP(ReponseCHECKINAP.BUY_NOK, getChargeUtile());
            }
        }
        else
        {
            System.out.println("Erreur Socket serveur card null");
            rep = new ReponseCHECKINAP(ReponseCHECKINAP.BUY_NOK, getChargeUtile());
        }
        /*
        if(ser.CarteValide(carte))
        {
            //si ok verif qu'il y a assez de place
            if(PlaceDispo(nom))
            {
                rep = new ReponseCHECKINAP(ReponseCHECKINAP.BUY_OK, getChargeUtile());
            }
            else
            {
                System.out.println("Pas de place dispo !");
                rep = new ReponseCHECKINAP(ReponseCHECKINAP.BUY_NOK, getChargeUtile());
            }
        }
        else
        {
           System.out.println("Carte invalide : "); 
           rep = new ReponseCHECKINAP(ReponseCHECKINAP.BUY_NOK, getChargeUtile());
        }
        */
        ObjectOutputStream oos;
        try
        {
        oos = new ObjectOutputStream(s.getOutputStream());
        oos.writeObject(rep); oos.flush();
        oos.close();
        }
        catch (IOException e)
        {
        System.err.println("Erreur réseau ? [" + e.getMessage() + "]");
        }
    }
    private synchronized void traiteRequeteClose(Socket s, ConsoleServeur cs)
    {
        cs.TraceEvenements(s.getRemoteSocketAddress().toString() + "#CLOSE" + "#Thread");
        
        ReponseCHECKINAP rep = new ReponseCHECKINAP(ReponseCHECKINAP.CLOSE_OK, getChargeUtile());
        
        ObjectOutputStream oos;
        try
        {
        oos = new ObjectOutputStream(s.getOutputStream());
        oos.writeObject(rep); oos.flush();
        oos.close();
        }
        catch (IOException e)
        {
        System.err.println("Erreur réseau ? [" + e.getMessage() + "]");
        }
        
        System.exit(1);
    }
    private synchronized void traiteRequeteLogin(Socket s, ConsoleServeur cs)
    {
        // Affichage des informations
        String adresseDistante = s.getRemoteSocketAddress().toString();
        System.out.println("Début de traiteRequete : adresse distante = " + adresseDistante);
        System.out.println("Charge utile recue : " + chargeUtile);
        cs.TraceEvenements(s.getRemoteSocketAddress().toString() + "#LOGIN" + "#Thread");
        //Separation champs user#pass
        Vector infos = new Vector();
        String user;
        String pass;
        StringTokenizer parser = new StringTokenizer(chargeUtile, "#");
        while(parser.hasMoreTokens())
            infos.add(parser.nextToken());
        user = (String) infos.get(0);
        pass = (String) infos.get(1);
        System.out.println("Login : " + user + "Pass: " + pass);
        // Verification dans la BD -> Hashtable pour le moment
        ReponseCHECKINAP rep;
        if(LoginExiste(user, pass))
        {
            rep = new ReponseCHECKINAP(ReponseCHECKINAP.LOGIN_OK, getChargeUtile());
        }
        else
        {
            rep = new ReponseCHECKINAP(ReponseCHECKINAP.LOGIN_NOK, getChargeUtile());
        }
        // Construction d'une réponse
        
        ObjectOutputStream oos;
        try
        {
        oos = new ObjectOutputStream(s.getOutputStream());
        oos.writeObject(rep); oos.flush();
        //oos.close();
        }
        catch (IOException e)
        {
        System.err.println("Erreur réseau ? [" + e.getMessage() + "]");
        }
    }
    
    public String getChargeUtile() { return chargeUtile; }
    public void setChargeUtile(String chargeUtile)
    {
        this.chargeUtile = chargeUtile;
    } 

    private boolean LoginExiste(String user, String pass) {
        if(tableLogin.containsKey(user))
            if(tableLogin.get(user).equals(pass))
                return true;
        return false;
    }

    private boolean BookingExiste(String code) {
        MyInstruction sgbd;
        sgbd = new MyInstruction();
        System.out.println("id reservation : " + code);
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(ClassNotFoundException e)
        {
            JOptionPane.showMessageDialog(null, "driver introuvable", "Erreur", JOptionPane.ERROR_MESSAGE); 
        }
        System.out.println("Connexion bd_ferries OK");
        sgbd.setAdresse("jdbc:mysql://localhost:3306/BD_FERRIES");
        sgbd.setLogin("root");
        sgbd.setPassword("root");
        try
        {
            sgbd.Connexion();
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "connexion à la BD impossible", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        try
        {
            sgbd.SelectionCond("reservations", "identifiant LIKE '"+ code + "'");
            if(sgbd.getResultat().next())
            {
               if(sgbd.getResultat().getString("identifiant") != null)
                {
                    System.out.println("res bd : " + sgbd.getResultat().getString("identifiant"));
                    return true;
                }
            }
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "recup reservation erreur", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    private boolean PlaceDispo(String nom) {
        MyInstruction sgbd;
        sgbd = new MyInstruction();
        
        System.out.println("Place dispo : ");
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(ClassNotFoundException e)
        {
            JOptionPane.showMessageDialog(null, "driver introuvable", "Erreur", JOptionPane.ERROR_MESSAGE); 
        }
        System.out.println("Connexion bd_ferries OK");
        sgbd.setAdresse("jdbc:mysql://localhost:3306/BD_FERRIES");
        sgbd.setLogin("root");
        sgbd.setPassword("root");
        try
        {
            sgbd.Connexion();
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "connexion à la BD impossible", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        try
        {
            //recup identifiant de la prochaine traversee ==> destination ?
            
            //ajout du voyageur (conducteur) et des accompagnants
            
            // ajout de la reservation pour la traversee et le conducteur
            
            if(sgbd.BuyTicket(nom))
            {
                return true;
            }
        }
        catch(SQLException e)
        {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "erreur buy ticket", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    private int RecupPlaces(String code) {
        MyInstruction sgbd;
        sgbd = new MyInstruction();
        int places;
        System.out.println("Recup places : ");
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(ClassNotFoundException e)
        {
            JOptionPane.showMessageDialog(null, "driver introuvable", "Erreur", JOptionPane.ERROR_MESSAGE); 
        }
        System.out.println("Connexion bd_ferries OK");
        sgbd.setAdresse("jdbc:mysql://localhost:3306/BD_FERRIES");
        sgbd.setLogin("root");
        sgbd.setPassword("root");
        try
        {
            sgbd.Connexion();
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "connexion à la BD impossible", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        try
        {
            sgbd.SelectCountCond("reservations", "identifiant LIKE '" + code + "'");
            if(sgbd.getResultat().next())
            {
               if(sgbd.getResultat().getInt(1) != -1)
                {
                    System.out.println("places bd : " + sgbd.getResultat().getInt(1));
                    places = sgbd.getResultat().getInt(1);
                    return places;
                }
            }
        }
        catch(SQLException e)
        {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "recup reservation des places", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return -1;
    }

    private boolean CarteValide(String carte, Socket card_Socket) {
        
        //ConnexionServeurCarte();
        Envoyer(RequeteCHECKINAP.IS_CARTE_VALIDE, carte, card_Socket);
        ReponseCHECKINAP rep = null;
        rep = Recevoir(card_Socket);
        
        if(rep.GetCode() == ReponseCHECKINAP.CARTE_OK)
        {
            return true;
        }
        
        return false;
    }
    private void Envoyer(int code, String chargeUtile, Socket card_Socket) {
        RequeteCHECKINAP req = null; 
        req = new RequeteCHECKINAP(code, chargeUtile);
        
        // Envoi de la requête
        try
        {
            ObjectOutputStream oos = new ObjectOutputStream(card_Socket.getOutputStream());
            oos.writeObject(req); oos.flush();
        }
        catch (IOException e)
        { System.err.println("Erreur réseau ? [" + e.getMessage() + "]"); }
        
    }

    private ReponseCHECKINAP Recevoir(Socket card_Socket) {
        // Lecture de la réponse
        ReponseCHECKINAP rep = null;
        try
        {
            ObjectInputStream ois = new ObjectInputStream(card_Socket.getInputStream());
            rep = (ReponseCHECKINAP)ois.readObject();
            System.out.println(" *** Reponse reçue : " + rep.getChargeUtile());
        }
        catch (ClassNotFoundException e)
        { System.out.println("--- erreur sur la classe = " + e.getMessage()); }
        catch (IOException e)
        { System.out.println("--- erreur IO = " + e.getMessage()); }
        return rep;
    }
    private void ConnexionServeurCarte() {
        try {
            System.out.println("Try connexion au serveur carte");
            socketServeurCard = new Socket("0.0.0.0", 50055);
        } 
        catch (UnknownHostException e)
        { System.err.println("Erreur Serveur Cartes ! Host non trouvé [" + e + "]");
        }
        catch (IOException e)
        { System.err.println("Erreur Serveur Cartes ! Pas de connexion ? [" + e + "]");
        }
    }

    private RequeteCHECKINAP RecevoirRequete(Socket s) {
        RequeteCHECKINAP rep = null;
        System.out.println("Recv requete "); 
        if(s == null)
           System.out.println("socket null ");  
        try
        {
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            rep = (RequeteCHECKINAP)ois.readObject();
            System.out.println(" *** Reponse reçue : " + rep.getChargeUtile());
        }
        catch (ClassNotFoundException e)
        { System.out.println("--- erreur sur la classe = " + e.getMessage()); }
        catch (IOException e)
        { System.out.println("--- erreur IO = " + e.getMessage()); }
        return rep;
    }
}
