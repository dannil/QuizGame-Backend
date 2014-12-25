package org.dannil.quizgamebackend.model;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public final class Category {

	private String category;

	public Category() {

	}

	public Category(final String category) {
		this.category = category;
	}

	public final String getCategory() {
		return this.category;
	}

	public final void setCategory(final String category) {
		this.category = category;
	}

}
