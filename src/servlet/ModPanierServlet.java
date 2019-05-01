package servlet;

import dao.CommandeDAO;
import dao.UtilisateurDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ModPanierServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commande_id = request.getParameter("id_commande");

        int id = Integer.parseInt(commande_id);
        try {
            CommandeDAO.getInstance().del(id);
            response.getWriter().write("Supprimer");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
