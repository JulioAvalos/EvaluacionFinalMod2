package com.facturacion.utilerias;

import evaluacionfinalmod2.MDIPrincipal;
import javax.swing.JInternalFrame;

public class Utilerias {
    public static void abrirForm(JInternalFrame jif){
        MDIPrincipal.desktopPane.add(jif);
        centrarForm(jif);
        jif.setVisible(true);
    }
    
    private static void centrarForm(JInternalFrame jif){
        jif.setLocation(
                jif.getParent().getWidth()/2-jif.getWidth()/2, 
                jif.getParent().getHeight()/2-jif.getHeight()/2);
    }
}
