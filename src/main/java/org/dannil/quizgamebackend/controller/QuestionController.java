package org.dannil.quizgamebackend.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/question")
public final class QuestionController {

	@RequestMapping(method = RequestMethod.GET)
	public final void questionGET(final HttpServletResponse response) {
		response.setContentType("application/json;charset=UTF-8");
		try {
			response.getWriter().write("{ \"name\" : \"daniel\" }");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
