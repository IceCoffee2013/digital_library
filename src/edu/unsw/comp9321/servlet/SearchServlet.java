package edu.unsw.comp9321.servlet;

import edu.unsw.comp9321.controller.SearchController;
import edu.unsw.comp9321.dao.BookDB;
import edu.unsw.comp9321.entity.Article;
import edu.unsw.comp9321.entity.Page;
import edu.unsw.comp9321.entity.Query;
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
 * Servlet implementation class AlbumSearch
 */
@WebServlet("/search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		String query = request.getParameter("query");
		String pageNumber = request.getParameter("pn");
		int pn = -1;
		if (pageNumber == null || !Utils.isInteger(pageNumber)) {
			pn = 1;
		} else {
			pn = Integer.parseInt(pageNumber);
		}
		System.out.println("pn: " + pn);
		SearchController searchController = new SearchController((BookDB) getServletContext().getAttribute("bookDb"));
		String nextPage = "";

		System.out.println("type:" + type + " | query: " + query);
		if (type == null || query == null) { //if url is tampered with, return to main page
			nextPage = "welcome.jsp";
		} else {
			request.setAttribute("query", query);

			List<Article> searchResults = null;

//			if (type.equals("title")) {
//				searchResults = searchController.simpleSearchByTitle(query);
//				request.setAttribute("searchResults", searchResults);
//				nextPage = "articleResults.jsp";
//			}
//			else if (type.equals("author")) {
//				searchResults = searchController.simpleSearchByAuthor(query);
//				request.setAttribute("searchResults", searchResults);
//				nextPage = "articleResults.jsp";
//			} else

			if (type.equals("generic")) {
				searchResults = searchController.simpleSearch(query);
//				request.setAttribute("searchResults", searchResults);
				System.out.println("generic result size: " + searchResults.size());
				Page page = Utils.getPage(searchResults, pn);
				request.setAttribute("page", page);
				String queryUrl = "/search" + filterQurey(request);
				request.setAttribute("queryUrl", queryUrl);
				nextPage = "articleResults.jsp?" + request.getQueryString() + "&pn=" + pn;
			}
			if (searchResults == null || searchResults.size() == 0) {
				// if search results are empty
				request.setAttribute("type", "empty");
			} else {
				request.setAttribute("type", type);
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
