package org.dannil.quizgamebackend.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.dannil.quizgamebackend.utility.CredentialsUtility;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public final class AuthenticationInterceptor extends HandlerInterceptorAdapter {

	private final static Logger LOGGER = Logger.getLogger(AuthenticationInterceptor.class.getName());

	@Override
	public final boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
		if (!CredentialsUtility.isLoginCorrect(request.getParameter("username"), request.getParameter("token"))) {
			LOGGER.error("Invalid login, sending 401 response!");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return false;
		}
		return super.preHandle(request, response, handler);
	}

	@Override
	public final void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler, final ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}
}