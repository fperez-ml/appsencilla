package me.fperez.appsencilla.model.persistencia.mariaDB;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import me.fperez.appsencilla.model.Cliente;
import me.fperez.appsencilla.model.persistencia.ClienteDAO;

public final class ClienteDAOImp implements ClienteDAO {

    private static final String URL = "jdbc:mariadb://localhost:3306/java-db";
    private static final String USER = "java-db";
    private static final String PASSWORD = "java-db";
    private static final ClienteDAOImp INSTANCE = new ClienteDAOImp();

    private ClienteDAOImp() {
    }

    public static ClienteDAOImp getInstance() {
        return INSTANCE;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    @Override
    public Cliente read(long id) throws SQLException {
        String sql = "SELECT Nombre AS nombre, Apellidos AS apellidos, "
                + "`Fecha de Alta` AS fecha_alta, Provincia AS provincia "
                + "FROM cliente WHERE ClienteID = ?";
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapCliente(resultSet);
                }
                return null;
            }
        }
    }

    @Override
    public void create(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO cliente (Nombre, Apellidos, `Fecha de Alta`, Provincia) "
                + "VALUES (?, ?, ?, ?)";
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            bindCliente(statement, cliente);
            statement.executeUpdate();
        }
    }

    @Override
    public void update(long id, Cliente cliente) throws SQLException {
        String sql = "UPDATE cliente SET Nombre = ?, Apellidos = ?, `Fecha de Alta` = ?, "
                + "Provincia = ? WHERE ClienteID = ?";
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            bindCliente(statement, cliente);
            statement.setLong(5, id);
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(long id) throws SQLException {
        String sql = "DELETE FROM cliente WHERE ClienteID = ?";
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }

    @Override
    public List<Cliente> list() throws SQLException {
        String sql = "SELECT Nombre AS nombre, Apellidos AS apellidos, "
                + "`Fecha de Alta` AS fecha_alta, Provincia AS provincia "
                + "FROM cliente ORDER BY ClienteID";
        List<Cliente> clientes = new ArrayList<>();
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                clientes.add(mapCliente(resultSet));
            }
        }
        return clientes;
    }

    private void bindCliente(PreparedStatement statement, Cliente cliente) throws SQLException {
        statement.setString(1, cliente.getNombre());
        statement.setString(2, cliente.getAppellidos());
        if (cliente.getFechaAlta() == null) {
            statement.setNull(3, Types.DATE);
        } else {
            statement.setDate(3, new Date(cliente.getFechaAlta().getTime()));
        }
        statement.setString(4, cliente.getProvincia());
    }

    private Cliente mapCliente(ResultSet resultSet) throws SQLException {
        Date fechaAlta = resultSet.getDate("fecha_alta");
        return new Cliente(
                resultSet.getString("nombre"),
                resultSet.getString("apellidos"),
                fechaAlta == null ? null : new java.util.Date(fechaAlta.getTime()),
                resultSet.getString("provincia"));
    }
}
