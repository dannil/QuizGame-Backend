package org.dannil.quizgamebackend.manager;

import java.util.LinkedList;
import java.util.List;

import org.dannil.quizgamebackend.model.Category;
import org.dannil.quizgamebackend.model.Question;

public final class QuestionManager {

	private static List<Question> questions;

	public QuestionManager() {
		if (questions == null) {
			questions = new LinkedList<Question>();
		}
	}

	public final Question get(final int id) {
		for (Question q : questions) {
			if (q.getId().equals(id)) {
				return q;
			}
		}
		return null;
	}

	public final LinkedList<Question> findByCategory(final Category category) {
		LinkedList<Question> temp = new LinkedList<Question>();
		for (Question q : questions) {
			LinkedList<Category> categories = q.getCategories();
			for (Category c : categories) {
				if (c.getCategory().equals(category.getCategory())) {
					temp.add(q);
				}
			}
		}
		return temp;
	}

	public final void add(final Question question) {
		QuestionManager.questions.add(question);
	}

	public final boolean delete(final int id) {
		for (Question q : questions) {
			if (q.getId().equals(id)) {
				delete(q);
				return true;
			}
		}
		return false;
	}

	public final void delete(final Question question) {
		QuestionManager.questions.remove(question);
	}

	public final LinkedList<Question> getQuestions() {
		return new LinkedList<Question>(QuestionManager.questions);
	}

}
