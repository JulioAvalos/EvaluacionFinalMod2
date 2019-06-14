package com.facturacion.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private final String usuario;
    private final String clave;
    private final String servidor;
    private final String puerto;
    private final String base;
    private final String url;
    private Connection conn;

    public Conexion() {
        this.usuario = "root";
        this.clave = "admin";
        this.servidor = "localhost";
        this.puerto = "3306";
        this.base = "facturacion";
        this.url = "jdbc:mysql://" + servidor + ":" + puerto + "/" + base;
    }

    public Connection conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, usuario, clave);
            System.out.println("conectado a: " + base);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("error al conectar: " + e.getMessage());
        }
        return conn;
    }

    public void desconectar() {
        try {
            conn.close();
            System.out.println("conexion cerrada a: " + base);
        } catch (SQLException ex) {
            System.out.println("error al cerrar conexion");
        }
    }
}
