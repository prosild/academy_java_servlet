package book.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.dao.BookShelf;
import book.vo.Book;

/**
 * 제품 전체 목록을 조회하는 서블릿
 * @author PC38218
 *
 */
@WebServlet({"/list", "/main/list"})
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 2. 조회에 필요한 객체 선ㅇ너
		BookShelf bookshelf;
		bookshelf = (BookShelf)getServletContext().getAttribute("bookshelf");
		
		// 3. 조회 결과를 request에 속성으로 추가
		List<Book> books = bookshelf.selectAll();
		request.setAttribute("books", books);
		
		// 4. 조회 결과가 추가된 request를
		//    적절한 목록 뷰(list.jsp)로 전달(페이지 이동)
		String view = "/listJsp";
		RequestDispatcher reqd;
		reqd = request.getRequestDispatcher(view);
		
		reqd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
