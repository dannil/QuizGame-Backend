package org.dannil.quizgamebackend.model;

public class Question {

	private Integer id;
	private String title;

	private Question() {

	}

	public Question(final String title) {
		this();
		this.title = title;
	}

	public Question(final int id, final String title) {
		this(title);
		this.id = id;
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

}
