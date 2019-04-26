package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDDConnexion {
    private static BDDConnexion instance = null;
    private Connection cnx;

    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/projetweb";
    private static String username = "root";
    private static String pwd = "root";

    private BDDConnexion(){
        try {
            // chargement du driver
            Class.forName(driver);
            cnx = DriverManager.getConnection(url,username, pwd);
//			Class.forName("com.mysql.jdbc.Driver");
//			cnx=DriverManager.getConnection("jdbc:mysql://localhost:3306/formation","root","");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getCnx() {
        return cnx;
    }

    public void closeCnx() throws SQLException{
        if(cnx!=null){
            cnx.close();
            instance=null;
        }
    }

    public static BDDConnexion getInstance(){
        if(instance == null)
            instance = new BDDConnexion();
        return instance;
    }
}