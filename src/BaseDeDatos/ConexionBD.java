package BaseDeDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    
    private static String driver = "com.mysql.jdbc.Driver";
    private static String database = "u997594570_SGC";
    private static String hostname = "185.201.10.129";
    private static String port = "3306";
    private static String url = "jdbc:mysql://"+hostname+":"+port+"/"+database+"?allowPublicKeyRetrieval=true&useSSL=false";
    private static String username = "u997594570_pcs_admin";
    private static String password = "s?SZB1F|";
    
    public static Connection iniciarConexionMySQL(){
        
        Connection conn = null;
        
        try{
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
           
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return conn;
    }
    
    
}
