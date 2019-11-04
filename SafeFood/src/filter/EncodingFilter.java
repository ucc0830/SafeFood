package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

@WebFilter(
			value= {"/*"},
			initParams=@WebInitParam(name="encoding", value="utf-8")
		)
public class EncodingFilter implements Filter{

	@Override
	public void destroy() {		
		
	}
	//이부분만 재정의 하면 된다...
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//사전작업...한글처리
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//뒤에 나오는 컴포넌트에 계속적으로 적용된다.
		//필터 다음의 서블릿 페이지들이 한글처리가 주욱 적용되어지다.
		chain.doFilter(request, response);		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {		
		
	}

}
