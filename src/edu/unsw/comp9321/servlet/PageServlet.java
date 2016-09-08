package edu.unsw.comp9321.servlet;

import edu.unsw.comp9321.controller.SearchController;
import edu.unsw.comp9321.dao.BookDB;
import edu.unsw.comp9321.entity.Article;
import edu.unsw.comp9321.util.Utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Langley on 8/28/16.
 */
@WebServlet(name = "PageServlet", urlPatterns = "/article")
public class PageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String nextPage = "";
        if (id == null || !Utils.isInteger(id)) {
            nextPage = "";
        } else {
            int articleID = Integer.parseInt(id);
            Article article = ((BookDB) getServletContext().getAttribute("bookDb")).getItemByID(articleID);
            if (article != null) {
                request.setAttribute("article", article);
                nextPage = "article.jsp";
            } else {
                System.err.println("article is null: " + getClass());
            }
        }

        RequestDispatcher rd = request.getRequestDispatcher("/"+nextPage);
        rd.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
