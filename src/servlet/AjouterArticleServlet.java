package servlet;

import dao.ArticleDAO;
import dao.CommandeDAO;
import dao.UtilisateurDAO;
import entite.Article;
import entite.Commande;
import entite.Panier;
import entite.Utilisateur;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AjouterArticleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id_article = request.getParameter("id_article");
        int id = Integer.parseInt(id_article);
        HttpSession session = request.getSession();
        int idUser = (int)session.getAttribute("id");
        Cookie[] panier = request.getCookies();
        boolean exist = false;
        Writer writer = new StringWriter();
        for (Cookie c : panier) {
            if (!c.getName().equals("JSESSIONID")) {
                JsonReader reader = Json.createReader(new StringReader(c.getValue()));
                JsonObject json = reader.readObject();
                JsonObjectBuilder jsonB = Json.createObjectBuilder(json);

                if(json.getInt("idArticle") == id) {
                    try {
                        Commande commande = new Commande(Integer.parseInt(c.getName()),new Article(id),json.getInt("quantite"));
                        commande.addQuantite();
                        commande.save(idUser);
                        jsonB.add("quantite",json.getInt("quantite")+1);
                        json = jsonB.build();

                        Json.createWriter(writer).write(json);
                        response.addCookie(new Cookie(c.getName(),writer.toString()));
                    } catch (SQLException e) {
                        request.setAttribute("erreur",e.getMessage());
                    }
                    exist = true;
                }
            }
        }

        if(!exist) {
            try {
                Commande c = new Commande(new Article(id),0);
                c.addQuantite();
                c.save(idUser);
                JsonObjectBuilder jsonB = Json.createObjectBuilder();
                Article article = new Article(id);
                jsonB.add("idArticle", id);
                jsonB.add("nom", article.getNom());
                jsonB.add("prixU", article.getPrix());
                jsonB.add("quantite", c.getQuantite());

                JsonObject json = jsonB.build();

                Json.createWriter(writer).write(json);

                response.addCookie(new Cookie("" + c.getId(), writer.toString()));
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        response.sendRedirect("articleClient");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
