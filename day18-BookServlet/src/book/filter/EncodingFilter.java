package book.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * 서비스에 진입하는 요청에 대하여<br>
 * 요청객체와 응답객체에 한글 설정을 하는<br>
 * 인코딩 필터 클래스<br>
 * web.xml에 <filter> 엘리먼트로 등록하거나<br>
 * ------------------------------------------------<br>
 * @WebFilter 애노테이션으로 등록해서 사용한다.<br>
 * filterName = encodingFilter<br>
 * <br>
 * 두개의 초기화 파라미터 설정<br>
 * charEncoding : UTF-8<br>
 * contentType : text/html; charset=UTF-8<br>
 * <br>
 * 이 필터가 작동할 주소 형태 등록<br>
 * urlPatterns : /*<br>
 * 				==> 이 서비스(어플리케이션)의 컨텍스트 패스 이하로 들어오는<br>
 * 					모든 요청 주소에 대하여 이 필터가 작동함<br>
 * 
 * @author PC38218
 *
 */
@WebFilter(filterName="encodingFilter"
		 , initParams= {@WebInitParam(name="charEncoding", value="UTF-8")
		 			  , @WebInitParam(name="contentType", value="text/html; charset=UTF-8")}
		 , urlPatterns= {"/*"})
public class EncodingFilter implements Filter {
	// 이 필터가 사용할 필터 설정값을 저장하는 멤버변수
	private FilterConfig config;

	@Override
	public void init(FilterConfig config) throws ServletException {
		/*
		 * 필터의 init() 메소드는 서블릿 컨테이너(톰캣)에 의해
		 * 필터 초기화시에 자동으로 작동함.
		 * init()이 호출될 때 서블릿 컨테이너는
		 * web.xml(@WebFilter)에 선언되어있는
		 * 필터 정보를 저장하는 FilterConfig 객체를 매개변수로 전달
		 * 
		 * 내 멤버변수로 저장하여 사용함.
		 */
		this.config = config;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		// 1. 전처리 : doFilter() 메소드 전에 작성
		System.out.println("===== 1. 인코딩 필터 진입 =====");
		// (1) 요청에 대한 인코딩 설정
		String charEncoding = config.getInitParameter("charEncoding");
		req.setCharacterEncoding(charEncoding);
		
		// (2) 응답에 대한 인코딩 설정
		String contentType = config.getInitParameter("contentType");
		res.setContentType(contentType);
		
		// (3) 콘솔창에 값 출력
		System.out.println("charEncoding : " + charEncoding);
		System.out.println("contentType : " + contentType);
		
		// 2. 다음필터 호출 : 체인 객체에 다음 필터를 호출하며
		//					  이때 요청(req), 응답(res)을 그대로 넘김
		chain.doFilter(req, res);
		
		// 3. 후처리 : doFilter() 메소드 뒤에 작성
		System.out.println("===== 1. 인코딩 필터 종료 =====");
		
	}

	@Override
	public void destroy() {
		this.config = null;
	}

}
