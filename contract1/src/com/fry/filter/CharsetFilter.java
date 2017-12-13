package com.fry.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

@WebFilter(filterName = "CharsetFilter",urlPatterns ="/*",initParams={@WebInitParam(name="encoding",value="text/html;charset=utf-8")})
public class CharsetFilter implements Filter {
    private String encoding;

    public void destroy() {
        encoding=null;
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        if(encoding!=null){
            resp.setContentType(encoding);
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        encoding= config.getInitParameter("encoding");
    }

}
