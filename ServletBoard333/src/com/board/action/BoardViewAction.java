package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.board.model.BoardDAO;
import com.board.model.BoardDAOImpl;
import com.board.model.BoardDTO;

/**
 * Servlet implementation class BoardViewAction
 */
@WebServlet("/board/view")
public class BoardViewAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardViewAction() {

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int num= Integer.parseInt(request.getParameter("num"));
		BoardDAO dao = BoardDAOImpl.getInstance();
		BoardDTO board = dao.boardfindById(num);
		//ÀÚ¹Ù Object -> JSON
		JSONObject obj = new JSONObject();
		obj.put("num",board.getNum());
		obj.put("writer",board.getWriter());
		obj.put("subject",board.getSubject());
		obj.put("content",board.getContent());
		obj.put("reg_date",board.getReg_date());
		obj.put("readcount",board.getReadcount());
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(obj.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
