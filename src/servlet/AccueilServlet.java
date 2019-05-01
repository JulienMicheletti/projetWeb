package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AccueilServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("vue/error404.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String page = "vue/error404.jsp";
        String pseudo = (String)session.getAttribute("pseudo");
        if (pseudo != null){
            int id = (int)session.getAttribute("role");
            request.setAttribute("pseudo", pseudo);
            if (id == 0){
                page = "vue/adminAccueil.jsp";
            } else if (id == 1){
                page = "vue/userAccueil.jsp";
            } else {
                page = "vue/error404.jsp";
            }
        } else {
            response.sendRedirect("login");
        }
        request.getRequestDispatcher(page).forward(request, response);
    }
}
