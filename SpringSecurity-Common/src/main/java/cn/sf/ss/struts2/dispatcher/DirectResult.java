package cn.sf.ss.struts2.dispatcher;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.util.TextParseUtil;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.StrutsResultSupport;

public class DirectResult extends StrutsResultSupport {
	private static final Log _log = LogFactory.getLog(DirectResult.class);
	private static final long serialVersionUID = 3633371605905583950L;
	private boolean prependServletContext = true;
	private static final String FORWARD_PREFIX = "forward:";
	private static final String REDIRECT_PREFIX = "redirect:";

	public DirectResult() {
	}

	public DirectResult(String location) {
		super(location);
	}

	public void setPrependServletContext(boolean prependServletContext) {
		this.prependServletContext = prependServletContext;
	}

	protected void doExecute(String finalLocation, ActionInvocation invocation)
			throws Exception {
		HttpServletResponse response = (HttpServletResponse) invocation
				.getInvocationContext()
				.get("com.opensymphony.xwork2.dispatcher.HttpServletResponse");
		HttpServletRequest request = (HttpServletRequest) invocation
				.getInvocationContext()
				.get("com.opensymphony.xwork2.dispatcher.HttpServletRequest");

		String resultCodeAsLocation = invocation.getResultCode();
		if (resultCodeAsLocation == null) {
			return;
		}

		resultCodeAsLocation = TextParseUtil.translateVariables(
				resultCodeAsLocation, invocation.getStack());

		if (resultCodeAsLocation.startsWith("!")) {
			doRedirect(invocation, request, response,
					resultCodeAsLocation.substring(1));
			return;
		}
		if (resultCodeAsLocation.startsWith("redirect:")) {
			doRedirect(invocation, request, response,
					resultCodeAsLocation.substring("redirect:".length()));
			return;
		}
		if (resultCodeAsLocation.startsWith("forward:")) {
			doDispatcher(response, request,
					resultCodeAsLocation.substring("forward:".length()));
			return;
		}
		doDispatcher(response, request, resultCodeAsLocation);
	}

	private void doDispatcher(HttpServletResponse response,
			HttpServletRequest request, String resultCodeAsLocation)
			throws IOException, ServletException {
		if (_log.isInfoEnabled()) {
			_log.info("Forwarding to location:" + resultCodeAsLocation);
		}

		PageContext pageContext = ServletActionContext.getPageContext();
		if (pageContext != null) {
			pageContext.include(resultCodeAsLocation);
			return;
		}

		RequestDispatcher dispatcher = request
				.getRequestDispatcher(resultCodeAsLocation);
		if (dispatcher == null) {
			response.sendError(404, "result '" + resultCodeAsLocation
					+ "' not found");
			return;
		}

		if ((!response.isCommitted())
				&& (request.getAttribute("javax.servlet.include.servlet_path") == null)) {
			request.setAttribute("struts.view_uri", resultCodeAsLocation);
			request.setAttribute("struts.request_uri", request.getRequestURI());

			dispatcher.forward(request, response);
		} else {
			dispatcher.include(request, response);
		}
	}

	private void doRedirect(ActionInvocation invocation,
			HttpServletRequest request, HttpServletResponse response,
			String redirectLocation) throws IOException {
		if (isPathUrl(redirectLocation)) {
			if (!redirectLocation.startsWith("/")) {
				String namespace = invocation.getProxy().getNamespace();
				if ((namespace != null) && (namespace.length() > 0)
						&& (!"/".equals(namespace)))
					redirectLocation = namespace + "/" + redirectLocation;
				else {
					redirectLocation = "/" + redirectLocation;
				}
			}
			if ((this.prependServletContext)
					&& (request.getContextPath() != null)
					&& (request.getContextPath().length() > 0)) {
				redirectLocation = request.getContextPath() + redirectLocation;
			}
		}

		if (_log.isInfoEnabled())
			_log.info("Redirect to location:" + redirectLocation);
		response.sendRedirect(response.encodeRedirectURL(redirectLocation));
	}

	private static boolean isPathUrl(String url) {
		return url.indexOf(':') == -1;
	}
}
