package com.book.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.model.BookDTO;
import com.book.model.BookImple;
import com.book.model.PageUtil;

/**
 * Servlet implementation class BookCardList
 */
@WebServlet("/book/BookCardList")
public class BookCardList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookCardList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		BookImple dao=BookImple.getInstance();
		
		String pageNum=request.getParameter("pageNum")==null? "1":request.getParameter("pageNum");
		int currentPage=Integer.parseInt(pageNum);
		int pageSize=10;
		int startRow=(currentPage-1)*pageSize+1;
		int endRow=currentPage*pageSize;
		
		int count=0;
		String field=request.getParameter("field")==null? "":request.getParameter("field");
		String word=request.getParameter("word")==null? "":request.getParameter("word");
		count=dao.getCount(field,word);
		
		int totPage=(count/pageSize)+(count%pageSize==0?0:1);
		int pageBlock=3;
		int startPage=((currentPage-1)/pageBlock)*pageBlock+1;
		int endPage=startPage+pageBlock-1;
		if(endPage>totPage) endPage=totPage;
		
		PageUtil pu=new PageUtil();
		pu.setCurrentPage(currentPage);
		pu.setEndPage(endPage);
		pu.setField(field);
		pu.setPageBlock(pageBlock);
		pu.setStartPage(startPage);
		pu.setTotPage(totPage);
		pu.setWord(word);
		
		ArrayList<BookDTO> arr=null;
		if(word.equals("")) {
			arr=dao.bookList(startRow,endRow);
		}else {
			arr=dao.bookList(field, word, startRow, endRow);
		}
		
		int rowNo=count-((currentPage-1)*pageSize);
		
		request.setAttribute("rowNo", rowNo);
		request.setAttribute("pu", pu);
		request.setAttribute("count", count);		
				
		request.setAttribute("dto", arr);
		RequestDispatcher rd = request.getRequestDispatcher("BookCardListResult.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
