package com.nixs.filter;

import com.nixs.model.User;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebFilter(urlPatterns = {"/admin-home", "/user-home"})
public class PageFilter implements Filter {
    private Map<Long, List<String>> pages = new HashMap<>();

    {
        pages.put(1L, Arrays.asList("/admin-home", "/add-user"));
        pages.put(2L, Arrays.asList("/user-home"));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        String servletPath = req.getServletPath();

        User user = (User) session.getAttribute("user");

        if (user == null) {
            chain.doFilter(request, response);
            return;
        }

        List<String> availablePages = pages.get(user.getRole().getId());

        if (availablePages.contains(servletPath)) {
            chain.doFilter(request, response);
        } else {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
