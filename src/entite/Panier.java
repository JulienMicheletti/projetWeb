package entite;

import dao.CommandeDAO;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.http.Cookie;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.SQLException;
import java.util.ArrayList;

public class Panier {
    private ArrayList<Commande> commandes;
    private int userId;

    public Panier(int userId) throws SQLException {
        this.userId = userId;
        commandes = CommandeDAO.getInstance().getCommandesByUser(userId);
    }

    public int getNbCom() {
        return commandes.size();
    }

    public ArrayList<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(ArrayList<Commande> commandes) {
        this.commandes = commandes;
    }

    public void addCommande(Article a) throws SQLException {
        if(a.getStock() > 0) {
            boolean exist = false;
            for (Commande com : commandes) {
                if (a.getId() == com.getArticle().getId()) {
                    com.addQuantite();
                    com.save(userId);
                    exist = true;
                }
            }
            if (!exist) {
                Commande c = new Commande(a, 1);
                c.save(userId);
                commandes.add(c);
            }
            a.dimStock();
            a.save();
        }
    }

    public void delCommande() throws SQLException {
        for (int i = 0; i < getNbCom(); i++) {
            commandes.get(i).dimAll();
            commandes.get(i).del();
            commandes.remove(i);
        }
    }

    public ArrayList<Cookie> toCookies() {
        ArrayList<Cookie> liste = new ArrayList<>();
        Writer writer = new StringWriter();
        //Import Librarry Maven: javax.json:javax.json-api:1.1.4

        for (Commande c : commandes) {
            JsonObjectBuilder jsonB = Json.createObjectBuilder();
            jsonB.add("idArticle", c.getArticle().getId());
            jsonB.add("nom", c.getArticle().getNom());
            jsonB.add("prixU", c.getArticle().getPrix());
            jsonB.add("quantite", c.getQuantite());

            JsonObject json = jsonB.build();

            Json.createWriter(writer).write(json);

            Cookie ck = new Cookie("" + c.getId(), writer.toString());
            liste.add(ck);
        }
        return liste;
    }
}
