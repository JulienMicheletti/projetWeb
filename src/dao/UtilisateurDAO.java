package dao;

import entite.Utilisateur;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UtilisateurDAO {
    private Connection cnx;
    private static UtilisateurDAO instance = null;

    private UtilisateurDAO() {
        cnx = BDDConnexion.getInstance().getCnx();
    }

    public static UtilisateurDAO getInstance(){
        if(instance == null)
            instance = new UtilisateurDAO();
        return instance;
    }

    public ResultSet setQuery(String query) throws SQLException {
        Statement stmt = cnx.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        return rs;
    }

    public void insertQuery(String query) throws SQLException {
        Statement stmt = cnx.createStatement();
        stmt.executeUpdate(query);
    }

    public void delUtilisateur(int id) throws SQLException {
        Statement stmt = cnx.createStatement();
        stmt.executeUpdate("DELETE FROM `utilisateur` WHERE `utilisateur`.`id` = "+id);
    }

    public ArrayList<Utilisateur> getAllUser() throws SQLException {
        ResultSet rs = setQuery("SELECT * FROM utilisateurs ORDER BY id");
        ArrayList<Utilisateur> array = new ArrayList<>();
        while (rs.next()) {
            array.add(new Utilisateur(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5), rs.getInt(6)));
        }
        return array;
    }

    public Utilisateur identification(String username, String password) throws SQLException {
        ResultSet rs = setQuery("SELECT * FROM utilisateur WHERE Pseudo='"+username+"' and Mdp='"+password+"'");
        if (!rs.next())
            return null;
        return new Utilisateur(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5), rs.getInt(6));
    }

    public boolean existPseudo(String pseudo) throws SQLException {
        ResultSet rs = setQuery("SELECT * FROM utilisateur where pseudo='"+ pseudo+"'");
        if(rs.next())
            return true;
        return false;
    }

    public int lastID() throws SQLException {
        ResultSet rs = setQuery("SELECT last_insert_id()");
        rs.next();
        return rs.getInt(1);
    }
}
