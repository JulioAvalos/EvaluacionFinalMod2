package com.facturacion.entidades;
import java.math.BigDecimal;

/**
 *
 * @author Julio Amadeo Avalos Urquiza
 */
public class Producto {
    private int idproducto;
    private int idmarca;
    private String producto;
    private int existencias;
    private BigDecimal precio;

    public Producto(int idproducto, int idmarca, String producto, int existencias, BigDecimal precio) {
        this.idproducto = idproducto;
        this.idmarca = idmarca;
        this.producto = producto;
        this.existencias = existencias;
        this.precio = precio;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public int getIdmarca() {
        return idmarca;
    }

    public void setIdmarca(int idmarca) {
        this.idmarca = idmarca;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getExistencias() {
        return existencias;
    }

    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return producto; //To change body of generated methods, choose Tools | Templates.
    }
    
}
