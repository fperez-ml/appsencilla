/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package me.fperez.appsencilla.model.persistencia;


import java.sql.SQLException;
import java.util.List;
import me.fperez.appsencilla.model.Cliente;

/**
 *
 * @author fperez
 */
public interface ClienteDAO {
    //Operacones CRUD (Creat, Read, Update, Delete)
    Cliente read(long id) throws SQLException;
    void create(Cliente cliente) throws SQLException;
    void update(long id, Cliente cliente) throws SQLException;
    void delete(long id) throws SQLException;
    List<Cliente> list() throws SQLException;
    
}
