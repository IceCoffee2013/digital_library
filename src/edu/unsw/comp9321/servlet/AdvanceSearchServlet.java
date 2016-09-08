package edu.unsw.comp9321.servlet;

import edu.unsw.comp9321.controller.SearchController;
import edu.unsw.comp9321.dao.BookDB;
import edu.unsw.comp9321.entity.Article;
import edu.unsw.comp9321.entity.Page;
import edu.unsw.comp9321.entity.Query;
import edu.unsw.comp9321.entity.QueryType;
import edu.unsw.comp9321.util.Utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by Langley on 8/28/16.
 */
@WebServlet(name = "AdvanceSearchServlet", urlPatterns = "/advance")
public class AdvanceSearchServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdvanceSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.err.println("Query String: " + request.getQueryString());

        String pageNumber = request.getParameter("pn");
        int pn = -1;
        if (pageNumber == null || !Utils.isInteger(pageNumber)) {
            pn = 1;
        } else {
            pn = Integer.parseInt(pageNumber);
        }

        List<Query> queryList = new ArrayList<>();
        Enumeration e =request.getParameterNames();
        String parameterName;
        // TODO check query form is not empty by js.
        while(e.hasMoreElements()){
            parameterName = (String) e.nextElement();
            String values[] = request.getParameterValues(parameterName);
//            System.out.println(">>>>" + parameterName + " | " + values.toString());
            if (parameterName.indexOf("query") != -1) {
                String[] queryString = request.getParameterValues(parameterName);
                // searchconditional_1 searchin_1
                String index = parameterName.split("_")[1];
                System.out.println("Name: " + parameterName + " | index: " + index);
                String[] searchConditional = request.getParameterValues("searchconditional_" + index);
                String[] searchIn = request.getParameterValues("searchin_" + index);
                if (queryString == null) {
                    continue; // TODO
                }
                Query query;
                if (searchConditional == null) {
                    query = new Query(queryString[0], null, searchIn[0]);
                } else {
                    query = new Query(queryString[0], searchConditional[0], searchIn[0]);
                }
                System.out.println("query object: " + query.toString());
                queryList.add(query);
            }
        }

        SearchController searchController = new SearchController((BookDB) getServletContext().getAttribute("bookDb"));
        String nextPage = "";
        List<Article> searchResults;

        searchResults = searchController.advanceSearch(queryList);

        System.out.println("advance result size: " + searchResults.size());
        Page page = Utils.getPage(searchResults, pn);
        request.setAttribute("page", page);
        String queryUrl = "/advance" + filterQurey(request);
        request.setAttribute("queryUrl", queryUrl);
        nextPage = "articleResults.jsp?" + request.getQueryString() + "&pn=" + pn;
//        System.out.println("advance result size: " + searchResults.size());
        request.setAttribute("type", "generic");
//        request.setAttribute("searchResults", searchResults);
//        nextPage = "articleResults.jsp";

        RequestDispatcher rd = request.getRequestDispatcher("/"+nextPage);
        rd.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private String filterQurey(HttpServletRequest request) {
        String result = "?";
        List<Query> queryList = new ArrayList<>();
        Enumeration e = request.getParameterNames();
        String parameterName;
        while(e.hasMoreElements()){
            parameterName = (String) e.nextElement();
            String[] values = request.getParameterValues(parameterName);
            if (!parameterName.equals("pn") && values != null && values.length != 0) {
                result += parameterName + "=" + values[0] + "&";
            }
        }
        return result;
    }

}
