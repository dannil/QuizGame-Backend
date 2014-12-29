package org.dannil.quizgamebackend.controller;

import java.io.IOException;
import java.util.LinkedList;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.dannil.quizgamebackend.manager.QuestionManager;
import org.dannil.quizgamebackend.model.Question;
import org.dannil.quizgamebackend.utility.JsonUtility;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/question")
public class QuestionController {

	private final static Logger LOGGER = Logger.getLogger(QuestionController.class.getName());

	private QuestionManager questionManager;

	@PostConstruct
	public final void init() {
		this.questionManager = new QuestionManager();

		final Question question1 = new Question("Solve 3 + 4");
		question1.addCategories("basic");
		question1.addAnswers("5", "7", "-1");

		final Question question2 = new Question("Solve 2^3");
		question2.addCategories("basic");
		question2.addAnswers("16", "8");

		final Question question3 = new Question("Factor the expression (a+b)(a−b)");
		question3.addCategories("algebra");
		question3.addAnswers("a-b", "a^2-b", "a^2-b^2", "a-b^2");

		this.questionManager.add(question1);
		this.questionManager.add(question2);
		this.questionManager.add(question3);
	}

	@RequestMapping(method = RequestMethod.GET)
	public final void questionGET(final HttpServletResponse response) throws IOException {
		response.setContentType("application/json");

		final LinkedList<Question> questions = this.questionManager.getQuestions();
		if (questions.size() > 0) {
			final String json = JsonUtility.convertToJson(questions);
			LOGGER.info("\n" + json);
			response.getWriter().write(json);
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public final void questionIdGET(final HttpServletResponse response, @PathVariable final Integer id) throws IOException {
		response.setContentType("application/json");

		final Question question = this.questionManager.get(id);
		if (question != null) {
			final String json = JsonUtility.convertToJson(question);
			LOGGER.info("\n" + json);
			response.getWriter().write(json);
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public final void questionPOST(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
		response.setContentType("application/json");

		final Question question = JsonUtility.convertFromJson(request.getParameter("json"));
		this.questionManager.add(question);

		final String json = JsonUtility.convertToJson(question);
		LOGGER.info("\n" + json);
		response.getWriter().write(json);
		response.setStatus(HttpServletResponse.SC_OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public final void questionIdPOST(final HttpServletRequest request, final HttpServletResponse response, @PathVariable final Integer id) throws IOException {
		response.setContentType("application/json");

		final Question question = JsonUtility.convertFromJson(request.getParameter("json"));
		final Question result = this.questionManager.edit(id, question);

		final String json = JsonUtility.convertToJson(result);
		LOGGER.info("\n" + json);
		response.getWriter().write(json);
		response.setStatus(HttpServletResponse.SC_OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public final void questionIdDELETE(final HttpServletResponse response, @PathVariable final Integer id) {
		response.setContentType("application/json");

		final boolean success = this.questionManager.delete(id);
		if (success) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	@RequestMapping(value = "/category/{category}", method = RequestMethod.GET)
	public final void questionCategoryGET(final HttpServletResponse response, @PathVariable final String category) throws IOException {
		response.setContentType("application/json");

		final LinkedList<Question> questions = this.questionManager.findByCategory(category);
		if (questions.size() > 0) {
			final String json = JsonUtility.convertToJson(questions);
			LOGGER.info("\n" + json);
			response.getWriter().write(json);
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
	}
}
