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
    private String apellidos;
    private Date fechaAlta;
    private String provincia;

    public Cliente(String nombre, String apellidos, Date fechaAlta, String provincia) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaAlta = fechaAlta;
        this.provincia = provincia;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
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

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
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
            this.apellidos, 
            this.fechaAlta != null ? this.fechaAlta.toString() : "",
            this.provincia
        };
    }
    
}
