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

@WebServlet(value = "/add-book")
public class AddBookServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("book_name");
        int authorID = Integer.parseInt(request.getParameter("book_author"));
        String price = request.getParameter("book_price");
        String genre = request.getParameter("book_genre");
        String description = request.getParameter("book_description");

        double bookPrice = Double.parseDouble(price);

        Author author = DBConnection.getAuthor(authorID);
        if(author!=null){
            Book book = new Book();
            book.setName(name);
            book.setPrice(bookPrice);
            book.setGenre(genre);
            book.setDescription(description);
            book.setAuthor(author);
            DBConnection.addBook(book);
        }


        response.sendRedirect("/");

    }
}
