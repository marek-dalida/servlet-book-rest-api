package zpt.servlets;


import com.google.gson.Gson;
import zpt.models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;

@WebServlet(name = "LoginServlet", urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = new User(username, password);

        if (!password.isEmpty() && !username.isEmpty()) {
            session.setAttribute("user", user);
        }

        System.out.println(username);
        System.out.println(password);

        session.setAttribute("user", user);
        Cookie userCookie = new Cookie("user", user.toString());
        response.addCookie(userCookie);
        //Cookie cookie
        String userIdBase64 = getBase64FromString(user.getLogin());
        response.addCookie(new Cookie("userId", userIdBase64));

        Gson gson = new Gson();
        gson.toJson(user, response.getWriter());

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.print("Login Servlet");
        out.close();
    }

    private String getBase64FromString(String str) {
        return Base64.getEncoder().encodeToString(str.getBytes());
    }
}
