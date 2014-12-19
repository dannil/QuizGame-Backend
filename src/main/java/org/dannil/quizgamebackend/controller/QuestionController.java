package org.dannil.quizgamebackend.controller;

import java.io.IOException;

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

	@RequestMapping(method = RequestMethod.GET)
	public final void questionGET(final HttpServletResponse response) {
		response.setContentType("application/json");

		Question question1 = new Question(7, "Test");
		Question question2 = new Question(14, "Not a prime");
		Question question3 = new Question(20, "Hello World");

		QuestionManager manager = new QuestionManager();
		manager.add(question1);
		manager.add(question2);
		manager.add(question3);

		LOGGER.info(manager.getQuestions().toString());

		ObjectMapper mapper = new ObjectMapper();
		try {
			String result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(manager.getQuestions());
			LOGGER.info("\n" + result);
			response.getWriter().write(result);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public final void questionPOST(final HttpServletResponse response) {
		response.setContentType("application/json");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public final void questionPUT(final HttpServletResponse response, @PathVariable final Integer id) {
		response.setContentType("application/json");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public final void questionDELETE(final HttpServletResponse response, @PathVariable final Integer id) {
		response.setContentType("application/json");

		QuestionManager manager = new QuestionManager();
		manager.delete(id);

		LOGGER.info(manager.getQuestions().toString());

	}

}
