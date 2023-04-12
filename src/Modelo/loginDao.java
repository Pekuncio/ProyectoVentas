
package Modelo;
import java.sql.*;
public class loginDao{
    //Conexion a la base de datos
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();
    public login log(String correo, String pass){
        login l = new login();
        String sql = "Select * from usuarios where correo = ? and pass = ?";
        try{
            //Iniciar conexion a la base de datos
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            //Comprobar si los datos introducidos son los correctos.
            ps.setString(1, correo);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            if(rs.next()){
                l.setId(rs.getInt("id"));
                l.setNombre(rs.getString("nombre"));
                l.setCorreo(rs.getString("correo"));
                l.setPass(rs.getString("pass"));
                
                
                
            }
            
            
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return l;
    }
    
    
}
