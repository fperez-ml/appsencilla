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

    private final String url;
    private final String user;
    private final String password;
    private final String dbName;
    private final String dbPort;
    private final String dbHostName;

    public ClienteDAOImp() {
        this("localhost", "3306", "java-db", "java-db", "java-db");
    }

    public ClienteDAOImp(String dbHostName, String dbPort, String dbName,
            String user, String password) {
        this.dbHostName = dbHostName;
        this.dbPort = dbPort;
        this.dbName = dbName;
        this.user = user;
        this.password = password;
        this.url = getURL();
    }

    private String getURL() {
        return String.format("jdbc:mariadb://%s:%s/%s", this.dbHostName,
                this.dbPort, this.dbName);
    }

    private void mostrarError(String operacion, SQLException e) {
        logger.log(Level.SEVERE, "Error " + operacion, e);
        JOptionPane.showMessageDialog(null,
                "No se pudo " + operacion + ". Consulte el registro para más detalles.",
                "Error de base de datos", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public Cliente read(String pk) {
        String sql = "SELECT nombre, apellidos, fechaAlta, provincia FROM cliente WHERE nombre = '"
                + pk + "'";
        try (Connection con = DriverManager.getConnection(this.url, this.user, this.password);
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                return new Cliente(rs.getString("nombre"), rs.getString("apellidos"),
                        new Date(rs.getDate("fechaAlta").getTime()), rs.getString("provincia"));
            }
        } catch (SQLException e) {
            mostrarError("leer el cliente", e);
            return null;
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
        try (Connection con = DriverManager.getConnection(this.url, this.user, this.password);
                Statement st = con.createStatement()) {
            st.executeUpdate(sql);
        } catch (SQLException e) {
            mostrarError("crear el cliente", e);
        }
    }

    @Override
    public void update(Cliente cliente) {
        String sql = "UPDATE cliente SET nombre = '" + cliente.getNombre() + "', apellidos = '"
                + cliente.getApellidos() + "', fechaAlta = '"
                + new java.sql.Date(cliente.getFechaAlta().getTime()) + "', provincia = '"
                + cliente.getProvincia() + "' WHERE nombre = '" + cliente.getNombre() + "'";
        try (Connection con = DriverManager.getConnection(this.url, this.user, this.password);
                Statement st = con.createStatement()) {
            st.executeUpdate(sql);
        } catch (SQLException e) {
            mostrarError("actualizar el cliente", e);
        }
    }

    @Override
    public void delete(Cliente cliente) {
        String sql = "DELETE FROM cliente WHERE nombre = '" + cliente.getNombre() + "'";
        try (Connection con = DriverManager.getConnection(this.url, this.user, this.password);
                Statement st = con.createStatement()) {
            st.executeUpdate(sql);
        } catch (SQLException e) {
            mostrarError("borrar el cliente", e);
        }
    }

    @Override
    public List<Cliente> list() {
        String sql = "SELECT nombre, apellidos, fechaAlta, provincia FROM cliente";
        List<Cliente> clientes = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(this.url, this.user, this.password);
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                clientes.add(new Cliente(rs.getString("nombre"), rs.getString("apellidos"),
                        new Date(rs.getDate("fechaAlta").getTime()), rs.getString("provincia")));
            }
        } catch (SQLException e) {
            mostrarError("listar los clientes", e);
            return new ArrayList<>();
        }
        return clientes;
    }
}
