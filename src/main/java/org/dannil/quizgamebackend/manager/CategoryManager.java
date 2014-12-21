package org.dannil.quizgamebackend.manager;

import java.util.LinkedList;
import java.util.List;

import org.dannil.quizgamebackend.model.Category;

public final class CategoryManager {

	private static List<Category> categories;

	public CategoryManager() {
		if (categories == null) {
			categories = new LinkedList<Category>();

			this.add(new Category("basic"));
			this.add(new Category("algebra"));
		}
	}

	public final Category get(final String category) {
		for (Category c : categories) {
			if (c.getCategory().equals(category)) {
				return c;
			}
		}
		return null;
	}

	public final void add(final Category category) {
		CategoryManager.categories.add(category);
	}

	public final LinkedList<Category> getCategories() {
		return new LinkedList<Category>(CategoryManager.categories);
	}

}
