package org.dannil.quizgamebackend.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.dannil.quizgamebackend.model.Question;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/question")
public class QuestionController {

	@RequestMapping(method = RequestMethod.GET)
	public final void questionGET(final HttpServletResponse response) {
		response.setContentType("application/json");

		ObjectMapper mapper = new ObjectMapper();

		try {
			response.getWriter().write(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(new Question()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
