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

			final Question question1 = new Question("Solve 3 + 4");
			question1.addCategories("basic");
			question1.addAnswers("5", "7", "-1");
			question1.setCorrect("7");

			final Question question2 = new Question("Solve 2^3");
			question2.addCategories("basic");
			question2.addAnswers("16", "8");
			question2.setCorrect("8");

			final Question question3 = new Question("Factor the expression (a+b)(a−b)");
			question3.addCategories("algebra");
			question3.addAnswers("a-b", "a^2-b", "a^2-b^2", "a-b^2");
			question3.setCorrect("a^2-b^2");

			this.add(question1);
			this.add(question2);
			this.add(question3);
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
		final LinkedList<Question> temp = new LinkedList<Question>();
		for (Question q : questions) {
			if (q.getCategories() != null) {
				for (String s : q.getCategories()) {
					if (s.equals(category)) {
						temp.add(q);
					}
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
		final Question editQuestion = this.get(id);
		final int index = QuestionManager.questions.indexOf(editQuestion);
		final Question newQuestion = new Question(question.getTitle(), question.getCategories(), question.getAnswers(), question.getCorrect());
		newQuestion.setId(editQuestion.getId());
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

	public final LinkedList<String> getCategories() {
		final LinkedList<String> categories = new LinkedList<String>();
		for (Question q : questions) {
			if (q.getCategories() != null) {
				for (String s : q.getCategories()) {
					if (!categories.contains(s)) {
						categories.add(s);
					}
				}
			}
		}
		return categories;
	}

	public final LinkedList<String> getAnswers(final int id) {
		final LinkedList<String> answers = new LinkedList<String>();
		for (Question q : questions) {
			if (q.getId().equals(id) && q.getAnswers() != null) {
				for (String s : q.getAnswers()) {
					answers.add(s);
				}
				answers.add(q.getCorrect());
			}
		}
		return answers;
	}

}
