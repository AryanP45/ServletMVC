package com.aryan.web;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aryan.web.dao.StudentDao;
import com.aryan.web.model.Student;

/**
 * Servlet implementation class GetStudentController
 */
@WebServlet("/getStudent")
public class GetStudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int rollno = Integer.parseInt(request.getParameter("rollno"));
		StudentDao dao = new StudentDao();
		
		Student stu = dao.getStudent(rollno);
		
		request.setAttribute("student", stu);
		
		RequestDispatcher rd = request.getRequestDispatcher("showStudent.jsp");
		rd.forward(request, response);
		
	}
}
