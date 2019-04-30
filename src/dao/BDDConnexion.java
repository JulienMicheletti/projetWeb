package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class BDDConnexion {
    private static BDDConnexion instance = null;
    private Connection cnx;

    private BDDConnexion(){
        try {
            Properties p = new Properties();
            p.load(Thread.currentThread().getContextClassLoader().
                    getResourceAsStream("confBDD.properties"));

            // chargement du driver
            Class.forName(p.getProperty("driver"));
            cnx = DriverManager.getConnection(p.getProperty("url"),
                    p.getProperty("user"), p.getProperty("pwd"));
//			Class.forName("com.mysql.jdbc.Driver");
//			cnx=DriverManager.getConnection("jdbc:mysql://localhost:3306/formation","root","");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
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