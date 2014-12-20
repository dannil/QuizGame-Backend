package org.dannil.quizgamebackend.model;

public class Question {

	private static Integer ID = 0;

	private Integer id;
	private String title;

	private Question() {
		this.id = ID;
		ID++;
	}

	public Question(final String title) {
		this();
		this.title = title;
	}

	public final Integer getId() {
		return this.id;
	}

	public final void setId(final int id) {
		this.id = id;
	}

	public final String getTitle() {
		return this.title;
	}

	public final void setTitle(final String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Question [id=" + this.id + ", title=" + this.title + "]";
	}

}
