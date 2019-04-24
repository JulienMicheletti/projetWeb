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

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String pass = request.getParameter("pass");
        ArrayList<String> erreurs = new ArrayList<>();
        if(session.getAttribute("id") != null) {
            response.sendRedirect("accueil");
            return;
        } else {
            if(username.equals(""))
                erreurs.add("Le pseudo est manquant!");
            if(pass.equals(""))
                erreurs.add("Le mot de passe est manquant!");
            if(erreurs.size() == 0) {
                Utilisateur user = null;
                try {
                    user = UtilisateurDAO.getInstance().identification(username,pass);
                    if(user == null) {
                        erreurs.add("L'association identifiant mot de passe n'existe pas!");
                    } else {
                        session.setAttribute("id", user.getId());
                        response.sendRedirect("accueil");
                        return;
                    }
                } catch (SQLException e) {
                    erreurs.add(e.getMessage());
                }
            }
        }

        request.setAttribute("erreurs",erreurs);
        request.getRequestDispatcher("vue/login.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("id") != null) {
            response.sendRedirect("accueil");
        } else
            request.getRequestDispatcher("vue/login.jsp").forward(request,response);
    }
}
