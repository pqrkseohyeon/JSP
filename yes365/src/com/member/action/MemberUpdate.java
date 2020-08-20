package com.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.MemberDTO;
import com.member.model.SMemberDAOImpl;

/**
 * Servlet implementation class MemberUpdate
 */
@WebServlet("/member/update.me")
public class MemberUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdate() {
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
		
		HttpSession session = request.getSession();
		MemberDTO dto = new MemberDTO();
		
		dto.setName(request.getParameter("name"));
		dto.setEmail(request.getParameter("email"));
		
		dto.setPhone(request.getParameter("phone"));
		dto.setAddr(request.getParameter("addr"));
		dto.setDetailAddr(request.getParameter("detailAddr"));
		dto.setExtraAddr(request.getParameter("extraAddr"));
		dto.setUserid((String)session.getAttribute("userid"));
		
		SMemberDAOImpl dao = SMemberDAOImpl.getInstance();
		dao.memberUpdate(dto);
		
		session.invalidate();

		response.sendRedirect("login.me");
		
	}

}
