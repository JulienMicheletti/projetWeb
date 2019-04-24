package dao;

import java.sql.Connection;

public class ArticleDAO {
    private Connection cnx;
    private static ArticleDAO instance = null;

    private ArticleDAO() {
        cnx = BDDConnexion.getInstance().getCnx();
    }

    public static ArticleDAO getInstance(){
        if(instance == null)
            instance = new ArticleDAO();
        return instance;
    }
}
