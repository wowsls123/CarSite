package com.javalec.ex.CommandCar;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.ex.Dao.BDao;
import com.javalec.ex.Dto.BDto6;

public class SellListSelectPriceCommandCar implements CommandCar {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String sCar_salePrice1 = request.getParameter("sCar_salePrice1");
		String sCar_salePrice2 = request.getParameter("sCar_salePrice2");
		System.out.println("sCar_salePrice1="+sCar_salePrice1);
		System.out.println("sCar_salePrice2="+sCar_salePrice2);

		
		
		BDao dao = new BDao();
		
		ArrayList <BDto6> SellList_select_price = dao.SellList_selectPrice(sCar_salePrice1,sCar_salePrice2);
		
		System.out.println("SellList_select_price="+SellList_select_price);
		
		request.setAttribute("SellList_select_price", SellList_select_price);
		
	}

}
