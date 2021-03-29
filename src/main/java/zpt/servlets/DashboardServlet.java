package zpt.servlets;

import com.google.gson.Gson;
import zpt.models.Book;
import zpt.models.User;
import zpt.responses.GetDashboardResponse;
import zpt.responses.Response;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Collections;

@WebServlet(name = "DashboardServlet", value = "/DashboardServlet")
public class DashboardServlet extends HttpServlet {
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //to powoduje pojawianie się błedu 405
        //super.doDelete(req, resp);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

        Gson gson = new Gson();
        Response response = new Response("delete huje muje", 200);
        gson.toJson(response, resp.getWriter());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        Gson gson = new Gson();

        ServletContext context = getServletContext();

        ArrayList<Book> books;
        books = (ArrayList<Book>) context.getAttribute("books");

        GetDashboardResponse res = new GetDashboardResponse(books, 200);


        gson.toJson(res, response.getWriter());

//        PrintWriter out = response.getWriter();
//        out.print("Dashboard Servlet");
//        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        Gson gson = new Gson();

        ServletContext context = getServletContext();
        ArrayList<Book> books;
        books = (ArrayList<Book>) context.getAttribute("books");

        Book newBook = gson.fromJson(request.getReader(), Book.class);

        Book maxBook = Collections.max(books);
        int newBookId = maxBook.getId() +1;
        newBook.setId(newBookId);

        books.add(newBook);
        context.setAttribute("books", books);

        gson.toJson(newBook, response.getWriter());
    }
}
