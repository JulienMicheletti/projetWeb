package servlet;

import dao.ArticleDAO;
import dao.UtilisateurDAO;
import entite.Article;
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

public class AdminArticlesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Article> articles = new ArrayList<>();
        ArrayList<String> erreurs = (ArrayList<String>)request.getAttribute("erreurs");
        if (erreurs == null)
            erreurs = new ArrayList<>();
        try {
            articles = ArticleDAO.getInstance().getAllOrderBy("prix");
        } catch (SQLException e) {
            erreurs.add(e.getMessage());
        }
        request.setAttribute("articles", articles);
        request.setAttribute("erreurs", erreurs);
        request.getRequestDispatcher("vue/adminListArticles.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String pseudo = (String)session.getAttribute("pseudo");
        String page = "vue/error404.jsp";
        ArrayList<String> erreurs = new ArrayList<>();
        ArrayList<Article> articles = new ArrayList<>();
        if (pseudo != null){
            try {
                page = "vue/adminListArticles.jsp";
                articles = ArticleDAO.getInstance().getAllOrderBy("nom");
            } catch (SQLException e) {
                erreurs.add(e.getMessage());
            }
        } else {
            response.sendRedirect("login");
        }
        request.setAttribute("articles", articles);
        request.setAttribute("erreurs",erreurs);
        request.getRequestDispatcher(page).forward(request, response);
    }
}
