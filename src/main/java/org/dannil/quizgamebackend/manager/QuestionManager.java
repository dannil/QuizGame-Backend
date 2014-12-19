package org.dannil.quizgamebackend.manager;

import java.util.LinkedList;
import java.util.List;

import org.dannil.quizgamebackend.model.Question;

public final class QuestionManager {

	private static List<Question> questions;

	public QuestionManager() {
		if (questions == null || questions.size() > 3) {
			questions = new LinkedList<Question>();
		}
	}

	public final void add(final Question question) {
		QuestionManager.questions.add(question);
	}

	public final void delete(final int id) {
		for (Question q : questions) {
			if (q.getId() == id) {
				delete(q);
				break;
			}
		}
	}

	public final void delete(final Question question) {
		if (questions.contains(question)) {
			QuestionManager.questions.remove(question);
		}
	}

	public final LinkedList<Question> getQuestions() {
		return new LinkedList<Question>(QuestionManager.questions);
	}

}