package org.dannil.quizgamebackend.model;

import java.util.LinkedList;
import java.util.List;

public class Question {

	private static Integer ID = 0;

	private Integer id;
	private String category;
	private String title;

	private List<Answer> answers;

	private Question() {
		this.id = ID;
		ID++;

		this.answers = new LinkedList<Answer>();
	}

	public Question(final String title) {
		this();
		this.title = title;
	}

	public Question(final String category, final String title) {
		this(title);
		this.category = category;
	}

	public Question(final String category, final String title, Answer... answers) {
		this(category, title);
		this.addAnswers(answers);
	}

	public final Integer getId() {
		return this.id;
	}

	public final String getTitle() {
		return this.title;
	}

	public final void setTitle(final String title) {
		this.title = title;
	}

	public final String getCategory() {
		return this.category;
	}

	public final void setCategory(final String category) {
		this.category = category;
	}

	public final List<Answer> getAnswers() {
		return this.answers;
	}

	public final void setAnswers(final List<Answer> answers) {
		this.answers = answers;
	}

	public final void addAnswer(final Answer answer) {
		if (this.answers != null) {
			this.answers.add(answer);
		}
	}

	public final void addAnswers(Answer... answers) {
		for (Answer a : answers) {
			this.addAnswer(a);
		}
	}

	@Override
	public String toString() {
		return "Question [id=" + this.id + ", category=" + this.category + ", title=" + this.title + ", answers=" + this.answers + "]";
	}

}
