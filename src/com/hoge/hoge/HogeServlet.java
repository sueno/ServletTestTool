package com.hoge.hoge;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HogeServlet
 */
@WebServlet("/HogeServlet")
public class HogeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	public void init() throws ServletException{
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HogeServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html; charset=Windows-31J");
        PrintWriter out = response.getWriter();
        out.println("Weaving Success");
        out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
