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

@WebServlet(name = "RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String username = request.getParameter("username");
        String mdp = request.getParameter("pass");
        ArrayList<String> erreurs = new ArrayList<>();

        if(session.getAttribute("id") != null) {
            response.sendRedirect("accueil");
            return;
        }else {
            if(username.equals(""))
                erreurs.add("Le pseudo est obligatoire!");
            if(nom.equals(""))
                erreurs.add("Le nom est obligatoire!");
            if(prenom.equals(""))
                erreurs.add("Le prénom est obligatoire!");
            if(mdp.equals(""))
                erreurs.add("Le mot de passe est obligatoire!");
            if(erreurs.size() == 0) {
                try {
                    if(UtilisateurDAO.getInstance().existPseudo(username))
                        erreurs.add("Le pseudo existe déjà!");
                    else {
                        Utilisateur user = new Utilisateur(nom, prenom, username, mdp);

                        user.save();
                        session.setAttribute("id", user.getId());
                        response.sendRedirect("test.jsp");
                        return;
                    }
                } catch (SQLException e) {
                    erreurs.add(e.getMessage());
                }
            }
        }
        request.setAttribute("erreurs", erreurs);
        request.getRequestDispatcher("register.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("id") != null) {
            response.sendRedirect("accueil");
        } else
            request.getRequestDispatcher("register.jsp").forward(request,response);
    }
}
