package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;
import com.member.model.SMemberDAOImpl;


/**
 * Servlet implementation class BoardInsertACtion
 */
@WebServlet("/board/BoardInsert")
public class BoardInsertACtion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardInsertACtion() {
        super();
        // TODO Auto-generated constructor stub
    }

    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd=request.getRequestDispatcher("BookCardDetail.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		BoardDTO board=new BoardDTO();
		board.setNum(Integer.parseInt(request.getParameter("num")));
		board.setUserid(request.getParameter("userid"));
		board.setSubject(request.getParameter("subject"));
		board.setContent(request.getParameter("content"));
		BoardDAO dao=BoardDAO.getInstance();
		
		int Number=Integer.parseInt(request.getParameter("num"));
		int flag=0;
		flag=dao.boardInsert(board);

		if(flag==1){
			response.sendRedirect("/yes365/book/BookCardDetail?num="+Number);
		}
	}

}
