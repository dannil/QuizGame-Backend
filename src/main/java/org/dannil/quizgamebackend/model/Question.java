package org.dannil.quizgamebackend.model;

import java.util.LinkedList;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Question {

	private Integer id;
	private String title;

	private List<Category> categories;
	private List<Answer> answers;

	public Question() {
		this.answers = new LinkedList<Answer>();
		this.categories = new LinkedList<Category>();
	}

	public Question(final String title) {
		this();
		this.title = title;
	}

	public Question(final List<Category> categories, final String title) {
		this(title);
		this.categories = categories;
	}

	public Question(final List<Category> categories, final String title, final List<Answer> answers) {
		this(categories, title);
		this.answers = answers;
	}

	public final Integer getId() {
		return this.id;
	}

	public final void setId(final Integer id) {
		this.id = id;
	}

	public final String getTitle() {
		return this.title;
	}

	public final void setTitle(final String title) {
		this.title = title;
	}

	public final List<Category> getCategories() {
		return this.categories;
	}

	public final void setCategory(final LinkedList<Category> categories) {
		this.categories = categories;
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

	public final void addAnswers(final Answer... answers) {
		for (Answer a : answers) {
			this.addAnswer(a);
		}
	}

	@Override
	public String toString() {
		return "Question [id=" + this.id + ", category=" + this.categories + ", title=" + this.title + ", answers=" + this.answers + "]";
	}

}
