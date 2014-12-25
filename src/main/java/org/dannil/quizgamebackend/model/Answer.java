package org.dannil.quizgamebackend.model;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public final class Answer {

	private String answer;

	public Answer() {

	}

	public Answer(final String answer) {
		this.answer = answer;
	}

	public final String getAnswer() {
		return this.answer;
	}

	public final void setAnswer(final String answer) {
		this.answer = answer;
	}

}
