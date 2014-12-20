package org.dannil.quizgamebackend.controller;

import java.io.IOException;
import java.util.LinkedList;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.dannil.quizgamebackend.manager.QuestionManager;
import org.dannil.quizgamebackend.model.Question;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/question")
public class QuestionController {

	private final static Logger LOGGER = Logger.getLogger(QuestionController.class.getName());

	private QuestionManager manager;

	@PostConstruct
	public final void init() {
		this.manager = new QuestionManager();

		Question question1 = new Question("Test");
		Question question2 = new Question("Not a prime");
		Question question3 = new Question("Hello World");

		this.manager.add(question1);
		this.manager.add(question2);
		this.manager.add(question3);

		LOGGER.info(this.manager.getQuestions().toString());
	}

	@RequestMapping(method = RequestMethod.GET)
	public final void questionGET(final HttpServletResponse response) {
		response.setContentType("application/json");

		final LinkedList<Question> questions = this.manager.getQuestions();
		if (questions != null) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				String result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(questions);
				LOGGER.info("\n" + result);
				response.getWriter().write(result);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public final void questionIdGET(final HttpServletResponse response, @PathVariable final Integer id) {
		response.setContentType("application/json");

		final Question question = this.manager.get(id);
		if (question != null) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				String result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(question);
				LOGGER.info("\n" + result);
				response.getWriter().write(result);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public final void questionPOST(final HttpServletResponse response) {
		response.setContentType("application/json");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public final void questionIdPUT(final HttpServletResponse response, @PathVariable final Integer id) {
		response.setContentType("application/json");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public final void questionIdDELETE(final HttpServletResponse response, @PathVariable final Integer id) {
		response.setContentType("application/json");

		boolean success = this.manager.delete(id);
		if (success) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}

	}

}
