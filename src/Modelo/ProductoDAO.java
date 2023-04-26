package Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class ProductoDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public boolean RegistrarProducto(Producto prod) {
        String sql = "INSERT INTO Productos (codigo, nombre, proveedor,stock,precio) values(?,?,?,?,?)";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, (prod.getCodigo()));
            ps.setString(2, prod.getNombre());
            ps.setString(3, prod.getProveedor());
            ps.setInt(4, prod.getStock());
            ps.setDouble(5, prod.getPrecio());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;

        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }

    }
    //Para que nos salga en el boton de seleccion todos los proveedores registrados.
    public void ConsultarProveedor(JComboBox proveedor) {
        String sql = "Select nombre FROM proveedor";
        
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                proveedor.addItem(rs.getString("nombre"));
            }
        }catch(SQLException e){
                    System.out.println(e.toString());
        }finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }
    }
      public List listarProductos(){
        List<Producto> listaPro= new ArrayList();
        String sql = "Select * from productos";
        try {
            con = cn.getConnection();
            ps=con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Producto pro= new Producto();
                pro.setId(rs.getInt("id"));
                pro.setCodigo(rs.getString("codigo"));
                pro.setNombre(rs.getString("nombre"));
                pro.setProveedor(rs.getString("proveedor"));
                pro.setStock(rs.getInt("stock"));
                pro.setPrecio(rs.getDouble("precio"));
                listaPro.add(pro);
            }
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return listaPro;
    }
       public boolean EliminarPrducto(int id){
        //Atributo para hacer el delete de la base de datos
        String sql = "delete from productos where id = ?";
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
       public boolean ModificarProducto(Producto pro){
        String sql = "Update productos Set codigo=?, nombre=?, proveedor=?, stock=?, precio=? where id=?";
        try{
            ps= con.prepareStatement(sql);
            ps.setString(1, pro.getCodigo());
            ps.setString(2, pro.getNombre());
            ps.setString(3, pro.getProveedor());
            ps.setInt(4,pro.getStock());
            ps.setDouble(5, pro.getPrecio());
            ps.setInt(6, pro.getId());
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
       public Producto BuscarPro(String cod){
           Producto producto = new Producto();
           String sql = "SELECT * FROM productos where codigo = ?";
           
           try {
               con = cn.getConnection();
               ps = con.prepareStatement(sql);
               ps.setString(1, cod);
               rs = ps.executeQuery();
               if(rs.next()){
                   producto.setNombre(rs.getString("nombre"));
                   producto.setPrecio(rs.getDouble("precio"));
                   producto.setStock(rs.getInt("stock"));
               }
           } catch (SQLException e) {
               System.out.println(e.toString());
           }
           return producto;
       }
}
