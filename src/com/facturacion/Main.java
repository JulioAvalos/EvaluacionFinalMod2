/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facturacion;

import com.facturacion.controllers.FacturaController;
import com.facturacion.dao.ClienteDao;
import com.facturacion.dao.FacturaDao;
import com.facturacion.views.FacturaView;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Julio Amadeo Avalos Urquiza
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainView principal = new MainView();
                FacturaView vista = new FacturaView();
                FacturaDao daof = new FacturaDao();
                ClienteDao daoc = new ClienteDao();
                FacturaController control = new FacturaController(vista, daof, daoc, principal);
                control.inicializar();
                principal.setVisible(true);
            }
        });
    }
}
