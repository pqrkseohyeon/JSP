package org.address.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.address.model.SAddressDAO;
import org.address.model.SAddressDTO;



public class AddrUpdateAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		SAddressDTO dto = new SAddressDTO();
		dto.setNum(Integer.parseInt(request.getParameter("num")));
		dto.setAddr(request.getParameter("addr"));
		dto.setName(request.getParameter("name"));
		dto.setTel(request.getParameter("tel"));
		dto.setZipcode(request.getParameter("zipcode"));
		
		SAddressDAO dao = SAddressDAO.getInstance();
		dao.addressUpdate(dto);
		response.sendRedirect("list.do");
	}
}
