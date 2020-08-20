package com.file;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class FileUploadAction
 */
@WebServlet("/file/upload.do")
public class FileUploadAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUploadAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int uploadFileSizeLimit =5*1024*1024;
		String encType="UTF-8";
		String savePath ="upload";
		ServletContext context = getServletContext();
		String uploadFilePath = context.getRealPath(savePath);
		System.out.println("서버상의 실제 디렉토리:"+uploadFilePath);
		
		MultipartRequest multi = new MultipartRequest(
				request,
				uploadFilePath,
				uploadFileSizeLimit,
				encType,
				new DefaultFileRenamePolicy());
		
		//업로드된 파일이름 구하기
		String fileName = multi.getFilesystemName("uploadFile");
		System.out.println("fileName"+fileName);
		if(fileName==null) {
			System.out.println("파일 업로드 되지 않았음");
		}else {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<b>책제목:"+multi.getParameter("name")+"<br>");
			out.println("<b>저자명:"+multi.getParameter("author")+"<br>");
			out.println("<b>출판사:"+multi.getParameter("publisher")+"<br>");
			out.println("<b>출판일:"+multi.getParameter("p_date")+"<br>");
			out.println("<b>책가격:"+multi.getParameter("price")+"<br>");
			out.println("<b>파일명:"+fileName);
			out.println("<b>책정보:"+multi.getParameter("info")+"<br>");
		}
				
	}

}
