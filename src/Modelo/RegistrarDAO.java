/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.sql.*;
/**
 *
 * @author PekuPC
 */
public class RegistrarDAO {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();
    Registrar re = new Registrar();
    public boolean reg(String correo, String pass, String nombre){
        
        String sql = "INSERT INTO usuarios (nombre, correo, pass) values (?,?,?)";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, re.getNombre());
            ps.setString(2, re.getCorreo());
            ps.setString(3, re.getPass());
            ps.executeUpdate();
            return true;
         
            
            
        }catch(SQLException e){
            System.out.println(e.toString());
            return false;
            
        }finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }
    }
}
