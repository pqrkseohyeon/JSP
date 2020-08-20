package com.guest.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.guest.model.GuestDAO;

/**
 * Servlet implementation class GuestLoginCheck
 */
@WebServlet("/guestbook/login.gb")
public class GuestLoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuestLoginAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String userid=request.getParameter("userid");
		String pwd=request.getParameter("pwd");
		GuestDAO dao = GuestDAO.getInstance();
		int flag = dao.guestLoginCheck(userid,pwd);//member 테이블 사용
		String path="";
		if(flag==1) {//회원
			HttpSession session = request.getSession();
			session.setAttribute("login", userid);//userid를 login이라는 이름으로 넣는다.
			path="insert.jsp";
		}else if(flag==0) {//비번오류
			request.setAttribute("errMsg", "비밀번호를 확인하세요");
			path="login.jsp";
		}else if(flag==-1) {//회원아님
			request.setAttribute("errMsg", "회원이 아닙니다.");
			path="login.jsp";
		}
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
		
	}

}
