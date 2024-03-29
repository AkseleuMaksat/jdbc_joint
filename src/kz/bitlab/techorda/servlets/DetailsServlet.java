package kz.bitlab.techorda.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.bitlab.techorda.db.Book;
import kz.bitlab.techorda.db.DBConnection;
import kz.bitlab.techorda.entity.Author;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/details")
public class DetailsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = -1;
        try {
            id = Integer.parseInt(request.getParameter("book_id"));
        }catch (Exception e){
        }

        ArrayList<Author> authors =DBConnection.getAuthors();
        request.setAttribute("author", authors);

        Book book = DBConnection.getBook(id);
        request.setAttribute("kniga", book);

        request.getRequestDispatcher("/details.jsp").forward(request, response);
    }
}