package com.company.securitywebapp.servlet;


import com.company.securitywebapp.bean.UserAccount;
import com.company.securitywebapp.utils.AppUtils;
import com.company.securitywebapp.utils.DataDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher
                = this.getServletContext().getRequestDispatcher("/views/loginView.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        UserAccount userAccount = DataDAO.findUser(userName,password);

        if (userAccount == null){
            String errorMessage = "Invalid userName or Password";

            request.setAttribute("errorMessage", errorMessage);

            RequestDispatcher dispatcher
                    =this.getServletContext().getRequestDispatcher("/views/loginView.jsp");
            dispatcher.forward(request,response);
            return;
        }

        AppUtils.storeLoginedUser(request.getSession(), userAccount);

        int redirectId = -1;
        try {
            redirectId = Integer.parseInt(request.getParameter("redirectId"));

        }catch (Exception e){

        }
        String requestUri = AppUtils.getRedirectAfterLoginUrl(request.getSession(), redirectId);
        if (requestUri != null){
            response.sendRedirect(requestUri);
        }else {
            // �� ��������� ����� ��������� ����� � �������
            // ������������� �� �������� /userInfo
            response.sendRedirect(request.getContextPath() + "/userInfo");
        }
    }
}
