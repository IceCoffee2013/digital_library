package edu.unsw.comp9321.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import edu.unsw.comp9321.Constant;
import edu.unsw.comp9321.dao.BookDB;
import edu.unsw.comp9321.entity.Article;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * This servlet displays a welcome page to the user
 * <p>
 * By clicking submit, the user is sent to the Menu page.
 */
@WebServlet(name = "WelcomeServlet", urlPatterns = "/welcome")
public class WelcomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BookDB bookDB;

    public WelcomeServlet() {
        super();

    }

    private void initBookDB() {
        System.err.println("begin init...");
        if (bookDB == null) {
            System.err.println(getServletContext().getContextPath());
            bookDB = new BookDB(getServletContext().getRealPath(Constant.XML_PATH));
            getServletContext().setAttribute("bookDb", bookDB); //TODO ensure load.
            System.getProperties().put("bookDb", bookDB);
        }
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Read the albums from the XML file and store it as a DB that is going
        // to be passed around the servlets.
        initBookDB();

        // Go to welcome page.
        List<Article> searchResults = bookDB.getIndexArticles();
        System.out.println("Index data size: " + searchResults.size());
        request.setAttribute("searchResults", searchResults);
        RequestDispatcher rd = request.getRequestDispatcher("/welcome.jsp");
        rd.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
