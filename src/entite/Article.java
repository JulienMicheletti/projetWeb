package entite;

import dao.ArticleDAO;
import dao.UtilisateurDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Article {
    private int id = -1;
    private String nom;
    private int stock;
    private float prix;

    public Article(int id, String nom, int stock, float prix) {
        this.id = id;
        this.nom = nom;
        this.stock = stock;
        this.prix = prix;
    }

    public Article(String nom, int stock, float prix) {
        this.nom = nom;
        this.stock = stock;
        this.prix = prix;
    }

    public Article(int idUser) throws SQLException {
        ResultSet rs;
        rs = UtilisateurDAO.getInstance().setQuery("select * from article where id = "+idUser);
        rs.next();
        id = rs.getInt(1);
        nom = rs.getString(2);
        stock = rs.getInt(3);
        prix = rs.getFloat(4);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void save() throws SQLException {
        if(id < 0) {
            ArticleDAO.getInstance().insertQuery("INSERT INTO `article` (`nom`, `stock`, `prix`) VALUES ('" + nom + "', '" + stock + "', '" + prix + "')");
            id = ArticleDAO.getInstance().lastID();
        } else
            ArticleDAO.getInstance().insertQuery("UPDATE `article` SET `nom` = '"+ nom +"', `stock` = '"+ stock +"', `prix` = '"+ prix +"' WHERE `article`.`id` = "+ id +";");
    }

    public void del() throws SQLException {
        UtilisateurDAO.getInstance().del(id);
    }
}
