package com.facturacion.modelo;

import com.facturacion.entidades.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class daoCliente {
    private final Conexion conexion;

    public daoCliente() {
        conexion = new Conexion();
    }
    
    public int insertar(Cliente cliente){
        int valorRetorno;
        try {
            Connection con = conexion.conectar();
            PreparedStatement pst = con.prepareStatement(
                    "insert into cliente(nombres,apellidos,telefono)"
                            + " values(?,?,?)");
            pst.setString(1, cliente.getNombres());
            pst.setString(2, cliente.getApellidos());
            pst.setString(3, cliente.getTelefono());
            valorRetorno = pst.executeUpdate();
            pst.close();
            conexion.desconectar();
        } catch (SQLException e) {
            return e.getErrorCode()*-1;
        }
        return valorRetorno;
    }
    public List<Cliente> consultar(String palabrasBusqueda){
        List<Cliente> listado = new ArrayList<>();
        try {
            Connection con = conexion.conectar();
            String sql ="select * from cliente "
                    + " where nombres like '%"+palabrasBusqueda+"%'";
            
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Cliente c = new Cliente(rs.getInt("idcliente"), 
                        rs.getString("nombres"), 
                        rs.getString("apellidos"), 
                        rs.getString("telefono"));
                listado.add(c);
            }
            st.close();
            conexion.desconectar();
        } catch (Exception e) {
            return null;
        }
        return listado;
    }
    public int eliminar(Cliente cliente){
        int valorRetorno;
        try {
            Connection con = conexion.conectar();
            PreparedStatement pst = con.prepareStatement(
                    "delete from cliente where idcliente=?");
            pst.setInt(1, cliente.getIdcliente());
            valorRetorno = pst.executeUpdate();
            pst.close();
            conexion.desconectar();
        } catch (SQLException e) {
            return e.getErrorCode()*-1;
        }
        return valorRetorno;
    }
    public int actualizar(Cliente cliente){
        int valorRetorno;
        try {
            Connection con = conexion.conectar();
            PreparedStatement pst = con.prepareStatement(
                    "update cliente set"
             + " nombres=?,apellidos=?,telefono=? where idcliente=?");
            pst.setString(1, cliente.getNombres());
            pst.setString(2, cliente.getApellidos());
            pst.setString(3, cliente.getTelefono());
            pst.setInt(4, cliente.getIdcliente());
            valorRetorno = pst.executeUpdate();
            pst.close();
            conexion.desconectar();
        } catch (SQLException e) {
            return e.getErrorCode()*-1;
        }
        return valorRetorno;
    }
}