/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author PekuPC
 */
public class login {
    //atributos de la clase login
    private int id;
    private String nombre;
    private String correo;
    private String pass;
    // metodo sin constructor de la clase login
    public login() {
    }
    // metodo constructor con todos los atributos de la clase login
    public login(int id, String nombre, String correo, String pass) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.pass = pass;
    }
    // metodos getter y setter de la clase login
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
