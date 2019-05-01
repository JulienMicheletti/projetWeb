package servlet;

import dao.ArticleDAO;
import dao.CommandeDAO;
import dao.UtilisateurDAO;
import entite.Article;
import entite.Commande;
import entite.Panier;
import entite.Utilisateur;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AjouterArticleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id_article = request.getParameter("id_article");
        int id = Integer.parseInt(id_article);
        HttpSession session = request.getSession();
        int idUser = (int)session.getAttribute("id");
        ArticleDAO articleDAO = ArticleDAO.getInstance();
        CommandeDAO commandeDAO = CommandeDAO.getInstance();
        try {
            Article article = articleDAO.getArticle(id);
            commandeDAO.insertCommandes(idUser, article);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
