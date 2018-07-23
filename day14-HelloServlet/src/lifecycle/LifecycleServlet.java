package lifecycle;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 서블릿의 라이프 사이클(생명주기) 메소드인
 * init(), service(), destroy()를 테스트하는 서블릿
 * 
 * @author PC38218
 *
 */
@WebServlet("/lifecycle")
public class LifecycleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// 이 서블릿의 요청 횟수를 저장하는 용도
	private int count = 0;

	/**
	 * 웹 컨테이너인(tomcat)에 의해 서블릿 초기화 과정에서
	 * 최초로 1회만 호출되는 라이프 사이클 메소드
	 * 
	 * 파라미터로 전달되는 config는 웹 컨테이너에 의해
	 * 자동으로 생성되어 전달된다
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("Lifecycle Servlet 인스턴스 생성 후");
		System.out.println("init() 메소드가");
		System.out.println("웹 컨테이너(tomcat)에 의해");
		System.out.println("자동으로 호출됩니다.");
	}

	/**
	 * 웹 컨테이너인(tomcat)에 의해 서비스 종료 과정에서
	 * 마지막에 1회만 호출되는 라이프 사이클 메소드
	 */
	public void destroy() {
		System.out.println("Lifecycle Servlet이 종료되기 직전");
		System.out.println("destroy() 메소드가");
		System.out.println("웹 컨테이너(tomcat)에 의해");
		System.out.println("자동으로 호출됩니다.");
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청 카운트 증가
		count++;
		
		// 1. 응답 문서 한글 설정
		response.setContentType("text/html; charset=UTF-8");
		
		// 2. 출력
		// (1) 기본 출력
		System.out.println("요청 횟수 : " + count);
		
		// (2) 브라우저 출력
		PrintWriter out = response.getWriter();
		out.println("<HTML><BODY>");
		out.println("요청 횟수 : " + count + "<br>");
		out.println("</BODY></HTML>");
		
		// 3. 출력 객체 닥기
		out.close();
	}

}
