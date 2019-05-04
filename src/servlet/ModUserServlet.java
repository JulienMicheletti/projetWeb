package servlet;

import dao.UtilisateurDAO;
import entite.Panier;
import entite.Utilisateur;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("mod");
        String user_id = request.getParameter("id_user");
        String user_pseudo = request.getParameter("user_pseudo");
        String user_nom = request.getParameter("user_nom");
        String user_pre = request.getParameter("user_pre");
        String user_role = request.getParameter("user_role");
        int id = Integer.parseInt(user_id);
        try {
            if (action.equals("Supprimer")) {
                Panier panier = new Panier(id);
                if (panier != null){
                    panier.delCommande();
                }
                UtilisateurDAO.getInstance().del(id);
                response.getWriter().write("Supprimer");
            } else {
                Utilisateur user = new Utilisateur(id);
                user.setPseudo(user_pseudo);
                user.setNom(user_nom);
                user.setPrenom(user_pre);
                if (user_role.equals("admin")){
                    user.setRole(0);
                } else {
                    user.setRole(1);
                }
                user.save();
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
