package cn.sf.ss.security.cas;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
public class CasLogoutSuccessHandler implements LogoutSuccessHandler {

	private String url = "";
	@Override
	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		if ("".equals(url)) {
			url = "http://cas.boc.com:8443/casServer/logout";
		}
		response.sendRedirect(url);
	}
	public void setTargetUrl(String url) {
		this.url  = url;
	}
}

