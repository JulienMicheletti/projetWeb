package servlet;

import dao.*;
import entite.Article;
import entite.Commande;
import jdk.jshell.execution.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebServlet(name = "panierClient")
public class PanierServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            CommandeDAO commandeDAO = dao.CommandeDAO.getInstance();
            HttpSession session = request.getSession();
            int id = (int)session.getAttribute("id");
            List<Commande> lu = commandeDAO.getCommandesByUser(id);
            request.setAttribute("commande", lu);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("vue/userPanier.jsp").forward(request, response);
    }

}