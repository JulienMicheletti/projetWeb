package servlet;

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

public class AddUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pseudo = request.getParameter("user_pseudo");
        String nom = request.getParameter("user_nom");
        String pre = request.getParameter("user_pre");
        String pass = request.getParameter("user_pass");
        String role = request.getParameter("user_role");
        Utilisateur new_user = new Utilisateur(nom, pre, pseudo, pass);
        ArrayList<String> erreurs = new ArrayList<>();
        if (role != null && role.equals("on")){
            new_user.setRole(0);
        }
        try {
            new_user.save();
        } catch (SQLException e) {
            erreurs.add("Erreur :  "+e.getMessage());
        }
        request.setAttribute("erreurs",erreurs);
        request.getRequestDispatcher("ListeUsers").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
