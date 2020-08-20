package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.board.model.BoardDAO;
import com.board.model.BoardDAOImpl;
import com.board.model.BoardDTO;
import com.board.model.PageUtil;

/**
 * Servlet implementation class BoardSearchAction
 */
@WebServlet("/board/search")
public class BoardSearchAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardSearchAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		BoardDAO dao = BoardDAOImpl.getInstance();
		String pageNum = request.getParameter("pageNum")==null?"1":request.getParameter("pageNum");
		String field = request.getParameter("field")==null?"":request.getParameter("field");
		String word = request.getParameter("word")==null?"":request.getParameter("word");
		int currentPage = Integer.parseInt(pageNum);
		int pageSize = 5;
		int startRow = (currentPage-1)*pageSize+1;
		int endRow = currentPage*pageSize;
		
		int count = dao.boardCount(field, word);
		//총 페이지수
		int totPage = (count/pageSize)+(count%pageSize==0?0:1);
		int pageBlock = 3;
		int startPage = ((currentPage-1)/pageBlock)*pageBlock+1;
		int endPage = startPage+pageBlock-1;
		if(endPage > totPage) endPage = totPage;
		
		PageUtil pu = new PageUtil();
		pu.setCurrentPage(currentPage);
		pu.setEndPage(endPage);
		pu.setPageBlock(pageBlock);
		pu.setStartPage(startPage);
		pu.setTotPage(totPage);
		pu.setField(field);
		pu.setWord(word);
		
		
		ArrayList<BoardDTO> arr = null;
		if(word.contentEquals("")) {
			arr = dao.boardList(field, word, startRow, endRow);
		}else {
			arr = dao.boardList(field, word, startRow, endRow);
		}		
		int rowNo = count - ((currentPage-1)*pageSize);//매 페이지의 시작번호
		
		JSONObject mainObj = new JSONObject();
		JSONArray jarr = new JSONArray();
		for(BoardDTO dto:arr) {
			JSONObject obj = new JSONObject();
			obj.put("num",dto.getNum());
			obj.put("writer",dto.getWriter());
			obj.put("subject",dto.getSubject());
			obj.put("content",dto.getContent());
			obj.put("reg_date",dto.getReg_date());
			obj.put("readcount",dto.getReadcount());
			jarr.add(obj);
		}
		mainObj.put("searchArr",jarr);
		JSONObject objCount = new JSONObject();
		objCount.put("scount",count);
		
		mainObj.put("searchArr",jarr);
		mainObj.put("searchCount",objCount);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(mainObj.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
