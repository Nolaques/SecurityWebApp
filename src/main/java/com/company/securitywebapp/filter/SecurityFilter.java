package com.company.securitywebapp.filter;


import com.company.securitywebapp.bean.UserAccount;
import com.company.securitywebapp.request.UserRoleRequestWrapper;
import com.company.securitywebapp.utils.AppUtils;
import com.company.securitywebapp.utils.SecurityUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebFilter("/*")
public class SecurityFilter implements Filter{

    public void destroy() {

    }

    public SecurityFilter() {
    }



    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String servletPath = request.getServletPath();

        // Информация пользователя сохранена в Session
        // (После успешного входа в систему).
        UserAccount loginedUser = AppUtils.getLoginedUser(request.getSession());

        if (servletPath.equals("/login")) {
            chain.doFilter(request, response);
            return;
        }
        HttpServletRequest wrapRequest = request;

        if (loginedUser != null) {
            //User Name
            String userName = loginedUser.getUserName();

            // Роли (Role).
            List<String> roles = loginedUser.getRoles();

            // Старый пакет request с помощью нового Request с информацией userName и Roles.
            wrapRequest = new UserRoleRequestWrapper(userName, roles, request);
        }

        // Страницы требующие входа в систему.
        if (SecurityUtils.isSecurityPage(request)) {
            // Если пользователь еще не вошел в систему,
            // Redirect (перенаправить) к странице логина.
            if (loginedUser == null) {
                String requestUri = request.getRequestURI();

                // Сохранить текущую страницу для перенаправления (redirect) после успешного входа в систему.

                int redirectId = AppUtils.storeRedirectAfterLoginUrl(request.getSession(), requestUri);

                response.sendRedirect(wrapRequest.getContextPath() + "/login?redirectId=" + redirectId);
                return;

            }
            // Проверить пользователь имеет действительную роль или нет?
            boolean hasPermission = SecurityUtils.hasPermission(wrapRequest);

            if (!hasPermission) {
                RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/views/accessDeniedView.jsp");
                dispatcher.forward(request, response);
                return;
            }

        }
        chain.doFilter(wrapRequest, response);

    }

    public void init(FilterConfig fConfig) throws ServletException {

    }

}
