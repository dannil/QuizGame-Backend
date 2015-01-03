package org.dannil.quizgamebackend.utility;

import java.util.LinkedList;
import java.util.List;

import org.dannil.quizgamebackend.model.Pair;

public class CredentialsUtility {

	private static List<Pair<String, String>> logins;

	static {
		logins = new LinkedList<Pair<String, String>>();
		logins.add(Pair.of("api-test", "2fb5e13419fc89246865e7a324f476ec624e8740"));
	}

	public CredentialsUtility() {
		throw new UnsupportedOperationException();
	}

	public static final boolean isLoginCorrect(final String username, final String token) {
		if (username == null || token == null) {
			return false;
		}
		return (logins.contains(Pair.of(username, token)));
	}
}
