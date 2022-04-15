package com.javalec.ex.CommandCar;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.ex.Dao.BDao;
import com.javalec.ex.Dto.BDto6;

public class SellListSelectCommandCar implements CommandCar {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String sCar_brand = request.getParameter("Car_brand");
		String sCar_type = request.getParameter("Car_type");
		String sCar_color = request.getParameter("Car_color");
		
		
		BDao dao = new BDao();
		ArrayList<BDto6> SellList_Select = dao.SellList_select(sCar_brand, sCar_type, sCar_color);
		
		request.setAttribute("SellList_Select", SellList_Select);
	}

}
