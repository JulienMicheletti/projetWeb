package dao;

import entite.Article;
import entite.Commande;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommandeDAO extends DAO {
    private static CommandeDAO instance = null;

    private CommandeDAO() {
        table = "utilisateur";
        cnx = BDDConnexion.getInstance().getCnx();
    }

    public static CommandeDAO getInstance(){
        if(instance == null)
            instance = new CommandeDAO();
        return instance;
    }

    public ArrayList<Commande> getCommandesByUser(int userID) throws SQLException {
        ResultSet rs = setQuery("SELECT * FROM commande where id='"+userID+"' ORDER BY id");
        ArrayList<Commande> array = new ArrayList<>();
        while (rs.next()) {
            Article article = new Article(rs.getInt(2));
            array.add(new Commande(rs.getInt(1),article,rs.getInt(3)));
        }
        return array;
    }
}
