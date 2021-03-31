package zpt.servlets;

import com.google.gson.Gson;
import zpt.models.Book;
import zpt.models.User;
import zpt.responses.ExceptionResponse;
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

@WebServlet(name = "DashboardServlet", value = "/dashboard/*")
public class DashboardServlet extends HttpServlet {
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //to powoduje pojawianie się błedu 405
        //super.doDelete(req, resp);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

        String pathInfo = req.getPathInfo();
        String[] pathParts = pathInfo.split("/");

        int deleteBookId = 0;
        try {
            deleteBookId = Integer.parseInt(pathParts[1]);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }

        ServletContext context = getServletContext();

        ArrayList<Book> books;
        books = (ArrayList<Book>) context.getAttribute("books");

        int finalDeleteBookId = deleteBookId;
        Book deletedBook = books.stream()
                .filter(book -> book.getId() == finalDeleteBookId)
                .findAny()
                .orElse(null);
        books.removeIf(book -> book.getId() == finalDeleteBookId);
        context.setAttribute("books", books);


        Gson gson = new Gson();

        if (deletedBook == null) {
            String msg = "Book with given id: " + pathParts[1] + " not found";
            Response objectNotFound = new Response(msg, 404);
            gson.toJson(objectNotFound, resp.getWriter());
        } else
            gson.toJson(deletedBook, resp.getWriter());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        Gson gson = new Gson();
        ServletContext context = getServletContext();

        try {
            ArrayList<Book> books;
            books = (ArrayList<Book>) context.getAttribute("books");
            GetDashboardResponse res = new GetDashboardResponse(books, 200);
            gson.toJson(res, response.getWriter());
        }
        catch(Exception ex){
            ExceptionResponse exResponse = new ExceptionResponse();
            exResponse.setMessage(ex.getLocalizedMessage());
            exResponse.setStatus(500);
            response.setStatus(500);
            gson.toJson(exResponse, response.getWriter());
        }
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
        int newBookId = maxBook.getId() + 1;
        newBook.setId(newBookId);

        books.add(newBook);
        context.setAttribute("books", books);

        gson.toJson(newBook, response.getWriter());
    }
}
