/*package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Food;
import model.FoodDAOImpl;
import model.FoodWthAllergy;

@WebServlet("/food.do")
public class FoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("command");
		
		if(cmd.equalsIgnoreCase("search")) {
			search(request, response, false);
		} else if(cmd.equalsIgnoreCase("searchIdx")) {
			search(request, response, true);
		} else if(cmd.equalsIgnoreCase("searchItem")) {
			searchItem(request, response, "info");
		} else if(cmd.equalsIgnoreCase("searchItemNutrition")) {
			searchItem(request, response, "nutrition");
		} else if(cmd.equalsIgnoreCase("searchItemJson")) {
			searchItemJson(request, response);
		} else if(cmd.equalsIgnoreCase("recommend")) {
			recommend(request,response);
		}
	}
	
	private void recommend(HttpServletRequest request, HttpServletResponse response) {
		String tmpmin = request.getParameter("mincalory");
		int mincalory = Integer.parseInt(tmpmin);
		String tmpmax = request.getParameter("maxcalory");
		int maxcalory = Integer.parseInt(tmpmax);
		if(maxcalory>1000) {
			
		}
		List<Food> list1;
		try {
//			list1 = FoodDAOImpl.getInstance().searchFoodsByCatogory("식사류");
//			List<Food> list2 = FoodDAOImpl.getInstance().searchFoodsByCatogory("음료류");
//			List<Food> list3 = FoodDAOImpl.getInstance().searchFoodsByCatogory("디저트류");
			List<String[]> list4 = new ArrayList<>();
			int sum = 0;
			for(int i=0;i<list1.size();i++) {
				sum+=(int)list1.get(i).getCalory();
				for(int j=0;j<list2.size();j++) {
					sum+=(int)list2.get(j).getCalory();
					for(int k=0;k<list3.size();k++) {
						sum+=(int)list3.get(k).getCalory();
						if(sum>=mincalory && sum<=maxcalory) {
							String[] tmp = new String[4];
							tmp[0] = Integer.toString(sum);
							tmp[1] = list1.get(i).getImg();
							tmp[2] = list2.get(j).getImg();
							tmp[3] = list3.get(k).getImg();
							list4.add(tmp);
						}
						sum-=(int)list3.get(k).getCalory();
					}
					sum-=(int)list2.get(j).getCalory();
				}
				sum-=(int)list1.get(i).getCalory();
			}
			int ran = (int) (Math.random()*list4.size());
			request.setAttribute("reco", list4.get(ran));
			request.getRequestDispatcher("mypage.jsp").forward(request, response);
		} catch (Exception e) {
			System.out.println("recommend Error");
		}
	}

	private void searchItemJson(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		Food food = null;
		PrintWriter out=response.getWriter();
		try {
			food = FoodDAOImpl.getInstance().searchFoodByName(name);
			ArrayList<Food> list = new ArrayList<>();
			list.add(food);
			JSONArray jAry = new JSONArray(list);
			out.print(jAry);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void searchItem(HttpServletRequest request, HttpServletResponse response, String type) throws ServletException, IOException {
		String name = request.getParameter("name");
		Food food = null;
		FoodWthAllergy foodAlg = null;
		String path = "Error.jsp";
		String[] alergyArray = {"대두", "땅콩", "원유", "우유", "게", "새우", "참치", "연어", "쑥", "소고기", "닭고기", 
				"돼지고기", "복숭아", "민들레", "계란흰자"};
		try {
			food = FoodDAOImpl.getInstance().searchFoodByName(name);
			String allergyStr = "";
			for (String allergy : alergyArray) {
				if(food.getMaterial().contains(allergy)) {
					allergyStr += allergy + ",";
				}
			}
			if(allergyStr.length()>0){
				allergyStr = allergyStr.substring(0, allergyStr.length()-1);
			}
			foodAlg = new FoodWthAllergy(food, allergyStr);
			
			request.setAttribute("result", foodAlg);
			if(type.equals("info"))	path = "detailProductItem.jsp";
			else if(type.equals("nutrition")) path = "detailProductItemNutrition.jsp";
		} catch (Exception e) {
			System.out.println(e);
			request.setAttribute("msg", "선택된 식품 정보를 찾는데 오류가 있습니다.");
		}
		request.getRequestDispatcher(path).forward(request, response);
	}

	private void search(HttpServletRequest request, HttpServletResponse response, boolean flg) throws ServletException, IOException {
		String opt = request.getParameter("searchOpt");
		String keyword = request.getParameter("searchKeyword");
		System.out.println("Search call " + flg+"   "+"opt::"+opt+"  k::"+keyword);
		String path = "Error.jsp";
		if(opt==null) opt="";
		if(keyword==null) keyword="";
		opt.toLowerCase().trim();
		keyword.trim();
		List<Food> list = null;
		try {
			switch(opt) {
			case "상품명":
				list = FoodDAOImpl.getInstance().searchFoodsByName(keyword);
				break;
			case "제조사":
				list = FoodDAOImpl.getInstance().searchFoodsByMaker(keyword);
				break;
			case "성분":
				list = FoodDAOImpl.getInstance().searchFoodsByMaterial(keyword);
				break;
			default:
				list = FoodDAOImpl.getInstance().search();
				break;
			}
						
			if(flg) {
				list = list.subList(0, 3);
				System.out.println(list.size());
			}
			request.setAttribute("result", list);
			request.setAttribute("field", opt);
			request.setAttribute("text", keyword);
			path = "searchedItem.jsp";
		} catch (Exception e) {
			System.out.println(e);
			request.setAttribute("msg", "책 목록을 불러오는 중에 오류가 발생했습니다.");
		}
		request.getRequestDispatcher(path).forward(request, response);
	}

}
*/