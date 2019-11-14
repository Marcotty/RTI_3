/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rti_2.client;

import rti_2.checkinap.ReponseCHECKINAP;
import static rti_2.checkinap.ReponseCHECKINAP.BOOKING_OK;
import static rti_2.checkinap.ReponseCHECKINAP.LOGIN_OK;
import rti_2.checkinap.RequeteCHECKINAP;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author fredm
 */
public class Applic_Checkin extends javax.swing.JFrame {

    /**
     * Creates new form Applic_Checkin
     */
    private ObjectInputStream ois;
    private ObjectOutputStream oos; 
    private Socket cliSocket;
    private String adresse;
    private int port;
    
    public Applic_Checkin() {
        initComponents();
        
        PanelCommandes.setVisible(false);
        PanelBook.setVisible(false);
        PanelBuy.setVisible(false);
    }
    public Applic_Checkin(Socket sock) {
        initComponents();
        cliSocket = sock;
        PanelCommandes.setVisible(false);
        PanelBook.setVisible(false);
        PanelBuy.setVisible(false);
    }
    public Applic_Checkin(Socket sock, String ad, int po) {
        initComponents();
        cliSocket = sock;
        PanelCommandes.setVisible(false);
        PanelBook.setVisible(false);
        PanelBuy.setVisible(false);
        adresse = ad;
        port = po;
        this.pack();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelLogin = new javax.swing.JPanel();
        TFUser = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        BLogin = new javax.swing.JButton();
        TFPass = new javax.swing.JTextField();
        LReponse = new javax.swing.JTextField();
        PanelCommandes = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        BBooking = new javax.swing.JButton();
        BBuy = new javax.swing.JButton();
        BClose = new javax.swing.JButton();
        PanelBook = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        TFCode = new javax.swing.JTextField();
        TFPassagersBook = new javax.swing.JTextField();
        BDemander = new javax.swing.JButton();
        PanelBuy = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        TFConducteur = new javax.swing.JTextField();
        TFImmatriculation = new javax.swing.JTextField();
        TFPassagersBuy = new javax.swing.JTextField();
        TFCarte = new javax.swing.JTextField();
        BEnvoyer = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        BLogout = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        TFUser.setText("a");
        TFUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFUserActionPerformed(evt);
            }
        });

        jLabel1.setText("user :");

        BLogin.setText("Login");
        BLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BLoginActionPerformed(evt);
            }
        });

        TFPass.setText("b");

        javax.swing.GroupLayout PanelLoginLayout = new javax.swing.GroupLayout(PanelLogin);
        PanelLogin.setLayout(PanelLoginLayout);
        PanelLoginLayout.setHorizontalGroup(
            PanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLoginLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelLoginLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BLogin))
                    .addGroup(PanelLoginLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(26, 26, 26)
                        .addGroup(PanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TFPass)
                            .addComponent(TFUser, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE))
                        .addGap(10, 10, 10)))
                .addContainerGap())
        );
        PanelLoginLayout.setVerticalGroup(
            PanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLoginLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(TFUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(TFPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BLogin)
                .addContainerGap())
        );

        jLabel2.setText("Commandes disponibles :");

        BBooking.setText("Verification réservation");
        BBooking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BBookingActionPerformed(evt);
            }
        });

        BBuy.setText("Acheter un ticket");
        BBuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BBuyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelCommandesLayout = new javax.swing.GroupLayout(PanelCommandes);
        PanelCommandes.setLayout(PanelCommandesLayout);
        PanelCommandesLayout.setHorizontalGroup(
            PanelCommandesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelCommandesLayout.createSequentialGroup()
                .addGroup(PanelCommandesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelCommandesLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel2))
                    .addGroup(PanelCommandesLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(BBooking))
                    .addGroup(PanelCommandesLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(BBuy)))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        PanelCommandesLayout.setVerticalGroup(
            PanelCommandesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelCommandesLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(BBooking)
                .addGap(18, 18, 18)
                .addComponent(BBuy)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        BClose.setText("Fermer Application");
        BClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BCloseActionPerformed(evt);
            }
        });

        jLabel3.setText("Code :");

        jLabel4.setText("Passagers :");

        TFCode.setText("20180915-RES01");

        BDemander.setText("Demander");
        BDemander.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDemanderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelBookLayout = new javax.swing.GroupLayout(PanelBook);
        PanelBook.setLayout(PanelBookLayout);
        PanelBookLayout.setHorizontalGroup(
            PanelBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelBookLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelBookLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TFPassagersBook, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelBookLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TFCode)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelBookLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BDemander)
                .addGap(42, 42, 42))
        );
        PanelBookLayout.setVerticalGroup(
            PanelBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelBookLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(TFCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(TFPassagersBook, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(BDemander)
                .addContainerGap(85, Short.MAX_VALUE))
        );

        jLabel5.setText("Conducteur :");

        jLabel6.setText("Immatricualtion :");

        jLabel7.setText("Passagers :");

        jLabel8.setText("Carte :");

        TFConducteur.setText("Bernard");

        TFImmatriculation.setText("CWF-123");

        TFPassagersBuy.setText("20");

        TFCarte.setText("5545-0550-0524-8256");

        BEnvoyer.setText("Envoyer");
        BEnvoyer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BEnvoyerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelBuyLayout = new javax.swing.GroupLayout(PanelBuy);
        PanelBuy.setLayout(PanelBuyLayout);
        PanelBuyLayout.setHorizontalGroup(
            PanelBuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelBuyLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(BEnvoyer))
            .addGroup(PanelBuyLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelBuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(PanelBuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TFPassagersBuy, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(TFImmatriculation, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(TFCarte)
                    .addComponent(TFConducteur)))
        );
        PanelBuyLayout.setVerticalGroup(
            PanelBuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelBuyLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(PanelBuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(TFConducteur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(PanelBuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(TFImmatriculation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelBuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(TFPassagersBuy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(PanelBuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(TFCarte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BEnvoyer))
        );

        jMenu1.setText("Menu");

        BLogout.setText("Logout");
        BLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BLogoutActionPerformed(evt);
            }
        });
        jMenu1.add(BLogout);
        jMenu1.add(jSeparator1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PanelLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PanelBook, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(PanelBuy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PanelCommandes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(LReponse)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BClose, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(PanelLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(PanelCommandes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelBuy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PanelBook, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BClose)
                    .addComponent(LReponse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BLoginActionPerformed
        // TODO add your handling code here:
        //PanelLogin.setVisible(false);
        RequeteLogin(TFUser.getText(), TFPass.getText());
    }//GEN-LAST:event_BLoginActionPerformed

    private void BLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BLogoutActionPerformed
        // TODO add your handling code here:
        LReponse.setText("Logout !");
        PanelLogin.setVisible(true);
    }//GEN-LAST:event_BLogoutActionPerformed

    private void BBookingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BBookingActionPerformed
        // TODO add your handling code here:
        RequeteBooking();
    }//GEN-LAST:event_BBookingActionPerformed

    private void BBuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BBuyActionPerformed
        // TODO add your handling code here:
        RequeteBuy();
    }//GEN-LAST:event_BBuyActionPerformed

    private void BCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BCloseActionPerformed
        // TODO add your handling code here:
        RequeteClose();
    }//GEN-LAST:event_BCloseActionPerformed

    private void BDemanderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BDemanderActionPerformed
        // TODO add your handling code here:
        Booking();
    }//GEN-LAST:event_BDemanderActionPerformed

    private void BEnvoyerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BEnvoyerActionPerformed
        // TODO add your handling code here:
        Buy();
    }//GEN-LAST:event_BEnvoyerActionPerformed

    private void TFUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFUserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFUserActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Applic_Checkin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Applic_Checkin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Applic_Checkin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Applic_Checkin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Applic_Checkin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BBooking;
    private javax.swing.JButton BBuy;
    private javax.swing.JButton BClose;
    private javax.swing.JButton BDemander;
    private javax.swing.JButton BEnvoyer;
    private javax.swing.JButton BLogin;
    private javax.swing.JMenuItem BLogout;
    private javax.swing.JTextField LReponse;
    private javax.swing.JPanel PanelBook;
    private javax.swing.JPanel PanelBuy;
    private javax.swing.JPanel PanelCommandes;
    private javax.swing.JPanel PanelLogin;
    private javax.swing.JTextField TFCarte;
    private javax.swing.JTextField TFCode;
    private javax.swing.JTextField TFConducteur;
    private javax.swing.JTextField TFImmatriculation;
    private javax.swing.JTextField TFPass;
    private javax.swing.JTextField TFPassagersBook;
    private javax.swing.JTextField TFPassagersBuy;
    private javax.swing.JTextField TFUser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    // End of variables declaration//GEN-END:variables

    private void RequeteLogin(String user, String password) {
        // Construction de la requête
        String chargeUtile = user;
        chargeUtile = chargeUtile + "#" + password;
        RequeteCHECKINAP req = null; 
        req = new RequeteCHECKINAP(RequeteCHECKINAP.LOGIN, chargeUtile);
        //EnvoyerRequete(RequeteCHECKINAP.LOGIN, chargeUtile);
        // Envoi de la requête
        System.out.println("CLIENT | Envoi Login");
        try
        {
            oos = new ObjectOutputStream(cliSocket.getOutputStream());
            oos.writeObject(req);// oos.flush();
        }
        catch (IOException e)
        { System.err.println("Erreur réseau ? [" + e.getMessage() + "]"); }
        // Lecture de la réponse
        //rep = RecevoirReponse();
        ReponseCHECKINAP rep = null;
        try
        {
            ois = new ObjectInputStream(cliSocket.getInputStream());
            rep = (ReponseCHECKINAP)ois.readObject();
            System.out.println(" *** Reponse reçue : " + rep.getChargeUtile());
        }
        catch (ClassNotFoundException e)
        { System.out.println("--- erreur sur la classe = " + e.getMessage()); }
        catch (IOException e)
        { System.out.println("--- erreur IO = " + e.getMessage()); }
        
        //LReponse.setText(rep.getChargeUtile()); 
        
        if(rep.GetCode() == LOGIN_OK)
        {
            System.out.println("Login ok !");
            LReponse.setText("Login accepté !");
            PanelLogin.setVisible(false);
            PanelCommandes.setVisible(true);
            this.pack();
        }
        else
        {
            System.out.println("Login not ok !");
        }
    }

    private void RequeteBooking() {
        //if(PanelBuy.isVisible())
        
        //PanelCommandes.setVisible(true);
        PanelBuy.setVisible(false);
        this.pack();
        PanelBook.setVisible(true);
        this.pack();
    }
    private void Booking()
    {
        //Connecter();
        String chargeUtile = TFCode.getText();// + "#" + TFPassagersBook.getText();
        //Envoyer(RequeteCHECKINAP.BOOKING, chargeUtile);
        RequeteCHECKINAP req = null; 
        req = new RequeteCHECKINAP(RequeteCHECKINAP.BOOKING, chargeUtile);
        System.out.println(" Try envoi requete bokk ");
        // Envoi de la requête
        try
        {
            oos = new ObjectOutputStream(cliSocket.getOutputStream());
            oos.writeObject(req); //oos.flush();
        }
        catch (IOException e)
        { System.err.println("Erreur réseau ? [" + e.getMessage() + "]"); }
        // Lecture de la réponse
        System.out.println(" *** Requete envoyee :" + req.getChargeUtile());
        ReponseCHECKINAP rep = null;
        try
        {
            ois = new ObjectInputStream(cliSocket.getInputStream());
            rep = (ReponseCHECKINAP)ois.readObject();
            System.out.println(" *** Reponse reçue : " + rep.getChargeUtile());
        }
        catch (ClassNotFoundException e)
        { System.out.println("--- erreur sur la classe = " + e.getMessage()); }
        catch (IOException e)
        { System.out.println("--- erreur IO = " + e.getMessage()); }
        LReponse.setText(rep.getChargeUtile()); 
        
        if(rep.GetCode() == ReponseCHECKINAP.BOOKING_OK)
        {
            LReponse.setText("Réservation réussie !");
            TFPassagersBook.setText(rep.getChargeUtile());
        }
        else
        {
            LReponse.setText("Réservation échouée !");
        }
    }
    private void RequeteBuy() {
       // if(PanelBook.isVisible())
        PanelBook.setVisible(false);
        PanelBuy.setVisible(true);
        this.pack();
    }

    private void RequeteClose() {
        //Connecter();
        
        String chargeUtile = "";
        ReponseCHECKINAP rep = null;
        
        Envoyer(RequeteCHECKINAP.CLOSE, chargeUtile);
        rep = Recevoir();
        
        if(rep.GetCode() == ReponseCHECKINAP.CLOSE_OK)
        {
            LReponse.setText("Close réussi !");
            this.dispose();
        }
        else
        {
            LReponse.setText("Close échoué !");
        }
    }
    private void Connecter()
    {
        try
        {
            cliSocket = new Socket(adresse, port);
            System.out.println(cliSocket.getInetAddress().toString());
        }
        catch (UnknownHostException e)
        { System.err.println("Erreur ! Host non trouvé [" + e + "]");
            LReponse.setText("Host non trouvé !\n");
        }
        catch (IOException e)
        { System.err.println("Erreur ! Pas de connexion ? [" + e + "]");
            LReponse.setText("Erreur ! Pas de connexion ?\n");
        } 
    }

    private void Buy() {
        //Connecter();
        String chargeUtile = TFConducteur.getText() + "#" + TFImmatriculation.getText() + "#" + TFPassagersBuy.getText() + "#" + TFCarte.getText();
        Envoyer(RequeteCHECKINAP.BUY, chargeUtile);
        
        ReponseCHECKINAP rep = null;
        rep = Recevoir();
        
        if(rep.GetCode() == ReponseCHECKINAP.BUY_OK)
        {
            LReponse.setText("Achat réussi !");
        }
        else if(rep.GetCode() == ReponseCHECKINAP.BUY_NOK)
        {
            LReponse.setText("Achat échoué !");
        }
    }

    private void Envoyer(int code, String chargeUtile) {
        RequeteCHECKINAP req = null; 
        req = new RequeteCHECKINAP(code, chargeUtile);
        
        // Envoi de la requête
        try
        {
            oos = new ObjectOutputStream(cliSocket.getOutputStream());
            oos.writeObject(req); //oos.flush();
        }
        catch (IOException e)
        { System.err.println("Erreur réseau ? [" + e.getMessage() + "]"); }
        
    }

    private ReponseCHECKINAP Recevoir() {
        // Lecture de la réponse
        ReponseCHECKINAP rep = null;
        try
        {
            ois = new ObjectInputStream(cliSocket.getInputStream());
            rep = (ReponseCHECKINAP)ois.readObject();
            System.out.println(" *** Reponse reçue : " + rep.getChargeUtile());
        }
        catch (ClassNotFoundException e)
        { System.out.println("--- erreur sur la classe = " + e.getMessage()); }
        catch (IOException e)
        { System.out.println("--- erreur IO = " + e.getMessage()); }
        LReponse.setText(rep.getChargeUtile());
        return rep;
    }
}
