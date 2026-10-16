/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package me.fperez.appsencilla.model.persistencia.mariaDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import me.fperez.appsencilla.model.Cliente;
import me.fperez.appsencilla.model.persistencia.ClienteDAO;

/**
 *
 * @author fperez
 */
public class ClienteDAOImp implements ClienteDAO {

    private static final Logger logger = Logger.getLogger(ClienteDAOImp.class.getName());
    private static ClienteDAOImp clientDAO = null;

    private final String url;
    private final String user;
    private final String password;
    private final String dbName;
    private final String dbPort;
    private final String dbHostName;

    private ClienteDAOImp() {
        this.dbHostName = "localhost";
        this.dbPort = "3306";
        this.dbName = "java-db";
        this.user = "java-db";
        this.password = "java-db";
        this.url = getURL();
    }

    public static ClienteDAOImp getClienteDAO() {
        
        if(clientDAO == null){
            clientDAO = new ClienteDAOImp();
        }
        
        return clientDAO;

    }

    private String getURL() {
        return String.format("jdbc:mariadb://%s:%s/%s", this.dbHostName,
                this.dbPort, this.dbName);
    }

    private Connection getConnection() {
        try {
            return DriverManager.getConnection(this.url, this.user, this.password);
        } catch (SQLException e) {
            showErrorMessage("Error conectando con la base de datos: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Cliente read(String pk) {
        String sql = "SELECT nombre, apellidos, fechaAlta, provincia FROM cliente WHERE nombre = '"
                + pk + "'";
        try (Connection con = getConnection()) {
            if (con == null) {
                return null;
            }
            try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
                if (rs.next()) {
                    return new Cliente(rs.getString("nombre"), rs.getString("apellidos"),
                            new Date(rs.getDate("fechaAlta").getTime()), rs.getString("provincia"));
                }
            }
        } catch (SQLException e) {
            showErrorMessage("Error leyendo el cliente: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void create(Cliente cliente) {
        String sql = "INSERT INTO cliente (nombre, apellidos, fechaAlta, provincia) VALUES ('"
                + cliente.getNombre() + "', '"
                + cliente.getApellidos() + "', '"
                + new java.sql.Date(cliente.getFechaAlta().getTime()) + "', '"
                + cliente.getProvincia() + "')";
        try (Connection con = getConnection()) {
            if (con == null) {
                return;
            }
            try (Statement st = con.createStatement()) {
                st.executeUpdate(sql);
            }
        } catch (SQLException e) {
            showErrorMessage("Error creando el cliente: " + e.getMessage());
        }
    }

    @Override
    public void update(Cliente cliente) {
        String sql = "UPDATE cliente SET nombre = '" + cliente.getNombre() + "', apellidos = '"
                + cliente.getApellidos() + "', fechaAlta = '"
                + new java.sql.Date(cliente.getFechaAlta().getTime()) + "', provincia = '"
                + cliente.getProvincia() + "' WHERE nombre = '" + cliente.getNombre() + "'";
        try (Connection con = getConnection()) {
            if (con == null) {
                return;
            }
            try (Statement st = con.createStatement()) {
                st.executeUpdate(sql);
            }
        } catch (SQLException e) {
            showErrorMessage("Error actualizando el cliente: " + e.getMessage());
        }
    }

    @Override
    public void delete(Cliente cliente) {
        String sql = "DELETE FROM cliente WHERE nombre = '" + cliente.getNombre() + "'";
        try (Connection con = getConnection()) {
            if (con == null) {
                return;
            }
            try (Statement st = con.createStatement()) {
                st.executeUpdate(sql);
            }
        } catch (SQLException e) {
            showErrorMessage("Error borrando el cliente: " + e.getMessage());
        }
    }

    @Override
    public List<Cliente> list() {
        String sql = "SELECT nombre, apellidos, fechaAlta, provincia FROM cliente";
        List<Cliente> clientes = new ArrayList<>();
        try (Connection con = getConnection()) {
            if (con == null) {
                return clientes;
            }
            try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
                while (rs.next()) {
                    clientes.add(new Cliente(rs.getString("nombre"), rs.getString("apellidos"),
                            new Date(rs.getDate("fechaAlta").getTime()), rs.getString("provincia")));
                }
            }
        } catch (SQLException e) {
            showErrorMessage("Error listando los clientes: " + e.getMessage());
        }
        return clientes;
    }

    private void showErrorMessage(String errorMessage) {
        logger.log(Level.SEVERE, errorMessage);
        JOptionPane.showMessageDialog(null, errorMessage, "Error de base de datos",
                JOptionPane.ERROR_MESSAGE);
    }
}
