package org.address.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.address.model.SAddressDAO;
import org.address.model.SAddressDTO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;



public class AddrSearchAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.setCharacterEncoding("utf-8");
		  String field = request.getParameter("field");
		  String word=request.getParameter("word");
		  SAddressDAO dao = SAddressDAO.getInstance();
		  ArrayList<SAddressDTO> arr =   dao.addressSearch(field, word);
		  int count = dao.searchCount(field, word);
		  
		  JSONObject mainObj = new JSONObject();
		  JSONArray jarr = new JSONArray();
			for (SAddressDTO dto : arr) {
				 JSONObject obj = new JSONObject();
				 obj.put("num", dto.getNum());
				 obj.put("name", dto.getName());
				 obj.put("addr", dto.getAddr());
				 obj.put("tel", dto.getTel());
				 obj.put("zipcode", dto.getZipcode());
				 jarr.add(obj);
			}	
			 JSONObject objCount= new JSONObject();
			 objCount.put("scount",count );
			 
			mainObj.put("searchArr", jarr);
			mainObj.put("searchCount", objCount);
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(mainObj.toString());
		
	}

}
