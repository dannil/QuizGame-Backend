package org.dannil.quizgamebackend.model;

public class Question {

	private int id;
	private String title;

	public Question() {
		this.id = 20;
		this.title = "hello world";
	}

	public final int getId() {
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
