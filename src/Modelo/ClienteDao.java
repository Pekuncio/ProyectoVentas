package Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ClienteDao {
    // atributos para iniciar la conexion a la base de datos
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    // Metodo para registrar clientes
    public boolean RegistrarCliente(Cliente cl) {
        String sql = "INSERT INTO clientes (dni,nombre,telefono,direccion,razon) values(?,?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, cl.getDni());
            ps.setString(2, cl.getNombre());
            ps.setInt(3, cl.getTelefono());
            ps.setString(4, cl.getDireccion());
            ps.setString(5, cl.getRazon());
            //Cuando queremos ejecutar una actualizacion de la aplicación utilizamos esta sentencia.
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }

        }
    }
    // Metodo para poder listar clientes.
    public List listarCliente(){
        List<Cliente> listaCl= new ArrayList();
        String sql = "Select * from clientes";
        try {
            con = cn.getConnection();
            ps=con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Cliente cl= new Cliente();
                cl.setId(rs.getInt("id"));
                cl.setDni(rs.getInt("dni"));
                cl.setNombre(rs.getString("nombre"));
                cl.setTelefono(rs.getInt("telefono"));
                cl.setDireccion(rs.getString("direccion"));
                cl.setRazon(rs.getString("razon"));
                listaCl.add(cl);
            }
        }catch(Exception e){
            System.out.println(e.toString());
        }
        return listaCl;
    }
    //Metodo para eliminar un cliente
    public boolean EliminarCliente(int id){
        //Atributo para hacer el delete de la base de datos
        String sql = "delete from clientes where id = ?";
        try { 
            //Conexion a la base de datos
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            //seleccion de atributo de la base de datos
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }finally {
            try {
                //Cierre de la conexion
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }
    }
    public boolean ModificarCliente(Cliente cl){
        String sql = "Update clientes Set dni=?, nombre =?, telefono=?, direccion=?, razon=? where id=?";
        try{
            ps= con.prepareStatement(sql);
            ps.setInt(1, cl.getDni());
            ps.setString(2, cl.getNombre());
            ps.setInt(3, cl.getTelefono());
            ps.setString(4,cl.getDireccion());
            ps.setString(5, cl.getRazon());
            ps.setInt(6, cl.getId());
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
}
