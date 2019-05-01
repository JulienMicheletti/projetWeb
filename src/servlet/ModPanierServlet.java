package servlet;

import dao.CommandeDAO;
import dao.UtilisateurDAO;
import entite.Article;
import entite.Commande;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.SQLException;

public class ModPanierServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commande_id = request.getParameter("id_commande");
        int id = Integer.parseInt(commande_id);
        int idUser = (int)request.getSession().getAttribute("id");

        Cookie[] panier = request.getCookies();
        boolean exist = false;
        Writer writer = new StringWriter();
        for (Cookie c : panier) {
            if (c.getName().equals(commande_id)) {
                JsonReader reader = Json.createReader(new StringReader(c.getValue()));
                JsonObject json = reader.readObject();
                JsonObjectBuilder jsonB = Json.createObjectBuilder(json);

                try {
                    Commande commande = new Commande(Integer.parseInt(c.getName()));
                    commande.dimQuantite();
                    if(commande.getQuantite() <= 0) {
                        commande.del();
                        c.setMaxAge(0);
                        response.addCookie(c);
                    }else {
                        commande.save(idUser);
                        jsonB.add("quantite", json.getInt("quantite") - 1);
                        json = jsonB.build();

                        Json.createWriter(writer).write(json);
                        response.addCookie(new Cookie(c.getName(), writer.toString()));
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                exist = true;
            }
        }
    }
}
