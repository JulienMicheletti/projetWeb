package servlet;

import dao.UtilisateurDAO;
import entite.Panier;
import entite.Utilisateur;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
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
                        Cookie[] cookies = request.getCookies();
                        //Supprime les anciens cookies
                        for (Cookie cookie : cookies) {
                            cookie.setMaxAge(0);
                            response.addCookie(cookie);
                        }
                        session.setAttribute("id", user.getId());
                        session.setAttribute("pseudo", user.getPseudo());
                        session.setAttribute("role", user.getRole());
                        if(user.getRole() == 1) {
                            Panier p = new Panier(user.getId());
                            for (Cookie c : p.toCookies())
                                response.addCookie(c);
                        }
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
