package edu.unsw.comp9321.servlet;

import edu.unsw.comp9321.dao.BookDB;
import edu.unsw.comp9321.entity.Cart;
import edu.unsw.comp9321.util.Utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class Cart1
 */
@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Cart c = (Cart) request.getSession().getAttribute("cart");
		BookDB bookDB = (BookDB) getServletContext().getAttribute("bookDb");
		String nextPage = "";
        String action = request.getParameter("action");

        if (action == null) {
            RequestDispatcher rd = request.getRequestDispatcher("/cart.jsp");
            rd.forward(request, response);
        }

		if (action.equals("add")) {
			String[] bookCheckBoxes = request.getParameterValues("book");

            if (bookCheckBoxes == null) {
                System.err.println("bookCheckBoxes");
            } else {
                System.out.println("bookCheckBoxes: " + bookCheckBoxes);
            }

			if (bookCheckBoxes != null) {
				for (String bookID : bookCheckBoxes) {
				    System.out.println("bookID: " + bookID);
					if (Utils.isInteger(bookID)) {
						c.addItem(bookDB.getItemByID(Integer.parseInt(bookID)));
                        System.out.println("Cart Size: " + c.getBookList().size());
					} else {
					    System.err.println("Book ID invalid: " + bookID);
                    }
				}
			}
			nextPage = "cart.jsp";
		} else if (action.equals("add-one")) {
		    String book = request.getParameter("book");
            if (book != null) {
                if (Utils.isInteger(book)) {
                    c.addItem(bookDB.getItemByID(Integer.parseInt(book)));
                    System.out.println("Cart Size: " + c.getBookList().size());
                } else {
                    System.err.println("Book ID invalid: " + book);
                }
            }
            nextPage = "cart.jsp";
        } else if (action.equals("remove")) {
		    String[] removeBookCheckBoxes = request.getParameterValues("book");

            if (removeBookCheckBoxes != null) {
                for (String bookID : removeBookCheckBoxes) {
                    if (Utils.isInteger(bookID)) {
                        c.removeItem(bookDB.getItemByID(Integer.parseInt(bookID)));
                    } else {
                        System.err.println("Book ID invalid: " + bookID);
                    }
                }
            }

			nextPage = "cart.jsp";
		}
		else if (action.equals("checkout")) {
            System.out.println("cart size: " + c.getBookList().size());
			nextPage = "checkout.jsp";
		} else {
			nextPage = "cart.jsp";
		}

        RequestDispatcher rd = request.getRequestDispatcher("/" + nextPage);
        rd.forward(request, response);

	}

}
