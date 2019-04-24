package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "DocFilter")
public class DocFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletResponse response = (HttpServletResponse) resp;
        ((HttpServletResponse) resp).sendRedirect("/projetWeb_war_exploded/");
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
