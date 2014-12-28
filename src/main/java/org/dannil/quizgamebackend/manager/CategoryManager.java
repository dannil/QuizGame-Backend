package org.dannil.quizgamebackend.manager;

import java.util.LinkedList;
import java.util.List;

public final class CategoryManager {

	private static List<String> categories;

	public CategoryManager() {
		if (categories == null) {
			categories = new LinkedList<String>();

			this.add("basic");
			this.add("algebra");
		}
	}

	public final String get(final String category) {
		for (String s : categories) {
			if (s.equals(category)) {
				return s;
			}
		}
		return null;
	}

	public final void add(final String category) {
		CategoryManager.categories.add(category);
	}

	public final LinkedList<String> getCategories() {
		return new LinkedList<String>(CategoryManager.categories);
	}

}
