/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facturacion.entidades;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Julio Amadeo Avalos Urquiza
 */
public class Factura {
    private int idfactura;
    private int num_fact;
    private Date fecha;
    private BigDecimal total;
    private int idcliente;
    private Date fecha_sis;

    public Factura(int idfactura, int num_fact, Date fecha, BigDecimal total, int idcliente, Date fecha_sis) {
        this.idfactura = idfactura;
        this.num_fact = num_fact;
        this.fecha = fecha;
        this.total = total;
        this.idcliente = idcliente;
        this.fecha_sis = fecha_sis;
    }

    public int getIdfactura() {
        return idfactura;
    }

    public void setIdfactura(int idfactura) {
        this.idfactura = idfactura;
    }

    public int getNum_fact() {
        return num_fact;
    }

    public void setNum_fact(int num_fact) {
        this.num_fact = num_fact;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public Date getFecha_sis() {
        return fecha_sis;
    }

    public void setFecha_sis(Date fecha_sis) {
        this.fecha_sis = fecha_sis;
    }
    
    
}
