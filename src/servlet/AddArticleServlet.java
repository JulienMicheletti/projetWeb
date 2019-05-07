package servlet;

import entite.Article;
import entite.Utilisateur;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddArticleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("article_nom");
        String qte = request.getParameter("article_qte");
        String prix = request.getParameter("article_prix");
        int article_qte = Integer.parseInt(qte);
        float article_prix = Float.parseFloat(prix);
        Article article = new Article(nom, article_qte, article_prix);
        ArrayList<String> erreurs = new ArrayList<>();
        try {
            article.save();
        } catch (SQLException e) {
            erreurs.add("Erreur :  "+e.getMessage());
        }
        request.setAttribute("erreurs",erreurs);
        request.getRequestDispatcher("adminArticles").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("adminArticles");
    }
}
