package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import model.Member;
import model.MemberDAOImpl;

@WebServlet("/member.do")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("command");

		if(cmd.equalsIgnoreCase("addMember")) {
			addMember(request, response);
		} else if(cmd.equalsIgnoreCase("login")) {
			login(request, response);
		} else if(cmd.equalsIgnoreCase("logout")) {
			logout(request, response);
		} else if(cmd.equalsIgnoreCase("mypage")) {
			mypage(request, response);
		} else if(cmd.equalsIgnoreCase("update")) {
			update(request, response);
		} else if(cmd.equalsIgnoreCase("delete")) {
			delete(request, response);
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("ID");
		System.out.println(id);
		try {
			MemberDAOImpl.getInstance().delete(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String path = "index.jsp";
		HttpSession session = request.getSession();
		if(session.getAttribute("user") != null) {
			session.invalidate();
		}

		request.getRequestDispatcher(path).forward(request, response);
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("ID");
		String pw = request.getParameter("PW");
		String name = request.getParameter("NAME");
		String address = request.getParameter("ADDRESS");
		// check box 값을 받을 때는 getParameterValues
		String[] allergy = request.getParameterValues("ALLERGY");
		String s="";
		for(int i=0;i<allergy.length-1;i++) {
			s+=allergy[i]+",";
		}
		s+=allergy[allergy.length-1];
		String path = "error.jsp";
		Member pvo = new Member(id, pw, name, address, s);
		try {
			MemberDAOImpl.getInstance().update(pvo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		HttpSession session = request.getSession();
		session.setAttribute("user", pvo);
		path = "index.jsp";

		request.getRequestDispatcher(path).forward(request, response);
	}

	private void mypage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "mypage.jsp";
		request.getRequestDispatcher(path).forward(request, response);
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("user") != null) {
			session.invalidate();
		}
		response.sendRedirect("index.jsp");
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("email");
		String pass = request.getParameter("password");
		String path = "error.jsp";
		try {
			if(MemberDAOImpl.getInstance().loginCheck(user, pass)) {
				System.out.println("success");
				// login success
				// session에 바인딩
				HttpSession session = request.getSession();
				Member mvo= MemberDAOImpl.getInstance().find(user);
				session.setAttribute("user", mvo);
				session.setAttribute("msg", "정상적으로 로그인 되었습니다.");
			} else {
				System.out.println("fail");
				// login fail
				request.setAttribute("msg", "아이디 또는 패스워드가 잘못되었습니다.");
			}
			path = "index.jsp";
		} catch (Exception e) {
			request.setAttribute("msg", "로그인 실패");
		}

		request.getRequestDispatcher(path).forward(request, response);
	}

	private void addMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("ID");
		String pw = request.getParameter("PW");
		String name = request.getParameter("NAME");
		String address = request.getParameter("ADDRESS");
		// check box 값을 받을 때는 getParameterValues
		String[] allergy = request.getParameterValues("ALLERGY");

		String path = "error.jsp";
		Member pvo = new Member(id, pw, name, address, allergy);

		try {
			MemberDAOImpl.getInstance().add(pvo);
			request.setAttribute("mvo", pvo);
			path = "index.jsp";
		} catch (Exception e) {
			request.setAttribute("msg", "회원 저장에 실패하였습니다.");

		}
		request.getRequestDispatcher(path).forward(request, response);
	}
}
