package servlet;

import dao.ArticleDAO;
import dao.CommandeDAO;
import dao.UtilisateurDAO;
import entite.Article;
import entite.Utilisateur;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ModArticleServlet")
public class ModArticleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("mod");
        String article_id = request.getParameter("id_article");
        String article_nom = request.getParameter("article_nom");
        String article_prix = request.getParameter("article_prix");
        String article_qte = request.getParameter("article_qte");
        int id = Integer.parseInt(article_id);
        float prix = Float.parseFloat(article_prix);
        int qte = (int)Float.parseFloat(article_qte);
        try {
            if (action.equals("Supprimer")) {
                CommandeDAO.getInstance().del(id);
                ArticleDAO.getInstance().del(id);
                response.getWriter().write("Supprimer");
            } else {
                Article article = new Article(id);
                article.setNom(article_nom);
                article.setPrix(prix);
                article.setStock(qte);
                article.save();
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("Modifier");
            }
            response.setStatus(200);
        } catch (SQLException e) {
            response.getWriter().write(e.getMessage());
            response.setStatus(400);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
