package demo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SearchAction
 */
@WebServlet("/SearchAction")
public class SearchAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public SearchAction() {}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		search(request, response);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/pages/search.jsp");
		dispatcher.forward(request, response);
	}

	private void search(HttpServletRequest request, HttpServletResponse response) {
		// Node node = NodeBuilder.nodeBuilder().local(true).node();
		// Client client = node.client();
		// node.close();
	}

}
