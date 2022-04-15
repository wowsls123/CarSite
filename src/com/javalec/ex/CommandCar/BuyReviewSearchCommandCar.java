package com.javalec.ex.CommandCar;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.ex.Dao.BDao;
import com.javalec.ex.Dto.BDto4;

public class BuyReviewSearchCommandCar implements CommandCar {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String bRSearchT = request.getParameter("bRSearch");
		String bRSearchW = request.getParameter("bRSearch");
		System.out.println("bRSearchT="+bRSearchT);
		System.out.println("bRSearchW="+bRSearchW);
		

		
		
		BDao dao = new BDao();
		
		ArrayList <BDto4> BuyReview_search = dao.BuyReview_search(bRSearchT,bRSearchW);
		
		System.out.println("BuyReview_search="+BuyReview_search);
		
		request.setAttribute("BuyReview_search", BuyReview_search);
	}

}
