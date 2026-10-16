/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package me.fperez.appsencilla.model.persistencia;

import java.util.List;
import me.fperez.appsencilla.model.Cliente;

/**
 *
 * @author fperez
 */
public interface ClienteDAO {
    //Operacones CRUD (Creat, Read, Update, Delete)
    Cliente read(String pk); //pk Primary Key
    void create(Cliente cliente);
    void update(Cliente cliente);
    void delete(Cliente cliente);
    List<Cliente> list(); //getAllClientes
    
}
