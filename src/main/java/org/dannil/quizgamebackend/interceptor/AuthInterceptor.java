package org.dannil.quizgamebackend.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.dannil.quizgamebackend.utility.AuthUtility;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public final class AuthInterceptor extends HandlerInterceptorAdapter {

	private final static Logger LOGGER = Logger.getLogger(AuthInterceptor.class.getName());

	@Override
	public final boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
		if (!AuthUtility.isAuthCorrect(request.getHeader("authorization"))) {
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