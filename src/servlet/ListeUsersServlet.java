package servlet;

import dao.UtilisateurDAO;
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

public class ListeUsersServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String pseudo = (String)session.getAttribute("pseudo");
        String page = "vue/error404.jsp";
        ArrayList<String> erreurs = new ArrayList<>();
        ArrayList<Utilisateur> users = new ArrayList<>();
        if (pseudo != null){
            try {
                page = "vue/adminListUsers.jsp";
                users = UtilisateurDAO.getInstance().getAllUser();
            } catch (SQLException e) {
                erreurs.add(e.getMessage());
            }
        } else {
            response.sendRedirect("login");
        }
        request.setAttribute("users", users);
        request.setAttribute("erreurs",erreurs);
        request.getRequestDispatcher(page).forward(request, response);
    }
}
