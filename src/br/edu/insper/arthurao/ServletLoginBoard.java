package br.edu.insper.arthurao;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginBoard
 */
@WebServlet("/LoginBoard")
public class ServletLoginBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLoginBoard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		// Control variable:
		Boolean validation = false;
		// User input variables:
		String userEmail = request.getParameter("email");
		String userPassword = request.getParameter("password");
		// Gets all users:
		DAOUsers daoUsers = new DAOUsers();
		List<Users> usersList = daoUsers.getUsers();
		// Verifies if there's a match of email and password:
		for (Users user : usersList) {
			if (userEmail.equals(user.getEmail()) && userPassword.equals(user.getPassword())) {
				validation = true;
			}
		}
		// Sends to board if login is valid otherwise returns to home page:		
		if(validation) {
			// Gets all tasks for a given user:
			DAOTasks daoTasks = new DAOTasks();
			List<Tasks> tasksList = daoTasks.getTasks(userEmail);
			// Adds attributes to request:
			request.setAttribute("taskslist", tasksList);
			request.setAttribute("user_email", userEmail);
			// Sends the request and response forward:
			RequestDispatcher dispatcher = request.getRequestDispatcher("/taskboard.jsp");
			dispatcher.forward(request, response);

		} else {
			response.sendRedirect("/TecWebProjeto01");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
