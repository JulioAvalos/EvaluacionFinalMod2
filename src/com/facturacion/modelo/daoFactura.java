/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facturacion.modelo;

import com.facturacion.entidades.Cliente;
import com.facturacion.entidades.Factura;
import com.facturacion.entidades.Producto;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Julio Amadeo Avalos Urquiza
 */
public class daoFactura {

    private final Conexion conexion;

    public daoFactura() {
        conexion = new Conexion();
    }

    public int insertar(DefaultTableModel modelo, Cliente c, Date fecha, String num_factura, BigDecimal total) {
        int valorRetorno;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String test = dateFormat.format(date);
        try {
            Connection con = conexion.conectar();

            con.setAutoCommit(false);

            PreparedStatement pst = con.prepareStatement(
                    "INSERT INTO factura (idfactura, num_fact, fecha, total, idcliente)"
                    + " values(?,?,?,?,?)");
            pst.setInt(1, Integer.valueOf(num_factura));
            pst.setInt(2, Integer.valueOf(num_factura));
            pst.setString(3, df.format(fecha));
            pst.setBigDecimal(4, total);
            pst.setInt(5, c.getIdcliente());
            valorRetorno = pst.executeUpdate();

            pst = con.prepareStatement(
                    "INSERT INTO factura_producto (idfactura_producto, cantidad, precio, idfactura, idproducto) "
                    + " values(?,?,?,?,?)");

            for (int i = 0; i < modelo.getRowCount(); i++) {
                pst.setInt(1, this.consultarNumFacturaProducto() + i);
                pst.setInt(2, (int) modelo.getValueAt(i, 0));
                pst.setDouble(3, (double) modelo.getValueAt(i, 2));
                pst.setInt(4, Integer.valueOf(num_factura));
                pst.setInt(5, this.consultarProductoId((String) modelo.getValueAt(i, 1)));
                valorRetorno = pst.executeUpdate();
            }
            con.commit();
            pst.close();
            conexion.desconectar();
        } catch (SQLException e) {
            return e.getErrorCode() * -1;
        }
        return valorRetorno;
    }

    public int consultarProductoId(String descripcion) {
        int idProducto = 0;
        try {
            Connection con = conexion.conectar();
            String sql = "select idProducto from producto where producto='" + descripcion + "'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                idProducto = rs.getInt("idproducto");
            }
            st.close();
            conexion.desconectar();
        } catch (Exception e) {
            return 0;
        }

        return idProducto;
    }

    public int consultarNum() {
        int maximo = 0;
        try {
            Connection con = conexion.conectar();
            String sql = "select MAX(idfactura) as maximo from factura ";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                maximo = rs.getInt("maximo");
            }
            st.close();
            maximo++;
            conexion.desconectar();
        } catch (Exception e) {
            return 0;
        }

        return maximo;
    }

    public int consultarNumFacturaProducto() {
        int maximo = 0;
        try {
            Connection con = conexion.conectar();
            String sql = "select MAX(idfactura_producto) as maximo from factura_producto ";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                maximo = rs.getInt("maximo");
            }
            st.close();
            maximo++;
            conexion.desconectar();
        } catch (Exception e) {
            return 0;
        }

        return maximo;
    }

    public List<Producto> consultarProductos() {
        List<Producto> listado = new ArrayList<>();
        try {
            Connection con = conexion.conectar();
            String sql = "select * from Producto ";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Producto r = new Producto(
                        rs.getInt("idproducto"),
                        rs.getInt("idmarca"),
                        rs.getString("producto"),
                        rs.getInt("existencias"),
                        rs.getBigDecimal("precio")
                );
                listado.add(r);
            }
            st.close();
            conexion.desconectar();
        } catch (Exception e) {
            return null;
        }
        return listado;
    }
}
