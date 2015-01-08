package org.dannil.quizgamebackend.model;

import java.util.LinkedList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Question {

	private Integer id;
	private String title;

	private List<String> categories;
	private List<String> answers;

	private String correct;

	public Question() {
		this.categories = new LinkedList<String>();
		this.answers = new LinkedList<String>();
	}

	public Question(final String title) {
		this();
		this.title = title;
	}

	public Question(final String title, final List<String> categories) {
		this(title);
		this.categories = categories;
	}

	public Question(final String title, final List<String> categories, final List<String> answers, final String correct) {
		this(title, categories);
		this.answers = answers;
		this.correct = correct;
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

	public final void setCategories(final LinkedList<String> categories) {
		this.categories = categories;
	}

	public final List<String> getAnswers() {
		return this.answers;
	}

	public final void setAnswers(final List<String> answers) {
		this.answers = answers;
	}

	public final String getCorrect() {
		return this.correct;
	}

	public final void setCorrect(final String correct) {
		this.correct = correct;
	}

	public final void addCategories(final String... categories) {
		for (String category : categories) {
			if (category != null && !this.categories.contains(category)) {
				this.categories.add(category);
			}
		}
	}

	public final void removeCategories(final String... categories) {
		for (String category : categories) {
			if (category != null) {
				this.categories.remove(category);
			}
		}
	}

	public final void addAnswers(final String... answers) {
		for (String answer : answers) {
			if (answer != null && !this.answers.contains(answer)) {
				this.answers.add(answer);
			}
		}
	}

	public final void removeAnswers(final String... answers) {
		for (String answer : answers) {
			if (answer != null) {
				this.answers.remove(answer);
			}
		}
	}

	@JsonIgnore
	public final boolean isValueNull() {
		return (this.getTitle() == null || this.getCategories() == null || this.getAnswers() == null || this.getCorrect() == null);
	}

	@Override
	public String toString() {
		return "Question [id=" + this.id + ", title=" + this.title + ", categories=" + this.categories + ", answers=" + this.answers + ", correct=" + this.correct + "]";
	}

}
