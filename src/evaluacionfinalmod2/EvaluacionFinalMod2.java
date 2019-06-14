/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evaluacionfinalmod2;

import com.facturacion.control.controlFactura;
import com.facturacion.modelo.daoCliente;
import com.facturacion.modelo.daoFactura;
import com.facturacion.vistas.vFactura;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Julio Amadeo Avalos Urquiza
 */
public class EvaluacionFinalMod2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EvaluacionFinalMod2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(EvaluacionFinalMod2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(EvaluacionFinalMod2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(EvaluacionFinalMod2.class.getName()).log(Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MDIPrincipal principal = new MDIPrincipal();
                vFactura vista = new vFactura();
                daoFactura daof = new daoFactura();
                daoCliente daoc = new daoCliente();
                controlFactura control = new controlFactura(vista, daof, daoc, principal);
                control.inicializar();
                principal.setVisible(true);
            }
        });
    }
}
