package org.dannil.quizgamebackend.model;

/**
 * Class for saving values as pairs; useful for i.e. logins
 * 
 * @author Daniel Nilsson
 *
 * @param <L> The left value of the pair
 * @param <R> The right value of the pair
 */
public final class Pair<L, R> {
	private L left;
	private R right;

	/**
	 * Constructor
	 * 
	 * @param left
	 * 				the left value of the pair
	 * @param right
	 * 				the right value of the pair
	 */
	public Pair(final L left, final R right) {
		this.left = left;
		this.right = right;
	}

	/**
	 * Generate a new pair.
	 * 
	 * @param left
	 * 				the left value of the pair
	 * @param right
	 * 				the right value of the pair
	 * 
	 * @return a new pair of the specified values
	 */
	public final static <L, R> Pair<L, R> of(final L left, final R right) {
		return new Pair<L, R>(left, right);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.left == null) ? 0 : this.left.hashCode());
		result = prime * result + ((this.right == null) ? 0 : this.right.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Pair)) {
			return false;
		}
		Pair other = (Pair) obj;
		if (this.left == null) {
			if (other.left != null) {
				return false;
			}
		} else if (!this.left.equals(other.left)) {
			return false;
		}
		if (this.right == null) {
			if (other.right != null) {
				return false;
			}
		} else if (!this.right.equals(other.right)) {
			return false;
		}
		return true;
	}

}