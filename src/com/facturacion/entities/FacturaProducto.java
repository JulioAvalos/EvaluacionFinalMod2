package com.facturacion.entities;

import java.math.BigDecimal;

/**
 *
 * @author Julio Amadeo Avalos
 */
public class FacturaProducto {
    private int idfactura_producto;
    private int cantidad;
    private BigDecimal precio;
    private int idfactura;
    private int idproducto;

    public FacturaProducto(int idfactura_producto, int cantidad, BigDecimal precio, int idfactura, int idproducto) {
        this.idfactura_producto = idfactura_producto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.idfactura = idfactura;
        this.idproducto = idproducto;
    }

    public int getIdfactura_producto() {
        return idfactura_producto;
    }

    public void setIdfactura_producto(int idfactura_producto) {
        this.idfactura_producto = idfactura_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public int getIdfactura() {
        return idfactura;
    }

    public void setIdfactura(int idfactura) {
        this.idfactura = idfactura;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }
    
    
    
}
