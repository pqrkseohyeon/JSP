package com.book.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.model.BookDTO;
import com.book.model.BookImple;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class BookInsertAction
 */
@WebServlet("/book/BookInsert")
public class BookInsertAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookInsertAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("BookInsert.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		BookImple dao = BookImple.getInstance();
		int uploadFileSizeLimit=5*1024*1024;
		String encType="UTF-8";
		String savePath="upload";
		ServletContext context=getServletContext();
		String uploadFilePath=context.getRealPath(savePath);
		
		//multipart/form 방식은 request.getParameter로 받아올 수 없음
		MultipartRequest multi=new MultipartRequest(
				request, //request객체
				uploadFilePath, //서버상의 실제 디렉토리
				uploadFileSizeLimit, //최대 업로드 파일 크기
				encType, //인코딩 방법
				new DefaultFileRenamePolicy()); //동일 파일 새이름 부여
		
		//업로드된 파일이름 구하기
		String fileName = multi.getFilesystemName("uploadFile");
	
		
		if(fileName==null) { //파일 업로드 안됨
			System.out.println("파일 업로드 되지 않았음");
		}
		BookDTO book = new BookDTO();
		book.setUploadFile(fileName);
		book.setTitle(multi.getParameter("title"));
		book.setAuthor(multi.getParameter("author"));
		book.setPublisher(multi.getParameter("publisher"));
		book.setP_date(multi.getParameter("p_date"));
		book.setPrice(Integer.parseInt(multi.getParameter("price")));
		book.setInfo(multi.getParameter("info"));
		
		int flag=dao.bookInsert(book);
		if(flag==1) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('도서가 등록되었습니다');");
			//out.println("history.back(-1);");
			out.println("</script>");
		}
		
		RequestDispatcher rd=request.getRequestDispatcher("BookList.jsp");
		rd.forward(request, response);
		
	}

}
