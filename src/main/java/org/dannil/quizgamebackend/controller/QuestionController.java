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
	}

	@RequestMapping(method = RequestMethod.GET)
	public final void questionGET(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
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
	public final void questionIdGET(final HttpServletRequest request, final HttpServletResponse response, @PathVariable("id") final Integer id) throws IOException {
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
		if (!question.isValueNull()) {
			this.questionManager.add(question);
			final String json = JsonUtility.convertToJson(question);
			LOGGER.info("\n" + json);
			response.getWriter().write(json);
		}
		response.setStatus(HttpServletResponse.SC_OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public final void questionIdPOST(final HttpServletRequest request, final HttpServletResponse response, @PathVariable("id") final Integer id) throws IOException {
		response.setContentType("application/json");

		final Question question = JsonUtility.convertFromJson(request.getParameter("json"));
		if (!question.isValueNull()) {
			final Question result = this.questionManager.edit(id, question);
			final String json = JsonUtility.convertToJson(result);
			LOGGER.info("\n" + json);
			response.getWriter().write(json);
		}
		response.setStatus(HttpServletResponse.SC_OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public final void questionIdDELETE(final HttpServletRequest request, final HttpServletResponse response, @PathVariable("id") final Integer id) {
		response.setContentType("application/json");

		final boolean success = this.questionManager.delete(id);
		if (success) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	@RequestMapping(value = "/category/{category}", method = RequestMethod.GET)
	public final void questionCategoryGET(final HttpServletRequest request, final HttpServletResponse response, @PathVariable("category") final String category) throws IOException {
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

	@RequestMapping(value = "/{id}/category", method = RequestMethod.GET)
	public final void questionIdCategoryGET(final HttpServletRequest request, final HttpServletResponse response, @PathVariable("id") final Integer id) throws IOException {
		response.setContentType("application/json");

		final Question question = this.questionManager.get(id);
		if (question != null && question.getCategories() != null) {
			final String json = JsonUtility.convertToJson(question.getCategories());
			LOGGER.info("\n" + json);
			response.getWriter().write(json);
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	@RequestMapping(value = "/{id}/answer", method = RequestMethod.GET)
	public final void questionIdAnswerGET(final HttpServletRequest request, final HttpServletResponse response, @PathVariable("id") final Integer id) throws IOException {
		response.setContentType("application/json");

		final Question question = this.questionManager.get(id);
		if (question != null && question.getAnswers() != null) {
			final LinkedList<String> answers = new LinkedList<String>(question.getAnswers());
			answers.add(question.getCorrect());

			final String json = JsonUtility.convertToJson(answers);
			LOGGER.info("\n" + json);
			response.getWriter().write(json);
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
	}
}
