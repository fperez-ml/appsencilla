/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package me.fperez.appsencilla.model.persistencia.mariaDB;

/**
 *
 * @author fperez
 */
public class ClienteDAOImp {
    private final String url = "";
    private final String user = "";
    private final String password = "";
    private final String dbName = "";
    private final String dbPort = "";
    private final String dbHostName = "";

    public ClienteDAOImp() {
    }
    
    private String getURL(){
        String url = "";
        //URL de ejemplo sin usuario ni contraseña
        //jdbc:mariadb://localhost:3306/DB"
        url = String.format("jdbc:mariadb://%s:%s/%s", this.dbHostName, 
                this.dbPort, this.dbName);
        return url;
    }
    
}
