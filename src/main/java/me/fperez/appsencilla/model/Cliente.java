/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package me.fperez.appsencilla.model;

import java.util.Date;

/**
 *
 * @author fperez
 */
public class Cliente {
    private String nombre;
    private String appellidos;
    private Date fechaAlta;
    private String provincia;

    public Cliente(String nombre, String appellidos, Date fechaAlta, String provincia) {
        this.nombre = nombre;
        this.appellidos = appellidos;
        this.fechaAlta = fechaAlta;
        this.provincia = provincia;
    }

    public String getNombre() {
        return nombre;
    }

    public String getAppellidos() {
        return appellidos;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAppellidos(String appellidos) {
        this.appellidos = appellidos;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
    
    public String[] clienteToArray(){
    /*
       Devuelve un array para ser usado en el formulario principal
    */
        return new String[]{
            this.nombre, 
            this.appellidos, 
            this.fechaAlta.toString(), 
            this.provincia
        };
    }
    
}
