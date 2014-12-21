package org.dannil.quizgamebackend.controller;

import java.io.IOException;
import java.util.LinkedList;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.dannil.quizgamebackend.manager.QuestionManager;
import org.dannil.quizgamebackend.model.Answer;
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

	private QuestionManager manager;

	@PostConstruct
	public final void init() {
		this.manager = new QuestionManager();

		Question question1 = new Question("basic", "Solve 3 + 4", new Answer("5"), new Answer("7"), new Answer("-1"));
		Question question2 = new Question("basic", "Solve 2^3", new Answer("16"), new Answer("8"));
		Question question3 = new Question("algebra", "Factor the expression (a+b)(a−b)", new Answer("a-b"), new Answer("a^2-b"), new Answer("a^2-b^2"), new Answer("a-b^2"));

		this.manager.add(question1);
		this.manager.add(question2);
		this.manager.add(question3);
	}

	@RequestMapping(method = RequestMethod.GET)
	public final void questionGET(final HttpServletResponse response) throws IOException {
		response.setContentType("application/json");

		final LinkedList<Question> questions = this.manager.getQuestions();
		if (questions != null) {
			String json = JsonUtility.generateJson(questions);
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

		final Question question = this.manager.get(id);
		if (question != null) {
			String json = JsonUtility.generateJson(question);
			LOGGER.info("\n" + json);
			response.getWriter().write(json);
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public final void questionPOST(final HttpServletRequest request, final HttpServletResponse response) {
		response.setContentType("application/json");

		ObjectMapper mapper = new ObjectMapper();
		try {
			// TODO
			Question question = mapper.readValue(mapper.writeValueAsString(this.manager.get(0)), Question.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	@RequestMapping(value = "/category/{category}", method = RequestMethod.GET)
	public final void questionIdDELETE(final HttpServletResponse response, @PathVariable final String category) throws IOException {
		response.setContentType("application/json");

		final LinkedList<Question> questions = this.manager.findByCategory(category);
		if (questions != null) {
			String json = JsonUtility.generateJson(questions);
			LOGGER.info("\n" + json);
			response.getWriter().write(json);
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
	}

}
