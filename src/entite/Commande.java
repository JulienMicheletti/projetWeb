package entite;

import dao.CommandeDAO;
import dao.UtilisateurDAO;

import javax.json.JsonObject;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Commande {
    private int id = -1;
    private Article article;
    private int quantite;

    public Commande(int id, Article article, int quantite) {
        this.id = id;
        this.article = article;
        this.quantite = quantite;
    }

    public Commande(int id) throws SQLException {
        ResultSet rs;
        rs = UtilisateurDAO.getInstance().setQuery("select * from commande where id = "+id);
        rs.next();
        this.id = rs.getInt(1);
        article = new Article(rs.getInt(3));
        quantite = rs.getInt(4);
    }

    public Commande(Article article, int quantite) {
        this.article = article;
        this.quantite = quantite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void addQuantite() throws SQLException {
        quantite++;
        article.dimStock();
        article.save();
    }

    public  void dimQuantite() throws SQLException {
        quantite--;
        article.addStock();
        article.save();
    }

    public void save(int userId) throws SQLException {
        if (id < 0) {
            CommandeDAO.getInstance().insertQuery("INSERT INTO `commande` (`utilisateur_ref_id`, `article_ref_id`, `quantite`) VALUES ('" + userId + "', '" + article.getId() + "', '" + quantite + "')");
            id = CommandeDAO.getInstance().lastID();
        } else {
            CommandeDAO.getInstance().insertQuery("UPDATE `commande` SET `utilisateur_ref_id` = '" + userId + "', `article_ref_id` = '" + article.getId() + "', `quantite` = '" + quantite + "' WHERE `commande`.`id` = " + id + ";");

        }
    }

    public void del() throws  SQLException {
        CommandeDAO.getInstance().del(id);
    }


}
