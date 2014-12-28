package org.dannil.quizgamebackend.model;

import java.util.LinkedList;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Question {

	private Integer id;
	private String title;

	private List<String> categories;
	private List<String> answers;

	public Question() {
		this.categories = new LinkedList<String>();
		this.answers = new LinkedList<String>();
	}

	public Question(final String title) {
		this();
		this.title = title;
	}

	public Question(final List<String> categories, final String title) {
		this(title);
		this.categories = categories;
	}

	public Question(final List<String> categories, final String title, final List<String> answers) {
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

	public final List<String> getCategories() {
		return this.categories;
	}

	public final void setCategory(final LinkedList<String> categories) {
		this.categories = categories;
	}

	public final List<String> getAnswers() {
		return this.answers;
	}

	public final void setAnswers(final List<String> answers) {
		this.answers = answers;
	}

	public final void addAnswer(final String answer) {
		if (this.answers != null) {
			this.answers.add(answer);
		}
	}

	public final void addAnswers(final String... answers) {
		for (String s : answers) {
			this.addAnswer(s);
		}
	}

	@Override
	public String toString() {
		return "Question [id=" + this.id + ", category=" + this.categories + ", title=" + this.title + ", answers=" + this.answers + "]";
	}

}
