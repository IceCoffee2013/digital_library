package edu.unsw.comp9321.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class confirmation
 */
@WebServlet("/confirmation")
public class ConfirmationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String message = "";
		
		if (request.getParameter("action").equals("confirm")) {
			message = "Thank you for purchasing from COMP9321 Book Store";
		}
		else if (request.getParameter("action").equals("cancel")) {
			message = "You didn't buy anything but thank you anyways";
		}
		request.getSession().invalidate();
		request.setAttribute("message", message);
		
		RequestDispatcher rd = request.getRequestDispatcher("/confirmation.jsp");
		rd.forward(request, response);
	}

}
