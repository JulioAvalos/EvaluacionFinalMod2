package com.facturacion.control;

import com.facturacion.entidades.Cliente;
import com.facturacion.modelo.daoFactura;
import com.facturacion.entidades.Producto;
import com.facturacion.modelo.daoCliente;
import com.facturacion.utilerias.Utilerias;
import com.facturacion.vistas.vFactura;
import evaluacionfinalmod2.MDIPrincipal;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Julio Amadeo Avalos Urquiza
 */
public class controlFactura {

    private vFactura vista;
    private MDIPrincipal mdi;
    private daoFactura daof;
    private daoCliente daoc;
    private DefaultTableModel model;
    private int swnuevo;

    public controlFactura(vFactura vista, daoFactura daof, daoCliente daoc, MDIPrincipal mdi) {
        this.vista = vista;
        this.daof = daof;
        this.daoc = daoc;
        this.mdi = mdi;
        model = new DefaultTableModel();
        vista.getTblProductos().setModel(model);
        model.addColumn("CANTIDAD");
        model.addColumn("DESCRIPCION");
        model.addColumn("PRECIO UNITARIO");
        model.addColumn("TOTAL");
    }

    public void inicializar() {
        mdi.getmItemNuevaFactura().addActionListener(e -> abrirForm());
        vista.getTblProductos().removeAll();
        vista.getTxtNumFactura().setText(String.valueOf(daof.consultarNum()));
        vista.getCmbProducto().addItemListener(e -> obtenerPrecio());
        vista.getBtnAceptar().addActionListener(e -> agregarProducto());
        vista.getBtnCalcular().addActionListener(e -> calculo());
        vista.getBtnEliminar().addActionListener(e -> eliminar());
        vista.getBtnFinalizar().addActionListener(e -> finalizar());
    }

    private void obtenerPrecio() {
        Producto p = (Producto) this.vista.getCmbProducto().getSelectedItem();
        if (p != null) {
            this.vista.getTxtPrecio().setText(p.getPrecio().toString());
        }
    }

    private void abrirForm() {
        Utilerias.abrirForm(this.vista);
        this.vista.getTxtFecha().setDate(new Date());
        llenarCmbCliente();
        llenarCmbProducto();
        if (this.model.getRowCount() > 0) {
            limpiarTabla();
        }

    }

    private void agregarProducto() {
        Producto prod = (Producto) this.vista.getCmbProducto().getSelectedItem();
        int cantidad = Integer.parseInt(this.vista.getTxtCantidad().getText());
        String descripcion = prod.getProducto();
        double precio = Double.parseDouble(prod.getPrecio().toString());
        model.addRow(new Object[]{cantidad, descripcion, precio, Double.valueOf(cantidad) * precio});
        this.calculo();
    }

    private void finalizar() {
        int r = this.daof.insertar(this.model,
                (Cliente) this.vista.getCmbCliente().getSelectedItem(),
                this.vista.getTxtFecha().getDate(),
                this.vista.getTxtNumFactura().getText(),
                new BigDecimal(this.vista.getTxtTotal().getText()));
        if (r > 0) {
            JOptionPane.showMessageDialog(vista, "Facturacion completa!",
                    "Aviso", JOptionPane.INFORMATION_MESSAGE);
            vista.getTxtNumFactura().setText(String.valueOf(daof.consultarNum()));
        } else {
            JOptionPane.showMessageDialog(vista, "error al facturar!",
                    "Aviso", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminar() {
        int id = vista.getTblProductos().getSelectedRow();
        int r = JOptionPane.showConfirmDialog(vista,
                "Desea eliminar el registro seleccionado?",
                "aviso", JOptionPane.YES_NO_OPTION);
        if (r == JOptionPane.YES_OPTION) {
            try {
                ((DefaultTableModel) vista.getTblProductos().getModel()).removeRow(id);
                JOptionPane.showMessageDialog(vista, "registro eliminado");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(vista, "Error no se pudo eliminar el registro, intente seleccionarlo de nuevo!");
            }
        }
        this.calculo();
    }

    private void calculo() {
        double sum = 0;
        int rows = model.getRowCount();
        for (int i = 0; i < rows; i++) {
            sum += (double) model.getValueAt(i, 3);
        }
        vista.getTxtTotal().setText(String.valueOf(sum));
    }

    public void llenarCmbCliente() {
        this.vista.getCmbCliente().removeAllItems();
        List<Cliente> listadoCliente = daoc.consultar("");
        for (Cliente c : listadoCliente) {
            this.vista.getCmbCliente().addItem(c);
        }
    }

    private void llenarCmbProducto() {
        this.vista.getCmbProducto().removeAllItems();
        List<Producto> listadoProductos = daof.consultarProductos();
        for (Producto p : listadoProductos) {
            this.vista.getCmbProducto().addItem(p);
        }
    }

    public void limpiarTabla() {
        int filas = vista.getTblProductos().getRowCount();
        for (int i = 0; i < filas; i++) {
            model.removeRow(0);
        }
    }
}