package org.dannil.quizgamebackend.controller;

import java.io.IOException;
import java.util.LinkedList;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.dannil.quizgamebackend.manager.CategoryManager;
import org.dannil.quizgamebackend.model.Category;
import org.dannil.quizgamebackend.utility.JsonUtility;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/category")
public final class CategoryController {

	private final static Logger LOGGER = Logger.getLogger(QuestionController.class.getName());

	private CategoryManager manager;

	@PostConstruct
	public final void init() {
		this.manager = new CategoryManager();

		Category category1 = new Category("basic");
		Category category2 = new Category("algebra");

		this.manager.add(category1);
		this.manager.add(category2);
	}

	@RequestMapping(method = RequestMethod.GET)
	public final void categoryGET(final HttpServletResponse response) throws IOException {
		response.setContentType("application/json");

		final LinkedList<Category> categories = this.manager.getCategories();
		if (categories.size() > 0) {
			String json = JsonUtility.generateJson(categories);
			LOGGER.info("\n" + json);
			response.getWriter().write(json);
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
	}

}
