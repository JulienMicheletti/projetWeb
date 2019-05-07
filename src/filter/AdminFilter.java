package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        HttpSession session = request.getSession();
        String pseudo = (String)session.getAttribute("pseudo");
        if (pseudo != null){
            int role = (int)session.getAttribute("role");
            if (role == 0)
                chain.doFilter(req, resp);
            else
                ((HttpServletResponse) resp).sendRedirect("/projetWeb_war_exploded/");
        } else {
            ((HttpServletResponse) resp).sendRedirect("/projetWeb_war_exploded/");
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }
}
