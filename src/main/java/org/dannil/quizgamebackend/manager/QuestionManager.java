package org.dannil.quizgamebackend.manager;

import java.util.LinkedList;
import java.util.List;

import org.dannil.quizgamebackend.model.Question;

public final class QuestionManager {

	private static int ID = 0;

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

	public final LinkedList<Question> findByCategory(final String category) {
		LinkedList<Question> temp = new LinkedList<Question>();
		for (Question q : questions) {
			final LinkedList<String> categories = new LinkedList<String>(q.getCategories());
			for (String s : categories) {
				if (s.equals(category)) {
					temp.add(q);
				}
			}
		}
		return temp;
	}

	public final void add(final Question question) {
		question.setId(ID);
		ID++;

		QuestionManager.questions.add(question);
	}

	public final Question edit(final int id, final Question question) {
		final int index = QuestionManager.questions.indexOf(this.get(id));
		final Question newQuestion = new Question(question.getCategories(), question.getTitle(), question.getAnswers());
		QuestionManager.questions.set(index, newQuestion);

		return QuestionManager.questions.get(index);
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
