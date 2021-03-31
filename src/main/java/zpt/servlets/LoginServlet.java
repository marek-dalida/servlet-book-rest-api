package zpt.servlets;


import com.google.gson.Gson;
import zpt.models.LoginRequest;
import zpt.models.Role;
import zpt.models.User;
import zpt.responses.ExceptionResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.HashMap;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    static HashMap<String, String> users = new HashMap<String, String>() {{
        put("user1", "user1");
        put("user2", "user2");
        put("user3", "user3");
        put("user4", "user4");
        put("user5", "user5");
    }};

    public void init() throws ServletException {
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        LoginRequest loginRequest = gson.fromJson(request.getReader(), LoginRequest.class);

        User user;
        try {
            if (loginRequest.getUsername().equals("admin") && loginRequest.getPassword().equals("admin")) {
                user = new User(loginRequest.getUsername(), loginRequest.getPassword(), Role.ADMIN);
                session.setAttribute("user", user);
            } else if (users.containsKey(loginRequest.getUsername())) {
                if (users.get(loginRequest.getUsername()).equals(loginRequest.getPassword())) {
                    user = new User(loginRequest.getUsername(), loginRequest.getPassword(), Role.USER);
                    session.setAttribute("user", user);
                } else {
                    throw new Exception("user " + loginRequest.getUsername() + " , " + loginRequest.getPassword()
                            + " has different login and password");
                }
            } else {
                throw new Exception("given user login  " + loginRequest.getUsername() + " , " + loginRequest.getPassword()
                        + " doesn't exist in database");
            }

            String userIdBase64 = getBase64FromString(user.getLogin());
            response.addCookie(new Cookie("userId", userIdBase64));

            gson.toJson(user, response.getWriter());
        }
        catch (Exception e){
            ExceptionResponse exResponse = new ExceptionResponse();
            exResponse.setMessage(e.getMessage());
            exResponse.setStatus(400);
            response.setStatus(400);
            gson.toJson(exResponse, response.getWriter());
        }

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
