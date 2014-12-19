package org.dannil.quizgamebackend.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/question")
public class QuestionController {

	@RequestMapping(method = RequestMethod.GET)
	public final void questionGET(final HttpServletResponse response) {
		response.setContentType("application/json");
	}

}
