package dao;

import entite.Article;
import entite.Utilisateur;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ArticleDAO extends DAO{
    private static ArticleDAO instance = null;

    private ArticleDAO() {
        table = "article";
        cnx = BDDConnexion.getInstance().getCnx();
    }

    public static ArticleDAO getInstance(){
        if(instance == null)
            instance = new ArticleDAO();
        return instance;
    }

    public ArrayList<Article> getAllOrderBy(String orderBy) throws SQLException {
        ResultSet rs = setQuery("SELECT * FROM article ORDER BY "+orderBy);
        ArrayList<Article> array = new ArrayList<>();
        while (rs.next()) {
            array.add(new Article(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getFloat(4)));
        }
        return array;
    }
}
