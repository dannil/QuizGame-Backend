package org.dannil.quizgamebackend.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dannil.quizgamebackend.utility.CredentialsUtility;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public final class AuthenticationInterceptor extends HandlerInterceptorAdapter {

	@Override
	public final boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
		if (!CredentialsUtility.isLoginCorrect((String) request.getParameter("username"), (String) request.getParameter("token"))) {
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