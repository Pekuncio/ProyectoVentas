
package Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ProveedorDAO {
        Conexion cn = new Conexion();
        Connection con;
        PreparedStatement ps;
        ResultSet rs;
    public boolean RegistrarProveedor(Proveedor pr){
        String sql= "INSERT INTO proveedor (ruc, nombre, telefono,direccion,razon) values(?,?,?,?,?)";
        
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, pr.getRuc());
            ps.setString(2, pr.getNombre());
            ps.setInt(3, pr.getTelefono());
            ps.setString(4, pr.getDireccion());
            ps.setString(5, pr.getRazon());
            ps.executeUpdate();
            return true;
        }catch(SQLException e){
            System.out.println(e.toString());
            return false;
            
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }
        
    }
    public List listarProveedor(){
        List<Proveedor> listaPR= new ArrayList();
        String sql = "Select * from proveedor";
        try {
            con = cn.getConnection();
            ps=con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Proveedor pr= new Proveedor();
                pr.setId(rs.getInt("id"));
                pr.setRuc(rs.getInt("ruc"));
                pr.setNombre(rs.getString("nombre"));
                pr.setTelefono(rs.getInt("telefono"));
                pr.setDireccion(rs.getString("direccion"));
                pr.setRazon(rs.getString("razon"));
                listaPR.add(pr);
            }
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return listaPR;
    }
    public boolean EliminarProveedor(int id){
            try {
                String sql ="DELETE FROM proveedor WHERE id=?";
                con = cn.getConnection();
                ps=con.prepareStatement(sql);
                ps.setInt(1, id);
                ps.executeUpdate();
                return true;
            } catch (SQLException ex) {
                System.out.println(ex.toString());
                return false;
            }finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    System.out.println(ex.toString());
                }
            }
    }
    public boolean modificarProveedor(Proveedor pr){
            try {
                String sql = "UPDATE proveedor SET ruc=?, nombre=?,telefono=?,direccion=?,razon=? WHERE id=?";
                con = cn.getConnection();
                ps=con.prepareStatement(sql);
                ps.setInt(1, pr.getRuc());
                ps.setString(2, pr.getNombre());
                ps.setInt(3, pr.getTelefono());
                ps.setString(4, pr.getDireccion());
                ps.setString(5, pr.getRazon());
                ps.setInt(6, pr.getId());
                ps.executeUpdate();
                return true;
            } catch (SQLException ex) {
                System.out.println(ex.toString());
                return false;
            }finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    System.out.println(ex.toString());
                }
                
            }
          
    }
}
