package dao;

import entite.Utilisateur;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UtilisateurDAO extends DAO{
    private static UtilisateurDAO instance = null;

    private UtilisateurDAO() {
        table = "utilisateur";
        cnx = BDDConnexion.getInstance().getCnx();
    }

    public static UtilisateurDAO getInstance(){
        if(instance == null)
            instance = new UtilisateurDAO();
        return instance;
    }

    public ArrayList<Utilisateur> getAll() throws SQLException {
        ResultSet rs = setQuery("SELECT * FROM utilisateur ORDER BY id");
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
}
