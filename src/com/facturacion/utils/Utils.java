package com.facturacion.utils;

import com.facturacion.MainView;
import javax.swing.JInternalFrame;

public class Utils {
    public static void abrirForm(JInternalFrame jif){
        MainView.desktopPane.add(jif);
        centrarForm(jif);
        jif.setVisible(true);
    }
    
    private static void centrarForm(JInternalFrame jif){
        jif.setLocation(
                jif.getParent().getWidth()/2-jif.getWidth()/2, 
                jif.getParent().getHeight()/2-jif.getHeight()/2);
    }
}
