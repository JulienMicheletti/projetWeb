package dao;

import entite.Article;
import entite.Commande;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommandeDAO extends DAO {
    private static CommandeDAO instance = null;

    private CommandeDAO() {
        table = "commande";
        cnx = BDDConnexion.getInstance().getCnx();
    }

    public static CommandeDAO getInstance(){
        if(instance == null)
            instance = new CommandeDAO();
        return instance;
    }

    public ArrayList<Commande> getCommandesByUser(int userID) throws SQLException {
        ResultSet rs = setQuery("SELECT * FROM commande where utilisateur_ref_id='"+userID+"' ORDER BY id");
        ArrayList<Commande> array = new ArrayList<>();
        while (rs.next()) {
            Article article = new Article(rs.getInt(3));
            array.add(new Commande(rs.getInt(1),article,rs.getInt(4)));
        }
        return array;
    }

    public int getMaxId() throws SQLException {
        ResultSet rs = setQuery("SELECT MAX(id) FROM commande");
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }
    
    public void insertCommandes(int userID, Article article) throws SQLException {
        int taille = getMaxId()+1;
        insertQuery("INSERT INTO commande VALUES("+taille+", "+userID+", "+article.getId()+", "+article.getStock()+")");
    }
}
