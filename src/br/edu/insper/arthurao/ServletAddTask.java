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
 * Servlet implementation class AddTask
 */
@WebServlet("/AddTask")
public class ServletAddTask extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ServletAddTask() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String userEmail = request.getParameter("user_email");
		// Gets all tasks for a given user:
		DAOTasks daoTasks = new DAOTasks();
		List<Tasks> tasksList = daoTasks.getTasks(userEmail);
		// Adds attributes to request:
		request.setAttribute("taskslist", tasksList);
		request.setAttribute("user_email", userEmail);
		// Sends the request and response forward:
		RequestDispatcher dispatcher = request.getRequestDispatcher("/taskboard.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Interest variables:
		String userEmail = request.getParameter("user_email");
		String buttonEdit = request.getParameter("edit");
		String buttonRemove = request.getParameter("remove");
		String newTaskDescription = request.getParameter("description");
		String taskId = request.getParameter("task_id");
		// Instantiates a DAO and add a value to database:
		DAOTasks dao = new DAOTasks();
		
		if (buttonEdit != null) {// Task is updated on the database:
			// Creates an edited Tasks object with the given values:
			Tasks editedTask = new Tasks();
			editedTask.setUserEmail(userEmail);
			editedTask.setDescription(newTaskDescription);
			editedTask.setTag(2);
			// Calls DAO's edit method:
			dao.editTask(editedTask, Integer.parseInt(taskId));
			
		} else if (buttonRemove != null) {// Task is removed from database:
			// Calls DAO's remove method:
			dao.removeTask(Integer.parseInt(taskId));
			
		} else {// Task is added to the database:			
			// Creates a new Tasks object with the given values:
			Tasks newTask = new Tasks();
			newTask.setUserEmail(userEmail);
			newTask.setDescription(newTaskDescription);
			newTask.setTag(1);
			// Calls DAO's add method:
			dao.addTask(newTask);
		}
		request.setAttribute("user_email", userEmail);
		// Reloads the page:
		doGet(request, response);
	}

}
