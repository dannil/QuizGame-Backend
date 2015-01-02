package org.dannil.quizgamebackend.controller;

import java.io.IOException;
import java.util.LinkedList;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.dannil.quizgamebackend.manager.QuestionManager;
import org.dannil.quizgamebackend.utility.CredentialsUtility;
import org.dannil.quizgamebackend.utility.JsonUtility;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/category")
public final class CategoryController {

	private final static Logger LOGGER = Logger.getLogger(CategoryController.class.getName());

	private QuestionManager questionManager;

	@PostConstruct
	public final void init() {
		this.questionManager = new QuestionManager();
	}

	@RequestMapping(method = RequestMethod.GET)
	public final void categoryGET(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
		response.setContentType("application/json");

		if (!CredentialsUtility.isLoginCorrect((String) request.getParameter("username"), (String) request.getParameter("token"))) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}

		final LinkedList<String> categories = this.questionManager.getCategories();
		if (categories.size() > 0) {
			final String json = JsonUtility.convertToJson(categories);
			LOGGER.info("\n" + json);
			response.getWriter().write(json);
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
	}

}
