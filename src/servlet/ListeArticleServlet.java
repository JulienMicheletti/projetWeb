package servlet;

import dao.*;
import entite.Article;
import jdk.jshell.execution.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebServlet(name = "articleClient")
public class ListeArticleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ArticleDAO articleDAO = ArticleDAO.getInstance();
            List<Article> lu = articleDAO.getAllOrderBy("nom");
            request.setAttribute("produits", lu);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("vue/userListeArticle.jsp").forward(request, response);
    }

}